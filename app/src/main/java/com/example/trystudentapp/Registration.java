package com.example.trystudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.HashMap;

public class Registration extends AppCompatActivity {
    private EditText regname, regemail, regpassword;
    private Button register;
    private String name,email,pass;

    private FirebaseAuth Auth;
    private DatabaseReference reference;
    private DatabaseReference dBref;

    private TextView openlog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        regname = findViewById(R.id.regname);
        regemail = findViewById(R.id.regemail);
        regpassword = findViewById(R.id.regpassword);
        register = findViewById(R.id.registersubmit);

        openlog = findViewById(R.id.openlogin);




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }
        });

        openlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });

    }

    private void openLogin() {
        startActivity(new Intent(Registration.this,loginactivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Auth.getCurrentUser()!= null){
            openMain();
        }
    }

    private void openMain() {

        startActivity(new Intent(this,MainActivity.class));

    }

    private void validatedata() {

        name = regname.getText().toString();
        email = regemail.getText().toString();
        pass = regpassword.getText().toString();



        if(name.isEmpty()){
            regname.setError("Required");
            regname.requestFocus();


        }else if(email.isEmpty()){
            regemail.setError("Required");
            regemail.requestFocus();

        }else if(pass.isEmpty()){
            regpassword.setError("Required");
            regpassword.requestFocus();
        }else{
            createUser();
        }
    }

    private void createUser() {

        Auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            uploadRagisterData();
                        }else{
                            Toast.makeText(Registration.this, "Erroe :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registration.this, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadRagisterData() {
        dBref =  reference.child("users");
        String key = dBref.push().getKey();

        HashMap<String, String > user = new HashMap<>();

        user.put("key",key);
        user.put("name",name);
        user.put("email",email);


        dBref.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(Registration.this, "User Crated", Toast.LENGTH_SHORT).show();
                            openMain();

                        }else{
                            Toast.makeText(Registration.this, "Erroe :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Registration.this, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });







    }
}