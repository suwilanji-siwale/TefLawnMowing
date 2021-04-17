package com.melborp.teflawn.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melborp.teflawn.R;

import utils.Tools;

public class MenuDrawerWhite extends AppCompatActivity implements View.OnClickListener {

    private ActionBar actionBar;
    private Toolbar toolbar;
    LinearLayout linearMower, linearSeeding, linearWater, linearDesigning;
    TextView firebaseEmail;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer_white);



        initToolbar();
        initNavigationMenu();
        initViews();

        //get firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
            firebaseEmail.setText(userEmail);
        } else {
            // No user is signed in
        }
        linearMower.setOnClickListener(this);
        linearSeeding.setOnClickListener(this);
        linearWater.setOnClickListener(this);
        linearDesigning.setOnClickListener(this);



    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Teflawn");
        Tools.setSystemBarColor(this, R.color.grey_10);
        Tools.setSystemBarLight(this);
    }

    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // open drawer at start
       // drawer.openDrawer(GravityCompat.START);


       
    }


    //Initialization Method
    public void initViews(){
        linearMower = findViewById(R.id.linearMower);
        linearSeeding = findViewById(R.id.linearSeeding);
        linearWater = findViewById(R.id.linearWater);
        linearDesigning = findViewById(R.id.linearDesigning);
        firebaseEmail = findViewById(R.id.firebaseEmail);
    }

    //On click listener
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.linearDesigning:

                launchMakeOrder();
                break;

            case R.id.linearMower:

                Intent i = new Intent(MenuDrawerWhite.this, PhotoActivity.class);
                //Toast.makeText(MenuDrawerWhite.this, "Hai am working!!", Toast.LENGTH_SHORT).show();
                //i.putExtra("mowing", "lawn mowing");
                startActivity(i);

                break;

            case R.id.linearWater:
                launchMakeOrder();
                break;

            case R.id.linearSeeding:
                launchMakeOrder();
                break;

        }
    }

    public void launchMakeOrder(){
        Intent i = new Intent(MenuDrawerWhite.this, MakeOrder.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.signOut){

            firebaseAuth.signOut();
            Intent i = new Intent(MenuDrawerWhite.this, CustomerLoginActivity.class);
            startActivity(i);
            finish();

        }

        return true;
    }
}