package com.example.ergasiamellon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.ergasiamellon.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener{

    private static String JSON_URL = "https://movies-sizhfvf6la-uc.a.run.app/";

    private ActivityMainBinding binding;
    RecyclerView recyclerView;
    List<Movie> moviesList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //binding objects
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //set the toolbar visible
        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle("Cine App");

        Button testButton = binding.buttonTest;
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //Handle the click on the icon

                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                //adding a serializable movie to pass to the ne activity
                //intent.putExtra("movies", (Serializable) Reference.basketMovie);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        GetData getData = new GetData();
        getData.execute();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //connecting main_menu icons with the toolbar
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    //    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        onMenuItemSelected(R.id.action_basket);
//        switch (item.getItemId()) {
//            case :
//                // Handle the click on the icon
//                Intent intent = new Intent(this, BasketActivity.class);
//                //adding a serializable movie to pass to the ne activity
//                intent.putExtra("movies", (Serializable) Reference.basketMovie);
//                startActivity(intent);
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onTimeClicked(Movie movie) {
        //misspelled should be onItemClicked
        //Toast.makeText(this,movie.getTitle(),Toast.LENGTH_SHORT).show();
        //Starting new activity for the full movie page
        Intent intent = new Intent(this, FullMoviePage.class);
        //adding a serializable movie to pass to the ne activity
        intent.putExtra("movie",movie);
        startActivity(intent);

    }


    public class GetData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            //fetching the url as a string stores to current

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
            //gets each json string and converts it to Movie object ,add it to the arraylist
            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("movies");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    Movie movie = new Movie();
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
            putDataIntoRecyclerView(moviesList);
        }

    }

    private  void putDataIntoRecyclerView(List<Movie> movieList){
        Adaptery adaptery = new Adaptery(this,movieList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }
}
class Reference {
    public static List<Movie> basketMovie = new ArrayList<>();

}

