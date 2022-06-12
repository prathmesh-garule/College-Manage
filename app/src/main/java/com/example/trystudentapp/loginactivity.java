package com.example.trystudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {

    private TextView openreg;
    private EditText logemail,logpass;
    private Button loginBtn;
    private String email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        auth = FirebaseAuth.getInstance();

        openreg = findViewById(R.id.openreg);
        logemail = findViewById(R.id.loginemail);
        logpass = findViewById(R.id.loginpassword);
        loginBtn = findViewById(R.id.loginsubmit);


        openreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });





    }

    private void validateData() {
        email = logemail.getText().toString();
        password =logpass.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "please provide all fields", Toast.LENGTH_SHORT).show();

        }else{
            loginUser();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            openMain();
                        }else{
                            Toast.makeText(loginactivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginactivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openMain() {
        startActivity(new Intent(loginactivity.this,MainActivity.class));
        finish();
    }

    private void openRegister() {
        startActivity(new Intent(loginactivity.this,Registration.class));
        finish();
    }
}