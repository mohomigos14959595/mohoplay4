package com.example.sazgar.mohoplay.Fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.sazgar.mohoplay.R;
import com.example.sazgar.mohoplay.Track;
import com.example.sazgar.mohoplay.TrackAdapter;

import java.util.ArrayList;
import java.util.logging.Handler;

import static com.example.sazgar.mohoplay.Fragments.ClassicFragment.mMediaPlayer;

public class HiphopFragment extends Fragment {
    //private MediaPlayer mediaPlayer;
    public static int mpositionhiphop;
    private TextView textViewsongname;
    SeekBar seekbarhiphop;
    TextView textunderseekbar, textplay, textpause;
    RelativeLayout relativeLayouthiphop;
    Handler mHandler;


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
        View view = inflater.inflate(R.layout.activity_hiphop_fragment, container, false);

        textunderseekbar = (TextView) view.findViewById(R.id.textviewsongnameselected);
        seekbarhiphop = (SeekBar) view.findViewById(R.id.seekbarhiphop);
        textpause = (TextView) view.findViewById(R.id.textviewpause);
        textplay = (TextView) view.findViewById(R.id.textviewstart);
        relativeLayouthiphop=(RelativeLayout)view.findViewById(R.id.realativelayouthiphop);
        seekbarhiphop.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMediaPlayer.seekTo(progress * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final ArrayList<Track> trackshiphop = new ArrayList<>();
        trackshiphop.add(new Track("chonk", R.raw.vincent_augustus_chonk));
        trackshiphop.add(new Track("1400", R.raw.yung_kartz_10_1400));
        trackshiphop.add(new Track("wise guy", R.raw.yung_kartz_11_wise_guy));
//       trackshiphop.add();
//       trackshiphop.add();
//       trackshiphop.add();
//       trackshiphop.add();
//       trackshiphop.add();

        final ListView listhiphop = (ListView) view.findViewById(R.id.listhiphop);
        TrackAdapter HiphopAdapter = new TrackAdapter(getContext(), trackshiphop);
        listhiphop.setAdapter(HiphopAdapter);

        listhiphop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
//                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
//
//                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                seekbarhiphop.setVisibility(View.VISIBLE);
                // if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(getContext(), trackshiphop.get(position).getmMusic());
                seekbarhiphop.setMax(mMediaPlayer.getDuration() / 1000);
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
                String songnamehiphop = trackshiphop.get(position).getmTitle();
                textunderseekbar.setText(songnamehiphop);
                textpause.setVisibility(View.VISIBLE);
                textplay.setVisibility(View.INVISIBLE);
                relativeLayouthiphop.setVisibility(View.VISIBLE);


            }
        });

        textpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.pause();
                textpause.setVisibility(View.INVISIBLE);
                textplay.setVisibility(View.VISIBLE);
            }
        });
        textplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textplay.setVisibility(View.INVISIBLE);
                textpause.setVisibility(View.VISIBLE);
                mMediaPlayer.start();
            }
        });


        return view;
    }


    //    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent gotohiphopsong=new Intent(getActivity(),HiphopDetails.class);
//        gotohiphopsong.putExtra("position",position);
//        startActivity(gotohiphopsong);
//    }
    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.stop();

            mMediaPlayer.release();

        }

    }
}
