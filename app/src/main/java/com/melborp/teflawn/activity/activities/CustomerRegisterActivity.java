package com.melborp.teflawn.activity.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.melborp.teflawn.R;
import com.melborp.teflawn.activity.models.Customer;

import utils.Tools;


public class CustomerRegisterActivity extends AppCompatActivity {

    private EditText editEmail, editPassword, editName, editNumber;
    private Button createAccount;

    FirebaseAuth fbAuth;
    FirebaseDatabase fbDatabase;
    DatabaseReference fbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
        

        //get firebase Authentication
        fbAuth = FirebaseAuth.getInstance();

        fbDatabase = FirebaseDatabase.getInstance();


        //activate the views
        init();


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomerRegisterActivity.this, "Am working", Toast.LENGTH_SHORT).show();
                Log.d("ErrorButtonCreate", "create button created");
               registeringUser();
                Log.d("user registered", "out of register method");

            }
        });


    }

    private void init() {

        editEmail = findViewById(R.id.emailRegister);
        editPassword = findViewById(R.id.passwordRegister);
        editName = findViewById(R.id.nameRegister);
        editNumber = findViewById(R.id.numberRegister);
        createAccount = findViewById(R.id.btnCreateAccount);
    }

    private void registeringUser() {
        Toast.makeText(CustomerRegisterActivity.this, "register method", Toast.LENGTH_SHORT).show();
        Log.d("registeringUser", "entered registering function");

        String name = editName.getText().toString();
        String number = editNumber.getText().toString();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !number.isEmpty()) {

            //createAccount.setEnabled(true);

            Log.d("creatingUser", "email and password not empty");

            final ProgressDialog mDialog = new ProgressDialog(CustomerRegisterActivity.this);
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setMessage("Registering please wait...");
            mDialog.show();

            fbAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(CustomerRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("authenticatingUser", "User authenticated");

                                if (fbAuth.getCurrentUser() != null) {
                                    String useridd = fbAuth.getCurrentUser().getUid();
                                    //
                                    //getReferenceFromUrl("https://teflawn-50696-default-rtdb.firebaseio.com/Customers").child(useridd);
                                    fbRef = fbDatabase.getReference("Customers").child(useridd);

                                    Customer customer = new Customer(name, email, number, password);
                                    fbRef.setValue(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Toast.makeText(CustomerRegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(CustomerRegisterActivity.this, MenuDrawerWhite.class);
                                            startActivity(i);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(CustomerRegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    Toast.makeText(CustomerRegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                            else

                            {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(CustomerRegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                    }

            });
            mDialog.dismiss();
        }
        else {
            Toast.makeText(CustomerRegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }
}





