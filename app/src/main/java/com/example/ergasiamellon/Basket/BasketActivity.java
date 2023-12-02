package com.example.ergasiamellon.Basket;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ergasiamellon.R;

public class BasketActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.basket);
        //List<Movie> movies = (List<Movie>) getIntent().getSerializableExtra("movies");
        recyclerView = findViewById(R.id.recyclerViewBasket);

        putDataIntoRecyclerView();

    }
    private  void putDataIntoRecyclerView(){
        BasketAdapter basketAdapter = new BasketAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(basketAdapter);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //connecting main_menu icons with the toolbar
//        getMenuInflater().inflate(R.menu.full_movie_view_menu, menu);
//        return true;
//    }
}
