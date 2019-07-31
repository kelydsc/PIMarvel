package br.com.digitalhouse.digital.pimarvel.game.view;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.Random;

import br.com.digitalhouse.digital.pimarvel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

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

        /*
        imageViewHeroi01 = view.findViewById(R.id.imageViewHeroi01);
        imageViewHeroi02 = view.findViewById(R.id.imageViewHeroi02);
        imageViewHeroi03 = view.findViewById(R.id.imageViewHeroi03);

        imageViewVilao01 = view.findViewById(R.id.imageViewVilao01);
        imageViewVilao02 = view.findViewById(R.id.imageViewVilao02);
        imageViewVilao03 = view.findViewById(R.id.imageViewVilao03);

        imageViewHeroi01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentHeroi01 = new Intent(getContext(), GameDetalheActivity.class);

                //intentLogin.putExtra("TELA", "LOGIN");

                startActivity(intentHeroi01);
            }
        });

        imageViewHeroi02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentHeroi02 = new Intent(getContext(), GameDetalheActivity.class);

                //intentLogin.putExtra("TELA", "LOGIN");

                startActivity(intentHeroi02);
            }
        });

        imageViewHeroi03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentHeroi03 = new Intent(getContext(), GameDetalheActivity.class);

                //intentLogin.putExtra("TELA", "LOGIN");

                startActivity(intentHeroi03);
            }
        });

        imageViewVilao01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVilao01 = new Intent(getContext(), GameDetalheActivity.class);

                //intentLogin.putExtra("TELA", "LOGIN");

                startActivity(intentVilao01);
            }
        });

        imageViewVilao02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVilao02 = new Intent(getContext(), GameDetalheActivity.class);

                //intentLogin.putExtra("TELA", "LOGIN");

                startActivity(intentVilao02);
            }
        });

        imageViewVilao03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVilao03 = new Intent(getContext(), GameDetalheActivity.class);

                //intentLogin.putExtra("TELA", "LOGIN");

                startActivity(intentVilao03);
            }
        });

*/
        return view;
    }

}
