package br.com.digitalhouse.digital.pimarvel.serie.view;

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
import br.com.digitalhouse.digital.pimarvel.serie.adapter.RecyclerviewSerieAdapter;
import br.com.digitalhouse.digital.pimarvel.serie.model.Result;
import br.com.digitalhouse.digital.pimarvel.serie.viewmodel.SerieViewModel;

public class SerieFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerviewSerieAdapter adapter;
    private SerieViewModel serieViewModel;

    private List<Result> serieList = new ArrayList<>();

    public SerieFragment() {
        // Required empty public constructor
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie, container, false);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        serieViewModel = ViewModelProviders.of(this).get(SerieViewModel.class);

        recyclerViewhome = view.findViewById(R.id.recyclerview_home_serie);

        adapter = new RecyclerviewSerieAdapter(serieList);
        recyclerViewhome.setAdapter(adapter);

        recyclerViewhome.setLayoutManager(new LinearLayoutManager(getActivity()));

        serieViewModel.searchSerie();

        // Adicionar os observables
        serieViewModel.getResults().observe(this, series -> adapter.update(series));

        //Observable Loading
        serieViewModel.getLoadingLiveData().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        //Observable Error
        serieViewModel.getErrorLiveData().observe(this, throwable -> {
            Snackbar.make(recyclerViewhome, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
        });

        return view;
    }
}
