package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sneaker.shoeapp.Adapter.CartAdapter;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.model.ListProduct;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity extends AppCompatActivity {
ImageButton decreasePro,increasePro;
TextView viewQuantity;
ListView listItem_cart;
CartAdapter cartAdapter;
ListProduct productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        addControls();
        addEvents();


      //  loadData();
    }

    private void loadData() {
        cartAdapter.add(new Product("Dunk nike year of dragon",200,"men's shoe",R.drawable.shoe1,"CACBCF"));
        cartAdapter.add(new Product("Dunk nike 2024",300,"hehe's shoe",R.drawable.shoe2,"FF422B"));
        cartAdapter.add(new Product("Jordan",300,"kid's shoe",R.drawable.shoe3,"C1C0C5"));
        cartAdapter.add(new Product("Hulk",300,"running's shoe",R.drawable.shoe4,"BAC07C"));
        cartAdapter.add(new Product("White Shoe",300,"football's shoe",R.drawable.shoe5,"8394E7"));
        cartAdapter.add(new Product("Hello",300,"hello's shoe",R.drawable.shoe6,"FF422B"));
        cartAdapter.add(new Product("Hehe boi",300,"nguyn's shoe",R.drawable.shoe7,"5D90DD"));
        cartAdapter.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220,"Men's Football Cleats",R.drawable.shoe8,"A59D2D"));
        cartAdapter.add(new Product("Nike Vapor Edge Elite 360 2 NRG",2200,"Hello's Football Cleats",R.drawable.shoe9,"585858"));

    }

    private void addEvents() {
 /*
        increasePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(viewQuantity.getText().toString());
                qty++;
                viewQuantity.setText(qty+"");
            }
        });
      decreasePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(viewQuantity.getText().toString());
                qty--;
                viewQuantity.setText(qty+"");
            }
        });*/
    }

    private void addControls() {
        decreasePro = findViewById(R.id.decreasePro);
        increasePro = findViewById(R.id.increasePro);
      //  viewQuantity = findViewById(R.id.viewQuantity);
        listItem_cart = findViewById(R.id.listItem_cart);
        Bundle bundle = getIntent().getExtras();
        Product pro = (Product) bundle.getSerializable("pro_details");
        cartAdapter = new CartAdapter(this,R.layout.item_cart);
        productList = new ListProduct();
        productList.add(pro);
        listItem_cart.setAdapter(cartAdapter);
        if(productList.getProductList() != null){
            for (Product p:productList.getProductList()
                 ) {
                cartAdapter.add(p);
            }
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.header_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.bag_header){
            Intent intent = new Intent(MyCartActivity.this,MyCartActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.account_header){
            Intent intent = new Intent(MyCartActivity.this,MyOrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}