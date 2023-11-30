package com.example.ergasiamellon;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ergasiamellon.databinding.MovieItemListBinding;

public class MyMovieHolderList extends RecyclerView.ViewHolder{

    ImageView image_view;
    TextView title;
    TextView text_length;
    RatingBar movie_rating_bar;


    public MyMovieHolderList(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        text_length = itemView.findViewById(R.id.text_length);
        movie_rating_bar = itemView.findViewById(R.id.movie_rating_bar);
        image_view = itemView.findViewById(R.id.image_view);
    }
}
