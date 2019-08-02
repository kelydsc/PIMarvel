package br.com.digitalhouse.digital.pimarvel.view.serie;

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
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;

public class SerieDetalheActivity extends AppCompatActivity {

    private ImageView imageHero;
    private Serie serie;
    private TextView textTitle;
    private TextView textViewRating;
    private TextView textViewStartYear;
    private TextView textViewEndYear;
    private ImageView serieImageViewShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detalhe);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializa as views que serão utilizadas na activity
        initViews();

        // Pegamos o quadrinho que que foi clicado na lista anterior
        serie = getIntent().getParcelableExtra("serie");

        // Pegamos o nome da transição para fazer a animação
        String transitionName = getIntent().getStringExtra("transitionName");
        imageHero.setTransitionName(transitionName);

        // Configuramos nas view os valores do quadrinho que pegamos

        if (serie.getTitle() != null) {
            textTitle.setText(serie.getTitle());
        }

        if (serie.getRating() != null) {
            textViewRating.setText(Html.fromHtml(serie.getRating()));
        }

        if (serie.getStartYear() != null) {
            textViewStartYear.setText(serie.getStartYear());
        }

        if (serie.getEndYear() != null) {
            textViewEndYear.setText(serie.getEndYear());
        }

        if (serie.getThumbnail().getPath() != null && serie.getThumbnail().getExtension() != null) {
            Picasso.get().load(serie.getThumbnail().getPath() + "/portrait_incredible." +
                    serie.getThumbnail().getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageHero);
        }

        //Metodo para acessar os aplicativos de compartilhamento de dados
        compartilharSerie();
    }

    private void compartilharSerie() {

        serieImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Sharing serie:" + "\n" +
                        "\nSerie: " + serie.getTitle() + "\n" +
                        "\nImage: " + serie.getThumbnail().getPath() + "/portrait_incredible."
                        + serie.getThumbnail().getExtension());

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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // one inherited from android.support.v4.app.FragmentActivity

        return false;
    }

    private void initViews() {
        imageHero = findViewById(R.id.iconDetail);
        textTitle = findViewById(R.id.textTitle);
        textViewRating = findViewById(R.id.textViewRating);
        textViewStartYear = findViewById(R.id.textViewStartYear);
        textViewEndYear = findViewById(R.id.textViewEndYear);
        serieImageViewShare = findViewById(R.id.serieImageViewShare);
    }
}
