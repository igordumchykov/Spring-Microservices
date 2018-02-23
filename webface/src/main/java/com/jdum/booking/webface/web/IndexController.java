package com.jdum.booking.webface.web;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.webface.dto.UIData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.jdum.booking.webface.constants.Constants.SEARCH_VIEW;
import static com.jdum.booking.webface.constants.Constants.UIDATA_ATTRIBUTE;
import static com.jdum.booking.webface.constants.REST.INDEX_PATH;

@Controller
public class IndexController {

    @GetMapping(INDEX_PATH)
    public String index(Model model) {
        model.addAttribute(UIDATA_ATTRIBUTE, new UIData(SearchQuery.getDefault()));
        return SEARCH_VIEW;
    }
}