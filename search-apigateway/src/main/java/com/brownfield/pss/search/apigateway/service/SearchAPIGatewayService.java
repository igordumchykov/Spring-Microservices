package com.brownfield.pss.search.apigateway.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author idumchykov
 * @since 10/12/17
 */
@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class SearchAPIGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultHub")
    public String getHub() {
        String hub = restTemplate.getForObject("http://search-service/search/hub", String.class);
        return hub;
    }

    public String getDefaultHub() {
        return "Possibily SFO";
    }
}
