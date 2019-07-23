package br.com.digitalhouse.digital.pimarvel.movie.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.event.adapter.RecyclerViewEventAdapter;
import br.com.digitalhouse.digital.pimarvel.movie.adapter.RecyclerViewMovieAdapter;
import br.com.digitalhouse.digital.pimarvel.movie.listener.RecyclerViewMovieClickListener;
import br.com.digitalhouse.digital.pimarvel.movie.model.Movie;

public class MovieFragment extends Fragment implements RecyclerViewMovieClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewMovieAdapter adapter;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter = new RecyclerViewMovieAdapter(getMovie(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Movie> getMovie() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));
        movies.add(new Movie("Avengers End Game", 2019, R.drawable.movies_avengers_endgame));

        return movies;

    }


    public void onClick(Movie movie) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new MovieFragmentDetail());
        ft.commit();
    }
}
