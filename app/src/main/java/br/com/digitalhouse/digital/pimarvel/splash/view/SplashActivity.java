package br.com.digitalhouse.digital.pimarvel.splash.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.login.view.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    //Declaração de atributos
    private ImageView imageViewSplash;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageViewSplash = findViewById(R.id.imageViewSplash);

        imageViewSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jump();
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                jump();
            }
        }, 3000);
    }

    private void jump() {

        timer.cancel();

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

        startActivity(intent);

        finish();
    }
}

