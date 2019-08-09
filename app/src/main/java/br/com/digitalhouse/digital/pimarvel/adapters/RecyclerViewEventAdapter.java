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
import br.com.digitalhouse.digital.pimarvel.model.event.Event;
import br.com.digitalhouse.digital.pimarvel.view.event.EventDetalheActivity;

public class RecyclerViewEventAdapter extends RecyclerView.Adapter<RecyclerViewEventAdapter.ViewHolder> {

    private List<Event> events;

    public RecyclerViewEventAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.event_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Event event = events.get(position);
        holder.bind(event);

        //Click na imagem para compartilhar evento
        holder.eventImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Sharing event:" + "\n" +
                        "\nEvent: " + event.getTitle() + "\n" +
                        "\nDescription: " + event.getDescription() + "\n" +
                        "\nImage: " + event.getThumbnail().getPath() + "/portrait_incredible."
                        + event.getThumbnail().getExtension());

                //tipo de compartilhamento
                intentCompartilhar.setType("text/plain");

                //Mostra os aplicativos disponiveis para compartilhamento de dados
                Intent intentChooser = Intent.createChooser(
                        intentCompartilhar, "Compartilhar via:");

                //Start na Activity de compartilhamento
                holder.itemView.getContext().startActivity(intentChooser);
            }
        });

        //Ação no click do Favoritos do Fragmento Event
        holder.eventImageViewFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Inverte opção do favoritos na tela
                event.setFavorite(!event.isFavorite());

                if (event.isFavorite()) {

                    holder.eventImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);

                    adicionaFavoritosUsuario(event);


                } else {

                    holder.eventImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);

                    //Remove o item no banco de dados
                    removeFavoritosUsuario(event);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String transitionName = "image" + position;
                Intent intent = new Intent(holder.itemView.getContext(), EventDetalheActivity.class);
                intent.putExtra("event", event);
                intent.putExtra("transitionName", transitionName);

                holder.imageEventHome.setTransitionName(transitionName);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                holder.imageEventHome, transitionName);

                holder.itemView.getContext().startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    //****Favoritos*********************************************************************************
    public void modifyObject(Event eventFavorite, Context context) {

        try {
            //Atualiza o registro com os dados adicionais dos favoritos
            for (Event eventLine : this.events) {
                if (eventLine.getId().equals(eventFavorite.getId())) {
                    eventLine.setFavorite(eventFavorite.isFavorite());
                }
            }

            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //****Favoritos*********************************************************************************


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageEventHome;
        TextView textViewEventTitle;
        TextView textViewEventDescription;
        ImageView eventImageViewShare;
        ImageView eventImageViewFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageEventHome = itemView.findViewById(R.id.imageEventHome);
            textViewEventTitle = itemView.findViewById(R.id.textTitle);
            textViewEventDescription = itemView.findViewById(R.id.textDescription);
            eventImageViewShare = itemView.findViewById(R.id.eventImageViewShare);
            eventImageViewFavorite = itemView.findViewById(R.id.eventImageViewFavorite);
        }

        private void bind(Event event) {

            //Verifica Favoritos
            if (event.isFavorite()) {
                eventImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
            } else {
                eventImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);
            }

            if (event.getThumbnail().getPath() != null && event.getThumbnail().getExtension() != null) {
                Picasso.get().load(event.getThumbnail().getPath() + "/portrait_incredible." +
                        event.getThumbnail().getExtension())
                        .placeholder(R.drawable.ic_logo_marvel)
                        .error(R.drawable.ic_logo_marvel)
                        .into(imageEventHome);
            }

            if (event.getTitle() != null) {
                textViewEventTitle.setText(event.getTitle());
            } else {
                textViewEventTitle.setText("");
            }

            if (event.getDescription() != null) {
                textViewEventDescription.setText(event.getDescription());
            } else {
                textViewEventDescription.setText("");
            }
        }
    }

    public void clear() {
        this.events.clear();
        notifyDataSetChanged();
    }

    public void update(List<Event> eventList) {
        this.events = eventList;
        notifyDataSetChanged();
    }

    public void adicionaFavoritosUsuario(Event eventFavorite) {

        eventFavorite.setEventFavorito("event");

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference eventReference = usuarioReference.child("favoritos").child("event");

        usuarioReference
                .child("favoritos")
                .child("event")
                .child(eventFavorite.getId())
                .setValue(eventFavorite);
    }

    public void removeFavoritosUsuario(Event eventFavorite) {

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference eventReference = usuarioReference.child("favoritos").child("event");

        usuarioReference
                .child("favoritos")
                .child("event")
                .child(eventFavorite.getId())
                .removeValue();
    }
}

