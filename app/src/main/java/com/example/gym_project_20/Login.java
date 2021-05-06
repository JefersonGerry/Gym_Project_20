package com.example.gym_project_20;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView createSignUp;
    EditText username,password;
    Button loginButton, forgetPasswordBtn;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();

        // Direcionando para o activity SingUp
        createSignUp = findViewById(R.id.signUpText);
        createSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });

        username = findViewById(R.id.LoginEmail);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.buttonLogin);
        forgetPasswordBtn = findViewById(R.id.forgetPasswordBtn);

        // Botão que direciona para Pop Forget Password
        forgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia dialogo de alerta
                View view = inflater.inflate(R.layout.reset_pop,null);
                reset_alert.setTitle("Reset Forgot Password ?").setMessage("Enter Your Email to get Password Reset link.")
                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // validação do endereço de email
                        EditText email = view.findViewById(R.id.resetEmailPop);
                        if(email.getText().toString().isEmpty()){
                            email.setError("Required Field");
                            return;
                        }
                        //envio do email com link de reset de senha
                        firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Email Sent.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("Cancel", null).setView(view).create().show();
            }
        });

        // Login do usuário com email e senha e os dois estando corretos direciona para classe Main activity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // extrair dados e validar dados
                if(username.getText().toString().isEmpty()){
                    username.setError("E-mail is Missing.");
                    return;
                }

                if(password.getText().toString().isEmpty()){
                    password.setError("Password is Missing.");
                    return;
                }

                // dados validados


                // Login do usuário

                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),
                        password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // Login feito com sucesso
                        startActivity( new Intent(getApplicationContext(), TransitionScreen.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}