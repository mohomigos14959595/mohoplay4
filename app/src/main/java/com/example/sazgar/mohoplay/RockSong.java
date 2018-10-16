package com.example.sazgar.mohoplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RockSong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_song);
//        Intent intent=getIntent();
//        String name=intent.getStringExtra("position");
//
//        text.append(String.valueOf(name));
        TextView text=(TextView)findViewById(R.id.textrock);
        int position;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            position = bundle.getInt("position");
            text.setText(String.valueOf(position));
        }
    }
}
