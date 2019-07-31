package br.com.digitalhouse.digital.pimarvel.comic.adapter;

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
import br.com.digitalhouse.digital.pimarvel.comic.model.Result;
import br.com.digitalhouse.digital.pimarvel.comic.view.ComicDetalheActivity;

public class RecyclerviewComicAdapter extends RecyclerView.Adapter<RecyclerviewComicAdapter.ViewHolder> {

    private List<Result> results;

    public RecyclerviewComicAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.bind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String transitionName = "image" + position;
                Intent intent = new Intent(holder.itemView.getContext(), ComicDetalheActivity.class);
                intent.putExtra("comic", result);
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
        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageComicHome;
        TextView textViewComicTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageComicHome = itemView.findViewById(R.id.imageComicHome);
            textViewComicTitle = itemView.findViewById(R.id.textTitle);

        }

        private void bind(Result result) {
            Picasso.get().load(result.getThumbnail().getPath() + "/portrait_incredible." + result.getThumbnail().getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageComicHome);

            textViewComicTitle.setText(result.getTitle());
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





