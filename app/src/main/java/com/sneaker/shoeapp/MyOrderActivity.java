package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sneaker.shoeapp.Adapter.OrderAdapter;
import com.sneaker.shoeapp.model.Order;
import com.sneaker.shoeapp.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {
    ImageButton finishLayout;
    RecyclerView rcv_order;
    List<Product> ListProduct;
    OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        finishLayout = findViewById(R.id.finishLayout);
        finishLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addControl();
    }

    private void addControl() {
        orderAdapter = new OrderAdapter(getListOrder());
        rcv_order = findViewById(R.id.rcv_order);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcv_order.setLayoutManager(linearLayoutManager);
        orderAdapter.setData(getListOrder());
        rcv_order.setAdapter(orderAdapter);
    }

    private List<Order> getListOrder() {
        List<Order> orderList = new ArrayList<>();

        orderList.add(new Order(1,2024,true,5,1200));
        orderList.add(new Order(2,2024,false,4,5200));
        orderList.add(new Order(3,2024,true,100,520000));
        orderList.add(new Order(4,2024,true,20,1200));
        orderList.add(new Order(5,2024,true,5,15000));
        return orderList;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.header_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.bag_header){
            Intent intent = new Intent(MyOrderActivity.this,MyCartActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.account_header){
            Intent intent = new Intent(MyOrderActivity.this,MyOrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}