package br.com.digitalhouse.digital.pimarvel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;

public class RecyclerViewFavoriteAdapter extends RecyclerView.Adapter<RecyclerViewFavoriteAdapter.ViewHolder> {

    private List<Comic> favorites;

    /*
    private RecyclerViewFavoriteClickListener listener;

    public RecyclerViewFavoriteAdapter(List<Favorite> favorites, RecyclerViewFavoriteClickListener listener) {
        this.favorites = favorites;
        this.listener = listener;
    }
    */

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

        //recuperaDadosBanco();

        //Remove item dos Favoritos
        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Remove o item na tela
                removeFavorites(position);

                //Remove o item no banco de dados
                //removeFavoritosUsuario(favorite);
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

    /*
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
    */

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

    /*
    public void removeFavoritosUsuario(Comic comicFavorite) {

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
    */
}