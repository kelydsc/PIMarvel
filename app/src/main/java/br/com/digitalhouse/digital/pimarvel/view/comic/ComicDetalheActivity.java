package br.com.digitalhouse.digital.pimarvel.view.comic;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.view.base.BaseActivity;

public class ComicDetalheActivity extends AppCompatActivity {

    private ImageView imageHero;
    private Comic comic;
    private TextView textTitle;
    private TextView textViewDescription;
    private TextView textViewPublished;
    private ImageView comicImageViewShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detalhe);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializa as views que serão utilizadas na activity
        initViews();

        // Pegamos o quadrinho que que foi clicado na lista anterior
        comic = getIntent().getParcelableExtra("comic");

        // Pegamos o nome da transição para fazer a animação
        String transitionName = getIntent().getStringExtra("transitionName");
        imageHero.setTransitionName(transitionName);

        // Configuramos nas view os valores do quadrinho que pegamos

        if (comic.getTitle() != null) {
            textTitle.setText(comic.getTitle());
        }

        if (comic.getDescription() != null) {
            textViewDescription.setText(Html.fromHtml(comic.getDescription()));
        }

        if (comic.getThumbnail().getPath() != null && comic.getThumbnail().getExtension() != null) {
            Picasso.get().load(comic.getThumbnail().getPath() + "/portrait_incredible." +
                    comic.getThumbnail().getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageHero);
        }

        /*
        if (comic.getImages() != null) {
            Picasso.get().load(comic.getImages().get(0).getPath() + "/portrait_incredible." +
                    comic.getImages().get(0).getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageHero);
        }
        */

        // Mudadamos a forma de mostrar a data DE '2007-10-31 00:00:00' para 'qua, 31 out 2007'
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
            SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
            Date date = formatDate.parse(comic.getDates().get(0).getDate());
            String dateString = format.format(date);
            textViewPublished.setText(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Metodo para acessar os aplicativos de compartilhamento de dados
        compartilharComic();
    }

    private void compartilharComic() {

        comicImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Sharing comic:" + "\n" +
                        "\nComic: " + comic.getTitle() + "\n" +
                        "\nImage: " + comic.getThumbnail().getPath() + "/portrait_incredible."
                        + comic.getThumbnail().getExtension());

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
        textViewDescription = findViewById(R.id.textDescription);
        textViewPublished = findViewById(R.id.textViewPublished);
        comicImageViewShare = findViewById(R.id.comicImageViewShare);
    }
}
