package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class memberdeletads extends AppCompatActivity {
    ImageView image;
    TextView tTitle,tPrice,tCatogory,tPhoneNumber,tDescription,tStatus;
    String userId;
    String title ,doId;
    String description;
    String photoUri;
    String price;
    String category;
    String phonenumber;
    String status;
    Button delete;
    FirebaseFirestore dbroot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvedetails);

        image=(ImageView) findViewById(R.id.imgDetails);
        dbroot = FirebaseFirestore.getInstance();
        tTitle=(TextView) findViewById(R.id.lbTitle);
        tPrice=(TextView) findViewById(R.id.lbPrice);
        tCatogory=(TextView) findViewById(R.id.lbCatogory);
        tPhoneNumber=(TextView) findViewById(R.id.lbPhoneNumber);
        tDescription=(TextView) findViewById(R.id.lbDescription);
        tStatus=(TextView) findViewById(R.id.lbStatus);
        delete=(Button) findViewById(R.id.btnDelete);



        Intent intent= getIntent();
        if(intent.getExtras()!=null) {

            description = intent.getStringExtra("description");
            phonenumber = intent.getStringExtra("phonenumber");
            photoUri = intent.getStringExtra("photoUri");
            price = intent.getStringExtra("price");
            category = intent.getStringExtra("category");
            title = intent.getStringExtra("title");
            status = intent.getStringExtra("status");
            doId=intent.getStringExtra("docId");


            Glide.with(memberdeletads.this).load(photoUri).into(image);
            tTitle.setText(title);
            tPrice.setText("LKR."+price+".00");
            tCatogory.setText(category);
            tPhoneNumber.setText(phonenumber);
            tDescription.setText(description);

            if(status.equals("0")){
                tStatus.setText("Pending");
            }else{
                tStatus.setText("Approd");
            }

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String,Object> approved = new HashMap<>();
                    approved.put("status","3");
                    dbroot.collection(category)
                            .document(doId)
                            .update(approved)
                            .addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(memberdeletads.this, "Decline successfully!", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            });



        }
    }

}
