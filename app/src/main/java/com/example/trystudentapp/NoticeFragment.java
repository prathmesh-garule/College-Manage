package com.example.trystudentapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoticeFragment extends Fragment {
    private ProgressBar pd;
    private RecyclerView recyclerView;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;
    private DatabaseReference reference;




    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");
        pd = view.findViewById(R.id.ProgressBar);
        recyclerView = view.findViewById(R.id.noticeDeleteRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        getNotice();
        return view;


    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    NoticeData data = snapshot1.getValue(NoticeData.class);
                    list.add(0,data);
                }
                adapter = new NoticeAdapter(getContext(),list);
                adapter.notifyDataSetChanged();
                pd.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}