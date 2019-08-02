package br.com.digitalhouse.digital.pimarvel.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.view.comic.ComicFragment;
import br.com.digitalhouse.digital.pimarvel.view.event.EventFragment;
import br.com.digitalhouse.digital.pimarvel.view.favorite.FavoriteFragment;
import br.com.digitalhouse.digital.pimarvel.view.game.GameFragment;
import br.com.digitalhouse.digital.pimarvel.view.login.LoginActivity;
import br.com.digitalhouse.digital.pimarvel.view.menu.AboutActivity;
import br.com.digitalhouse.digital.pimarvel.view.menu.FaqActivity;
import br.com.digitalhouse.digital.pimarvel.view.serie.SerieFragment;

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

        replaceFragment(new EventFragment());

        /*
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new EventFragment())
                .commit();
       */
    }

    //Troca os fragmentos do container
    public void replaceFragment(Fragment fragment) {

        try {
            String TAG = fragment.getClass().toString();
            String backStackName = fragment.getClass().getName();

            FragmentManager manager = getSupportFragmentManager();

            boolean fragmentPopped = manager.popBackStackImmediate(backStackName, 0);

            if (!fragmentPopped && getSupportFragmentManager().findFragmentByTag(TAG) == null) {
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment, TAG);
                ft.addToBackStack(backStackName);
                ft.commit();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("FRAGMENTS")
                .commit();
        */
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
                case R.id.navigation_serie:
                    replaceFragment(new SerieFragment());
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