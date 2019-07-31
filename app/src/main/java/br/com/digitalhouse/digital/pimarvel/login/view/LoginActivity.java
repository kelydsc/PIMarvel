package br.com.digitalhouse.digital.pimarvel.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.base.view.BaseActivity;
import br.com.digitalhouse.digital.pimarvel.register.view.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    //Declaração de atributos
    private TextInputLayout textInputLayoutLogEmail;
    private TextInputLayout textInputLayoutLogPassword;
    private Button btnCreateAccount;
    private Button btnLogin;
    private ImageView imageViewGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inicialização de Views
        initViews();

        //Valida os dados do botão Login
        validaDadosLogin();

        //Chama a tela de Cadastro
        chamaTelaCadastro();

        //Chama a tela de acesso do Google
        chamaTelaAcessoGoogle();
    }

    private void initViews() {
        textInputLayoutLogEmail = findViewById(R.id.textInputLayoutEmailLog);
        textInputLayoutLogPassword = findViewById(R.id.textInputLayoutPasswordLog);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnLogin = findViewById(R.id.btnLogin);
        imageViewGoogle = findViewById(R.id.imageViewGoogle);
    }

    private void validaDadosLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailLog = textInputLayoutLogEmail.getEditText().getText().toString();
                String senhaLog = textInputLayoutLogPassword.getEditText().getText().toString();

                //Inicializa o set Error
                textInputLayoutLogEmail.setError("");
                textInputLayoutLogPassword.setError("");

                if (emailLog.isEmpty()) {
                    textInputLayoutLogEmail.setError("Enter your email address");
                    return;
                }

                if (senhaLog.isEmpty()) {
                    textInputLayoutLogPassword.setError("Inform your password");
                    return;
                }

                //Se todos os campos estiverem preenchidos chama a tela de Login
                if (!(emailLog.isEmpty()) && !(senhaLog.isEmpty())) {

                    //Chama a tela de Home
                    Intent intent = new Intent(LoginActivity.this, BaseActivity.class);

                    startActivity(intent);
                }
            }
        });
    }

    private void chamaTelaCadastro() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela de Cadastro
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        });
    }

    private void chamaTelaAcessoGoogle() {
        imageViewGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
