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
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

public class myads extends AppCompatActivity {
    Button addPhoto;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myads);
        mAuth=FirebaseAuth.getInstance();

        addPhoto=(Button) findViewById(R.id.btnAds);

               addPhoto.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent = new Intent();
                       intent.setType("image/*");
                       intent.setAction(Intent.ACTION_GET_CONTENT);
                       launcher.launch(intent);
                       System.out.println("aaaaaaaaaaaaaa");
                   }
               });


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

        ProgressDialog dialog = new ProgressDialog(myads.this);

        storage.getReference("add").child(mAuth.getCurrentUser().getUid())
                .putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        //    getProPicFromDB(picContainer);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(myads.this, "Uploading Faild", Toast.LENGTH_LONG).show();
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

}