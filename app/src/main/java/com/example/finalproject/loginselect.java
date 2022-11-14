package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class loginselect extends AppCompatActivity {
    Button rcrew;
    Button cstm;
    Button rdr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginselect);
        rcrew=(Button)findViewById(R.id.crowe);
        cstm=(Button)findViewById(R.id.member);
        rdr=(Button)findViewById(R.id.visitors);

        rcrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(loginselect.this,devoloper.class);
                startActivity(i);
            }
        });

        cstm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(loginselect.this,mymember.class);
                startActivity(i1);
            }
        });

        rdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2= new Intent(loginselect.this,visitors.class);
                startActivity(i2);
            }
        });
    }
}