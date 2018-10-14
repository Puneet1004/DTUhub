package com.example.ashish.dtuhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class secondyear extends AppCompatActivity implements View.OnClickListener {

    CardView coe, it, se, ce, ee, ece, me, bt;
    String firebasestorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondyear);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        coe = findViewById(R.id.COE);
        it = findViewById(R.id.It);
        se = findViewById(R.id.SE);
        ce = findViewById(R.id.CE);
        ee = findViewById(R.id.EE);
        ece = findViewById(R.id.ECE);
        bt = findViewById(R.id.BT);
        me = findViewById(R.id.ME);

        coe.setOnClickListener(this);
        it.setOnClickListener(this);
        se.setOnClickListener(this);
        ce.setOnClickListener(this);
        ee.setOnClickListener(this);
        ece.setOnClickListener(this);
        bt.setOnClickListener(this);
        me.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        final PopupMenu popupMenu = new PopupMenu(secondyear.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.buttonclickoption, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.booksa) {
                    switch (v.getId()) {
                        case R.id.COE:
                            firebasestorage = "COE";
                            Intent intent = new Intent(secondyear.this, books_and_notes.class);
                            intent.putExtra("year", "second year");
                            intent.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent);
                            break;
                        case R.id.It:
                            firebasestorage = "IT";
                            Intent intent1 = new Intent(secondyear.this, books_and_notes.class);
                            intent1.putExtra("year", "second year");
                            intent1.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent1);
                            break;
                        case R.id.SE:
                            firebasestorage = "software";
                            Intent intent2 = new Intent(secondyear.this, books_and_notes.class);
                            intent2.putExtra("firebasestorage", firebasestorage);
                            intent2.putExtra("year", "second year");
                            startActivity(intent2);
                            break;

                        case R.id.CE:
                            firebasestorage = "CE";
                            Intent intent3 = new Intent(secondyear.this, books_and_notes.class);
                            intent3.putExtra("year", "second year");
                            intent3.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent3);
                            break;

                        case R.id.EE:
                            firebasestorage = "EE";
                            Intent intent4 = new Intent(secondyear.this, books_and_notes.class);
                            intent4.putExtra("year", "second year");
                            intent4.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent4);
                            break;

                        case R.id.ECE:
                            firebasestorage = "ECE";
                            Intent intent5 = new Intent(secondyear.this, books_and_notes.class);
                            intent5.putExtra("year", "second year");
                            intent5.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent5);
                            break;

                        case R.id.BT:
                            firebasestorage = "BT";
                            Intent intent6 = new Intent(secondyear.this, books_and_notes.class);
                            intent6.putExtra("firebasestorage", firebasestorage);
                            intent6.putExtra("year", "second year");
                            startActivity(intent6);
                            break;

                        case R.id.ME:
                            firebasestorage = "ME";
                            Intent intent7 = new Intent(secondyear.this, books_and_notes.class);
                            intent7.putExtra("year", "second year");
                            intent7.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent7);
                            break;

                    }

                } else {
                    switch (v.getId()) {
                        case R.id.COE:
                            firebasestorage = "COE";
                            Intent intent = new Intent(secondyear.this, papers.class);
                            intent.putExtra("year", "second year");
                            intent.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent);
                            break;
                        case R.id.It:
                            firebasestorage = "IT";
                            Intent intent1 = new Intent(secondyear.this, papers.class);
                            intent1.putExtra("year", "second year");
                            intent1.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent1);
                            break;
                        case R.id.SE:
                            firebasestorage = "software";
                            Intent intent2 = new Intent(secondyear.this, papers.class);
                            intent2.putExtra("firebasestorage", firebasestorage);
                            intent2.putExtra("year", "second year");
                            startActivity(intent2);
                            break;

                        case R.id.CE:
                            firebasestorage = "CE";
                            Intent intent3 = new Intent(secondyear.this, papers.class);
                            intent3.putExtra("year", "second year");
                            intent3.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent3);
                            break;

                        case R.id.EE:
                            firebasestorage = "EE";
                            Intent intent4 = new Intent(secondyear.this, papers.class);
                            intent4.putExtra("year", "second year");
                            intent4.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent4);
                            break;

                        case R.id.ECE:
                            firebasestorage = "ECE";
                            Intent intent5 = new Intent(secondyear.this, books_and_notes.class);
                            intent5.putExtra("year", "second year");
                            intent5.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent5);
                            break;

                        case R.id.BT:
                            firebasestorage = "BT";
                            Intent intent6 = new Intent(secondyear.this, papers.class);
                            intent6.putExtra("firebasestorage", firebasestorage);
                            intent6.putExtra("year", "second year");
                            startActivity(intent6);
                            break;

                        case R.id.ME:
                            firebasestorage = "ME";
                            Intent intent7 = new Intent(secondyear.this, papers.class);
                            intent7.putExtra("year", "second year");
                            intent7.putExtra("firebasestorage", firebasestorage);
                            startActivity(intent7);
                            break;
                    }
                }
                //Toast.makeText(secondyear.this,""+item.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            }

        });

        popupMenu.show();

    }
}
