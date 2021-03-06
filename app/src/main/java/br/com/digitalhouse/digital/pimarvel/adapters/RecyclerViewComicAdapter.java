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
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.view.comic.ComicDetalheActivity;

public class RecyclerViewComicAdapter extends RecyclerView.Adapter<RecyclerViewComicAdapter.ViewHolder> {

    private List<Comic> comics;

    public RecyclerViewComicAdapter(List<Comic> comics) {
        this.comics = comics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Comic comic = comics.get(position);
        holder.bind(comic);

        //Click na imagem para compartilhar evento
        holder.comicImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Sharing comic:" + "\n" +
                        "\nComic: " + comic.getTitle() + "\n" +
                        "\nImage: " + comic.getThumbnail().getPath() + "/portrait_incredible."
                        + comic.getThumbnail().getExtension());

                //tipo de compartilhamento
                intentCompartilhar.setType("text/plain");

                //Mostra os aplicativos disponiveis para compartilhamento de dados
                Intent intentChooser = Intent.createChooser(
                        intentCompartilhar, "Compartilhar via:");

                //Start na Activity de compartilhamento
                holder.itemView.getContext().startActivity(intentChooser);
            }
        });

        //Ação no click do Favoritos do Fragmento Comic
        holder.comicImageViewFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Inverte opção do favoritos na tela
                comic.setFavorite(!comic.isFavorite());

                if (comic.isFavorite()) {
                    holder.comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);

                    adicionaFavoritosUsuario(comic);

                } else {
                    holder.comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);

                    //Remove o item no banco de dados
                    removeFavoritosUsuario(comic);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String transitionName = "image" + position;
                Intent intent = new Intent(holder.itemView.getContext(), ComicDetalheActivity.class);
                intent.putExtra("comic", comic);
                intent.putExtra("transitionName", transitionName);

                holder.imageComicHome.setTransitionName(transitionName);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                holder.imageComicHome, transitionName);

                holder.itemView.getContext().startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    //****Favoritos*********************************************************************************
    public void modifyObject(Comic comicFavorite, Context context) {

        try {
            //Atualiza o registro com os dados adicionais dos favoritos
            for (Comic comicLine : this.comics) {
                if (comicLine.getId().equals(comicFavorite.getId())) {
                    comicLine.setFavorite(comicFavorite.isFavorite());
                }
            }

            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //****Favoritos*********************************************************************************


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageComicHome;
        TextView textViewComicTitle;
        ImageView comicImageViewShare;
        ImageView comicImageViewFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageComicHome = itemView.findViewById(R.id.imageComicHome);
            textViewComicTitle = itemView.findViewById(R.id.textTitle);
            comicImageViewShare = itemView.findViewById(R.id.comicImageViewShare);
            comicImageViewFavorite = itemView.findViewById(R.id.comicImageViewFavorite);
        }

        private void bind(Comic comic) {

            //Verifica favoritos
            if (comic.isFavorite()) {
                comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
            } else {
                comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);
            }

            if (comic.getThumbnail().getPath() != null && comic.getThumbnail().getExtension() != null) {
                Picasso.get().load(comic.getThumbnail().getPath() + "/portrait_incredible." +
                        comic.getThumbnail().getExtension())
                        .placeholder(R.drawable.ic_logo_marvel)
                        .error(R.drawable.ic_logo_marvel)
                        .into(imageComicHome);
            }

            if (comic.getTitle() != null) {
                textViewComicTitle.setText(comic.getTitle());
            } else {
                textViewComicTitle.setText("");
            }
        }
    }

    public void clear() {
        this.comics.clear();
        notifyDataSetChanged();
    }

    public void update(List<Comic> comicList) {
        this.comics = comicList;

        notifyDataSetChanged();
    }

    public void adicionaFavoritosUsuario(Comic comicFavorite) {

        comicFavorite.setComicFavorito("comic");

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference comicReference = usuarioReference.child("favoritos").child("comic");

        usuarioReference
                .child("favoritos")
                .child("comic")
                .child(comicFavorite.getId())
                .setValue(comicFavorite);
    }

    public void removeFavoritosUsuario(Comic comicFavorite) {

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference comicReference = usuarioReference.child("favoritos").child("comic");

        usuarioReference
                .child("favoritos")
                .child("comic")
                .child(comicFavorite.getId())
                .removeValue();
    }
}

