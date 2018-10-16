package com.example.sazgar.mohoplay.Fragments;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.sazgar.mohoplay.ClassicSong;
import com.example.sazgar.mohoplay.R;
import com.example.sazgar.mohoplay.RockSong;
import com.example.sazgar.mohoplay.Track;
import com.example.sazgar.mohoplay.TrackAdapter;

import java.util.ArrayList;

import static com.example.sazgar.mohoplay.Fragments.ClassicFragment.mMediaPlayer;

public class RockFragment extends Fragment {

    SeekBar seeekbarrock;
    TextView textunderrock,playrock,pauserock;
    RelativeLayout relativeLayoutrock;





    private MediaPlayer.OnCompletionListener mCompletionListener =

            new MediaPlayer.OnCompletionListener() {

                @Override

                public void onCompletion(MediaPlayer mediaPlayer) {

                    releaseMediaPlayer();

                }

            };

//public static int mpositionrock;
    @Nullable
    @Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.activity_rock_fragment, container, false);
//seeekbarrock.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        mMediaPlayer.seekTo(progress * 1000);
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }
//});

        seeekbarrock= (SeekBar) view.findViewById(R.id.seekbarrock);
        textunderrock=(TextView)view.findViewById(R.id.textviewsongrockselected);
        pauserock=(TextView)view.findViewById(R.id.textviewpauserock);
        playrock=(TextView)view.findViewById(R.id.textviewstartrock);
        relativeLayoutrock= (RelativeLayout) view.findViewById(R.id.relativerock);


        seeekbarrock.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMediaPlayer.seekTo(progress*1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


     final ArrayList<Track> tracksrock=new ArrayList<>();
    tracksrock.add(new Track("test",R.raw.yung_kartz_11_wise_guy));
    tracksrock.add(new Track("test2",R.raw.better_now_post_malone));
//    tracksrock.add();
//   tracksrock.add();
//    tracksrock.add();
//   tracksrock.add();
//    tracksrock.add();*/

    ListView listrock=(ListView)view.findViewById(R.id.listviewrock);
    TrackAdapter rockadapter = new TrackAdapter(getContext(),tracksrock);
    listrock.setAdapter(rockadapter);

listrock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        releaseMediaPlayer();
//                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
//
//                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        seeekbarrock.setVisibility(View.VISIBLE);
        // if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
        mMediaPlayer = MediaPlayer.create(getContext(), tracksrock.get(position).getmMusic());
        mMediaPlayer.start();
        seeekbarrock.setMax(mMediaPlayer.getDuration() / 1000);
        String songnamehiphop = tracksrock.get(position).getmTitle();
        textunderrock.setText(songnamehiphop);
        playrock.setVisibility(View.INVISIBLE);
        pauserock.setVisibility(View.VISIBLE);
        relativeLayoutrock.setVisibility(View.VISIBLE);
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
});
        pauserock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.pause();
                pauserock.setVisibility(View.INVISIBLE);
                playrock.setVisibility(View.VISIBLE);
            }
        });
        playrock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playrock.setVisibility(View.INVISIBLE);
                pauserock.setVisibility(View.VISIBLE);
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
