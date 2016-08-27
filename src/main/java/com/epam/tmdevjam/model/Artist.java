package com.epam.tmdevjam.model;

import com.ticketmaster.discovery.model.Attraction;

/**
 * Created with IntelliJ IDEA.
 * User: D. Kulakhmetov
 * Date: 28.08.2016
 * Time: 0:48
 */
public class Artist {
    private String id;
    private String name;
    private String mainImageUrl;
    private String info;
    private Attraction attraction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
