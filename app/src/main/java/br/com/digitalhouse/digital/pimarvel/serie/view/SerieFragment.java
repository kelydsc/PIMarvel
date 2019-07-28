package br.com.digitalhouse.digital.pimarvel.serie.view;

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
import br.com.digitalhouse.digital.pimarvel.serie.adapter.RecyclerviewSerieAdapter;
import br.com.digitalhouse.digital.pimarvel.serie.model.Result;
import br.com.digitalhouse.digital.pimarvel.serie.viewmodel.SerieViewModel;

public class SerieFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerviewSerieAdapter recyclerviewSerieAdapter;
    private SerieViewModel serieViewModel;

    public SerieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie, container, false);

        serieViewModel = ViewModelProviders.of(this).get(SerieViewModel.class);

        recyclerViewhome = view.findViewById(R.id.recyclerview_home_serie);

        recyclerviewSerieAdapter = new RecyclerviewSerieAdapter(new ArrayList<>());
        recyclerViewhome.setAdapter(recyclerviewSerieAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);

        recyclerViewhome.setLayoutManager(gridLayoutManager);

        serieViewModel.getSeries();

        serieViewModel.getResults().observe(this, (List<Result> results) -> {
            recyclerviewSerieAdapter.update(results);
        });

        return view;
    }
}