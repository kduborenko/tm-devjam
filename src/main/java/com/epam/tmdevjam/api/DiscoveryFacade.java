package com.epam.tmdevjam.api;

import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Events;
import org.springframework.cache.annotation.Cacheable;

import java.io.IOException;
import java.util.List;

/**
 * @author Kiryl Dubarenka
 */
public interface DiscoveryFacade {
    PagedResponse<Events> searchEvents(SearchEventsOperation operation) throws IOException;

    List<Attraction> searchAttractions() throws IOException;

    Attraction getAttraction(String id);
}
