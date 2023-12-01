package com.example.ergasiamellon;

import static com.example.ergasiamellon.Reference.basketMovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class BasketAdapter extends RecyclerView.Adapter<BasketViewHolder>{
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
        holder.textViewTotal.setText(Integer.toString((int) basketMovie.get(position).getTicket_price()));


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
