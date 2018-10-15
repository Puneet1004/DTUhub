package com.example.ashish.dtuhub;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class upload_activity extends AppCompatActivity {

    String year, branch, type;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri pdfUri;
    ProgressDialog progressDialog;
    private Spinner u_year, u_branch, u_type;
    private Button selectfile, uploadfile;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_activity);

        u_year = findViewById(R.id.spinner_year);
        u_branch = findViewById(R.id.spinner_branch);
        u_type = findViewById(R.id.spinner_type);
        selectfile = findViewById(R.id.u_select_file);
        uploadfile = findViewById(R.id.u_upload_file);
        msg = findViewById(R.id.file_info);

        ArrayAdapter<CharSequence> adapteryear = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_dropdown_item);
        adapteryear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        u_year.setAdapter(adapteryear);

        u_year.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                year = adapterView.getItemAtPosition(position).toString();
            }
        });

        u_branch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                branch = adapterView.getItemAtPosition(i).toString();

            }
        });

        u_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
            }
        });

        ArrayAdapter<CharSequence> adapterbranch = ArrayAdapter.createFromResource(this, R.array.branches, android.R.layout.simple_spinner_dropdown_item);
        adapterbranch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        u_branch.setAdapter(adapterbranch);

        ArrayAdapter<CharSequence> adaptertype = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_dropdown_item);
        adaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        u_type.setAdapter(adaptertype);

        //  year.getSelectedItem().toString();

       /* year = u_year.getSelectedItem().toString();
        branch = u_branch.getSelectedItem().toString();
        type = u_type.getSelectedItem().toString();*/


        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        selectfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(upload_activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectpdf();
                } else {
                    ActivityCompat.requestPermissions(upload_activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                }

            }
        });


        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdfUri != null)
                    UploadFile(pdfUri);
                else
                    Toast.makeText(upload_activity.this, "select a file ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void UploadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();


        StorageReference storageReference = storage.getReference();
        //     if (year.equals(Select Year))
        storageReference.child(year).child(branch).child(type).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        DatabaseReference reference = database.getReference();
                        reference.child(year).child(branch).child(type).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                    Toast.makeText(upload_activity.this, "file successfully uploaded", Toast.LENGTH_SHORT).show();

                                else
                                    Toast.makeText(upload_activity.this, "file not successfully Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(upload_activity.this, "file not successfully uploaded", Toast.LENGTH_SHORT).show();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectpdf();
        } else
            Toast.makeText(upload_activity.this, "please provide permission ", Toast.LENGTH_SHORT).show();
    }


    private void selectpdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            msg.setText("A file is selected:" + data.getData().getLastPathSegment());
        } else {
            Toast.makeText(upload_activity.this, "please select a file ", Toast.LENGTH_SHORT).show();
        }
    }
}
