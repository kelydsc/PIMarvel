package br.com.digitalhouse.digital.pimarvel.serie.view;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.serie.model.Result;

public class SerieDetalheActivity extends AppCompatActivity {

    private ImageView imageHero;
    private Result result;
    private TextView textTitle;
    //private TextView textViewDescription;
    private TextView textViewRating;
    private TextView textViewStartYear;
    private TextView textViewEndYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detalhe);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Marvel Universe Comic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializa as views que serão utilizadas na activity
        initViews();

        // Pegamos o quadrinho que que foi clicado na lista anterior
        result = getIntent().getParcelableExtra("serie");

        // Pegamos o nome da transição para fazer a animação
        String transitionName = getIntent().getStringExtra("transitionName");
        imageHero.setTransitionName(transitionName);

        // Configuramos nas view os valores do quadrinho que pegamos
        textTitle.setText(result.getTitle());

        textViewRating.setText(Html.fromHtml(result.getRating()));

        textViewStartYear.setText(result.getStartYear());

        textViewEndYear.setText(result.getEndYear());

        //textViewDescription.setText(Html.fromHtml(result.getDescription()));

        /*
        Picasso.get().load(result.getThumbnail().getPath() + "/portrait_incredible." + result.getThumbnail().getExtension())
                .placeholder(R.drawable.ic_logo_marvel)
                .error(R.drawable.ic_logo_marvel)
                .into(imageHero);
                *///voltar

        /*
        if (!result.getImages().isEmpty()) {
            Picasso.get().load(result.getImages().get(0).getPath() + "/portrait_incredible." + result.getImages().get(0).getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageHero);
        }
        */

        /*
        // Mudadamos a forma de mostrar a data DE '2007-10-31 00:00:00' para 'qua, 31 out 2007'
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
            SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
            Date date = formatDate.parse(result.getDates().get(0).getDate());
            String dateString = format.format(date);
            textViewPublished.setText(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
    }

    private void initViews() {
        imageHero = findViewById(R.id.iconDetail);
        textTitle = findViewById(R.id.textTitle);
        //textViewDescription = findViewById(R.id.textDescription);
        textViewRating = findViewById(R.id.textViewRating);
        textViewStartYear = findViewById(R.id.textViewStartYear);
        textViewEndYear = findViewById(R.id.textViewEndYear);
    }
}
