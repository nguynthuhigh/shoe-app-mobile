package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView recyclerMyCart;
    CartAdapter cartAdapter;
    ArrayList<Product> productArrayList;
    ImageButton decreasePro, increasePro;
    ListProduct productList;
    ImageButton btnBack;

    //    ListView listItem_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        Toolbar main_header = findViewById(R.id.menu_header_back);
        setSupportActionBar(main_header);
        addControls();
        addEvents();
        loadData();
    }

    private void loadData() {
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));
        productArrayList.add(new Product("ShitBox", 10000, "Sport", R.drawable.shoe8, "red", 1));

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

        recyclerMyCart = findViewById(R.id.recyclerMyCart);
        productArrayList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, productArrayList);
        recyclerMyCart.setAdapter(cartAdapter);
        recyclerMyCart.setLayoutManager(new LinearLayoutManager(this));
        btnBack = findViewById(R.id.btnBack);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bag_header) {
            Intent intent = new Intent(MyCartActivity.this, MyCartActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.account_header) {
            Intent intent = new Intent(MyCartActivity.this, MyOrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
   }

}