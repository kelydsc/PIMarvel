package br.com.digitalhouse.digital.pimarvel.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;
import br.com.digitalhouse.digital.pimarvel.view.comic.ComicDetalheActivity;

public class RecyclerViewFavoriteAdapter extends RecyclerView.Adapter<RecyclerViewFavoriteAdapter.ViewHolder> {

    private List<Favorite> favorites;

    /*
    private RecyclerViewFavoriteClickListener listener;

    public RecyclerViewFavoriteAdapter(List<Favorite> favorites, RecyclerViewFavoriteClickListener listener) {
        this.favorites = favorites;
        this.listener = listener;
    }
    */

    public RecyclerViewFavoriteAdapter(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.favorite_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Favorite favorite = favorites.get(position);
        holder.bind(favorite);

        recuperaDadosBanco();

        //Remove item dos Favoritos
        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Remove o item na tela
                removeFavorites(position);

                //Remove o item no banco de dados
                removeFavoritosUsuario(favorite);
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                String transitionName = "image" + position;
                Intent intent = new Intent(holder.itemView.getContext(), ComicDetalheActivity.class);
                intent.putExtra("comic", favorite);
                intent.putExtra("transitionName", transitionName);

                holder.imageFavoriteHome.setTransitionName(transitionName);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                holder.imageFavoriteHome, transitionName);

                holder.itemView.getContext().startActivity(intent, options.toBundle());

                */

            }
        });
    }

    private void recuperaDadosBanco() {

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
                updateFavorites(favoriteList);
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageFavoriteHome;
        private ImageView imageViewFavorite;
        private TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFavoriteHome = itemView.findViewById(R.id.imageFavoriteHome);
            imageViewFavorite = itemView.findViewById(R.id.imageViewFavorite);
            textTitle = itemView.findViewById(R.id.textTitle);
        }

        public void bind(Favorite favorite) {

            //Imagem
            if (favorite.getComicFavorite().getThumbnail().getPath() != null && favorite.getComicFavorite().getThumbnail().getExtension() != null) {
                Picasso.get().load(favorite.getComicFavorite().getThumbnail().getPath() + "/portrait_incredible." +
                        favorite.getComicFavorite().getThumbnail().getExtension())
                        .placeholder(R.drawable.ic_logo_marvel)
                        .error(R.drawable.ic_logo_marvel)
                        .into(imageFavoriteHome);
            }

            //Favoritos Título
            if (favorite.getComicFavorite().getTitle() != null) {
                textTitle.setText(favorite.getComicFavorite().getTitle());
            } else {
                textTitle.setText("");
            }
        }
    }

    //Método para remover o item
    public void removeFavorites(int position) {

        favorites.remove(position);

        notifyDataSetChanged();
    }

    public void updateFavorites(List<Favorite> favoriteList) {
        this.favorites = favoriteList;

        notifyDataSetChanged();
    }

    public void removeFavoritosUsuario(Favorite comicFavorite) {

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference comicReference = usuarioReference.child("favoritos").child("comic");

        usuarioReference
                .child("favoritos")
                .child("comic")
                .child(comicFavorite.getIdComic())
                .removeValue();
    }
}