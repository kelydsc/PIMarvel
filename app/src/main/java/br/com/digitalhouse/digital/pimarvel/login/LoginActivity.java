package br.com.digitalhouse.digital.pimarvel.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.cadastro.CadastroActivity;
import br.com.digitalhouse.digital.pimarvel.home.MainActivity;


public class LoginActivity extends AppCompatActivity {

    //Declaração de atributos
    private TextInputLayout textInputLayoutLogEmail;
    private TextInputLayout textInputLayoutLogPassword;
    private TextView textViewCreateAccount;
    private Button btnLogin;

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

        //Recebe a chamada da tela de Cadastro
        Intent intent = getIntent();
    }

    private void initViews() {
        textInputLayoutLogEmail = findViewById(R.id.textInputLayoutEmailLog);
        textInputLayoutLogPassword = findViewById(R.id.textInputLayoutPasswordLog);
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        btnLogin = findViewById(R.id.btnLogin);
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
                if (!(emailLog.isEmpty()) && !(senhaLog.isEmpty())){

                    //Chama a tela de Login
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);
                }
            }
        });
    }

    private void chamaTelaCadastro() {
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela de Cadastro
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);

                startActivity(intent);
            }
        });
    }
}
