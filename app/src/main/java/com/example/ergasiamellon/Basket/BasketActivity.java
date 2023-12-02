package com.example.ergasiamellon.Basket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ergasiamellon.MainActivity;
import com.example.ergasiamellon.Movie;
import com.example.ergasiamellon.R;

import java.text.DecimalFormat;
import java.util.List;

public class BasketActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.basket);
        //List<Movie> movies = (List<Movie>) getIntent().getSerializableExtra("movies");
        recyclerView = findViewById(R.id.recyclerViewBasket);

        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle("Basket");

        Button buttonBuy = findViewById(R.id.ButtonBuy);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BasketActivity.this, "Buy", Toast.LENGTH_SHORT).show();
            }
        });
        Button buttonClear = findViewById(R.id.ButtonClear);
        TextView textView = findViewById(R.id.TextSum);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.basketMovie.clear();
                putDataIntoRecyclerView();
                textView.setText("Sum:0"+"€");
            }
        });

        putDataIntoRecyclerView();

        double sum = calculateSum(mainActivity.basketMovie);
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedY = df.format(sum);
        formattedY = formattedY.replace(",",".");
        //textViewTotal.setText(formattedY+"€");
        textView.setText("Sum:"+formattedY+"€");

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
    public double calculateSum(List<Movie> basketMovie){
        double sum =0;
        for (int i=0;i<basketMovie.size();i++){
            sum = sum + basketMovie.get(i).getTicket_price();
        }
        return sum;
    }
}
