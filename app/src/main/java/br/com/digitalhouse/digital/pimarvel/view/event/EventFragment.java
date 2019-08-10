package br.com.digitalhouse.digital.pimarvel.view.event;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        eventViewModel.getEventLiveData().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                adapter.update(events);

                /*
                // *** Favoritos *******************************************************************
                //Instancia do firebase
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                //Referencia
                DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

                //Verifica se há favoritos para os itens
                for (Event eventLine : events) {

                    //Recupera dados do Event
                    DatabaseReference eventReference = usuarioReference.child("favoritos").child("event").child(eventLine.getId());

                    //Adiciona o listener para buscar o objeto gravado em favoritos
                    eventReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Event eventLocal = dataSnapshot.getValue(Event.class);

                            if (eventLocal != null && eventLocal.getId() != null) {
                                adapter.modifyObject(eventLocal, getContext());
                            }

                        }

                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                // *** Favoritos *******************************************************************
                */
            }
        });

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

    /*
    //Recupera os dados do Firebase qdo retorna do DetalheActivity
    @Override
    public void onStart() {
        super.onStart();

        //Recupera os favoritos do Firebase
        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        //Recupera dados dos Favoritos
        DatabaseReference favoritesReference = usuarioReference.child("favoritos").child("event");

        //Adiciona o listener para buscar o objeto gravado em favoritos
        favoritesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshotLine : dataSnapshot.getChildren()) {
                    Event eventLocal = dataSnapshotLine.getValue(Event.class);

                    if (eventLocal != null && eventLocal.getId() != null) {
                        adapter.modifyObject(eventLocal, getContext());
                    }
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    */
}

