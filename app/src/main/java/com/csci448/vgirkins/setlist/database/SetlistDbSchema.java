package com.csci448.vgirkins.setlist.database;

public class SetlistDbSchema {
    public static final class SongTable {
        public static final String NAME = "songs";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String ARTIST = "artist";
            public static final String KEY = "key";
            public static final String SHARP = "sharp";
            public static final String FLAT = "flat";
            public static final String MINOR = "minor";
            public static final String VIDEO = "video";
            public static final String CHORDS = "chords";
            public static final String DESCRIPTION = "description";
        }
    }

    public static final class PerformanceTable {
        public static final String NAME = "performances";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String PRACTICE = "practice";
            public static final String NAME = "name";
            public static final String BAND_NAME = "band_name";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String AM = "am";
            public static final String LOCATION = "location";
            public static final String DESCRIPTION = "description";
        }
    }
}
