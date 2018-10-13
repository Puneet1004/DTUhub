package com.example.ashish.dtuhub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class papers extends AppCompatActivity {

    RecyclerView recyclerView;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    private String DOWNLOAD_DIR = Environment.getExternalStoragePublicDirectory
            (Environment.DIRECTORY_DOWNLOADS).getPath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);

        Intent intent = getIntent();
        String year = intent.getStringExtra("year");
        String firebasestorage = intent.getStringExtra("firebasestorage");

        if (year.equals("first year")) {
            String subject = intent.getStringExtra("subject");
            databaseReference = FirebaseDatabase.getInstance().getReference().child(year).child(firebasestorage).child("papers").child(subject);
        } else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child(year).child(firebasestorage).child("papers");
        }

        // DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child(year).child(subject).child("papers");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String filename = dataSnapshot.getKey();
                String url = dataSnapshot.getValue(String.class);

                ((recyclerviewadapter) recyclerView.getAdapter()).update(filename, url);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // storageReference= FirebaseStorage.getInstance().getReference().child("first year").child("Group A").child("papers").child("bme");
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerviewadapter recyclerviewadapter = new recyclerviewadapter(papers.this, recyclerView, new ArrayList<String>(), new ArrayList<String>());
        recyclerView.setAdapter(recyclerviewadapter);
    }
}
