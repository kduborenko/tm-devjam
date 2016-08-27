package com.epam.tmdevjam.api;

import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchAttractionsOperation;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Attractions;
import com.ticketmaster.discovery.model.Events;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Cacheable("attractions")
    public List<Attraction> searchAttractions() throws IOException {
        return Stream.of("Beyoncé", "Coldplay", "Slayer", "Meghan Trainor", "Black Sabbath")
                .flatMap(kw -> searchAttractions(kw).stream())
                .collect(Collectors.toList());
    }

    private List<Attraction> searchAttractions(String keyword) {
        try {
            SearchAttractionsOperation operation = new SearchAttractionsOperation();
            if (keyword != null) {
                operation.keyword(keyword);
            }
            List<Attraction> attractions = new ArrayList<>();
            operation.pageSize(500);
            PagedResponse<Attractions> response = api.searchAttractions(operation);
            attractions.addAll(response.getContent().getAttractions());
            return attractions;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

}
