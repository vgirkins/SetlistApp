package com.csci448.vgirkins.setlist;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Tori on 3/15/2018.
 */

public class Performance {
    private Date date;  // Check if this is correct data type
    private Time time;  // "
    private List<Song> songs;
    private String location;
    private String description;
    private boolean isPractice;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public boolean isPractice() {
        return isPractice;
    }

    public void setIsPractice(boolean isPractice) {
        this.isPractice = isPractice;
    }
}

