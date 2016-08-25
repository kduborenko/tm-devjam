package com.epam.tmdevjam.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kiryl Dubarenka
 */
@RestController
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}
