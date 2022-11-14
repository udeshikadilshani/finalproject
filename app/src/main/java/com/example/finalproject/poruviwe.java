package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class poruviwe extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private List<Product> products;
    Product product;
    private GridView productView;
    private List<String> docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poruviwe);

        firebaseFirestore = FirebaseFirestore.getInstance();
        products= new ArrayList<>();
        docId= new ArrayList<>();
        productView=(GridView) findViewById(R.id.porugrid);
        additem();




        productView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductAdapter adapter = new ProductAdapter(poruviwe.this,products);

                Product p1 =(Product) adapter.getItem(i);
                Intent intent = new Intent(poruviwe.this, detelsads.class)
                        .putExtra("title",p1.getTitle())
                        .putExtra("category",p1.getCategory())
                        .putExtra("description",p1.getDescription())
                        .putExtra("phonenumber",p1.getPhonenumber())
                        .putExtra("photoUri",p1.getPhotoUri())
                        .putExtra("price",p1.getPrice())
                        .putExtra("status",p1.getStatus())
                        .putExtra("docId",docId.get(i))

                        ;
                startActivity(intent);

                System.out.println(p1.getTitle());
                System.out.println(p1.getPrice());

            }
        });

    }

    private void additem() {
        ProductAdapter adapter = new ProductAdapter(poruviwe.this,products);
        productView.setAdapter(adapter);
        firebaseFirestore.collection("Poruwa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()){

                        product= snapshot.toObject(Product.class);

                        if (product.getStatus().equals("1")) {
                            docId.add(snapshot.getId());
                            products.add(product);
                        }

                    }
                    adapter.notifyDataSetChanged();
                }


            }
        });
    }
}