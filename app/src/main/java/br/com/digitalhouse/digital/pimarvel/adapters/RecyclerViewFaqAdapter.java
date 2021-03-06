package br.com.digitalhouse.digital.pimarvel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.interfaces.RecyclerViewFaqClickListener;
import br.com.digitalhouse.digital.pimarvel.model.faq.Faq;

public class RecyclerViewFaqAdapter extends RecyclerView.Adapter<RecyclerViewFaqAdapter.ViewHolder> {

    private List<Faq> faqs;
    private RecyclerViewFaqClickListener listener;

    public RecyclerViewFaqAdapter(List<Faq> faqs, RecyclerViewFaqClickListener listener) {
        this.faqs = faqs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.faq_recyclerview_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Faq faq = faqs.get(i);
        viewHolder.bind(faq);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(faq);
            }
        });
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recyclerTextViewFaq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerTextViewFaq = itemView.findViewById(R.id.recyclerTextViewFaq);
        }

        public void bind(Faq faq) {
            recyclerTextViewFaq.setText(faq.getQuestion());
        }
    }
}
