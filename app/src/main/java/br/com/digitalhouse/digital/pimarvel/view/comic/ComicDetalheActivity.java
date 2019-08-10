package br.com.digitalhouse.digital.pimarvel.view.comic;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.FavoriteDAO;
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;

public class ComicDetalheActivity extends AppCompatActivity {

    private ImageView imageHero;
    private Comic comic;
    private TextView textTitle;
    private TextView textViewDescription;
    private TextView textViewPublished;
    private ImageView comicImageViewShare;
    private ImageView comicImageViewFavorite;

    //Declaração da tabela de favoritos
    private Favorite favorite;
    private FavoriteDAO favoriteDAO;


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

        //Favoritos
        if (comic.isFavorite()) {
            comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
        } else {
            comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);
        }

        // Pegamos o nome da transição para fazer a animação
        String transitionName = getIntent().getStringExtra("transitionName");
        imageHero.setTransitionName(transitionName);

        // Configuramos nas view os valores do quadrinho que pegamos
        if (comic.getTitle() != null) {
            textTitle.setText(comic.getTitle());
        } else {
            textTitle.setText("");
        }

        if (comic.getDescription() != null) {
            textViewDescription.setText(Html.fromHtml(comic.getDescription()));
        } else {
            textViewDescription.setText("");
        }

        if (comic.getThumbnail().getPath() != null && comic.getThumbnail().getExtension() != null) {
            Picasso.get().load(comic.getThumbnail().getPath() + "/portrait_incredible." +
                    comic.getThumbnail().getExtension())
                    .placeholder(R.drawable.ic_logo_marvel)
                    .error(R.drawable.ic_logo_marvel)
                    .into(imageHero);
        }

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

        //Metodo para adicionar o comic como favorite
        adicionarComicFavovito();
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

    private void adicionarComicFavovito() {

        comicImageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Inverte opção do favoritos na tela
                comic.setFavorite(!comic.isFavorite());

                if (comic.isFavorite()) {

                    comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);

                    adicionaFavoritosUsuario(comic);


                } else {

                    comicImageViewFavorite.setImageResource(R.drawable.ic_favorite_24dp);

                    //Remove o item no banco de dados
                    removeFavoritosUsuario(comic);
                }
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
        comicImageViewFavorite = findViewById(R.id.comicImageViewFavorite);
    }

    public void adicionaFavoritosUsuario(Comic comicFavorite) {

        comic.setComicFavorito("comic");

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference comicReference = usuarioReference.child("favoritos").child("comic");

        usuarioReference
                .child("favoritos")
                .child("comic")
                .child(comicFavorite.getId())
                .setValue(comicFavorite);
    }

    public void removeFavoritosUsuario(Comic comicFavorite) {

        //Instancia do firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //Referencia
        DatabaseReference usuarioReference = databaseReference.child("tab_usuarios").child("usuario");

        DatabaseReference comicReference = usuarioReference.child("favoritos").child("comic");

        usuarioReference
                .child("favoritos")
                .child("comic")
                .child(comicFavorite.getId())
                .removeValue();
    }
}
