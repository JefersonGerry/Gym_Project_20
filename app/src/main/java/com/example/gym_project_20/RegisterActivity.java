package com.example.gym_project_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gym_project_20.ui.object.ResgisterObj;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText registerDate, registerHeight, registerWeight,registerGender;
    Button registerBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ResgisterObj registerObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerDate = findViewById(R.id.editTextDate);
        registerHeight = findViewById(R.id.registerHeight);
        registerWeight = findViewById(R.id.registerWeight);
        registerGender = findViewById(R.id.registerGender);
        registerBtn = findViewById(R.id.btnSaveRegister);

        firebaseDatabase  = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Member Register");

        registerObj = new ResgisterObj();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = registerDate.getText().toString();
                String height = registerHeight.getText().toString();
                String weight = registerWeight.getText().toString();
                String gender = registerGender.getText().toString();

                if(TextUtils.isEmpty(date) && TextUtils.isEmpty(height) && TextUtils.isEmpty(weight) && TextUtils.isEmpty(gender)){
                    Toast.makeText(RegisterActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                }else{
                    addDataToFirebase(date,height,weight,gender);
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
            }
        });

    }

    private void addDataToFirebase(String date, String height, String weight, String gender){

        registerObj.setBirthDate(date);
        registerObj.setHeight(height);
        registerObj.setWeight(weight);
        registerObj.setGender(gender);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                databaseReference.setValue(registerObj);
                Toast.makeText(RegisterActivity.this, "Data added.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(RegisterActivity.this, "Fail to add data.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}