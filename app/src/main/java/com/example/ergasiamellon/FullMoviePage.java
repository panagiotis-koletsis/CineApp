package com.example.ergasiamellon;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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

        //populating elements with the movie
        binding.title.setText(movie.getTitle());
        binding.movieRatingBar.setRating(movie.getRating());
        binding.textLength.setText(Integer.toString(movie.getDuration()));
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
}
