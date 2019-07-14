package br.com.digitalhouse.digital.pimarvel.event.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.event.listener.RecyclerViewEventClickListener;
import br.com.digitalhouse.digital.pimarvel.event.model.Event;

public class RecyclerViewEventAdapter extends RecyclerView.Adapter<RecyclerViewEventAdapter.ViewHolder> {

    private List<Event> events;
    private RecyclerViewEventClickListener listener;

    public RecyclerViewEventAdapter(List<Event> events, RecyclerViewEventClickListener listener) {
        this.events = events;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_recyclerview_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Event event = events.get(i);
        viewHolder.bind(event);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void update(List<Event> eventList){
        this.events = eventList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView eventImageViewThumbnail;
        private TextView eventTextViewTitle;
        private TextView eventTextViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventImageViewThumbnail = itemView.findViewById(R.id.eventImageViewThumbnail);
            eventTextViewTitle = itemView.findViewById(R.id.eventTextViewTitle);
            eventTextViewDescription = itemView.findViewById(R.id.eventTextViewDescription);
        }

        public void bind(Event event) {
            eventImageViewThumbnail.setImageDrawable(ContextCompat.getDrawable(eventImageViewThumbnail.getContext(), event.getThumbnail()));
            eventTextViewTitle.setText(event.getTitle());
            eventTextViewDescription.setText(event.getDescription());
        }
    }
}
