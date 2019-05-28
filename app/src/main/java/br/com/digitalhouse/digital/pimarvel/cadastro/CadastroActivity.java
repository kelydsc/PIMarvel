package br.com.digitalhouse.digital.pimarvel.cadastro;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.login.LoginActivity;

public class CadastroActivity extends AppCompatActivity {

    //Declaração de atributos
    private Toolbar toolbar;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfPassword;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Inicialização de View's
        initViews();

        //Seta o titulo na ToolBar
        setToolBar();

        //Valida o preenchimento dos Dados
        validaDados();

    }

    private void initViews() {
        textInputLayoutName = findViewById(R.id.TextInputLayoutName);
        textInputLayoutEmail = findViewById(R.id.TextInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.TextInputLayoutPassword);
        textInputLayoutConfPassword = findViewById(R.id.TextInputLayoutConfPassword);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setToolBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Register"); //Titulo para ser exibido na sua Action Bar em frente à seta
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();

        //Inclui a seta de Back
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//Mostrar o botão Back
            actionBar.setDisplayShowHomeEnabled(true); //Ativar o botão Back
        }
    }

    private void validaDados() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = textInputLayoutName.getEditText().getText().toString();
                String email = textInputLayoutEmail.getEditText().getText().toString();
                String senha = textInputLayoutPassword.getEditText().getText().toString();
                String confsenha = textInputLayoutConfPassword.getEditText().getText().toString();

                //minimo de caracteres permitidos para o cadastro de senha
                int minimalPassLen = 6;

                //Inicializa o set Error
                initSetError();

                if (nome.isEmpty()) {
                    textInputLayoutName.setError("Enter your name");
                    return;
                }

                if (email.isEmpty()) {
                    textInputLayoutEmail.setError("Enter your email address");
                    return;
                }

                if (senha.isEmpty()) {
                    textInputLayoutPassword.setError("Inform your password");
                    return;
                }

                if (senha.length() < minimalPassLen){
                    textInputLayoutPassword.setError("Enter password with 6 or more characters");
                    return;
                }

                if (confsenha.isEmpty()) {
                    textInputLayoutConfPassword.setError("Inform your password confirmation");
                    return;
                }

                //Verifica se a confirmação de senha está igual a senha
                if (!confsenha.equals(senha)) {
                    textInputLayoutConfPassword.setError("Invalid password verification");
                    return;
                }

                //Se todos os campos estiverem preenchidos chama a tela de Login
                if (!(nome.isEmpty()) && !(email.isEmpty()) && !(senha.isEmpty())
                        && !(confsenha.isEmpty())) {

                    //Chama a tela de Login
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);

                    startActivity(intent);
                }
            }
        });
    }

    private void initSetError() {

        textInputLayoutName.setError("");
        textInputLayoutEmail.setError("");
        textInputLayoutPassword.setError("");
        textInputLayoutConfPassword.setError("");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            //Chama a tela de Login a partir do click do botão Back da tela de Cadastro
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);

            startActivity(intent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
