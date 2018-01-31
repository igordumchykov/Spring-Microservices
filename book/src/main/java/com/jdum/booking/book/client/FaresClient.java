package com.jdum.booking.book.client;

import com.jdum.booking.book.dto.Fare;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author idumchykov
 * @since 1/26/18
 */
@FeignClient(name = "apigateway")
public interface FaresClient {

    @RequestMapping(method = RequestMethod.GET, value = "/fares/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Fare getFare(@RequestParam("flightNumber") String flightNumber, @RequestParam("flightDate") String flightDate);
}
