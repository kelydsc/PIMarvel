package br.com.digitalhouse.digital.pimarvel.game.view;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

import br.com.digitalhouse.digital.pimarvel.R;

public class GameDetalheActivity extends AppCompatActivity {



    //Declaração das variaveis para calcular a pontuação e o numero de jogadas
    private int pontuacaoApp = 0;
    private int pontuacaoUsuario = 0;
    private int pontuacaoEmpate = 0;
    private int numeroJogadas = 0;

    private ImageView imageViewAppGame;
    private ImageView imageViewUserGame;

    private TextView textViewTituloVitoria;
    private TextView textViewTituloEmpate;
    private TextView textViewTituloPerdedor;
    private TextView textViewTituloQteJogadas;

    private ImageView imageViewHeroi01;
    private ImageView imageViewHeroi02;
    private ImageView imageViewHeroi03;

    private ImageView imageViewVilao01;
    private ImageView imageViewVilao02;
    private ImageView imageViewVilao03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detalhe);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Marvel Universe Comic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicialização das views
        initViews();

    }

    private void selecionarHeroi01(View view) {
        this.opcaoSelecionada("heroi01");
    }

    private void selecionarHeroi02(View view) {
        this.opcaoSelecionada("heroi02");
    }

    private void selecionarHeroi03(View view) {
        this.opcaoSelecionada("heroi03");
    }

    private void selecionarVilao01(View view) {
        this.opcaoSelecionada("vilao01");
    }

    private void selecionarVilao02(View view) {
        this.opcaoSelecionada("vilao02");
    }

    private void selecionarVilao03(View view) {
        this.opcaoSelecionada("vilao03");
    }

    //realiza o tratamento da validação da imagem selecionada
    public void opcaoSelecionada(String escolhaUsuario) {

        /*
        //Cast converte tipo de dados. Exemplo: converte o objeto View (R.id.imageResultadoId)
        // para ImageView (ImageView)
        ImageView imagemResultado = (ImageView) findViewById(R.id.imageResultadoId);

        //Será alterado conforme o ganhador: "App" ou "Usuário"
        TextView textoResultado = (TextView) findViewById(R.id.tvResultadoId);

        //Irá retornar a pontuação do App
        TextView textoPontosApp = (TextView) findViewById(R.id.tvPontosAppId);

        //Irá retornar a poontuação do Usuário
        TextView textoPontosUsuario = (TextView) findViewById(R.id.tvPontosUsuarioId);

        //Irá retornar a pontuação do Empate entre App e Usuário
        TextView textoPontosEmpate = (TextView) findViewById(R.id.tvPontosEmpateId);

        //Irá retornar oa quantidade de jogadas
        TextView textoNumeroJogadas = (TextView) findViewById(R.id.tvNumeroJoagadasId);
        */

        //Array
        String[] opcoes = {"heroi01", "heroi02", "heroi03", "vilao01", "vilao02", "vilao03"};

        //gerar numero inteiro aleatorio para escolha do App que 0 até 5 ele gera o proximo inteiro
        int numero = new Random().nextInt(6);
        String escolhaApp = opcoes[numero];

        switch (escolhaApp) {
            case "heroi01":
                //metodo que configura uma Imagem(setImageResource) para o objeto imagem.Resultado
                imageViewAppGame.setImageResource(R.drawable.ic_personagem_game_groot);
                break;
            case "heroi02":
                imageViewAppGame.setImageResource(R.drawable.ic_personagem_game_thor);
                break;
            case "heroi03":
                imageViewAppGame.setImageResource(R.drawable.ic_personagem_game_pantera_negra);
                break;
            case "vilao01":
                imageViewAppGame.setImageResource(R.drawable.ic_personagem_game_thanos);
                break;
            case "vilao02":
                imageViewAppGame.setImageResource(R.drawable.ic_personagem_game_loki);
                break;
            case "vilao03":
                imageViewAppGame.setImageResource(R.drawable.ic_personagem_game_erik);
                break;
        }

        //calcula quantidade de jogadas
        numeroJogadas = numeroJogadas + 1;

        if (
                (escolhaApp == "heroi01" && escolhaUsuario == "vilao01") ||
                        (escolhaApp == "heroi01" && escolhaUsuario == "vilao02") ||
                        (escolhaApp == "heroi01" && escolhaUsuario == "vilao03") ||

                        (escolhaApp == "heroi02" && escolhaUsuario == "vilao01") ||
                        (escolhaApp == "heroi02" && escolhaUsuario == "vilao02") ||
                        (escolhaApp == "heroi02" && escolhaUsuario == "vilao03") ||

                        (escolhaApp == "heroi03" && escolhaUsuario == "vilao01") ||
                        (escolhaApp == "heroi03" && escolhaUsuario == "vilao02") ||
                        (escolhaApp == "heroi03" && escolhaUsuario == "vilao03") ||

                        (escolhaApp == "vilao01" && escolhaUsuario == "heroi01") ||
                        (escolhaApp == "vilao01" && escolhaUsuario == "heroi02") ||
                        (escolhaApp == "vilao01" && escolhaUsuario == "heroi03") ||

                        (escolhaApp == "vilao02" && escolhaUsuario == "heroi01") ||
                        (escolhaApp == "vilao02" && escolhaUsuario == "heroi02") ||
                        (escolhaApp == "vilao02" && escolhaUsuario == "heroi03") ||

                        (escolhaApp == "vilao03" && escolhaUsuario == "heroi01") ||
                        (escolhaApp == "vilao03" && escolhaUsuario == "heroi02") ||
                        (escolhaApp == "vilao03" && escolhaUsuario == "heroi03")
        ) {//App é ganhador

            //envia o TextView de acordo o resultado da validação do IF
            //textoResultado.setText("Você perdeu :( ");

            //calcula pontuação App
            pontuacaoApp = pontuacaoApp + 1;

        } else if (
                        (escolhaApp == "heroi01" && escolhaUsuario == "heroi02") ||
                        (escolhaApp == "heroi01" && escolhaUsuario == "heroi03") ||

                        (escolhaApp == "heroi02" && escolhaUsuario == "heroi01") ||
                        (escolhaApp == "heroi02" && escolhaUsuario == "heroi03") ||

                        (escolhaApp == "heroi03" && escolhaUsuario == "heroi01") ||
                        (escolhaApp == "heroi03" && escolhaUsuario == "heroi02") ||

                        (escolhaApp == "vilao01" && escolhaUsuario == "vilao02") ||
                        (escolhaApp == "vilao01" && escolhaUsuario == "vilao03") ||

                        (escolhaApp == "vilao02" && escolhaUsuario == "vilao01") ||
                        (escolhaApp == "vilao02" && escolhaUsuario == "vilao03") ||

                        (escolhaApp == "vilao03" && escolhaUsuario == "vilao01") ||
                        (escolhaApp == "vilao03" && escolhaUsuario == "vilao02")
        ) {//Usuario é ganhador

            //envia o TextView de acordo o resultado da validação do IF
            //textoResultado.setText("Você ganhou :) ");

            //calcula pontuação Usuário
            pontuacaoUsuario = pontuacaoUsuario + 1;

        } else {//Empate
            //envia o TextView de acordo o resultado da validação do IF
            //textoResultado.setText("Empatamos ;) ");

            //calcula pontuação Empate
            pontuacaoEmpate = pontuacaoEmpate + 1;
        }

        //envia o TextView com a pontuação do App
        textViewTituloPerdedor.setText("Pontuação do App: " + pontuacaoApp);

        //envia o TextView com a pontuação do Usuário
        textViewTituloVitoria.setText("Pontuação do Usuario: " + pontuacaoUsuario);

        //envia o TextView com a pontuação do Empate
        textViewTituloEmpate.setText("Pontuação do Empate: " + pontuacaoEmpate);

        //envia o TextView com o Numero de Jogadas
        textViewTituloQteJogadas.setText("Numero de Jogadas: " + numeroJogadas);
    }

    private void initViews() {

        textViewTituloVitoria = findViewById(R.id.textViewTituloVitoria);
        textViewTituloEmpate = findViewById(R.id.textViewTituloEmpate);
        textViewTituloPerdedor = findViewById(R.id.textViewTituloPerdedor);
        textViewTituloQteJogadas = findViewById(R.id.textViewTituloQteJogadas);

        imageViewAppGame = findViewById(R.id.imageViewAppGame);
        imageViewUserGame = findViewById(R.id.imageViewUserGame);

        imageViewHeroi01 = findViewById(R.id.imageViewHeroi01);
        imageViewHeroi02 = findViewById(R.id.imageViewHeroi02);
        imageViewHeroi03 = findViewById(R.id.imageViewHeroi03);

        imageViewVilao01 = findViewById(R.id.imageViewVilao01);
        imageViewVilao02 = findViewById(R.id.imageViewVilao02);
        imageViewVilao03 = findViewById(R.id.imageViewVilao03);
    }


}
