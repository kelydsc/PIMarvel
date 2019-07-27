package br.com.digitalhouse.digital.pimarvel.comic.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.comic.adapter.RecyclerviewComicAdapter;
import br.com.digitalhouse.digital.pimarvel.comic.model.Result;
import br.com.digitalhouse.digital.pimarvel.comic.viewmodel.ComicViewModel;

public class ComicFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerviewComicAdapter recyclerviewComicAdapter;
    private ComicViewModel comicViewModel;

    public ComicFragment() {
        // Required empty public constructor
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comic, container, false);

        comicViewModel = ViewModelProviders.of(this).get(ComicViewModel.class);

        recyclerViewhome = view.findViewById(R.id.recyclerview_home_comic);

        recyclerviewComicAdapter = new RecyclerviewComicAdapter(new ArrayList<>());
        recyclerViewhome.setAdapter(recyclerviewComicAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);

        recyclerViewhome.setLayoutManager(gridLayoutManager);

        comicViewModel.getComics();

        comicViewModel.getResults().observe(this, (List<Result> results) -> {
            recyclerviewComicAdapter.update(results);
        });

        return view;
    }
}
