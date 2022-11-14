package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class visitors extends AppCompatActivity {
    Button bride;
    Button broom;
    Button hotel;
    Button photo;
    Button poru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors);
        bride=(Button)findViewById(R.id.bridal);
        broom=(Button)findViewById(R.id.hot);
        hotel=(Button)findViewById(R.id.broom);
        photo=(Button)findViewById(R.id.photo);
        poru=(Button)findViewById(R.id.poru);

        bride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(visitors.this,bridalviwe.class);
                startActivity(i);
            }
        });
        broom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(visitors.this,broomviwe.class);
                startActivity(i);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(visitors.this,hotelviwe.class);
                startActivity(i);
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(visitors.this,photoviwe.class);
                startActivity(i);
            }
        });
        poru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(visitors.this,poruviwe.class);
                startActivity(i);
            }
        });
    }
}