package com.jdum.booking.webface.web;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.webface.dto.UIData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    static final String UIDATA_ATTRIBUTE = "uiData";
    static final String SEARCH_VIEW = "search";

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("123");
        model.addAttribute(UIDATA_ATTRIBUTE, new UIData(SearchQuery.getDefault()));
        return SEARCH_VIEW;
    }
}