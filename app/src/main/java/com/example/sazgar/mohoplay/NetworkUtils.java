package com.example.sazgar.mohoplay;

import com.example.sazgar.mohoplay.Fragments.ClassicFragment;
import com.example.sazgar.mohoplay.Fragments.NetworkActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */

public class NetworkUtils {

  //  public static String position=NetworkActivity.position;

    public static Detail fetchData(String requestURL){
        URL url=createURL(requestURL);
        String json=makeHTTP(url);
        Detail detail= null;
        try {
            detail = extractfromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return detail;
    }

    private static Detail extractfromJson(String json) throws IOException, JSONException {


        JSONObject root = null;
        try {
            root = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray features = root.getJSONArray("nav_genres");

    JSONObject feature = features.getJSONObject(PositionValue.positionvalue);
   // JSONObject properties = feature.getJSONObject("");
    String title = feature.getString("genre_title");
    String color = feature.getString("genre_color");
    String siteURL = feature.getString("genre_url");
    Detail detail = new Detail(title,color,siteURL);

        return detail;
}











    private static String makeHTTP(URL url) {
        String json = "";
        if (url == null) {
            return json;
        }
        InputStream inputStream = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();

                json = readFromStream(inputStream);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static String readFromStream(InputStream inputStream)throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createURL(String requestURL) {

        URL url=null;
        try {
            url=new URL(requestURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
return url;
    }
}
