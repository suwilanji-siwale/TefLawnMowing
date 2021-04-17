package com.melborp.teflawn.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.melborp.teflawn.R;

import java.util.UUID;

public class TeflawnerLogin extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button login;

    private String email, password;

    static final String UUID = "R6d4FbnsMXSOr9gH0d1pREpMjY13";

    //Firebase Auth instance
    FirebaseAuth fbAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teflawnian);

        edtEmail = findViewById(R.id.emailLogin);
        edtPassword = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.btnLogin);

        //Firebase
        fbAuth = FirebaseAuth.getInstance();

        //LoginButton onclick listener

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    fbAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (fbAuth.getCurrentUser().getUid().equals(UUID)){
                                Intent i = new Intent(TeflawnerLogin.this, HomeTeflawn.class);
                                startActivity(i);
                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TeflawnerLogin.this, e.getMessage() + "please sign up as a customer", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }




}
