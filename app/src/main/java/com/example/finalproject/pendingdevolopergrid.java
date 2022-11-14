package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class pendingdevolopergrid extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private List<Product> products;
    private List<String> docId;
    Product product;
    private GridView productView;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pendingdevolopergrid);
        firebaseFirestore = FirebaseFirestore.getInstance();
        products = new ArrayList<>();
        docId = new ArrayList<>();
        productView = (GridView) findViewById(R.id.penddingdevogrid);

        addProduct();

        productView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductAdapter adapter = new ProductAdapter(pendingdevolopergrid.this, products);

                Product p1 = (Product) adapter.getItem(i);
                Intent intent = new Intent(pendingdevolopergrid.this, detailsdevoloper.class)
                        .putExtra("title", p1.getTitle())
                        .putExtra("category", p1.getCategory())
                        .putExtra("description", p1.getDescription())
                        .putExtra("phonenumber", p1.getPhonenumber())
                        .putExtra("photoUri", p1.getPhotoUri())
                        .putExtra("price", p1.getPrice())
                        .putExtra("status", p1.getStatus())
                        .putExtra("docId", docId.get(i));
                startActivity(intent);
                System.out.println(docId.get(i));
                System.out.println(p1.getTitle());
                System.out.println(p1.getPrice());

            }
        });

    }

    private void addProduct() {

        ProductAdapter adapter = new ProductAdapter(pendingdevolopergrid.this, products);
        productView.setAdapter(adapter);
        firebaseFirestore.collection("Bride").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {

                        product = snapshot.toObject(Product.class);
                        if (product.getStatus().equals("0")) {
                            docId.add(snapshot.getId());
                            products.add(product);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                firebaseFirestore.collection("Broom").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {

                                product = snapshot.toObject(Product.class);
                                if (product.getStatus().equals("0")) {
                                    docId.add(snapshot.getId());
                                    products.add(product);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                firebaseFirestore.collection("Hotel").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {

                                product = snapshot.toObject(Product.class);
                                if (product.getStatus().equals("0")) {
                                    docId.add(snapshot.getId());
                                    products.add(product);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

                firebaseFirestore.collection("Photography").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {

                                product = snapshot.toObject(Product.class);

                                if (product.getStatus().equals("0")) {
                                    docId.add(snapshot.getId());
                                    products.add(product);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                firebaseFirestore.collection("Poruwa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {

                                product = snapshot.toObject(Product.class);

                                if (product.getStatus().equals("0")) {
                                    docId.add(snapshot.getId());
                                    products.add(product);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

            }
        });

    }
}