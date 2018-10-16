package com.example.sazgar.mohoplay.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sazgar.mohoplay.PlaylistCursorAdapter;
import com.example.sazgar.mohoplay.R;
import com.example.sazgar.mohoplay.SongContract;
import com.example.sazgar.mohoplay.SongHelper;
import com.example.sazgar.mohoplay.Track;

import java.util.ArrayList;

public class PlaylistFragment extends Fragment {


    @Override
    public void onStart() {
        super.onStart();
        showstudent();
    }

     ListView listplaylist;
    private MediaPlayer mMediaPlayer;
    Button buttondeleteall;
    private Track mTrack;
    TextView textsongname;

    // private SongHelper mDbhelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_playlist_fragment, container, false);
        buttondeleteall = (Button) view.findViewById(R.id.buttondeleteall);
        textsongname=(TextView)view.findViewById(R.id.textviewsongname);
        buttondeleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllstudents();
                showstudent();
            }
        });
        listplaylist = (ListView) view.findViewById(R.id.listviewplaylist);



        return view;
    }

    private void showstudent() {

        SongHelper mDbhelper = new SongHelper(getContext());
        SQLiteDatabase db = mDbhelper.getReadableDatabase();
        Cursor cursor = db.query(SongContract.SongEntry.TABLE_NAME, null, null, null, null, null, null);
        PlaylistCursorAdapter adapter = new PlaylistCursorAdapter(getContext(), cursor);
        listplaylist.setAdapter(adapter);
    }


    private void deleteAllstudents() {
        SongHelper mDbhelper = new SongHelper(getContext());
        SQLiteDatabase db = mDbhelper.getWritableDatabase();
        db.delete(SongContract.SongEntry.TABLE_NAME, null, null);
    }


}


