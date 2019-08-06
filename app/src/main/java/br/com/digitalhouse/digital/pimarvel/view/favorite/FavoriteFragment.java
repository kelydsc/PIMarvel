package br.com.digitalhouse.digital.pimarvel.view.favorite;


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
import br.com.digitalhouse.digital.pimarvel.adapters.RecyclerViewFavoriteAdapter;
import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;
import br.com.digitalhouse.digital.pimarvel.viewmodel.FavoriteViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerViewFavoriteAdapter adapter;

    private FavoriteViewModel favoriteViewModel;

    private List<Favorite> favoriteList = new ArrayList<>();

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        recyclerViewhome = view.findViewById(R.id.recyclerview_home_favorite);

        adapter = new RecyclerViewFavoriteAdapter(favoriteList);
        recyclerViewhome.setAdapter(adapter);

        recyclerViewhome.setLayoutManager(new LinearLayoutManager(getActivity()));

        favoriteViewModel.searchFavorite();

        // Adicionar os observables
        favoriteViewModel.getFavoriteLiveData().observe(this, comics -> adapter.updateFavorites(favoriteList));

        //Observable Loading
        favoriteViewModel.getFavoriteLoadingLiveData().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        //Observable Error
        favoriteViewModel.getFavoriteErrorLiveData().observe(this, throwable -> {
            Snackbar.make(recyclerViewhome, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
        });

        return view;
    }

}
