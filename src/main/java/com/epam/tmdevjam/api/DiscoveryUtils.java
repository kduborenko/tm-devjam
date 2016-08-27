package com.epam.tmdevjam.api;

import com.ticketmaster.api.discovery.operation.SearchAttractionsOperation;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Attractions;
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

    public static List<Attraction> loadAllAttractions(DiscoveryFacade discoveryFacade, SearchAttractionsOperation operation) throws IOException {
        List<Attraction> allAttractions = new ArrayList<>();
        operation.pageSize(PAGE_SIZE);
        Integer totalElements = null;
        Integer pageNumber = 0;
        while(totalElements == null || allAttractions.size()<totalElements){
            operation.pageNumber(pageNumber);
            PagedResponse<Attractions> pagedResponse = discoveryFacade.searchAttractions(operation);
            if (pagedResponse == null){
                break;
            }
            Attractions attractionsWrapper = pagedResponse.getContent();
            if (attractionsWrapper==null){
                break;
            }
            List<Attraction> attractions = attractionsWrapper.getAttractions();
            if (attractions!=null && attractions.size()>0){
                allAttractions.addAll(attractions);
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
        return allAttractions;
    }
}
