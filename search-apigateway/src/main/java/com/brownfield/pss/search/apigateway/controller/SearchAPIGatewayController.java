package com.brownfield.pss.search.apigateway.controller;

import com.brownfield.pss.search.apigateway.service.SearchAPIGatewayService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author idumchykov
 * @since 10/12/17
 */
@RestController
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class SearchAPIGatewayController {

    @Autowired
    private SearchAPIGatewayService searchAPIGatewayService;

    @RequestMapping("/hubongw")
    public String getHub() {
        log.info("Search Request in API gateway for getting Hub, forwarding to search-service ");
        return searchAPIGatewayService.getHub();
    }

}
