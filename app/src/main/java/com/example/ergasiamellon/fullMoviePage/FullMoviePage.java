package com.example.ergasiamellon.fullMoviePage;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ergasiamellon.Basket.BasketActivity;
import com.example.ergasiamellon.MainActivity;
import com.example.ergasiamellon.Movie;
import com.example.ergasiamellon.R;
import com.example.ergasiamellon.databinding.MovieItemFullBinding;

public class FullMoviePage extends AppCompatActivity {

    private MovieItemFullBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_item_full);
        //getting movie from intent
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        //binding xml element
        binding = MovieItemFullBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle("Movie");

        //populating elements with the movie
        binding.title.setText(movie.getTitle());
        binding.movieRatingBar.setRating(movie.getRating());

        binding.textLength.setText(durationConverter(movie));

        binding.description.setText(movie.getTitle());
        Glide.with(this).load(movie.getImg_url()).into(binding.imageView);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adding a fragment for the bottom sheet ticket selector
                //also we pass the movie to it
                FullMoviePageFragment bottomSheetFragment = FullMoviePageFragment.newInstance(movie);
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //adding icons on the toolbar
        getMenuInflater().inflate(R.menu.full_movie_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public String durationConverter(Movie movie){
        int duration ;
        int hours;
        int minutes;
        String result;
        duration = movie.getDuration();
        hours = duration / 60;
        minutes = duration % 60;
        result =  hours+"h "+minutes+"m ";
        return result;

    }
}
