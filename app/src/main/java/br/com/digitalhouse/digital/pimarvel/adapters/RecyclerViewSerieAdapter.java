package br.com.digitalhouse.digital.pimarvel.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;
import br.com.digitalhouse.digital.pimarvel.view.serie.SerieDetalheActivity;

public class RecyclerViewSerieAdapter extends RecyclerView.Adapter<RecyclerViewSerieAdapter.ViewHolder> {

    private List<Serie> series;

    public RecyclerViewSerieAdapter(List<Serie> series) {
        this.series = series;
    }

    @NonNull
    @Override
    public RecyclerViewSerieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.serie_recyclerview_item, parent, false);

        return new RecyclerViewSerieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSerieAdapter.ViewHolder holder, int position) {

        Serie serie = series.get(position);
        holder.bind(serie);

        //Click na imagem para compartilhar evento
        holder.serieImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Sharing serie:" + "\n" +
                        "\nSerie: " + serie.getTitle() + "\n" +
                        "\nImage: " + serie.getThumbnail().getPath() + "/portrait_incredible."
                        + serie.getThumbnail().getExtension());

                //tipo de compartilhamento
                intentCompartilhar.setType("text/plain");

                //Mostra os aplicativos disponiveis para compartilhamento de dados
                Intent intentChooser = Intent.createChooser(
                        intentCompartilhar, "Compartilhar via:");

                //Start na Activity de compartilhamento
                holder.itemView.getContext().startActivity(intentChooser);
            }
        });

        //Ação no click do Favoritos do Fragmento Serie
        holder.serieImageViewFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Inverte opção do favoritos na tela
                serie.setFavorite(!serie.isFavorite());

                if (serie.isFavorite()) {

                    holder.serieImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);

                    adicionaFavoritosUsuario(serie);

                } else {

                    holder.serieImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);

                    //Remove o item no banco de dados
                    removeFavoritosUsuario(serie);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String transitionName = "image" + position;
                Intent intent = new Intent(holder.itemView.getContext(), SerieDetalheActivity.class);
                intent.putExtra("serie", serie);
                intent.putExtra("transitionName", transitionName);

                holder.imageSerieHome.setTransitionName(transitionName);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                holder.imageSerieHome, transitionName);

                holder.itemView.getContext().startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    //****Favoritos*********************************************************************************
    public void modifyObject(Serie serieFavorite, Context context) {

        try {
            //Atualiza o registro com os dados adicionais dos favoritos
            for (Serie serieLine : this.series) {
                if (serieLine.getId().equals(serieFavorite.getId())) {
                    serieLine.setFavorite(serieFavorite.isFavorite());
                }
            }

            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //****Favoritos*********************************************************************************

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageSerieHome;
        TextView textViewSerieTitle;
        ImageView serieImageViewShare;
        ImageView serieImageViewFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSerieHome = itemView.findViewById(R.id.imageSerieHome);
            textViewSerieTitle = itemView.findViewById(R.id.textTitle);
            serieImageViewShare = itemView.findViewById(R.id.serieImageViewShare);
            serieImageViewFavorite = itemView.findViewById(R.id.serieImageViewFavorite);

        }

        private void bind(Serie serie) {

            //Verifica Favoritos
            if (serie.isFavorite()) {
                serieImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
            } else {
                serieImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);
            }

            if (serie.getThumbnail().getPath() != null && serie.getThumbnail().getExtension() != null) {
                Picasso.get().load(serie.getThumbnail().getPath() + "/portrait_incredible." +
                        serie.getThumbnail().getExtension())
                        .placeholder(R.drawable.ic_logo_marvel)
                        .error(R.drawable.ic_logo_marvel)
                        .into(imageSerieHome);
            }

            if (serie.getTitle() != null) {
                textViewSerieTitle.setText(serie.getTitle());
            } else {
                textViewSerieTitle.setText("");
            }
        }
    }

    public void clear() {
        this.series.clear();
        notifyDataSetChanged();
    }

    public void update(List<Serie> serieList) {
        this.series = serieList;
        notifyDataSetChanged();
    }

    public void adicionaFavoritosUsuario(Serie serieFavorite) {

        serieFavorite.setSerieFavorito("serie");

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference serieReference = usuarioReference.child("favoritos").child("serie");

        usuarioReference
                .child("favoritos")
                .child("serie")
                .child(serieFavorite.getId())
                .setValue(serieFavorite);

    }

    public void removeFavoritosUsuario(Serie serieFavorite) {

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference serieReference = usuarioReference.child("favoritos").child("serie");

        usuarioReference
                .child("favoritos")
                .child("serie")
                .child(serieFavorite.getId())
                .removeValue();
    }
}
