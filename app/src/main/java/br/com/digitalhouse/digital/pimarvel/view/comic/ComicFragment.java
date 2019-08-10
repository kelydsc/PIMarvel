package br.com.digitalhouse.digital.pimarvel.view.comic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
        comicViewModel.getComicLiveData().observe(this, comics -> {
            adapter.update(comics);


            // *** Favoritos ***********************************************************************
            //Instancia do firebase
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            //Referencia
            DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

            //Verifica se há favoritos para os itens
            for (Comic comicLine : comics) {

                //Recupera dados do Comic
                DatabaseReference comicReference = usuarioReference.child("favoritos").child("comic").child(comicLine.getId());

                //Adiciona o listener para buscar o objeto gravado em favoritos
                comicReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Comic comicLocal = dataSnapshot.getValue(Comic.class);

                        if (comicLocal != null && comicLocal.getId() != null) {
                            adapter.modifyObject(comicLocal, getContext());
                        }

                    }

                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            // *** Favoritos ***********************************************************************


        });

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
        DatabaseReference favoritesReference = usuarioReference.child("favoritos").child("comic");

        //Adiciona o listener para buscar o objeto gravado em favoritos
        favoritesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshotLine : dataSnapshot.getChildren()) {
                    Comic comicLocal = dataSnapshotLine.getValue(Comic.class);

                    if (comicLocal != null && comicLocal.getId() != null) {
                        adapter.modifyObject(comicLocal, getContext());
                    }
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}