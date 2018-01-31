package com.jdum.booking.webface.client;

import com.jdum.booking.webface.dto.CheckInRecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author idumchykov
 * @since 1/24/18
 */
@FeignClient(name = "apigateway")
public interface CheckinClient {

    @RequestMapping(method = RequestMethod.POST, value = "/checkin/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    long create(CheckInRecord checkInRecord);
}
