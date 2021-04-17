package com.melborp.teflawn.activity.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.melborp.teflawn.R;


public class ChooseOne extends AppCompatActivity {

    Button teflawner, Customer;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);
        teflawner = (Button) findViewById(R.id.chef);
        Customer = (Button) findViewById(R.id.customer);
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic1), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic2), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic3), 3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic4), 3000);




        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);
        bgimage = findViewById(R.id.back3);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        teflawner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent toTeflawner = new Intent(ChooseOne.this, TeflawnerLogin.class);
                    startActivity(toTeflawner);


            }
        });

        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCustomer = new Intent(ChooseOne.this, CustomerLoginActivity.class);
                startActivity(toCustomer);

            }

        });


        }
    }


