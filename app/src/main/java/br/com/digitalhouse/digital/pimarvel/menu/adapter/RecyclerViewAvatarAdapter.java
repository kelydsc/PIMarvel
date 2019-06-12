package br.com.digitalhouse.digital.pimarvel.menu.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.RecyclerViewAvatarClickListener;
import br.com.digitalhouse.digital.pimarvel.menu.model.Avatar;

public class RecyclerViewAvatarAdapter extends RecyclerView.Adapter<RecyclerViewAvatarAdapter.ViewHolder> {

    private List<Avatar> avatars;
    private RecyclerViewAvatarClickListener listener;

    public RecyclerViewAvatarAdapter(List<Avatar> avatars, RecyclerViewAvatarClickListener listener) {
        this.avatars = avatars;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.settings_avatar_recyclerview_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Avatar avatar = avatars.get(i);
        viewHolder.bind(avatar);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(avatar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewAvatar =itemView.findViewById(R.id.recyclerImageViewAvatar);
        }

        public void bind(Avatar avatar) {
            imageViewAvatar.setImageDrawable(ContextCompat.getDrawable(imageViewAvatar.getContext(), avatar.getImage()));
        }
    }
}
