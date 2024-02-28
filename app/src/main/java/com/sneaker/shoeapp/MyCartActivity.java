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

<<<<<<< HEAD
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
        btnBack.setOnClickListener(v -> finish());

//<<<<<<< HEAD
        //decreasePro.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //   public void onClick(View v) {
        //        int qty = Integer.parseInt(viewQuantity.getText().toString());
        //        qty--;
        //      viewQuantity.setText(qty+"");
        // }
        //});
//=======
//>>>>>>> c59e382918375fc89b3c77a62b84f9f51f9fb6e3
    }

    private void addControls() {
        decreasePro = findViewById(R.id.decreasePro);
//<<<<<<< HEAD
        //  increasePro = findViewById(R.id.increasePro);
        // viewQuantity = findViewById(R.id.viewQuantity);
//=======
        increasePro = findViewById(R.id.increasePro);
        btnBack = findViewById(R.id.btnBack);
        //  viewQuantity = findViewById(R.id.viewQuantity);
//>>>>>>> c59e382918375fc89b3c77a62b84f9f51f9fb6e3
        recyclerMyCart = findViewById(R.id.recyclerMyCart);
        productArrayList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, productArrayList);
        recyclerMyCart.setAdapter(cartAdapter);
        recyclerMyCart.setLayoutManager(new LinearLayoutManager(this));

        ////////

//        Bundle bundle = getIntent().getExtras();
//        Product pro = new Product();
//        if (bundle == null) {
//            setContentView(R.layout.layout_cart_null);
//        } else pro = (Product) bundle.getSerializable("pro_details");
//
//        productList = new ListProduct();
//        productList.add(pro);
//
//        if (productList.getProductList() != null) {
//            for (Product p : productList.getProductList()
//            ) {
//                cartAdapter.add(p);
//            }
//        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.bag_header) {
//            Intent intent = new Intent(MyCartActivity.this, MyCartActivity.class);
//            startActivity(intent);
//        }
//
//        if (item.getItemId() == R.id.account_header) {
//            Intent intent = new Intent(MyCartActivity.this, MyOrderActivity.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
=======
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
>>>>>>> 98b7e84d9b38eee27ae35f5242ba448d88261fb2
}