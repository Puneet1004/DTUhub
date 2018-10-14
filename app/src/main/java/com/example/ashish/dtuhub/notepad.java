package com.example.ashish.dtuhub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ashish.dtuhub.model.notepadlayoutdetails;

import java.util.ArrayList;
import java.util.Date;

public class notepad extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<notepadlayoutdetails> notes;
    private notepadadapter notepadadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onAddnewNote();

            }
        });
    }

    private void loadNotes() {
        this.notes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            notes.add(new notepadlayoutdetails("this is my first note..", new Date().getTime()));

        }
        notepadadapter = new notepadadapter(this, notes);
        recyclerView.setAdapter(notepadadapter);
        notepadadapter.notifyDataSetChanged();
    }

    private void onAddnewNote() {
        if (notes != null) {
            notes.add(new notepadlayoutdetails("this is new note", new Date().getTime()));
            if (notepadadapter != null)
                notepadadapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }
}