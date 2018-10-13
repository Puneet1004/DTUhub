package com.example.ashish.dtuhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registrationpage extends AppCompatActivity {
    TextView signin;
    TextView name;
    TextView email;
    TextView year;
    TextView password;
    Button signin_button;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mrefrence;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        signin = findViewById(R.id.signin);
        name = findViewById(R.id.r_name);
        email = findViewById(R.id.r_email);
        year = findViewById(R.id.r_year);
        password = findViewById(R.id.r_password);
        signin_button = findViewById(R.id.r_signin_btn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mrefrence = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
/*        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(registrationpage.this, MainActivity.class));
            finish();
        }
/*
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registrationpage.this, LoginActivity.class);
                startActivity(intent);
            }
        });
*/

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String sname, syear, spassword, semail;
                semail = email.getText().toString();
                sname = name.getText().toString();
                syear = year.getText().toString();
                spassword = password.getText().toString();

                if (TextUtils.isEmpty(sname)) {
                    Toast.makeText(registrationpage.this, "enter name ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(spassword)) {
                    Toast.makeText(registrationpage.this, "enter password ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(syear)) {
                    Toast.makeText(registrationpage.this, "enter year ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(semail)) {
                    Toast.makeText(registrationpage.this, "enter email ", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(semail, spassword).addOnCompleteListener(registrationpage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("registerpage", "login successful");
                                Toast.makeText(registrationpage.this, "registered successfully", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                write_user_data(sname, semail, syear);

                              /*  preferences = PreferenceManager.getDefaultSharedPreferences(registrationpage.this);
                                editor=preferences.edit();
                                editor.putString("name" ,sname);
                                editor.putString("year",syear);
                                editor.commit();*/
                                Intent intent = new Intent(registrationpage.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Log.w("register_here", "signInWithEmail:failure", task.getException());
                                Toast.makeText(registrationpage.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_LONG).show();
                            }


                        }
                    });
                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void write_user_data(String name, String year, String email_id) {
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();
        user_registration_details user_registration_details = new user_registration_details(name, year, email_id);
        mrefrence.child("details").child(userId).setValue(user_registration_details);
    }

}
