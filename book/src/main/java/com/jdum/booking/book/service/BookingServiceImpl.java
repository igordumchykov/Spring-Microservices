package com.jdum.booking.book.service;

import com.jdum.booking.book.client.PricesClient;
import com.jdum.booking.book.dto.Price;
import com.jdum.booking.book.exception.BookingException;
import com.jdum.booking.book.jms.Sender;
import com.jdum.booking.book.model.BookingRecord;
import com.jdum.booking.book.model.BookingStatus;
import com.jdum.booking.book.model.Inventory;
import com.jdum.booking.book.model.Passenger;
import com.jdum.booking.book.repository.BookingRepository;
import com.jdum.booking.book.repository.InventoryRepository;
import com.jdum.booking.common.dto.BookingRecordDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {

    private static String BUS_NUMBER_MSG = "BUS_NUMBER";
    private static String TRIP_DATE_MSG = "TRIP_DATE";
    private static String NEW_INVENTORY_MSG = "NEW_INVENTORY";

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private PricesClient pricesClient;

    @Autowired
    private Sender sender;

    @Override
    public long book(BookingRecordDTO bookingRecord) {

        checkPrice(bookingRecord);
        Inventory inventory = getInventory(bookingRecord);

        inventoryRepository.saveAndFlush(inventory);
        log.debug("Inventory was updated");

        long id = saveBooking(bookingRecord);

        sendBookingEvent(bookingRecord, inventory);

        return id;
    }

    private void sendBookingEvent(BookingRecordDTO bookingRecord, Inventory inventory) {
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put(BUS_NUMBER_MSG, bookingRecord.getBusNumber());
        bookingDetails.put(TRIP_DATE_MSG, bookingRecord.getTripDate());
        bookingDetails.put(NEW_INVENTORY_MSG, inventory.getBookableInventory());

        sender.send(bookingDetails);
        log.debug("booking event successfully delivered: {}", bookingDetails);
    }

    private long saveBooking(BookingRecordDTO bookingRecordDTO) {

        BookingRecord bookingRecord = mapperFacade.map(bookingRecordDTO, BookingRecord.class);
        bookingRecord.setStatus(BookingStatus.BOOKING_CONFIRMED);
        Set<Passenger> passengers = bookingRecord.getPassengers();
        passengers.forEach(passenger -> passenger.setBookingRecord(bookingRecord));
        bookingRecordDTO.setBookingDate(new Date());
        long id = bookingRepository.save(bookingRecord).getId();
        log.debug("Booking was saved");
        return id;
    }

    private Inventory getInventory(BookingRecordDTO bookingRecordDTO) {
        Inventory inventory = inventoryRepository.findByBusNumberAndTripDate(bookingRecordDTO.getBusNumber(), bookingRecordDTO.getTripDate());
        if (inventory == null || !inventory.isAvailable(bookingRecordDTO.getPassengers().size())) {
            throw new BookingException("No more seats available");
        }
        log.debug("Successfully checked inventory: {}", inventory);

        inventory.setAvailable(inventory.getAvailable() - bookingRecordDTO.getPassengers().size());
        return inventory;
    }

    private void checkPrice(BookingRecordDTO bookingRecord) {
        Price price = pricesClient.getPrice(bookingRecord.getBusNumber(), bookingRecord.getTripDate());
        log.debug("PriceDTO: {} ", price);

        if (!bookingRecord.getPrice().equals(price.getPriceAmount()))
            throw new BookingException("PriceDTO is tampered");
    }

    @Override
    public BookingRecordDTO getBooking(long id) {
        BookingRecord foundRecord = bookingRepository.findOne(id);
        return mapperFacade.map(foundRecord, BookingRecordDTO.class);
    }

    @Transactional
    @Override
    public void updateStatus(BookingStatus status, long bookingId) {
        BookingRecord record = bookingRepository.findOne(bookingId);
        record.setStatus(status);
    }
}
