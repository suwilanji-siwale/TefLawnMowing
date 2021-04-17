
package com.melborp.teflawn.activity.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.melborp.teflawn.R;
import com.melborp.teflawn.activity.fragments.NameFragment;

import utils.Tools;

public class MakeOrder extends AppCompatActivity {
    TextView bookAppointment;
    EditText editTextName, editTextNumber, editTextAddress,
    chooseDate, chooseTime;
    CheckBox fenced, dogs, home;
    MaterialRippleLayout rippleBook;
    LinearLayout uploadPhoto;
    ImageView photoCaption;

    FirebaseAuth fAuth;
    FirebaseStorage fStorage;
    FirebaseDatabase fDabase;
    DatabaseReference fReference;

    //fragment variables
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NameFragment  nameFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makefragment);

        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        //initializeViews();

        //Firebase Instances
        fAuth = FirebaseAuth.getInstance();
        fDabase = FirebaseDatabase.getInstance("https://teflawn-50696-default-rtdb.firebaseio.com/");
        fStorage = FirebaseStorage.getInstance();
        fReference = fDabase.getReference();

        String s = getIntent().getStringExtra("mowing");

        Bundle bundle = new Bundle();
        bundle.putString("mowing", "Lawn Mowing");

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        nameFragment= new NameFragment();
        nameFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.changing, nameFragment);
        fragmentTransaction.commit();

    }


   /* //Initialize views
    public void initializeViews(){

        //EditText Objects
        editTextName = findViewById(R.id.editTextName);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextAddress = findViewById(R.id.editTextAddress);
        chooseDate = findViewById(R.id.chooseDate);
        chooseTime = findViewById(R.id.chooseTime);

        //CheckedBox Objects
        fenced = findViewById(R.id.checkedBoxFenced);
        dogs = findViewById(R.id.checkedBoxDogs);
        home = findViewById(R.id.checkedBoxHome);

        //ripple button
        rippleBook = findViewById(R.id.rippleBook);

        //upload photo
        uploadPhoto = findViewById(R.id.uploadPhoto);
        photoCaption = findViewById(R.id.imgUploadPhoto);

    }

    private void bookAppointment(){

    }*/
}