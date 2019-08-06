package br.com.digitalhouse.digital.pimarvel.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.digitalhouse.digital.pimarvel.R;
import br.com.digitalhouse.digital.pimarvel.view.base.BaseActivity;
import br.com.digitalhouse.digital.pimarvel.view.register.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    //Declaração de atributos
    private TextInputLayout textInputLayoutLogEmail;
    private TextInputLayout textInputLayoutLogPassword;
    private Button btnCreateAccount;
    private Button btnLogin;
    private ImageView imageViewGoogle;

    //Declaração de FirebaseAuth
    private FirebaseAuth firebaseAuth;

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

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
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

                    Toast.makeText(v.getContext(),
                            "Login in progress...",
                            Toast.LENGTH_SHORT).show();

                    //Login do usuario
                    firebaseAuth.signInWithEmailAndPassword(emailLog, senhaLog)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) { //Retorna true se o usuario for logado
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        updateUI(user);

                                        //Chama a tela de Home
                                        Intent intent = new Intent(LoginActivity.this, BaseActivity.class);

                                        startActivity(intent);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(v.getContext(),
                                                "Authentication failed: " + task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                        updateUI(null);
                                    }
                                }
                            });
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
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
