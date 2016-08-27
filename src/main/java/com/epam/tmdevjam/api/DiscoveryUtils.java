package com.epam.tmdevjam.api;

import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D. Kulakhmetov
 * Date: 28.08.2016
 * Time: 2:00
 */
public class DiscoveryUtils {
    public static final int PAGE_SIZE = 100;
    public static List<Event> loadAllEvents(DiscoveryFacade discoveryFacade, SearchEventsOperation operation) throws IOException {
        List<Event> allEvents = new ArrayList<>();
        operation.pageSize(PAGE_SIZE);
        Integer totalElements = null;
        Integer pageNumber = 0;
        while(totalElements == null || allEvents.size()<totalElements){
            operation.pageNumber(pageNumber);
            PagedResponse<Events> pagedResponse = discoveryFacade.searchEvents(operation);
            if (pagedResponse == null){
                break;
            }
            Events eventsWrapper = pagedResponse.getContent();
            if (eventsWrapper==null){
                break;
            }
            List<Event> events = eventsWrapper.getEvents();
            if (events!=null && events.size()>0){
                allEvents.addAll(events);
            }
            if (pagedResponse.getPageInfo()==null){
                break;
            }
            if (totalElements==null){
                totalElements = pagedResponse.getPageInfo().getTotalElements();
            }
            if (totalElements==null){
                break;
            }
            pageNumber++;
        }
        return allEvents;
    }

}
