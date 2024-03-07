package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sneaker.shoeapp.Adapter.FavoriteAdapter;
import com.sneaker.shoeapp.databinding.ActivityFavouriteBinding;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
 RecyclerView recyclerFavo;
 FavoriteAdapter favoriteAdapter;
 ArrayList<Product> arr_favorite;
 ImageButton btnBack,btnFv,btnCart,btnPro;
 FirebaseAuth firebaseAuth;
    private ActivityFavouriteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        addControls();
        loadData();
        addEvents();
        //loadFavouritePro();
    }

//    private void loadFavouritePro() {
//            arr_favorite = new ArrayList<>();
//            //load favourite pro from db
//            //User > userId > Favourites
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
//            ref.child(firebaseAuth.getUid()).child("Favourites")
//                    .addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            //clear list before starting adding data
//                            arr_favorite.clear();
//                            for (DataSnapshot ds: snapshot.getChildren()){
//                                //will only get the id here
//                                String id=" "+ds.child("id").getValue();
//                                //set id to pd
//                                Product pd = new Product();
//                                pd.setId(id);
//                                //add pd to list
//                                arr_favorite.add(pd);
//                            }
//                            //set adapter
//                            favoriteAdapter = new FavoriteAdapter(FavouriteActivity.this,arr_favorite);
//                            //set adapter to recyclerview
//                            binding.recyclerFavorite.setAdapter(favoriteAdapter);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FavouriteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        arr_favorite.add(new Product("Dunk nike year of dragon",200.0,"men's shoe","1","CACBCF",0,"1"));
        arr_favorite.add(new Product("Hello",300.0,"hello's shoe","1","FF422B",0,"1"));
        arr_favorite.add(new Product("Hehe boi",300.0,"nguyn's shoe","1","5D90DD",0,"1"));
        arr_favorite.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220.0,"Men's Football Cleats","1","A59D2D",0,"1"));
        arr_favorite.add(new Product("Nike Vapor Edge Elite 360 2",2200.0,"Hello's Football Cleats","1","585858",0,"1"));
    }

    private void addControls() {
        recyclerFavo=findViewById(R.id.recyclerFavorite);
        btnBack=findViewById(R.id.btnBack);
        arr_favorite=new ArrayList<>();
        favoriteAdapter=new FavoriteAdapter(this,arr_favorite);
        recyclerFavo.setAdapter(favoriteAdapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerFavo.setLayoutManager(gridLayoutManager);
    }
}