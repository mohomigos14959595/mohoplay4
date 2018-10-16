package com.example.sazgar.mohoplay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */

public class SongHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="songnames.db";
    private static final int DATABASE_VERSION=1;

    public SongHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE_SONGNAMES = "CREATE TABLE "+
                SongContract.SongEntry.TABLE_NAME+"(" +
                SongContract.SongEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SongContract.SongEntry.COLUMN_NAME + " TEXT NOT NULL UNIQUE);" ;


        db.execSQL(SQL_CREATE_TABLE_SONGNAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE"+ SongContract.SongEntry.TABLE_NAME);
        onCreate(db);
    }
}
