package com.epam.tmdevjam.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Kiryl Dubarenka
 */
@Controller
@RequestMapping("/")
public class Example {

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "example/list";
    }

}
