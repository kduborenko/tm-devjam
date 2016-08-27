package com.epam.tmdevjam.web;

import com.epam.tmdevjam.api.DiscoveryFacade;
import com.epam.tmdevjam.api.DiscoveryUtils;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Comparator;
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

    @Value("${ticketMaster.undefinedGenre}")
    private String undefinedGenre;

    @RequestMapping(method = RequestMethod.GET)
    public PagedResponse<Events> list(@RequestParam(required = false) String keyword) throws IOException {
        SearchEventsOperation operation = new SearchEventsOperation();
        operation.keyword(keyword);
        return discoveryFacade.searchEvents(operation);
    }

    @RequestMapping(value = "timeline/{attractionId}", method = RequestMethod.GET)
    public List<Event> timeline(@PathVariable String attractionId) throws IOException {
        SearchEventsOperation operation = new SearchEventsOperation();
        operation.withParam("source", "ticketmaster");
        operation.withParam("classificationId", category);
        operation.attractionId(attractionId);
        //PagedResponse<Events> list = discoveryFacade.searchEvents(operation);

        Map<Date, List<Event>> byDate = DiscoveryUtils.loadAllEvents(discoveryFacade, operation)
                //.getEvents()
                .stream()
                .filter(this::removeUndefinedGenre)
                .collect(Collectors.groupingBy((event) -> event.getDates()));

        return byDate.entrySet()
                .stream()
                .sorted(Comparator.comparing(e1 -> e1.getKey().getStart().getDateTime()))
                .map(e1 -> e1.getValue().get(0)) // todo find main event
                .collect(Collectors.toList());
    }

    private boolean removeUndefinedGenre(Event e) {
        return e.getClassifications()
                .stream()
                .filter(c -> !this.undefinedGenre.equals(c.getGenre().getId()))
                .findAny()
                .isPresent();
    }


}
