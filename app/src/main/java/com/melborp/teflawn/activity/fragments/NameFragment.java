package com.melborp.teflawn.activity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.melborp.teflawn.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Book edits
    EditText editName, editNumber, editAddress, editService;

    //Ripple Button
    MaterialRippleLayout ripple;

    //Firebase
    FirebaseAuth fAuth;
    FirebaseStorage fStorage;
    FirebaseDatabase fDabase;
    DatabaseReference fReference;

    public NameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NameFragment newInstance(String param1, String param2) {
        NameFragment fragment = new NameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String strtext = getArguments().getString("mowing");
        View v = inflater.inflate(R.layout.fragment_name, container, false);

        editName = v.findViewById(R.id.editTextName);
        editAddress = v.findViewById(R.id.editTextAddress);
        editNumber = v.findViewById(R.id.editTextNumber);
        editService = v.findViewById(R.id.editService);

        editService.setText(strtext);

        //Button
        ripple = v.findViewById(R.id.rippleBook);

        //Firebase Instances
        fAuth = FirebaseAuth.getInstance();
        fDabase = FirebaseDatabase.getInstance("https://teflawn-50696-default-rtdb.firebaseio.com/");
        fStorage = FirebaseStorage.getInstance();
        fReference = fDabase.getReference();

        ripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNext();
            }
        });



        return v;
    }

    private void onClickNext(){

        String name = editName.getText().toString();
        String address = editAddress.getText().toString();
        String number = editNumber.getText().toString();
        String service = editService.getText().toString();

        if (!name.isEmpty() && !address.isEmpty() &&
        !number.isEmpty() && !service.isEmpty()){

            fReference.child(fAuth.getCurrentUser().getUid()).child("name").setValue(name);
            fReference.child(fAuth.getCurrentUser().getUid()).child("address").setValue(address);
            fReference.child(fAuth.getCurrentUser().getUid()).child("number").setValue(number);
            fReference.child(fAuth.getCurrentUser().getUid()).child("service").setValue(service);




        }else {
            Toast.makeText(getActivity().getApplicationContext(), "Please ensure you enter all the fields", Toast.LENGTH_SHORT).show();
        }
    }
}