package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class memberdashboad extends AppCompatActivity {
    Button rcrew;
    Button cstm;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdashboad);
        rcrew=(Button)findViewById(R.id.deletedecline);
        cstm=(Button)findViewById(R.id.my_ads);
        logout=(Button)findViewById(R.id.logout);

        rcrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(memberdashboad.this,addads.class);
                startActivity(i);
            }
        });

        cstm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(memberdashboad.this,viwe_ads.class);
                startActivity(i1);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(memberdashboad.this, mymember.class);
                startActivity(intent);

            }
        });


    }
}