package com.example.sazgar.mohoplay;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

/**
 *
 */

public class PlaylistCursorAdapter extends CursorAdapter {
private static int positionname;

    public PlaylistCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_playlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(SongContract.SongEntry.COLUMN_NAME));
        TextView textplaylistname=(TextView)view.findViewById(R.id.textplaylistname);
        textplaylistname.setText(name);

        }
    }
