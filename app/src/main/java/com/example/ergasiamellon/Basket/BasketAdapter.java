package com.example.ergasiamellon.Basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ergasiamellon.MainActivity;
import com.example.ergasiamellon.Movie;
import com.example.ergasiamellon.R;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketViewHolder>{
    //for accessing static arraylist with the movies added to basket
    ArrayList<Movie> basketMovie = (ArrayList<Movie>) MainActivity.basketMovie;
    Context mContext;

    public BasketAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.basket_list,parent,false);
        return new BasketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {

        holder.textViewTitle.setText(basketMovie.get(position).getTitle());
        holder.textViewTotal.setText(Double.toString((double) basketMovie.get(position).getTicket_price())+"€");

        //using glide to fetch image from url
        Glide.with(mContext)
                .load(basketMovie.get(position).getImg_url())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return basketMovie.size();
    }
}
