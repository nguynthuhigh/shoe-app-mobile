package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sneaker.shoeapp.Adapter.FavoriteAdapter;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
 RecyclerView recyclerFavo;
 FavoriteAdapter favoriteAdapter;
 ArrayList<Product> arr_Favorite;
 ImageButton btnBack,btnFv,btnCart,btnPro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        addControls();
        loadData();
        addEvents();
    }

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

        arr_Favorite.add(new Product("Dunk nike year of dragon",200.0,"men's shoe","1","CACBCF",0,"1"));
        arr_Favorite.add(new Product("Hello",300.0,"hello's shoe","1","FF422B",0,"1"));
        arr_Favorite.add(new Product("Hehe boi",300.0,"nguyn's shoe","1","5D90DD",0,"1"));
        arr_Favorite.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220.0,"Men's Football Cleats","1","A59D2D",0,"1"));
        arr_Favorite.add(new Product("Nike Vapor Edge Elite 360 2",2200.0,"Hello's Football Cleats","1","585858",0,"1"));

    }

    private void addControls() {
        recyclerFavo=findViewById(R.id.recyclerFavorite);
        btnBack=findViewById(R.id.btnBack);

        arr_Favorite=new ArrayList<>();
        favoriteAdapter=new FavoriteAdapter(this,arr_Favorite);
        recyclerFavo.setAdapter(favoriteAdapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerFavo.setLayoutManager(gridLayoutManager);
    }
}