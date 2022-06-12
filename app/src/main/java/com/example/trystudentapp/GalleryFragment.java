package com.example.trystudentapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    RecyclerView festRecycler, otherRecycler;
    GalleryAdapter adapter;

    DatabaseReference databaseReference;


    public GalleryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        festRecycler = view.findViewById(R.id.festRecycler);
        otherRecycler = view.findViewById(R.id.othersRecycler);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Gallery");

        getFestImage();
        getOtherImage();
        return view;
    }

    private void getOtherImage() {

        databaseReference.child("Seminar").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(),imageList);
                otherRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                otherRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getFestImage() {
        databaseReference.child("Fests").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(),imageList);
                festRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                festRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}