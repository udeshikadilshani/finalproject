 package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class viwe_ads extends AppCompatActivity {
     Button aprrove;
     Button pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viwe_ads);
        aprrove=(Button)findViewById(R.id.Uploade);
        pending=(Button)findViewById(R.id.pendding);

        aprrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(viwe_ads.this,approve.class);
                startActivity(i);
            }
        });
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(viwe_ads.this,pending.class);
                startActivity(i);
            }
        });
    }
}