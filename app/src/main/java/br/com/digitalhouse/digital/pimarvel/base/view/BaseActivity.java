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
import br.com.digitalhouse.digital.pimarvel.comic.view.ComicFragment;
import br.com.digitalhouse.digital.pimarvel.login.view.LoginActivity;
import br.com.digitalhouse.digital.pimarvel.menu.view.AboutActivity;
import br.com.digitalhouse.digital.pimarvel.menu.view.FaqActivity;
import br.com.digitalhouse.digital.pimarvel.menu.view.SettingsActivity;
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
            case R.id.navigation_comic:
                replaceFragment(new ComicFragment());
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
        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_settings:
                intent = new Intent(BaseActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_faq:
                intent = new Intent(BaseActivity.this, FaqActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                intent = new Intent(BaseActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_logout:
                intent = new Intent(BaseActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
