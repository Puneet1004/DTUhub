package com.example.ashish.dtuhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class firstyear extends AppCompatActivity {

    CardView groupa, groupb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstyear);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        groupa = findViewById(R.id.groupA);
        groupb = findViewById(R.id.groupB);

        groupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(firstyear.this, groupa);
                popupMenu.getMenuInflater().inflate(R.menu.buttonclickoption, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.booksa) {
                            Intent intent = new Intent(firstyear.this, books_and_notes.class);
                            intent.putExtra("year", "first year");
                            intent.putExtra("group", "Group A");
                            intent.putExtra("subject", "bme");
                            startActivity(intent);

                        } else {
                            Intent intent = new Intent(firstyear.this, papers.class);
                            intent.putExtra("year", "first year");
                            intent.putExtra("group", "Group A");
                            intent.putExtra("subject", "bme");
                            startActivity(intent);
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        groupb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(firstyear.this, groupb);
                popupMenu.getMenuInflater().inflate(R.menu.buttonclickoption, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.booksa) {
                            Intent intent = new Intent(firstyear.this, books_and_notes.class);
                            intent.putExtra("year", "first year");
                            intent.putExtra("firebasestorage", "Group B");
                            intent.putExtra("subject", "bme");
                            startActivity(intent);

                        } else {
                            Intent intent = new Intent(firstyear.this, papers.class);
                            intent.putExtra("year", "first year");
                            intent.putExtra("firebasestorage", "Group B");
                            intent.putExtra("subject", "bme");
                            startActivity(intent);
                        }

                        return true;
                    }
                });

                popupMenu.show();
            }
        });
        Toast.makeText(this, "yoyo", Toast.LENGTH_SHORT).show();
    }

}
