package com.example.ergasiamellon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<MyMovieHolderList> {

    private Context mContext;
    private List<Movie> movieList;
    private SelectListener listener;
    private int duration ;
    int hours;
    int minutes;
    String result;

    public Adaptery(Context mContext, List<Movie> movieList,SelectListener listener) {
        this.mContext = mContext;
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyMovieHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //getting movie item list xml connects it to MyMovieHolderList class
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item_list,parent,false);
        return new MyMovieHolderList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMovieHolderList holder, int position) {
        //populate xml elements
        holder.title.setText(movieList.get(position).getTitle());
        //format duration
        duration = movieList.get(position).getDuration();
        hours = duration / 60;
        minutes = duration % 60;
        result =  hours+"h "+minutes+"m ";
        holder.text_length.setText(result);
        //holder.text_length.setText(Integer.toString(movieList.get(position).getDuration()));
        holder.movie_rating_bar.setRating(movieList.get(position).getRating());

        //using glide to fetch image from url
        Glide.with(mContext)
                .load(movieList.get(position).getImg_url())
                .into(holder.image_view);

        //adding event listener
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(movieList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
