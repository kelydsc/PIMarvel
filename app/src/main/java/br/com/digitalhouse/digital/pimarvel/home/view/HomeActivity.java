package br.com.digitalhouse.digital.pimarvel.home.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.digitalhouse.digital.pimarvel.R;

public class HomeActivity extends AppCompatActivity {

    //Declaração de atributos
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Configura a ToolBar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Marvel Universe");
        setSupportActionBar(toolbar);

        //Configura a BottomNavigationBar e seta o listener dos botões
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_bar_view);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Recebe a chamada da tela de Login
        Intent intent = getIntent();
    }

    //Define as ações de cada botão do NavigationBar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_game:
                    return true;
                case R.id.navigation_movie:
                    return true;
                case R.id.navigation_hq:
                    return true;
                case R.id.navigation_news:
                    return true;
                case R.id.navigation_favorite:
                    return true;
            }
            return false;
        }
    };
}
