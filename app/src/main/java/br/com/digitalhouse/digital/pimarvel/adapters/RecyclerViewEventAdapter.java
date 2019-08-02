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


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageEventHome;
        TextView textViewEventTitle;
        TextView textViewEventDescription;
        ImageView eventImageViewShare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageEventHome = itemView.findViewById(R.id.imageEventHome);
            textViewEventTitle = itemView.findViewById(R.id.textTitle);
            textViewEventDescription = itemView.findViewById(R.id.textDescription);
            eventImageViewShare = itemView.findViewById(R.id.eventImageViewShare);
        }

        private void bind(Event event) {

            if (event.getThumbnail().getPath() != null && event.getThumbnail().getExtension() != null) {
                Picasso.get().load(event.getThumbnail().getPath() + "/portrait_incredible." +
                        event.getThumbnail().getExtension())
                        .placeholder(R.drawable.ic_logo_marvel)
                        .error(R.drawable.ic_logo_marvel)
                        .into(imageEventHome);
            }

            if (event.getTitle() != null) {
                textViewEventTitle.setText(event.getTitle());
            }

            if (event.getDescription() != null) {
                textViewEventDescription.setText(event.getDescription());
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
}

