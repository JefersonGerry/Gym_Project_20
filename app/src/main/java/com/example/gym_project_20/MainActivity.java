package com.example.gym_project_20;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView verifyMsg, titlePageMain,subTitlePageMain, exerciceBtn;
    ImageView imageBackgroudMain;
    Animation animimgpage, bttonemain, bttwomain, btthreemain, lefttorightmain;
    View bgprogress, bgprogressstop;
    Button verifyEmail;
    FirebaseAuth auth;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();



        // Carregando animações
        animimgpage = AnimationUtils.loadAnimation(this,R.anim.animimgpage);
        bttonemain = AnimationUtils.loadAnimation(this,R.anim.bttone);
        bttwomain = AnimationUtils.loadAnimation(this,R.anim.bttwo);
        btthreemain = AnimationUtils.loadAnimation(this,R.anim.btthree);
        lefttorightmain = AnimationUtils.loadAnimation(this,R.anim.lefttoright);

        // carregando atributos de activity_main
        titlePageMain = findViewById(R.id.titlePageMenu);
        subTitlePageMain = findViewById(R.id.subTitlePageMenu);
        exerciceBtn =  findViewById(R.id.exerciceBtnMain);
        imageBackgroudMain = findViewById(R.id.imagemBackgroundMenu);
        bgprogress =  findViewById(R.id.progressBtnMain);
        bgprogressstop = findViewById(R.id.stopProgressBtnMain);

        // aplicando animações
        imageBackgroudMain.startAnimation(animimgpage);
        titlePageMain.startAnimation(bttonemain);
        subTitlePageMain.startAnimation(bttonemain);

        exerciceBtn.startAnimation(btthreemain);
        bgprogress.startAnimation(bttwomain);
        bgprogressstop.startAnimation(lefttorightmain);

        // direicionando para a page de WorkActivity
        exerciceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // faz parte da verificação de email
        verifyMsg = findViewById(R.id.verifyEmailMsg);
        verifyEmail = findViewById(R.id.verifyEmailBtn);
        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();

        // verifica se o email ja foi verificado
        if (!auth.getCurrentUser().isEmailVerified()) {
            verifyEmail.setVisibility(View.VISIBLE);
            verifyMsg.setVisibility(View.VISIBLE);
        }

        // caso email já verificado
        verifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Verification Email Sent.", Toast.LENGTH_SHORT).show();
                        verifyEmail.setVisibility(View.GONE);
                        verifyMsg.setVisibility(View.GONE);
                    }
                });
            }
        });

    }

    // daqui para baixo somente funções para app bar
    // cria menu action bar lateral
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // todas as opções de itens dentro do menu action bar lateral
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // execução de reset de senha do usuário
        if (item.getItemId() == R.id.resetUserPassword) {
            startActivity(new Intent(getApplicationContext(), ResetPassword.class));
        }

        if (item.getItemId() == R.id.updateEmailMenu) {
            View view = inflater.inflate(R.layout.reset_pop,null);
            reset_alert.setTitle("Update Email ?").setMessage("Enter New Email Address..")
                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // validação do endereço de email
                            EditText email = view.findViewById(R.id.resetEmailPop);
                            if (email.getText().toString().isEmpty()) {
                                email.setError("Required Field");
                                return;
                            }
                            //pega o usuário e seu email e permite a atualização do email do mesmo
                            FirebaseUser user = auth.getCurrentUser();
                            user.updateEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(MainActivity.this,"Email Update", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).setNegativeButton("Cancel", null).setView(view).create().show();

        }

        // deleta conta do usuário
        if(item.getItemId() == R.id.deleteAcoountMenu){
            reset_alert.setTitle("Delete Account Permanently").setMessage("Are You sure ?")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseUser user = auth.getCurrentUser();
                    user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this,"Account Deleted.", Toast.LENGTH_SHORT).show();
                            auth.signOut();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        }
                    });
                }
            }).setNegativeButton("Cancel", null).create().show();
        }

        // encerra sessão do usuário no app (log out)
        if(item.getItemId() == R.id.logOutBtnMenu){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    } 

}