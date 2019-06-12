package br.com.digitalhouse.digital.pimarvel.menu.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.menu.model.Faq;

public class FaqDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_detail);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView faqDetailsTextViewQuestion = findViewById(R.id.faqDetailsTextViewQuestion);
        TextView faqDetailsTextViewAnswer = findViewById(R.id.faqDetailsTextViewAnswer);

        if (getIntent() != null && getIntent().getExtras() != null) {
            Faq faq = getIntent().getParcelableExtra("FAQ");

            if (faq != null) {
                faqDetailsTextViewQuestion.setText(faq.getQuestion());
                faqDetailsTextViewAnswer.setText(faq.getAnswer());
            }
        }
    }
}
