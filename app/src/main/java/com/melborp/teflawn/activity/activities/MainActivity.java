package com.melborp.teflawn.activity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.melborp.teflawn.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearMower, linearSeeding, linearWater, linearDesigning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


    }

    //Initialization Method
    public void initViews(){
        linearMower = findViewById(R.id.linearMower);
        linearSeeding = findViewById(R.id.linearSeeding);
        linearWater = findViewById(R.id.linearWater);
        linearDesigning = findViewById(R.id.linearDesigning);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.linearDesigning:

                break;

            case R.id.linearMower:

                break;

            case R.id.linearWater:

                break;

            case R.id.linearSeeding:

                break;

        }
    }

    public void launchMakeOrder(){
        Intent i = new Intent(MainActivity.this, MakeOrder.class);
        startActivity(i);
    }
}