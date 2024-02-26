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
import com.sneaker.shoeapp.Interface.ClickItemCart;
import com.sneaker.shoeapp.model.ListProduct;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity extends AppCompatActivity {
ImageButton decreasePro,increasePro;

ListView listItem_cart;
CartAdapter cartAdapter;
ListProduct productList;
ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        addControls();
        addEvents();

    }

    private void addControls() {
        btnBack = findViewById(R.id.btnBack);

        listItem_cart = findViewById(R.id.listItem_cart);
        Bundle bundle = getIntent().getExtras();
        Product pro = new Product();
        if(bundle ==null) {
            setContentView(R.layout.layout_cart_null);
        }
        else pro = (Product) bundle.getSerializable("pro_details");
        cartAdapter = new CartAdapter(this, R.layout.item_cart);
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


    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}