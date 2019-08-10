package br.com.digitalhouse.digital.pimarvel.view.menu;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.model.faq.Faq;

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
