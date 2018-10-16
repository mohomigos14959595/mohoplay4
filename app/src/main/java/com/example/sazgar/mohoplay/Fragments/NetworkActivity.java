package com.example.sazgar.mohoplay.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sazgar.mohoplay.Detail;
import com.example.sazgar.mohoplay.NetworkUtils;
import com.example.sazgar.mohoplay.PositionValue;
import com.example.sazgar.mohoplay.R;

import static java.lang.Integer.*;

public class NetworkActivity extends AppCompatActivity {

    private ProgressBar progressbar;
    private static String mSiteUrl;
    private static String mColor;

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (mSiteUrl == null) {
                Toast.makeText(NetworkActivity.this, "There is no more information", Toast.LENGTH_SHORT).show();
                return;
            }
            openwebpage(mSiteUrl);
        }
    };
   // public static  String position=NetworkActivity.position;

    public static final String EVENT_URL = "https://freemusicarchive.org/featured.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);



        progressbar=(ProgressBar)findViewById(R.id.progressBar);
        RelativeLayout realative=(RelativeLayout)findViewById(R.id.activity_network);
    //    realative.setBackground(parseInt(mColor));

        //textposition.setText(String.valueOf(ClassicFragment.positionclassic));
//        Intent intent=getIntent();
//         position=intent.getStringExtra("position");


        Button siteurlbutton = (Button)findViewById(R.id.buttonsiteurl);
        siteurlbutton.setOnClickListener(mClick);

        new EventAsyncTask().execute(EVENT_URL);
    }

    public class EventAsyncTask extends AsyncTask<String,Void,Detail>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Detail doInBackground(String... urls) {

            String url=urls[0];
            return NetworkUtils.fetchData(url);
        }

        @Override
        protected void onPostExecute(Detail detail) {
            TextView texttitle=(TextView)findViewById(R.id.texttitle);
            texttitle.setText(detail.getMtitle());
            mSiteUrl=detail.getmSiteUrl();
            progressbar.setVisibility(View.INVISIBLE);
  //          mColor=detail.getMcolor();

//            int color=Integer.parseInt(detail.getMcolor());
//            ImageView imageView=(ImageView)findViewById(R.id.imageview);
  //          imageView.setBackgroundColor(color);
        }
    }

        public void openwebpage(String url){

            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
}
