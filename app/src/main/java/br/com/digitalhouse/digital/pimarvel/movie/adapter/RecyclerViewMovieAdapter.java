package br.com.digitalhouse.digital.pimarvel.movie.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.movie.listener.RecyclerViewMovieClickListener;
import br.com.digitalhouse.digital.pimarvel.movie.model.Movie;

public class RecyclerViewMovieAdapter extends RecyclerView.Adapter<RecyclerViewMovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private RecyclerViewMovieClickListener listener;

    public RecyclerViewMovieAdapter(List<Movie> movies, RecyclerViewMovieClickListener movieListener) {
        this.movies = movies;
        this.listener = movieListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Movie movie = movies.get(i);
        viewHolder.bind(movie);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMovie = itemView.findViewById(R.id.image_movie);
        }

        public void bind(Movie movies) {
            imageMovie.setImageDrawable(ContextCompat.getDrawable(imageMovie.getContext(), movies.getImage()));

        }
    }
}
