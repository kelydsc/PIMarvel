package br.com.digitalhouse.digital.pimarvel.movie.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.digitalhouse.digital.pimarvel.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragmentDetail extends Fragment {

    public MovieFragmentDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ImageView image_movie = view.findViewById(R.id.image_movie);
        ImageView image_star_1 = view.findViewById(R.id.image_star_1);
        ImageView image_star_2 = view.findViewById(R.id.image_star_2);
        ImageView image_star_3 = view.findViewById(R.id.image_star_3);
        ImageView image_star_4 = view.findViewById(R.id.image_star_4);
        ImageView image_star_5 = view.findViewById(R.id.image_star_5);
        ImageView image_favorite = view.findViewById(R.id.image_favorite);
        TextView text_movie_title = view.findViewById(R.id.text_movie_title);
        TextView text_release = view.findViewById(R.id.text_release);
        TextView text_genre = view.findViewById(R.id.text_genre);
        TextView text_director = view.findViewById(R.id.text_director);
        TextView text_synopsis = view.findViewById(R.id.text_synopsis);

        text_movie_title.setText("Avengers Endgame");
        text_release.setText("Release: April 25, 2019.");
        text_genre.setText("Genre: Action & Adventure, Drama, Science Fiction & Fantasy.");
        text_director.setText("Directed by: Joe Russo, Anthony Russo.");
        text_synopsis.setText("Synopsis: After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.");

        return view;
    }

}
