package br.com.digitalhouse.digital.pimarvel.view.favorite;


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
import br.com.digitalhouse.digital.pimarvel.adapters.RecyclerViewFavoriteAdapter;
import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;
import br.com.digitalhouse.digital.pimarvel.viewmodel.FavoriteViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
//public class FavoriteFragment extends Fragment implements RecyclerViewFavoriteClickListener {

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

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference serieReference = usuarioReference.child("favoritos").child("serie");

        //Adicionamos o listener par pegar os resultados do firebase
        serieReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Lista vazia pra pegar os resultados do firebase
                List<Favorite> favoriteList = new ArrayList<>();

                //Após receber os dados, os memos serão adicionados para atualização do Adapter
                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {

                    Favorite favoriteLocal = resultSnapshot.getValue(Favorite.class);

                    //Acrescenta o registro na lista de favoritos
                    favoriteList.add(favoriteLocal);
                }

                //Atualiza o Adapter para exibição da lista de favoritos a partir do Firebase
                adapter.updateFavorites(favoriteList);
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
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
        */

        return view;
    }
}
