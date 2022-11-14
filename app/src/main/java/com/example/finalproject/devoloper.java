package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class devoloper extends AppCompatActivity {
    Button login;
    EditText email,password;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devoloper);
         login=findViewById(R.id.submit);
        email=findViewById(R.id.username);
        password=findViewById(R.id.login_password);
        firebaseFirestore = FirebaseFirestore.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginin();
            }
        });




    }

    private void loginin() {
        firebaseFirestore.collection("adminlogin").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                        String eMail=snapshot.get("email").toString().trim();
                        String pass=snapshot.get("password").toString().trim();
                        String txtEmail= email.getText().toString().trim();
                        String txtpassword= password.getText().toString().trim();
                        if(eMail.equals(txtEmail) && pass.equals(txtpassword)){
                            Intent intent = new Intent(devoloper.this, devoloper_option.class);
                            startActivity(intent);
                        }else{
                            email.setError("Login Eroor!!");
                        }
                    }
                }
            }
        });


    }
}
