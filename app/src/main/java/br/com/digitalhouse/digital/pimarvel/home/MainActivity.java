package br.com.digitalhouse.digital.pimarvel.home;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.digitalhouse.digital.pimarvel.R;

public class MainActivity extends AppCompatActivity {

    //Declaração de atributos
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configura a ToolBar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Marvel Universe");
        setSupportActionBar(toolbar);

        //Recebe a chamada da tela de Login
        Intent intent = getIntent();
    }
}
