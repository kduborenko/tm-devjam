package com.epam.tmdevjam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kiryl Dubarenka
 */
@Service
public class LastFmArtistDetails implements ArtistDetails {

    @Value("${lastFm.apiKey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public LastFmArtistDetails(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getBio(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("artist", name);
        params.put("api_key", apiKey);
        Map<String, Map<String, Map<String, String>>> resp = restTemplate.getForObject(
                "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&format=json&artist={artist}&api_key={api_key}",
                Map.class, params);
        return resp.getOrDefault("artist", Collections.emptyMap())
                .getOrDefault("bio", Collections.emptyMap())
                .get("content");
    }
}
