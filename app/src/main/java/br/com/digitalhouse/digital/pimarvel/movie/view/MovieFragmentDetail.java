package br.com.digitalhouse.digital.pimarvel.movie.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

}
