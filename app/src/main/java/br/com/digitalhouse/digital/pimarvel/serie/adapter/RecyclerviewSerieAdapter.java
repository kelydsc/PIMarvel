package br.com.digitalhouse.digital.pimarvel.serie.adapter;

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
import br.com.digitalhouse.digital.pimarvel.serie.model.Result;
import br.com.digitalhouse.digital.pimarvel.serie.view.SerieDetalheActivity;

public class RecyclerviewSerieAdapter extends RecyclerView.Adapter<RecyclerviewSerieAdapter.ViewHolder> {

    private List<Result> results;

    public RecyclerviewSerieAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public RecyclerviewSerieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serie_recyclerview_item, parent, false);

        return new RecyclerviewSerieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewSerieAdapter.ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.bind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String transitionName = "image" + position;
                Intent intent = new Intent(holder.itemView.getContext(), SerieDetalheActivity.class);
                intent.putExtra("serie", result);
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
        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageSerieHome;
        TextView textViewSerieTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSerieHome = itemView.findViewById(R.id.imageSerieHome);
            textViewSerieTitle = itemView.findViewById(R.id.textTitle);

        }


        private void bind(Result result) {
            Picasso.get().load(result.getThumbnail().getPath() + "/portrait_incredible." + result.getThumbnail().getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageSerieHome);

            textViewSerieTitle.setText(result.getTitle());
        }
    }

    public void clear() {
        this.results.clear();
        notifyDataSetChanged();
    }

    public void update(List<Result> resultList) {
        this.results = resultList;
        notifyDataSetChanged();
    }
}

