package com.example.ergasiamellon.Basket;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ergasiamellon.R;

public class BasketViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textViewTitle;
    TextView textViewTotal;


    public BasketViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewTotal = itemView.findViewById(R.id.textViewTotal);
        imageView = itemView.findViewById(R.id.image_view);
    }
}
