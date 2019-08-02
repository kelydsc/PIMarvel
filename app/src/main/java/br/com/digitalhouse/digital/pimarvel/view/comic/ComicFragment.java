package br.com.digitalhouse.digital.pimarvel.view.comic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.adapters.RecyclerViewComicAdapter;
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.viewmodel.ComicViewModel;

public class ComicFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerViewComicAdapter adapter;
    private ComicViewModel comicViewModel;

    private List<Comic> comicList = new ArrayList<>();

    public ComicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comic, container, false);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        comicViewModel = ViewModelProviders.of(this).get(ComicViewModel.class);

        recyclerViewhome = view.findViewById(R.id.recyclerview_home_comic);

        adapter = new RecyclerViewComicAdapter(comicList);
        recyclerViewhome.setAdapter(adapter);

        recyclerViewhome.setLayoutManager(new LinearLayoutManager(getActivity()));

        comicViewModel.searchComic();

        // Adicionar os observables
        comicViewModel.getComicLiveData().observe(this, comics -> adapter.update(comics));

        //Observable Loading
        comicViewModel.getLoadingLiveData().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        //Observable Error
        comicViewModel.getErrorLiveData().observe(this, throwable -> {
            Snackbar.make(recyclerViewhome, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
        });

        return view;
    }
}
