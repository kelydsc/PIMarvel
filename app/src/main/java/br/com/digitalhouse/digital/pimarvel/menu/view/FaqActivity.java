package br.com.digitalhouse.digital.pimarvel.menu.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.menu.adapter.RecyclerViewFaqAdapter;
import br.com.digitalhouse.digital.pimarvel.menu.listener.RecyclerViewFaqClickListener;
import br.com.digitalhouse.digital.pimarvel.menu.model.Faq;

public class FaqActivity extends AppCompatActivity implements RecyclerViewFaqClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewFaqAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Para listar dinâmicamente todas as perguntas que possam vir a existir,
        coloquei um Recycler View listando todas elas e a resposta é mostrada no detalhe quando selecionada*/
        recyclerView = findViewById(R.id.faqRecyclerViewFaqs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewFaqAdapter(getQuestions(), this);
        recyclerView.setAdapter(adapter);

    }

    private List<Faq> getQuestions() {
        List<Faq> faqs = new ArrayList<>();
        faqs.add(new Faq("How do I choose a Series?", "1-Click on the phone icon in the bottom navigation bar.                                                               2-Click the series you would like to know more about."));
        faqs.add(new Faq("How do I choose a HQ?", "1-Click on HQs in the bottom navigation bar.             2-Pick the HQ you would like to know more about."));
        faqs.add(new Faq("How do I favorite a Event, HQ or Series?", "1-Click on the item you would like to favorite.      2- Now you just have to click on the heart icon.       3-If you would like to see all your favorites, click on the favorite tab on the bottom navigation bar."));
        faqs.add(new Faq("How to play the game?", "1-Click on Game in the bottom navigation bar.        2-Now you are ready to beat the computer."));
        return faqs;
    }

    @Override
    public void onClick(Faq faq) {
        Intent intent = new Intent(this, FaqDetailActivity.class);
        intent.putExtra("FAQ", faq);
        startActivity(intent);
    }
}
