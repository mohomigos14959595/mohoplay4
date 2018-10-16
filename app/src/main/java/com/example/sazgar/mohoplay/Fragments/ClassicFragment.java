package com.example.sazgar.mohoplay.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.sazgar.mohoplay.ClassicSong;
import com.example.sazgar.mohoplay.NetworkUtils;
import com.example.sazgar.mohoplay.R;
import com.example.sazgar.mohoplay.Track;
import com.example.sazgar.mohoplay.TrackAdapter;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class ClassicFragment extends Fragment {

    TextView textclassic,textpauseclassic,textplayclassic;
    public static MediaPlayer mMediaPlayer;
    private RelativeLayout relativeLayout;
    private AudioManager mAudioManager;
    SeekBar seekBar;
    RelativeLayout relativeLayoutclassic;
    private Handler mHandler;


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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_classic_fragment, container, false);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.backgroundchange);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        textclassic=(TextView)view.findViewById(R.id.textclassicselected);
textpauseclassic=(TextView)view.findViewById(R.id.textviewpauseclassic);
textplayclassic=(TextView)view.findViewById(R.id.textviewstartclassic);
relativeLayoutclassic=(RelativeLayout) view.findViewById(R.id.relativeclassic);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        SongSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (fromUser)
//                    mMediaPlayer.seekTo(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


        // mMediaPlayer.start();
        final ArrayList<Track> tracksclassic = new ArrayList<>();
        tracksclassic.add(new Track("amarasiri", R.raw.amarasiri_peiris_08_kath_kawuruwath));
        tracksclassic.add(new Track("mid air machine", R.raw.midair_machine_by_sight_of_butterfly));


        final TextView textsongname = (TextView) view.findViewById(R.id.c);
        TextView textnetwork = (TextView) view.findViewById(R.id.textviewnetwork);
        final TextView textviewsongname = (TextView) view.findViewById(R.id.textviewsongname);

        final ListView listclassic = (ListView) view.findViewById(R.id.listviewclassic);
        TrackAdapter ClassicAdapter = new TrackAdapter(getContext(), tracksclassic);
        listclassic.setAdapter(ClassicAdapter);


        listclassic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();
//                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
//
//                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                seekBar.setVisibility(View.VISIBLE);
                // if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(getContext(), tracksclassic.get(position).getmMusic());
                mMediaPlayer.start();
                seekBar.setMax(mMediaPlayer.getDuration() / 1000);
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
                String classicsongnameselected=tracksclassic.get(position).getmTitle();
                textclassic.setText(classicsongnameselected);
                textpauseclassic.setVisibility(View.VISIBLE);
                textplayclassic.setVisibility(View.INVISIBLE);
                relativeLayoutclassic.setVisibility(View.VISIBLE);


                //   }
            }
        });

        textpauseclassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.pause();
                textpauseclassic.setVisibility(View.INVISIBLE);
                textplayclassic.setVisibility(View.VISIBLE);
            }
        });
        textplayclassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textplayclassic.setVisibility(View.INVISIBLE);
                textpauseclassic.setVisibility(View.VISIBLE);
                mMediaPlayer.start();
            }
        });
        return view;
    }


    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.stop();

            mMediaPlayer.release();

        }

    }


}