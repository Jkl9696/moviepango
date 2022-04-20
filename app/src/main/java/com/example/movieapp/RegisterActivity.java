package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button login, register;
    EditText user, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        user = findViewById(R.id.et_user);
        password = findViewById(R.id.et_password);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(v -> {
            String email_user = user.getText().toString();
            String email_pass = password.getText().toString();

            if(TextUtils.isEmpty(email_user) || TextUtils.isEmpty(email_pass)){
                Toast.makeText(RegisterActivity.this, "Enter credentials", Toast.LENGTH_SHORT).show();
            }else if (email_pass.length() < 6){
                Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
            }else{
                registerUser(email_user, email_pass);
            }
        });

    }

    private void registerUser(String email_user, String email_pass) {
        auth.createUserWithEmailAndPassword(email_user, email_pass).addOnCompleteListener(RegisterActivity.this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(RegisterActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, UserProfileActivity.class);
                startActivity(intent);
            } else
            {

                Toast.makeText(RegisterActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                Log.println(Log.ERROR, "register error", "register of current user failed");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.println(Log.ERROR, "register error", e.toString());


            }
        });
    }
}