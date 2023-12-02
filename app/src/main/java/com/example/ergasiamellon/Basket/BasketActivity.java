package com.example.ergasiamellon.Basket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ergasiamellon.MainActivity;
import com.example.ergasiamellon.R;

public class BasketActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.basket);
        //List<Movie> movies = (List<Movie>) getIntent().getSerializableExtra("movies");
        recyclerView = findViewById(R.id.recyclerViewBasket);

        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle("Basket");

        putDataIntoRecyclerView();

    }
    private  void putDataIntoRecyclerView(){
        BasketAdapter basketAdapter = new BasketAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(basketAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //adding icons on the toolbar
        getMenuInflater().inflate(R.menu.full_movie_view_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
