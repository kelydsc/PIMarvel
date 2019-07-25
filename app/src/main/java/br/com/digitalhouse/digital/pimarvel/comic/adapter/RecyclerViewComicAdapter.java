package br.com.digitalhouse.digital.pimarvel.comic.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.comic.listener.RecyclerViewComicListener;
import br.com.digitalhouse.digital.pimarvel.comic.model.Comic;

public class RecyclerViewComicAdapter extends RecyclerView.Adapter<RecyclerViewComicAdapter.ViewHolder> {

    private List<Comic> comics;
    private RecyclerViewComicListener listener;

    public RecyclerViewComicAdapter(List<Comic> avatars, RecyclerViewComicListener listener) {
        this.comics = comics;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comic_recyclerview_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Comic comic = comics.get(i);
        viewHolder.bind(comic);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(comic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView recyclerImageViewComic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerImageViewComic = itemView.findViewById(R.id.recyclerImageViewComic);
        }

        public void bind(Comic comic) {
            recyclerImageViewComic.setImageDrawable(ContextCompat.getDrawable(recyclerImageViewComic.getContext(), comic.getImage()));
        }
    }
}





