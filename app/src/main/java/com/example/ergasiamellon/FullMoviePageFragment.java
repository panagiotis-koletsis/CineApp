package com.example.ergasiamellon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ergasiamellon.databinding.FullMoviePageFragmentButtonBinding;
import com.example.ergasiamellon.databinding.MovieItemFullBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FullMoviePageFragment extends BottomSheetDialogFragment {

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
        textViewTotal.setText(String.valueOf(movie.getTicket_price()));

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

                //price
                double y = Double.parseDouble(String.valueOf(textViewTotal.getText()));
                y=y+movie.getTicket_price();
                textViewTotal.setText(String.valueOf(y));
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

                    double y = Double.parseDouble(String.valueOf(textViewTotal.getText()));
                    y = y - movie.getTicket_price();
                    textViewTotal.setText(String.valueOf(y));
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
                    Reference.basketMovie.add(movie);
            }
        });
        return view;
    }
}
