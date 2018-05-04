package com.csci448.vgirkins.setlist;

import java.net.URL;
import java.util.UUID;

/**
 * Created by Tori on 3/15/2018.
 */

public class Song {

    private UUID mId;
    private String title;
    private String artist;
    private char key; // Maybe not the best way to store, consider class or struct
    private boolean isSharpKey;
    private boolean isFlatKey;
    private boolean isMinorKey;
    private String linkToVid;
    private String linkToChordCharts;
    private String description;

    public Song() {
        mId = UUID.randomUUID();
        title = "Title";
        artist = "Artist";
        key = 'X';
        isMinorKey = true;
        linkToVid = "";
        linkToChordCharts = "";
        description = "Default Song";
    }

    public Song(String title, String artist, char key, boolean isSharpKey, boolean isFlatKey, boolean isMinorKey, String linkToVid, String linkToChordCharts, String description) {
        mId = UUID.randomUUID();
        this.title = title;
        this.artist = artist;
        this.key = key;
        this.isSharpKey = isSharpKey;
        this.isFlatKey = isFlatKey;
        this.isMinorKey = isMinorKey;
        this.linkToVid = linkToVid;
        this.linkToChordCharts = linkToChordCharts;
        this.description = description;
    }

    public UUID getId() { return mId; }

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

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public boolean isSharpKey() {
        return isSharpKey;
    }

    public void setIsSharpKey(boolean isSharpKey) {
        this.isSharpKey = isSharpKey;
    }

    public boolean isFlatKey() {
        return isFlatKey;
    }

    public void setIsFlatKey(boolean isFlatKey) {
        this.isFlatKey = isFlatKey;
    }

    public boolean isMinorKey() {
        return isMinorKey;
    }

    public void setIsMinorKey(boolean isMinorKey) {
        this.isMinorKey = isMinorKey;
    }

    public String getLinkToVid() {
        return linkToVid;
    }

    public void setLinkToVid(String linkToVid) {
        this.linkToVid = linkToVid;
    }

    public String getLinkToChordCharts() {
        return linkToChordCharts;
    }

    public void setLinkToChordCharts(String linkToChordCharts) {
        this.linkToChordCharts = linkToChordCharts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

