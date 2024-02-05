package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageButton btnAddFav;
EditText searchProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddFav = findViewById(R.id.btnAddFav);
        searchProduct = findViewById(R.id.searchProduct);

        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.bag_header){
            Toast.makeText(MainActivity.this,"Bag",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.favorite_header){
            Toast.makeText(MainActivity.this,"Favorite",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.account_header){
            Toast.makeText(MainActivity.this,"Account",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}