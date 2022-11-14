package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class decline extends AppCompatActivity {
Button delect,deline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decline);
        delect=(Button)findViewById(R.id.deletads);
        deline=(Button)findViewById(R.id.declineads);

        delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(decline.this,deletgrid.class);
                startActivity(i);
            }
        });

        deline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(decline.this,declinegrid.class);
                startActivity(i);
            }
        });
    }
}