package com.example.sazgar.mohoplay.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sazgar.mohoplay.R;

public class ExtraActivity extends Fragment {
private Button buttonvisitsitefma;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_extra, container, false);

        buttonvisitsitefma=(Button)view.findViewById(R.id.buttonsitefma);
        buttonvisitsitefma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwebpage("http://freemusicarchive.org/member/lizb/Top_Picks_of_2016_short_list");
            }
        });


 return view;

    }
    public void openwebpage(String url){

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

    }


}
