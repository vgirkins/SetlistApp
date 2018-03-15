package com.csci448.vgirkins.setlist;

import java.net.URL;

/**
 * Created by Tori on 3/15/2018.
 */

public class Song {
    private String title;
    private String artist;
    private String key; // Maybe not the best way to store, consider class or struct
    private boolean isMajorKey;
    private URL linkToVid;
    private URL linkToChordCharts;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isMajorKey() {
        return isMajorKey;
    }

    public void setIsMajorKey(boolean isMajorKey) {
        this.isMajorKey = isMajorKey;
    }

    public URL getLinkToVid() {
        return linkToVid;
    }

    public void setLinkToVid(URL linkToVid) {
        this.linkToVid = linkToVid;
    }

    public URL getLinkToChordCharts() {
        return linkToChordCharts;
    }

    public void setLinkToChordCharts(URL linkToChordCharts) {
        this.linkToChordCharts = linkToChordCharts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

