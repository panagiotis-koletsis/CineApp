package com.example.ergasiamellon.fullMoviePage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ergasiamellon.MainActivity;
import com.example.ergasiamellon.Movie;
import com.example.ergasiamellon.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FullMoviePageFragment extends BottomSheetDialogFragment {
    MainActivity mainActivity = new MainActivity();
    ArrayList<Movie> basketMovie = (ArrayList<Movie>) MainActivity.basketMovie;

    public static FullMoviePageFragment newInstance(Movie movie) {
        FullMoviePageFragment fragment = new FullMoviePageFragment();
        Bundle args = new Bundle();
        args.putSerializable("movie",movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //setting xml
        View view = inflater.inflate(R.layout.full_movie_page_fragment_button, container, false);
        //getting movie
        Movie movie = (Movie) getArguments().getSerializable("movie");

        //find the elements
        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText(movie.getTitle());

        TextView textViewTotal = view.findViewById(R.id.textViewTotal);
        String val = String.valueOf(movie.getTicket_price());
        val = val+"€";
        textViewTotal.setText(val);
        //textViewTotal.setText(String.valueOf(movie.getTicket_price()));

        TextView textViewNumber = view.findViewById(R.id.textViewNumber);

        Button buttonIncr = view.findViewById(R.id.buttonInc);
        //on click button increase
        buttonIncr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //quantity
                int x = Integer.parseInt(String.valueOf(textViewNumber.getText()));
                x++;
                textViewNumber.setText(String.valueOf(x));

                String originalString = String.valueOf(textViewTotal.getText());
                char charToRemove = '€';
                String modifiedString = originalString.replace(String.valueOf(charToRemove), "");
                double y = Double.parseDouble(modifiedString);

                //price
                //double y = Double.parseDouble(String.valueOf(textViewTotal.getText()));
                y=y+movie.getTicket_price();

                DecimalFormat df1 = new DecimalFormat("#.##");
                String formattedY = df1.format(y);
                formattedY = formattedY.replace(",",".");
                textViewTotal.setText(formattedY+"€");
                //textViewTotal.setText(String.valueOf(y));
            }
        });
        Button buttonDecr = view.findViewById(R.id.buttonDecr);
        //on click decrease
        buttonDecr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = Integer.parseInt(String.valueOf(textViewNumber.getText()));
                if(x>0) {
                    x--;
                    textViewNumber.setText(String.valueOf(x));

                    String originalString = String.valueOf(textViewTotal.getText());
                    char charToRemove = '€';
                    String modifiedString = originalString.replace(String.valueOf(charToRemove), "");
                    double y = Double.parseDouble(modifiedString);
                    //double y = Double.parseDouble(String.valueOf(textViewTotal.getText()));
                    y = y - movie.getTicket_price();

                    DecimalFormat df = new DecimalFormat("#.##");
                    String formattedY = df.format(y);
                    formattedY = formattedY.replace(",",".");
                    textViewTotal.setText(formattedY+"€");

                    //textViewTotal.setText(String.valueOf(y));
                }
            }
        });
        //adds to basketMoviesList
        Button buttonTicket = view.findViewById(R.id.buttonTicket);
        buttonTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"ticket added to basket",Toast.LENGTH_SHORT).show();
                int size = Integer.parseInt(String.valueOf(textViewNumber.getText()));
                for(int u=0;u<size;u++)
                    basketMovie.add(movie);
            }
        });
        return view;
    }
}
