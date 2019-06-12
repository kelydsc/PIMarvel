package br.com.digitalhouse.digital.pimarvel.menu.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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
        faqs.add(new Faq("How do I choose a movie?", "Clicar em Filmes na barra de navegação embaixo > Escolher uma das capas > Ponto! Já tem todos os dados do filme selecionado."));
        faqs.add(new Faq("How do I choose a HQ?", "Clicar em HQs na barra de navegação embaixo > Escolher uma das capas > Ponto! Já tem todos os dados do HQ selecionado."));
        faqs.add(new Faq("How do I choose an avatar and save on preference?", "Clicar no menu superior > Escolher Settings > Ponto! Escolha o seu herói!"));
        faqs.add(new Faq("How to play the game?", "Clicar em Game na barra de navegação embaixo > Escolher um herói ou vilão na barra abaixo dos quadros de luta > Ponto! Agora derrote o computador!"));
        return faqs;
    }

    @Override
    public void onClick(Faq faq) {
        Intent intent = new Intent(this, FaqDetailActivity.class);
        intent.putExtra("FAQ", faq);
        startActivity(intent);
    }
}
