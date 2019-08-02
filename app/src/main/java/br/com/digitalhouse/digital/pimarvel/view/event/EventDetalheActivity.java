package br.com.digitalhouse.digital.pimarvel.view.event;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.model.event.Event;

public class EventDetalheActivity extends AppCompatActivity {

    private ImageView imageHero;
    private Event event;
    private TextView textTitle;
    private TextView textViewDescription;
    private ImageView eventImageViewShare;

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
        event = getIntent().getParcelableExtra("event");

        // Pegamos o nome da transição para fazer a animação
        String transitionName = getIntent().getStringExtra("transitionName");
        imageHero.setTransitionName(transitionName);

        // Configuramos nas view os valores do quadrinho que pegamos

        if (event.getTitle() != null) {
            textTitle.setText(event.getTitle());
        }

        if (event.getDescription() != null) {
            textViewDescription.setText(Html.fromHtml(event.getDescription()));
        }

        if (event.getThumbnail().getPath() != null && event.getThumbnail().getExtension() != null) {
            Picasso.get().load(event.getThumbnail().getPath() + "/portrait_incredible." +
                    event.getThumbnail().getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageHero);
        }

        //Metodo para acessar os aplicativos de compartilhamento de dados
        compartilharEvento();
    }

    private void compartilharEvento() {

        eventImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Sharing event:" + "\n" +
                        "\nEvent: " + event.getTitle() + "\n" +
                        "\nDescription: " + event.getDescription() + "\n" +
                        "\nImage: " + event.getThumbnail().getPath() + "/portrait_incredible."
                        + event.getThumbnail().getExtension());

                //tipo de compartilhamento
                intentCompartilhar.setType("text/plain");

                //Mostra os aplicativos disponiveis para compartilhamento de dados
                Intent intentChooser = Intent.createChooser(
                        intentCompartilhar, "Compartilhar via:");

                //Start na Activity de compartilhamento
                startActivity(intentChooser);
            }
        });
    }

    private void initViews() {
        imageHero = findViewById(R.id.imageEventHome);
        textTitle = findViewById(R.id.textTitle);
        textViewDescription = findViewById(R.id.textDescription);
        eventImageViewShare = findViewById(R.id.eventImageViewShare);
    }
}