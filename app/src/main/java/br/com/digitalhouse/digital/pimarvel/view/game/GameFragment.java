package br.com.digitalhouse.digital.pimarvel.view.game;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

import br.com.digitalhouse.digital.pimarvel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    //Declaração das variaveis para calcular a pontuação e o numero de jogadas
    private int pontuacaoApp = 0;
    private int pontuacaoUsuario = 0;
    private int pontuacaoEmpate = 0;
    private int numeroJogadas = 0;

    private ImageView imageViewAppGame;
    private ImageView imageViewUserGame;

    private TextView textViewTituloPerguntaGame;

    private TextView textViewPontosApp;
    private TextView textViewPontosUser;
    private TextView textViewPontosEmpate;
    private TextView textViewPontosQtdeJogadas;

    private ImageView imageViewHeroi01;
    private ImageView imageViewHeroi02;
    private ImageView imageViewHeroi03;

    private ImageView imageViewVilao01;
    private ImageView imageViewVilao02;
    private ImageView imageViewVilao03;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        //Inicialização das views
        initViews(view);

        imageViewHeroi01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opcaoSelecionada("heroi01");

                imageViewUserGame.setImageResource(R.drawable.ic_personagem_game_groot);
            }
        });

        imageViewHeroi02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opcaoSelecionada("heroi02");

                imageViewUserGame.setImageResource(R.drawable.ic_personagem_game_thor);
            }
        });

        imageViewHeroi03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opcaoSelecionada("heroi03");

                imageViewUserGame.setImageResource(R.drawable.ic_personagem_game_pantera_negra);
            }
        });

        imageViewVilao01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opcaoSelecionada("vilao01");

                imageViewUserGame.setImageResource(R.drawable.ic_personagem_game_thanos);
            }
        });

        imageViewVilao02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opcaoSelecionada("vilao02");

                imageViewUserGame.setImageResource(R.drawable.ic_personagem_game_loki);
            }
        });

        imageViewVilao03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opcaoSelecionada("vilao03");

                imageViewUserGame.setImageResource(R.drawable.ic_personagem_game_erik);
            }
        });

        return view;
    }

    //realiza o tratamento da validação da imagem selecionada
    public void opcaoSelecionada(String escolhaUsuario) {

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

        //App é o ganhador
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
        ) {
            //App é o ganhador
            //envia o TextView de acordo o resultado da validação do IF
            textViewTituloPerguntaGame.setText("The app has won!");

            //calcula pontuação App
            pontuacaoApp = pontuacaoApp + 1;

            //Usuario é o ganhador
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
        ) {

            //Usuario é o ganhador
            //envia o TextView de acordo o resultado da validação do IF
            textViewTituloPerguntaGame.setText("Congratulations!");

            //calcula pontuação Usuário
            pontuacaoUsuario = pontuacaoUsuario + 1;

        } else {

            //Empate
            //envia o TextView de acordo o resultado da validação do IF
            textViewTituloPerguntaGame.setText("No one won!");

            //calcula pontuação Empate
            pontuacaoEmpate = pontuacaoEmpate + 1;
        }

        //envia o TextView com a pontuação do App
        textViewPontosApp.setText(" " + pontuacaoApp);

        //envia o TextView com a pontuação do Usuário
        textViewPontosUser.setText(" " + pontuacaoUsuario);

        //envia o TextView com a pontuação do Empate
        textViewPontosEmpate.setText(" " + pontuacaoEmpate);

        //envia o TextView com o Numero de Jogadas
        textViewPontosQtdeJogadas.setText(" " + numeroJogadas);
    }

    private void initViews(View view) {

        imageViewAppGame = view.findViewById(R.id.imageViewAppGame);
        imageViewUserGame = view.findViewById(R.id.imageViewUserGame);
        textViewTituloPerguntaGame = view.findViewById(R.id.textViewTituloPerguntaGame);

        textViewPontosApp = view.findViewById(R.id.textViewPontosApp);
        textViewPontosUser = view.findViewById(R.id.textViewPontosUser);
        textViewPontosEmpate = view.findViewById(R.id.textViewPontosEmpate);
        textViewPontosQtdeJogadas = view.findViewById(R.id.textViewPontosQtdeJogadas);

        imageViewHeroi01 = view.findViewById(R.id.imageViewHeroi01);
        imageViewHeroi02 = view.findViewById(R.id.imageViewHeroi02);
        imageViewHeroi03 = view.findViewById(R.id.imageViewHeroi03);

        imageViewVilao01 = view.findViewById(R.id.imageViewVilao01);
        imageViewVilao02 = view.findViewById(R.id.imageViewVilao02);
        imageViewVilao03 = view.findViewById(R.id.imageViewVilao03);

        pontuacaoApp = 0;
        pontuacaoUsuario = 0;
        pontuacaoEmpate = 0;
        numeroJogadas = 0;
    }
}
