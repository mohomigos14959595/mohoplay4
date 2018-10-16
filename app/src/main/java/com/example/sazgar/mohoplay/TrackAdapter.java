package com.example.sazgar.mohoplay;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sazgar.mohoplay.Fragments.ClassicFragment;
import com.example.sazgar.mohoplay.Fragments.NetworkActivity;

import java.util.List;


public class TrackAdapter extends ArrayAdapter<Track> {

    TextView textdatabase;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =

            new AudioManager.OnAudioFocusChangeListener() {

                @Override

                public void onAudioFocusChange(int focusChange) {

                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||

                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                        mMediaPlayer.pause();

//                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                        mMediaPlayer.start();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                        releaseMediaPlayer();

                    }

                }

            };

    private MediaPlayer.OnCompletionListener mCompletionListener =

            new MediaPlayer.OnCompletionListener() {

                @Override

                public void onCompletion(MediaPlayer mediaPlayer) {

                    releaseMediaPlayer();

                }

            };

    public TrackAdapter(Context context, List<Track> objects) {
        super(context, 0, objects);
    }

   // private RelativeLayout relativeLayout;
    private Track mTrack;
    private TextView textplay, textstop,textsong;
    private static int mposition;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private static int m2position;


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitemsong, parent, false);
        }
        //m2position=position;
       // mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

       // textplay = (TextView) convertView.findViewById(R.id.textviewplay);
        textdatabase=(TextView)convertView.findViewById(R.id.textviewdatabase);
        textsong = (TextView) convertView.findViewById(R.id.textviewsongname);
       // textstop = (TextView) convertView.findViewById(R.id.textviewstop);
     //   relativeLayout = (RelativeLayout) convertView.findViewById(R.id.backgroundchange);
        Track track = getItem(position);
        textsong.setText(track.getmTitle());


//        textsong.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//m2position=position;
//                //problem is here down line probably
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//
//                        .setTitle("Favorite?")
//
//                        .setMessage("Add To PlayList?")
//
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                            @Override
//
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                newsongname();
//
//                            }
//
//                        })
//
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//
//                            @Override
//
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                dialogInterface.cancel();
//
//                            }
//
//                        });
//
//                AlertDialog dialog = builder.create();
//
//                dialog.show();
//
//                return true;
//            }
//        });

        textdatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m2position=position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())

                        .setTitle("Favorite?")

                        .setMessage("Add To PlayList?")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialogInterface, int i) {

                                newsongname();

                            }

                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.cancel();

                            }

                        });

                AlertDialog dialog = builder.create();

                dialog.show();

            }
        });
//        textsong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTrack = getItem(position);

                //textsong.setBackgroundColor(getContext().getResources().getColor(R.color.songdelected));


//                releaseMediaPlayer();
////                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
////
////                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//
//            //    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    mMediaPlayer = MediaPlayer.create(getContext(), mTrack.getmMusic());
//
//                    mMediaPlayer.start();
//
//                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

              //  }
           // }
        //});

        TextView textnetwork = (TextView) convertView.findViewById(R.id.textviewnetwork);

        final ListView listclassic = (ListView) convertView.findViewById(R.id.listviewclassic);

        textnetwork.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mTrack = getItem(position);
                mposition = position;
                if (mTrack.getmTitle().equals("amarisiri")) {
                    PositionValue.positionvalue = 1;
                }
                if (mTrack.getmTitle().equals("mid air machine")) {
                    PositionValue.positionvalue = 1;
                }
                if (mTrack.getmTitle().equals("test")) {
                    PositionValue.positionvalue = 13;
                }
                if (mTrack.getmTitle().equals("test2")) {
                    PositionValue.positionvalue = 13;
                }
                if (mTrack.getmTitle().equals("chonk")) {
                    PositionValue.positionvalue = 6;
                }
                if (mTrack.getmTitle().equals("1400")) {
                    PositionValue.positionvalue = 6;
                }
                if (mTrack.getmTitle().equals("wise guy")) {
                    PositionValue.positionvalue = 6;
                }
                Intent gonetwork;
                v.getContext().startActivity(gonetwork = new Intent(v.getContext(), NetworkActivity.class));
                gonetwork.putExtra("position", position);
            }

        });


//        textplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTrack = getItem(mposition);
//                mMediaPlayer = MediaPlayer.create(getContext(), mTrack.getmMusic());
//                mMediaPlayer.start();
//                textplay.setVisibility(View.INVISIBLE);
//                textstop.setVisibility(View.VISIBLE);
//
//            }
//        });
//
//
//        textstop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textstop.setVisibility(View.INVISIBLE);
//                textplay.setVisibility(View.VISIBLE);
//                mMediaPlayer.pause();
//
//            }
//        });

        return convertView;


    }


    private void newsongname() {
        mTrack = getItem(m2position);
        String name = mTrack.getmTitle();
        insert(name);
    }

    private void insert(String name) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(SongContract.SongEntry.COLUMN_NAME, name);
        SongHelper dBhelper = new SongHelper(getContext());
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.insert(SongContract.SongEntry.TABLE_NAME, null, contentValues);

    }

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.stop();

            mMediaPlayer.release();

        }

    }
}
