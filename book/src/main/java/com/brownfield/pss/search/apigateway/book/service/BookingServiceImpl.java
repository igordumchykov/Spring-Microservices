package com.brownfield.pss.search.apigateway.book.service;

import com.brownfield.pss.search.apigateway.book.dto.Fare;
import com.brownfield.pss.search.apigateway.book.exception.BookingException;
import com.brownfield.pss.search.apigateway.book.jms.Sender;
import com.brownfield.pss.search.apigateway.book.model.BookingRecord;
import com.brownfield.pss.search.apigateway.book.model.Inventory;
import com.brownfield.pss.search.apigateway.book.model.Passenger;
import com.brownfield.pss.search.apigateway.book.repository.BookingRepository;
import com.brownfield.pss.search.apigateway.book.repository.InventoryRepository;
import com.brownfield.pss.search.apigateway.book.util.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Value("${application.booking.fare.url}")
    private String fareUrl;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Sender sender;

    @Override
    public long book(BookingRecord record) {

        checkFare(record);
        Inventory inventory = getInventory(record);

        inventoryRepository.saveAndFlush(inventory);
        log.debug("Inventory was updated");

        long id = saveBooking(record);

        sendBookingEvent(record, inventory);

        return id;
    }

    private void sendBookingEvent(BookingRecord record, Inventory inventory) {
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("FLIGHT_NUMBER", record.getFlightNumber());
        bookingDetails.put("FLIGHT_DATE", record.getFlightDate());
        bookingDetails.put("NEW_INVENTORY", inventory.getBookableInventory());

        sender.send(bookingDetails);
        log.debug("booking event successfully delivered: {}", bookingDetails);
    }

    private long saveBooking(BookingRecord record) {
        record.setStatus(BookingStatus.BOOKING_CONFIRMED);
        Set<Passenger> passengers = record.getPassengers();
        passengers.forEach(passenger -> passenger.setBookingRecord(record));
        record.setBookingDate(new Date());
        long id = bookingRepository.save(record).getId();
        log.debug("Booking was saved");
        return id;
    }

    private Inventory getInventory(BookingRecord record) {
        Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(record.getFlightNumber(), record.getFlightDate());
        if (inventory == null || !inventory.isAvailable(record.getPassengers().size())) {
            throw new BookingException("No more seats available");
        }
        log.debug("Successfully checked inventory: {}", inventory);

        inventory.setAvailable(inventory.getAvailable() - record.getPassengers().size());
        return inventory;
    }

    private void checkFare(BookingRecord record) {
        Fare fare = restTemplate.getForObject(fareUrl + "/get?flightNumber=" + record.getFlightNumber() + "&flightDate=" + record.getFlightDate(), Fare.class);
        log.debug("Fare: {} ", fare);

        if (!record.getFare().equals(fare.getFare()))
            throw new BookingException("Fare is tampered");
    }

    @Override
    public BookingRecord getBooking(long id) {
        return bookingRepository.findOne(id);
    }

    @Override
    public void updateStatus(String status, long bookingId) {
        BookingRecord record = bookingRepository.findOne(bookingId);
        record.setStatus(status);
    }


}
