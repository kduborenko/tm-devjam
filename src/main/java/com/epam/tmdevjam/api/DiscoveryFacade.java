package com.epam.tmdevjam.api;

import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchAttractionsOperation;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attractions;
import com.ticketmaster.discovery.model.Events;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: D. Kulakhmetov
 * Date: 27.08.2016
 * Time: 13:17
 */
@Service
public class DiscoveryFacade {
    private DiscoveryApi api;

    private DiscoveryFacade(@Value("${ticketMaster.apiKey}") String apiKey) {
        api = new DiscoveryApi(apiKey);
    }

    public PagedResponse<Events> searchEvents(SearchEventsOperation operation) throws IOException {
        return api.searchEvents(operation);
    }

    public PagedResponse<Attractions> searchAttractions(SearchAttractionsOperation operation) throws IOException {
        return api.searchAttractions(operation);
    }
}
