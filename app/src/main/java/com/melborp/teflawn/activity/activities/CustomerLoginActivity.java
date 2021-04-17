package com.melborp.teflawn.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.melborp.teflawn.R;

import utils.Tools;


public class CustomerLoginActivity extends AppCompatActivity {
    EditText emailLogin, passwordLogin;
    Button btnLogin, toRegister;

    FirebaseAuth firebaseAuth;

    private View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple_green);
        parent_view = findViewById(android.R.id.content);

        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

      /*  ((View) findViewById(R.id.sign_up_for_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Sign up for an account", Snackbar.LENGTH_SHORT).show();
            }
        });*/
        init();

        //get firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();



        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, Customer_BottomNavigation.class));
            finish();
        } else {
            //
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerLoginActivity.this, CustomerRegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void init(){

        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        toRegister = findViewById(R.id.btnRegister);
    }

    private void loginUser(){

        if(!emailLogin.getText().toString().isEmpty() &&
                !passwordLogin.getText().toString().isEmpty()){



            firebaseAuth.signInWithEmailAndPassword(emailLogin.getText().toString().trim(), passwordLogin
                    .getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(CustomerLoginActivity.this, "Logged On Successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CustomerLoginActivity.this, MenuDrawerWhite.class);
                    startActivity(i);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CustomerLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else{
            Toast.makeText(CustomerLoginActivity.this, "please enter email and password!", Toast.LENGTH_SHORT).show();
        }
    }
}
