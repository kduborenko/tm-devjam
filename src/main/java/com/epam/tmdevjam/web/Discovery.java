package com.epam.tmdevjam.web;

import com.epam.tmdevjam.api.DiscoveryFacade;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: D. Kulakhmetov
 * Date: 27.08.2016
 * Time: 13:14
 */
@RestController
@RequestMapping("/discovery")
public class Discovery {
    @Autowired
    private DiscoveryFacade discoveryFacade;
    @RequestMapping(method = RequestMethod.GET)
    public PagedResponse<Events> list(@RequestParam(required = false) String keyword) throws IOException {
        SearchEventsOperation operation = new SearchEventsOperation();
        operation.keyword(keyword);
        return discoveryFacade.searchEvents(operation);
    }
}
