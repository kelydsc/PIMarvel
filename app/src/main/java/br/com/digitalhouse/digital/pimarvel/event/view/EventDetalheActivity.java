package br.com.digitalhouse.digital.pimarvel.event.view;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.event.model.Result;

public class EventDetalheActivity extends AppCompatActivity {

    private ImageView imageHero;
    private Result result;
    private TextView textTitle;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detalhe);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializa as views que serão utilizadas na activity
        initViews();

        // Pegamos o quadrinho que que foi clicado na lista anterior
        result = getIntent().getParcelableExtra("event");

        // Pegamos o nome da transição para fazer a animação
        String transitionName = getIntent().getStringExtra("transitionName");
        imageHero.setTransitionName(transitionName);

        // Configuramos nas view os valores do quadrinho que pegamos
        textTitle.setText(result.getTitle());
        textViewDescription.setText(Html.fromHtml(result.getDescription()));

        Picasso.get().load(result.getThumbnail().getPath() + "/portrait_incredible." + result.getThumbnail().getExtension())
                .placeholder(R.drawable.ic_logo_marvel)
                .error(R.drawable.ic_logo_marvel)
                .into(imageHero);
    }

    private void initViews() {
        imageHero = findViewById(R.id.imageEventHome);
        textTitle = findViewById(R.id.textTitle);
        textViewDescription = findViewById(R.id.textDescription);
    }
}