package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.FavoriteAdapter;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.model.Cart;
import com.sneaker.shoeapp.model.Product;


import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    RecyclerView recyclerFavorite ;
    FavoriteAdapter favoriteAdapter;
    ArrayList<Product> arr_Favourite;
    FirebaseAuth mauth;
    FirebaseUser user;
    FirebaseFirestore db;
    ImageButton btnBack, removeToFVbtn;
    Product product=new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        db = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        user = mauth.getCurrentUser();

        addControls();
        addEvents();
        loadData();
    }

    private void loadData() {
        db.collection("User").document(user.getUid()).collection("Favorite").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot dc: task.getResult()
                             ) {
                            String proID = dc.getString("id");

                            db.collection("Product").document(proID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    String namePro = task.getResult().getString("proName");
                                    String imgPro = task.getResult().getString("image");
                                    String color = task.getResult().getString("color");
                                    Double price = Double.valueOf(task.getResult().getString("price")) ;

                                    arr_Favourite.add(new Product(namePro,price , dc.getString("category"), imgPro, color, 0, proID));
                                    favoriteAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addControls() {
        recyclerFavorite = findViewById(R.id.recyclerFavorite);
        btnBack = findViewById(R.id.btnBack);

        arr_Favourite = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(FavouriteActivity.this,arr_Favourite);

        recyclerFavorite.setAdapter(favoriteAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerFavorite.setLayoutManager(gridLayoutManager);
    }
}