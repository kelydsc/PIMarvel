package br.com.digitalhouse.digital.pimarvel.view.event;


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
import br.com.digitalhouse.digital.pimarvel.adapters.RecyclerViewEventAdapter;
import br.com.digitalhouse.digital.pimarvel.model.event.Event;
import br.com.digitalhouse.digital.pimarvel.viewmodel.EventViewModel;

public class EventFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerViewEventAdapter adapter;
    private EventViewModel eventViewModel;

    private List<Event> eventList = new ArrayList<>();

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        recyclerViewhome = view.findViewById(R.id.recyclerview_home_event);

        adapter = new RecyclerViewEventAdapter(eventList);
        recyclerViewhome.setAdapter(adapter);

        recyclerViewhome.setLayoutManager(new LinearLayoutManager(getActivity()));

        eventViewModel.searchEvent();

        //Adicionar os observables
        eventViewModel.getEventLiveData().observe(this, events -> adapter.update(events));

        //Observable Loading
        eventViewModel.getLoadingLiveData().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        //Observable Error
        eventViewModel.getErrorLiveData().observe(this, throwable -> {
            Snackbar.make(recyclerViewhome, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
        });

        return view;
    }
}

