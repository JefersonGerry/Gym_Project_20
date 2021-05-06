package com.example.gym_project_20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText signUpFullName, signUpEmail, signUpPassword, singUpConfPassword;
    Button buttonSignUp;
    TextView buttonLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        signUpFullName = findViewById(R.id.signUpfullname);
        signUpEmail = findViewById(R.id.signUpemail);
        signUpPassword = findViewById(R.id.signUpPassword);
        singUpConfPassword = findViewById(R.id.signUpConfpassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogin = findViewById(R.id.loginText);
        firebaseAuth = FirebaseAuth.getInstance();

        // Direciona para o Login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // extraindo dados de singUp
                String fullName = signUpFullName.getText().toString();
                String email =  signUpEmail.getText().toString();
                String password =  signUpPassword.getText().toString();
                String confPassword = singUpConfPassword.getText().toString();

                if(fullName.isEmpty()){
                    signUpFullName.setError("Full name is required");
                    return;
                }
                if(email.isEmpty()){
                    signUpEmail.setError("E-Mail is required");
                    return;
                }
                if(password.isEmpty()){
                    signUpPassword.setError("Password is required");
                    return;
                }
                if(confPassword.isEmpty()){
                    singUpConfPassword.setError("Confirm password is required");
                    return;
                }

                if(!password.equals(confPassword)){
                    singUpConfPassword.setError("Password do not match!!");
                    return;
                }

                // validação dos dados
                // registro de usuário no firebase

                Toast.makeText(SignUp.this,"Data validated", Toast.LENGTH_SHORT).show();

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // encaminhando usuário para próxima activity
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}