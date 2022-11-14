package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class devoloper_option extends AppCompatActivity {
    Button addads, myads,approve,pending ,decline ,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoloper_option);
        addads=(Button)findViewById(R.id.add2);
        myads =(Button)findViewById(R.id.my_ads);
        approve =(Button)findViewById(R.id.approvede);
        pending =(Button)findViewById(R.id.pending);
        decline =(Button)findViewById(R.id.deletedecline);
        logout=(Button)findViewById(R.id.logOut);

        addads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(devoloper_option.this,addadsdeveloper.class);
                startActivity(i);
            }
        });

        myads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(devoloper_option.this,myadsshows.class);
                startActivity(i);
            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(devoloper_option.this,grideviwedevoloper.class);
                startActivity(i);
            }
        });
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(devoloper_option.this,devolopwerpnding.class);
                startActivity(i);
            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(devoloper_option.this,decline.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(devoloper_option.this,loginselect.class);
                startActivity(i);
            }
        });
    }
}