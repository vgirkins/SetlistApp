package com.csci448.vgirkins.setlist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.vgirkins.setlist.Performance;
import com.csci448.vgirkins.setlist.database.SetlistDbSchema.*;

import com.csci448.vgirkins.setlist.Song;

import java.util.UUID;

public class SetlistCursorWrapper extends CursorWrapper {
    public SetlistCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Song getSong() {
        String uuidString = getString(getColumnIndex(SongTable.Cols.UUID));
        String title = getString(getColumnIndex(SongTable.Cols.TITLE));
        String artist = getString(getColumnIndex(SongTable.Cols.ARTIST));
        char key = (getString(getColumnIndex(SongTable.Cols.KEY))).charAt(0);
        int sharp = getInt(getColumnIndex(SongTable.Cols.SHARP));
        int flat = getInt(getColumnIndex(SongTable.Cols.FLAT));
        int minor = getInt(getColumnIndex(SongTable.Cols.MINOR));
        String linkToVid = getString(getColumnIndex(SongTable.Cols.VIDEO));
        String linkToChordCharts = getString(getColumnIndex(SongTable.Cols.CHORDS));
        String description = getString(getColumnIndex(SongTable.Cols.DESCRIPTION));

        Song song = new Song(UUID.fromString(uuidString));
        song.setTitle(title);
        song.setArtist(artist);
        song.setKey(key);
        song.setIsSharpKey(sharp == 1);
        song.setIsFlatKey(flat == 1);
        song.setIsMinorKey(minor == 1);
        song.setLinkToVid(linkToVid);
        song.setLinkToChordCharts(linkToChordCharts);
        song.setDescription(description);

        return song;
    }

    public Performance getPerformance() {
        String uuidString = getString(getColumnIndex(PerformanceTable.Cols.UUID));
        int practice = getInt(getColumnIndex(PerformanceTable.Cols.PRACTICE));
        String name = getString(getColumnIndex(PerformanceTable.Cols.NAME));
        String bandName = getString(getColumnIndex(PerformanceTable.Cols.BAND_NAME));
        String date = getString(getColumnIndex(PerformanceTable.Cols.DATE));
        String time = getString(getColumnIndex(PerformanceTable.Cols.TIME));
        int am = getInt(getColumnIndex(PerformanceTable.Cols.AM));
        String location = getString(getColumnIndex(PerformanceTable.Cols.LOCATION));
        String description = getString(getColumnIndex(PerformanceTable.Cols.DESCRIPTION));

        Performance performance = new Performance(UUID.fromString(uuidString));
        performance.setIsPractice(practice == 1);
        performance.setName(name);
        performance.setBandName(bandName);
        performance.setDate(date);
        performance.setTime(time);
        performance.setTimeIsAM(am == 1);
        performance.setLocation(location);
        performance.setDescription(description);

        return performance;
    }
}
