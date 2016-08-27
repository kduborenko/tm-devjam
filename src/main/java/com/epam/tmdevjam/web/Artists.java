package com.epam.tmdevjam.web;

import com.epam.tmdevjam.api.DiscoveryFacade;
import com.epam.tmdevjam.api.DiscoveryUtils;
import com.epam.tmdevjam.model.Artist;
import com.ticketmaster.api.discovery.operation.SearchAttractionsOperation;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Attractions;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: D. Kulakhmetov
 * Date: 28.08.2016
 * Time: 0:04
 */
@RestController
@RequestMapping("/artists")
public class Artists {
    public static final String PARAM_CLASSIFICATION = "classificationId";
    public static final String CLASSIFICATION_MUSIC_ID = "KZFzniwnSyZfZ7v7nJ";

    @Autowired
    private DiscoveryFacade discoveryFacade;

    @RequestMapping(method = RequestMethod.GET)
    public List<Artist> groups(@RequestParam(required = false) String keyword) throws IOException {
        SearchAttractionsOperation operation = new SearchAttractionsOperation();
        //operation.withParam(PARAM_CLASSIFICATION, CLASSIFICATION_MUSIC_ID);
        //pagedResponse.getPageInfo().
        //ToDo: how to grab/navigate by all pages?
        //ToDo: multiple genres? params?
        //ToDo: search in attration by classification
        Set<String> groupIds = new HashSet<String>();
        List<Artist> artists = new ArrayList<Artist>();
        List<Attraction> attractions = DiscoveryUtils.loadAllAttractions(discoveryFacade, operation);
        if (attractions!=null && attractions.size()>0){
            for(Attraction attraction:attractions){
                String artistId = attraction.getId();
                if (!groupIds.contains(artistId)){
                    groupIds.add(artistId);
                    artists.add(createArtist(attraction));
                }
            }
        }
        return artists;
    }





    private static Artist createArtist(Attraction attraction) {
        Artist artist = new Artist();
        artist.setId(attraction.getId());
        artist.setName(attraction.getName());
        return artist;
    }
}
