package br.com.digitalhouse.digital.pimarvel.view.serie;

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
import br.com.digitalhouse.digital.pimarvel.adapters.RecyclerViewSerieAdapter;
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;
import br.com.digitalhouse.digital.pimarvel.viewmodel.SerieViewModel;

public class SerieFragment extends Fragment {

    private RecyclerView recyclerViewhome;
    private RecyclerViewSerieAdapter adapter;
    private SerieViewModel serieViewModel;

    private List<Serie> serieList = new ArrayList<>();

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

        adapter = new RecyclerViewSerieAdapter(serieList);
        recyclerViewhome.setAdapter(adapter);

        recyclerViewhome.setLayoutManager(new LinearLayoutManager(getActivity()));

        serieViewModel.searchSerie();

        // Adicionar os observables
        serieViewModel.getSerieLiveData().observe(this, new Observer<List<Serie>>() {
            @Override
            public void onChanged(List<Serie> series) {
                adapter.update(series);


                // *** Favoritos ******************************************************************
                //Instancia do firebase
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                //Referencia
                DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

                //Verifica se há favoritos para os itens
                for (Serie serieLine : series) {

                    //Recupera dados do Serie
                    DatabaseReference serieReference = usuarioReference.child("favoritos").child("serie").child(serieLine.getId());

                    //Adiciona o listener para buscar o objeto gravado em favoritos
                    serieReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Serie serieLocal = dataSnapshot.getValue(Serie.class);

                            if (serieLocal != null && serieLocal.getId() != null) {
                                adapter.modifyObject(serieLocal, getContext());
                            }

                        }

                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                // *** Favoritos ******************************************************************

            }
        });

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
        DatabaseReference favoritesReference = usuarioReference.child("favoritos").child("serie");

        //Adiciona o listener para buscar o objeto gravado em favoritos
        favoritesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshotLine : dataSnapshot.getChildren()) {
                    Serie serieLocal = dataSnapshotLine.getValue(Serie.class);

                    if (serieLocal != null && serieLocal.getId() != null) {
                        adapter.modifyObject(serieLocal, getContext());
                    }
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
