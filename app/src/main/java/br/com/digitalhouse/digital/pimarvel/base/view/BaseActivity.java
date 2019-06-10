package br.com.digitalhouse.digital.pimarvel.base.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.event.view.EventFragment;
import br.com.digitalhouse.digital.pimarvel.favorite.view.FavoriteFragment;
import br.com.digitalhouse.digital.pimarvel.game.view.GameFragment;
import br.com.digitalhouse.digital.pimarvel.hq.view.HqFragment;
import br.com.digitalhouse.digital.pimarvel.movie.view.MovieFragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //Configura a ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configura a BottomNavigationBar e seta o listener dos botões
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_bar_view);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Recebe a chamada da tela de Login
        Intent intent = getIntent();

        //Inicia o container com event fragment
        initFirstFragment();
    }

    //Define o primeiro fragmento que será inflado no cointainer da BaseActivity
    private void initFirstFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new EventFragment())
                .commit();
    }

    //Troca os fragmentos do container
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("FRAGMENTS")
                .commit();
    }

    //Define as ações de cada botão do NavigationBar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_event:
                            replaceFragment(new EventFragment());
                            return true;
                        case R.id.navigation_favorite:
                            replaceFragment(new FavoriteFragment());
                            return true;
                        case R.id.navigation_game:
                            replaceFragment(new GameFragment());
                            return true;
                        case R.id.navigation_movie:
                            replaceFragment(new MovieFragment());
                            return true;
                        case R.id.navigation_hq:
                            replaceFragment(new HqFragment());
                            return true;
                    }
                    return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.upper_right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
