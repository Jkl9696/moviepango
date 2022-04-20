package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login, register;
    private FirebaseAuth auth;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btn_login);
        register= findViewById(R.id.btn_register);
        auth = FirebaseAuth.getInstance();
        user = findViewById(R.id.et_user);
        pass = findViewById(R.id.et_password);

        register.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String userID = user.getText().toString();
            String userPass = pass.getText().toString();
            login(userID, userPass);


        });
    }

    private void login(String userID, String userPass) {
        auth.signInWithEmailAndPassword(userID, userPass)
                .addOnCompleteListener(this, task -> {
                   if(task.isSuccessful()){
                       Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                       startActivity(intent);
                   } else{
                       Toast.makeText(LoginActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                       Log.println(Log.ERROR, "login error", "login of current user failed");

                   }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.println(Log.ERROR, "register error", e.toString());


            }
        });
    }
}