package com.jdum.booking.webface.controller;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.webface.dto.UIData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    static final String UIDATA_ATTRIBUTE = "uiData";
    static final String SEARCH_VIEW = "search";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute(UIDATA_ATTRIBUTE, new UIData(SearchQuery.getDefault()));
        return SEARCH_VIEW;
    }
}