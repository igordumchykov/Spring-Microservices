package com.jdum.booking.book.client;

import com.jdum.booking.book.dto.Price;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author idumchykov
 * @since 1/26/18
 */
@FeignClient(name = "${client.prices.service}")
public interface PricesClient {

    @RequestMapping(method = RequestMethod.GET, value = "${client.prices.requests.get}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Price getPrice(@RequestParam("busNumber") String busNumber, @RequestParam("tripDate") String tripDate);
}
