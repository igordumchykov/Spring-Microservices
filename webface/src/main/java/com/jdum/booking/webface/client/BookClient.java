package com.jdum.booking.webface.client;

import com.jdum.booking.webface.dto.BookingRecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author idumchykov
 * @since 1/24/18
 */
@FeignClient(name = "apigateway")
public interface BookClient {

    @RequestMapping(method = RequestMethod.POST, value = "/book/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    long create(BookingRecord bookingRecord);

    @RequestMapping(method = RequestMethod.GET, value = "/book/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    BookingRecord getBookingRecord(@PathVariable("id") long id);
}
