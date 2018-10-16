package com.example.sazgar.mohoplay;

import android.provider.BaseColumns;

/**
 *
 */

public class SongContract {

    public static final class SongEntry implements BaseColumns {


        public static final String TABLE_NAME="playlistsongs";
        public static final String COLUMN_ID=_ID;
        public static final String COLUMN_NAME="songname";
    }
}
