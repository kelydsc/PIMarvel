package br.com.digitalhouse.digital.pimarvel.view.menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.adapters.RecyclerViewFaqAdapter;
import br.com.digitalhouse.digital.pimarvel.interfaces.RecyclerViewFaqClickListener;
import br.com.digitalhouse.digital.pimarvel.model.faq.Faq;

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

        faqs.add(new Faq("How do I choose a Series?",
                "1- Click on the phone icon in the bottom navigation bar." + "\n" +
                        "\n2- Click the series you would like to know more about."));

        faqs.add(new Faq("How do I choose a Comic?",
                "1- Click on Comics in the bottom navigation bar." + "\n" +
                        "\n2- Click the Comic you would like to know more about."));

        faqs.add(new Faq("How do I favorite a Event, Comic or Series?",
                "1- Click on the item you would like to favorite." + "\n" +
                        "\n2- Now you just have to click on the heart icon." + "\n" +
                        "\n3- If you would like to see all your favorites, click on the favorite tab on the" +
                        " bottom navigation bar."));

        faqs.add(new Faq("How to play the game?",
                "1- Click on Game in the bottom navigation bar." + "\n" +
                        "\nThe game is very simple, just choose a character:" + "\n" +
                        "\nWinner: You choose a hero and the app chooses another hero, or you choose a " +
                        "villain and the app another villain." + "\n" +
                        "\nDraw: The app and you choose the same character." + "\n" +
                        "\nLoser: You choose a hero and the app a villain, or you choose a villain and the " +
                        "app a hero." + "\n" +
                        "\n2- Now you are ready to beat the computer."));

        return faqs;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // one inherited from android.support.v4.app.FragmentActivity

        return false;
    }




    @Override
    public void onClick(Faq faq) {
        Intent intent = new Intent(this, FaqDetailActivity.class);
        intent.putExtra("FAQ", faq);
        startActivity(intent);
    }
}
