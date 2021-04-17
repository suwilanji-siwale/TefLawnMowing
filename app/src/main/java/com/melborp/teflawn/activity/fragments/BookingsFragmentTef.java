package com.melborp.teflawn.activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melborp.teflawn.R;
import com.melborp.teflawn.activity.adapters.RecyclerViewAdapter;
import com.melborp.teflawn.activity.models.Booking;

import java.util.ArrayList;

public class BookingsFragmentTef extends Fragment {

    //Firebase
    FirebaseAuth fbAuth;
    FirebaseDatabase fbDatabase;
    DatabaseReference fbRef;

    ArrayList<Booking> book = new ArrayList<>();

    RecyclerView rView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_teflawner_home, container, false);

        fbAuth = FirebaseAuth.getInstance();
        fbDatabase = FirebaseDatabase.getInstance();
        fbRef = fbDatabase.getReference(fbAuth.getCurrentUser().toString());

        rView = v.findViewById(R.id.recycler_container);

        fbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    book.add(snapshot.getValue(Booking.class));
                }
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), book);
                rView.setAdapter(adapter);
                rView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return v;
    }
}
