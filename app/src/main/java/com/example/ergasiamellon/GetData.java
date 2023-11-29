package com.example.ergasiamellon;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.ergasiamellon.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GetData extends AsyncTask<String,String,String> {
    Movie movie = new Movie();
    List<Movie> moviesList =  new ArrayList<>();
    private static String JSON_URL = "https://movies-sizhfvf6la-uc.a.run.app/";
    //current is the Json movies file
    @Override
    protected String doInBackground(String... strings) {


        String current = "";
        try{
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url =new URL(JSON_URL);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream is = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int data = isr.read();
                while(data != -1){
                    current += (char) data;
                    data = isr.read();
                }
                return current;

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        //return current;
    }

    @Override
    protected void onPostExecute(String s) {
        try{
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("movies");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                movie.setId(jsonObject1.getInt("id"));
                movie.setTitle(jsonObject1.getString("title"));
                movie.setImg_url(jsonObject1.getString("image_url"));
                movie.setDuration(jsonObject1.getInt("duration"));
                movie.setRating(jsonObject1.getInt("rating"));
                movie.setTicket_price(jsonObject1.getDouble("ticket_price"));

                moviesList.add(movie);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
