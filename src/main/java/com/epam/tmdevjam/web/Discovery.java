package com.epam.tmdevjam.web;

import com.epam.tmdevjam.api.DiscoveryFacade;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @Value("${ticketMaster.mainCategory}")
    private String category;

    @RequestMapping(method = RequestMethod.GET)
    public PagedResponse<Events> list(@RequestParam(required = false) String keyword) throws IOException {
        SearchEventsOperation operation = new SearchEventsOperation();
        operation.keyword(keyword);
        return discoveryFacade.searchEvents(operation);
    }

    @RequestMapping(value = "timeline", method = RequestMethod.GET)
    public Map<Date, List<Event>> timeline(@RequestParam(required = false) String keyword) throws IOException {
        SearchEventsOperation operation = new SearchEventsOperation();
        operation.keyword(keyword);
        PagedResponse<Events> list = discoveryFacade.searchEvents(operation);

        Map<Date, List<Event>> byDate = list.getContent()
                .getEvents()
                .stream()
                .filter(this::checkClassification)
                .collect(Collectors.groupingBy((event) -> event.getDates()));

        return byDate;
    }

    private boolean checkClassification(Event e) {
        return e.getClassifications()
                .stream()
                .filter(c -> this.category.equals(c.getSegment().getId()))
                .findAny()
                .isPresent();
    }
}
