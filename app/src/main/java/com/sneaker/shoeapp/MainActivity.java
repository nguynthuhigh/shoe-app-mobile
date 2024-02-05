package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageButton btnAddFav,btnSeller, btnSearch;
EditText searchProduct,searchProduct_2;
CardView productCard;
ImageButton finishLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        btnSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Chưa add được nha e",Toast.LENGTH_SHORT).show();
            }
        });
        productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataSearch =searchProduct.getText().toString();
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("dataSearch",dataSearch);
                startActivity(intent);
            }
        });
        /*finishLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    private void addControls() {
        btnSeller = findViewById(R.id.btnSeller);
        btnAddFav = findViewById(R.id.btnAddFav);
        searchProduct = findViewById(R.id.searchProduct);
        searchProduct_2 = findViewById(R.id.searchProduct_2);
        productCard = findViewById(R.id.productCard);
        btnSearch = findViewById(R.id.btnSearch);
        finishLayout = findViewById(R.id.finishLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.header_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.bag_header){
            Intent intent = new Intent(MainActivity.this,MyCartActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.favorite_header){
            Toast.makeText(MainActivity.this,"Favorite",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.account_header){
            Intent intent = new Intent(MainActivity.this,MyOrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}