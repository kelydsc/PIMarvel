package br.com.digitalhouse.digital.pimarvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recebe a chamada da tela de Cadastro
        Intent intent = getIntent();



    }
}
