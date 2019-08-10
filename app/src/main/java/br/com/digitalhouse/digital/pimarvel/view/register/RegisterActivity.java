package br.com.digitalhouse.digital.pimarvel.view.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.view.base.BaseActivity;
import br.com.digitalhouse.digital.pimarvel.view.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    //Declaração de atributos
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfPassword;
    private Button btnSave;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Seta a toolbar e o botão voltar(back)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicialização de View's
        initViews();

        //Valida o preenchimento dos Dados
        validaDados();

    }

    private void initViews() {
        textInputLayoutName = findViewById(R.id.TextInputLayoutName);
        textInputLayoutEmail = findViewById(R.id.TextInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.TextInputLayoutPassword);
        textInputLayoutConfPassword = findViewById(R.id.TextInputLayoutConfPassword);
        btnSave = findViewById(R.id.btnSave);

        //Inicializa Objetos
        firebaseAuth = FirebaseAuth.getInstance(); //Autenticação
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

                if (senha.length() < minimalPassLen) {
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

                    //Cadastro do usuario
                    firebaseAuth.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) { //Retorna true se o usuario for criado

                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        updateUI(user);

                                        Toast.makeText(v.getContext(),
                                                "Registered user successfully, login in progress...",
                                                Toast.LENGTH_LONG).show();

                                        //Chama a tela de Login
                                        Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);

                                        startActivity(intent);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(v.getContext(),
                                                "Authentication failed: " + task.getException(),
                                                Toast.LENGTH_LONG).show();

                                        updateUI(null);
                                    }
                                }
                            });
                }
            }
        });
    }

    private void updateUI(Object o) {
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
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

            startActivity(intent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
