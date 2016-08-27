package com.epam.tmdevjam.web;

import com.epam.tmdevjam.api.DiscoveryFacade;
import com.epam.tmdevjam.model.Artist;
import com.ticketmaster.discovery.model.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    private static Map<String, String> artistsImagesMap = new HashMap<>();

    private static final String BASE_URL = "/images/";
    public static final String IMG_BLACK_SABBATH = BASE_URL + "blacksabbath.jpg";
    public static final String IMG_COLDPLAY = BASE_URL + "coldplay.jpg";
    public static final String IMG_SLAYER = BASE_URL + "slayer.jpg";
    public static final String IMG_BEYONCE = BASE_URL + "beyonce.jpg";
    public static final String IMG_MEGHAN_TRAINOR = BASE_URL + "coldplay.jpg";


    static {
        artistsImagesMap.put("K8vZ9178vQV", IMG_COLDPLAY);
        artistsImagesMap.put("K8vZ9174pc7", IMG_SLAYER);
        artistsImagesMap.put("K8vZ9175rX7", IMG_BEYONCE);
        artistsImagesMap.put("K8vZ91715h0", IMG_BLACK_SABBATH);
        artistsImagesMap.put("K8vZ9171izV", IMG_COLDPLAY);
        artistsImagesMap.put("K8vZ917ol1f", IMG_BEYONCE);
        artistsImagesMap.put("K8vZ9173ReV", IMG_MEGHAN_TRAINOR);
        artistsImagesMap.put("K8vZ9171G_0", IMG_SLAYER);
        artistsImagesMap.put("K8vZ917C2j0", IMG_SLAYER);
        artistsImagesMap.put("K8vZ917G4S0", IMG_SLAYER);
    }

    @Value("${ticketMaster.mainCategory}")
    private String category;

    @Value("${ticketMaster.undefinedGenre}")
    private String undefinedGenre;

    @Autowired
    private DiscoveryFacade discoveryFacade;

    @RequestMapping(method = RequestMethod.GET)
    public List<Artist> groups(@RequestParam(required = false) String keyword) throws IOException {
        //operation.withParam(PARAM_CLASSIFICATION, CLASSIFICATION_MUSIC_ID);
        //pagedResponse.getPageInfo().
        //ToDo: how to grab/navigate by all pages?
        //ToDo: multiple genres? params?
        //ToDo: search in attration by classification
        Set<String> groupIds = new HashSet<String>();
        List<Artist> artists = new ArrayList<Artist>();
        List<Attraction> attractions = loadAllAttractions();
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

    private List<Attraction> loadAllAttractions() throws IOException {
        List<Attraction> attractions = discoveryFacade.searchAttractions();
        return attractions
                .stream()
                .filter(this::isMusic)
                .filter(this::removeUndefinedGenre)
                .sorted(Comparator.comparing(Attraction::getName))
                .collect(Collectors.toList());
    }

    private boolean isMusic(Attraction attraction) {
        return attraction.getClassifications()
                .stream()
                .filter(c -> this.category.equals(c.getSegment().getId()))
                .findAny()
                .isPresent();
    }


    private static Artist createArtist(Attraction attraction) {
        return createArtist(attraction, false);
    }

    private boolean removeUndefinedGenre(Attraction attraction) {
        return attraction.getClassifications()
                .stream()
                .filter(c -> !this.undefinedGenre.equals(c.getGenre().getId()))
                .findAny()
                .isPresent();
    }



    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Artist attraction(@PathVariable String id) throws IOException {
        return createArtist(discoveryFacade.getAttraction(id), true);
    }

    private static Artist createArtist(Attraction attraction, boolean addAttractionInfo) {
        Artist artist = new Artist();
        artist.setId(attraction.getId());
        artist.setName(attraction.getName());
        if (addAttractionInfo) {
            artist.setAttraction(attraction);
            if (artistsImagesMap.containsKey(attraction.getId())){
                artist.setMainImageUrl(artistsImagesMap.get(attraction.getId()));
            } else {
                if (attraction.getImages()!=null && attraction.getImages().size()>0){
                    //ToDo: select best image
                    artist.setMainImageUrl(attraction.getImages().get(0).getUrl());
                }
            }
        }
        return artist;
    }
}
