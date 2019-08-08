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

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.FavoriteDAO;
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.view.comic.ComicDetalheActivity;
import br.com.digitalhouse.digital.pimarvel.view.event.EventDetalheActivity;
import br.com.digitalhouse.digital.pimarvel.view.serie.SerieDetalheActivity;

public class RecyclerViewFavoriteAdapter extends RecyclerView.Adapter<RecyclerViewFavoriteAdapter.ViewHolder> {

    private List<Comic> favorites;

    //Verifica se o favorito selecionado eh comic, event ou serie
    private String tipoFavorito = "";
    private String comic = "comic";
    private String event = "event";
    private String serie = "serie";

    public RecyclerViewFavoriteAdapter(List<Comic> favorites) {
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

        Comic favorite = favorites.get(position);
        holder.bind(favorite);

        //Remove item dos Favoritos
        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Remove o item no banco de dados
                removeFavoritosComicUsuario(favorite);

                //Remove o item na tela
                removeFavorites(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verifica se o favorito selecionado eh comic, event ou serie
                tipoFavorito = favorite.getComicFavorito();

                if (tipoFavorito == comic) {

                    String transitionName = "image" + position;
                    Intent intent = new Intent(holder.itemView.getContext(), ComicDetalheActivity.class);
                    intent.putExtra("comic", favorite);
                    intent.putExtra("transitionName", transitionName);

                    holder.imageFavoriteHome.setTransitionName(transitionName);

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                    holder.imageFavoriteHome, transitionName);

                    holder.itemView.getContext().startActivity(intent, options.toBundle());

                }
                /*?????
                else if (tipoFavorito == event) {

                    String transitionName = "image" + position;
                    Intent intent = new Intent(holder.itemView.getContext(), EventDetalheActivity.class);
                    intent.putExtra("event", favorite);
                    intent.putExtra("transitionName", transitionName);

                    holder.imageFavoriteHome.setTransitionName(transitionName);

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                    holder.imageFavoriteHome, transitionName);

                    holder.itemView.getContext().startActivity(intent, options.toBundle());

                } else if (tipoFavorito == serie) {

                    String transitionName = "image" + position;
                    Intent intent = new Intent(holder.itemView.getContext(), SerieDetalheActivity.class);
                    intent.putExtra("serie", favorite);
                    intent.putExtra("transitionName", transitionName);

                    holder.imageFavoriteHome.setTransitionName(transitionName);

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                    holder.imageFavoriteHome, transitionName);

                    holder.itemView.getContext().startActivity(intent, options.toBundle());
                }
                */
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

        public void bind(Comic favorite) {

            //Imagem
            if (favorite.getThumbnail().getPath() != null && favorite.getThumbnail().getExtension() != null) {
                Picasso.get().load(favorite.getThumbnail().getPath() + "/portrait_incredible." +
                        favorite.getThumbnail().getExtension())
                        .placeholder(R.drawable.ic_logo_marvel)
                        .error(R.drawable.ic_logo_marvel)
                        .into(imageFavoriteHome);
            }

            //Favoritos Título
            if (favorite.getTitle() != null) {
                textTitle.setText(favorite.getTitle());
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

    public void updateFavorites(List<Comic> favoriteList) {
        this.favorites = favoriteList;

        notifyDataSetChanged();
    }

    public void addFavorites(Comic favoriteLocal) {
        this.favorites.add(favoriteLocal);

        notifyDataSetChanged();
    }

    public void removeFavoritosComicUsuario(Comic comicFavorite) {

        //Verifica se o favorito selecionado eh comic, event ou serie
        tipoFavorito = comicFavorite.getComicFavorito();

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia do firebase
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        if (tipoFavorito == comic) {

            //Recupera dados do Comic
            DatabaseReference comicReference = usuarioReference.child("favoritos").child("comic");

            usuarioReference
                    .child("favoritos")
                    .child("comic")
                    .child(comicFavorite.getId())
                    .removeValue();

        } else if (tipoFavorito == event) {

            //Recupera dados do Event
            DatabaseReference eventReference = usuarioReference.child("favoritos").child("event");

            usuarioReference
                    .child("favoritos")
                    .child("event")
                    .child(comicFavorite.getId())
                    .removeValue();

        } else if (tipoFavorito == serie) {

            //Recupera dados da Serie
            DatabaseReference serieReference = usuarioReference.child("favoritos").child("serie");

            usuarioReference
                    .child("favoritos")
                    .child("serie")
                    .child(comicFavorite.getId())
                    .removeValue();
        }
    }
}