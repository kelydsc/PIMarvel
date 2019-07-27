package br.com.digitalhouse.digital.pimarvel.game.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

}
