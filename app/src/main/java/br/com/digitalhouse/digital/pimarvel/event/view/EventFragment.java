package br.com.digitalhouse.digital.pimarvel.event.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.base.view.BaseActivity;
import br.com.digitalhouse.digital.pimarvel.event.adapter.RecyclerViewEventAdapter;
import br.com.digitalhouse.digital.pimarvel.event.listener.RecyclerViewEventClickListener;
import br.com.digitalhouse.digital.pimarvel.event.model.Event;

public class EventFragment extends Fragment implements RecyclerViewEventClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewEventAdapter adapter;

    public EventFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewEventAdapter(getEvents(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        events.add(new Event("Memorial Event", "Event dedicated to the greatest comic book writer, yes him, Stan Lee!", R.drawable.stan_lee));
        return events;
    }

    @Override
    public void onClick(Event event) {
        Toast.makeText(getActivity(), "Your will be redirected to the event site.", Toast.LENGTH_LONG).show();
    }
}
