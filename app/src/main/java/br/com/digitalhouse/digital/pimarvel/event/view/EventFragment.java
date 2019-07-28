package br.com.digitalhouse.digital.pimarvel.event.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.event.adapter.RecyclerviewEventAdapter;
import br.com.digitalhouse.digital.pimarvel.event.model.Result;
import br.com.digitalhouse.digital.pimarvel.event.viewmodel.EventViewModel;

public class EventFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerviewEventAdapter recyclerviewEventAdapter;
    private EventViewModel eventViewModel;

    public EventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        recyclerViewhome = view.findViewById(R.id.recyclerview_home_event);

        recyclerviewEventAdapter = new RecyclerviewEventAdapter(new ArrayList<>());
        recyclerViewhome.setAdapter(recyclerviewEventAdapter);

        recyclerViewhome.setLayoutManager(new LinearLayoutManager(getContext()));

        eventViewModel.getEvents();

        eventViewModel.getResults().observe(this, (List<Result> results) -> {
            recyclerviewEventAdapter.update(results);
        });

        return view;
    }
}


