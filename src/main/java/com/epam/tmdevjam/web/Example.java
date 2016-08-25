package com.epam.tmdevjam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kiryl Dubarenka
 */
@Controller
@RequestMapping("/")
public class Example {

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("index");
    }

}
