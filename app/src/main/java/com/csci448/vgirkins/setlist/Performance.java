package com.csci448.vgirkins.setlist;

import android.support.annotation.NonNull;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

/**
 * Created by Tori on 3/15/2018.
 */

public class Performance {
    private UUID mId;
    private boolean isPractice;
    private String name;
    private String bandName;
    private String date;  // Not the best data type
    private String time;  // "
    private boolean timeIsAM;
    private List<Song> songs;
    private String location;
    private String description;

    public Performance(UUID id) {
        mId = id;
    }

    public Performance(boolean isPractice, String name, String bandName, String date, String time, boolean timeIsAM, String location, String description) {
        mId = UUID.randomUUID();
        this.isPractice = isPractice;
        this.name = name;
        this.bandName = bandName;
        this.date = date;
        this.time = time;
        this.timeIsAM = timeIsAM;
        this.songs = new ArrayList<Song>();
        this.location = location;
        this.description = description;
    }

    public UUID getId() { return mId; }

    public void setId(UUID id) { this.mId = id; }

    public boolean isPractice() {
        return isPractice;
    }

    public void setIsPractice(boolean isPractice) {
        this.isPractice = isPractice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isTimeAM() {
        return timeIsAM;
    }

    public void setTimeIsAM(boolean timeIsAM) {
        this.timeIsAM = timeIsAM;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}

