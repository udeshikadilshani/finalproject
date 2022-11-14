package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detelsads extends AppCompatActivity {
    ImageView image;
    TextView tTitle,tPrice,tCatogory,tPhoneNumber,tDescription,tStatus;
    String userId;
    String title;
    String description;
    String photoUri;
    String price;
    String category;
    String phonenumber;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detelsads);
        image=(ImageView) findViewById(R.id.imgDetails);

        tTitle=(TextView) findViewById(R.id.lbTitle);
        tPrice=(TextView) findViewById(R.id.lbPrice);
        tCatogory=(TextView) findViewById(R.id.lbCatogory);
        tPhoneNumber=(TextView) findViewById(R.id.lbPhoneNumber);
        tDescription=(TextView) findViewById(R.id.lbDescription);
        tStatus=(TextView) findViewById(R.id.lbStatus);



        Intent intent= getIntent();
        if(intent.getExtras()!=null) {

            description = intent.getStringExtra("description");
            phonenumber = intent.getStringExtra("phonenumber");
            photoUri = intent.getStringExtra("photoUri");
            price = intent.getStringExtra("price");
            category = intent.getStringExtra("category");
            title = intent.getStringExtra("title");
            status = intent.getStringExtra("status");

            Glide.with(detelsads.this).load(photoUri).into(image);
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


        }
        }

        }
