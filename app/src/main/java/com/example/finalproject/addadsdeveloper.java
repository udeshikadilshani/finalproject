package com.example.finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class addadsdeveloper extends AppCompatActivity {
    Spinner s;
    String addCatogry;
    EditText title,price,phoneNumber,description;
    FirebaseFirestore dbroot;
    Button addPhoto, submit ;
    private FirebaseAuth mAuth;
    Uri proPicUri;
    String path;
    ImageView photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadsdeveloper);
        mAuth=FirebaseAuth.getInstance();
        photo=(ImageView) findViewById(R.id.ads);


        String[] arraySpinner = new String[] {
                "Bridal", "Broom", "Hotel", "Photography","Poruwa"
        };
        s= (Spinner) findViewById(R.id.catar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        addPhoto=(Button) findViewById(R.id.btnAds);
        submit=(Button) findViewById(R.id.sub);
        title=(EditText)findViewById(R.id.tai);
        price =(EditText)findViewById(R.id.price);
        phoneNumber=(EditText)findViewById(R.id.phone);
        description=(EditText)findViewById(R.id.Des);
        dbroot=FirebaseFirestore.getInstance();

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                launcher.launch(intent);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });


    }

    private void insertData() {
        addCatogry= String.valueOf(s.getSelectedItem());
        Map<String, Object> adsdata = new HashMap<>();
        adsdata.put("userId", "admin");
        adsdata.put("category", addCatogry);
        adsdata.put("photoUri", proPicUri);
        adsdata.put("title", title.getText().toString().trim());
        adsdata.put("price", price.getText().toString().trim());
        adsdata.put("phonenumber", phoneNumber.getText().toString().trim());
        adsdata.put("description", description.getText().toString().trim());
        adsdata.put("status", "1");



        if(addCatogry.equals("Bridal")){
            dbroot.collection("Bridal").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            phoneNumber.setText("");
                            price.setText("");
                            description.setText("");
                        }
                    });

        }else if(addCatogry.equals("Broom")){
            dbroot.collection("Broom").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            phoneNumber.setText("");
                            price.setText("");
                            description.setText("");
                        }
                    });


        }else if(addCatogry.equals("Hotel")){
            dbroot.collection("Hotel").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            price.setText("");
                            phoneNumber.setText("");
                            description.setText("");
                        }
                    });
        }else if(addCatogry.equals("Photography")){
            dbroot.collection("Photography").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            phoneNumber.setText("");
                            price.setText("");
                            description.setText("");
                        }
                    });





        }else if(addCatogry.equals("Poruwa")){
            dbroot.collection("Poruwa").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            phoneNumber.setText("");
                            price.setText("");
                            description.setText("");
                        }
                    });

        }
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i("IMG", "onActivityResult: came to launcher");
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Uri data = result.getData().getData();
                        addProfilePictureToFirestore(FirebaseStorage.getInstance(), data);
//                            Toast.makeText(getContext(), "came to onActivityResult", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("IMG", "onActivityResult: getData is null");
                    }
                }
            });
    private void addProfilePictureToFirestore(FirebaseStorage storage, Uri imgPath) {
        path=mAuth.getUid()+String.valueOf(imgPath);

        ProgressDialog dialog = new ProgressDialog(addadsdeveloper.this);

        storage.getReference("add").child(path)

                .putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        getProPicFromDB(photo);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addadsdeveloper.this, "Uploading Faild", Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        dialog.setMessage("Uploading Profile Picutre...");
                        dialog.setCancelable(false);
                        dialog.show();
                    }
                });
    }
    private void getProPicFromDB( ImageView container){
        // String id = Add_Service.this.getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("id", "");
        StorageReference reference = FirebaseStorage.getInstance().getReference().child("add/" + path);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("SET PRO PIC", "URI: "+ uri);
                Glide.with(addadsdeveloper.this).load(uri).into(container);
                proPicUri = uri;
                System.out.println(proPicUri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("SET PRO PIC", "URI: "+ "FAILED");
            }
        });
    }


}