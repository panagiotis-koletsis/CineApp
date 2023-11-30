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

    public Adaptery(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyMovieHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item_list,parent,false);
        return new MyMovieHolderList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMovieHolderList holder, int position) {
        holder.title.setText(movieList.get(position).getTitle());
        //holder.text_length.setText(movieList.get(position).getDuration());
        holder.text_length.setText(Integer.toString(movieList.get(position).getDuration()));
        holder.movie_rating_bar.setRating(movieList.get(position).getRating());

        Glide.with(mContext)
                .load(movieList.get(position).getImg_url())
                .into(holder.image_view);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
