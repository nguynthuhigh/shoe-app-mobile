package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sneaker.shoeapp.model.Order;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView orderID_Details,orderDate_Details,orderQuantity_Details,orderValue_Details,orderAddress_Details,orderStatus_Details;
    Order order;
    Button close_order_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        addControls();
        addEvents();
    }

    private void addEvents() {
        Bundle bundle = getIntent().getExtras();
        order = (Order) bundle.get("order_obj");

        orderID_Details.setText("Order ID #"+order.getId());
        orderDate_Details.setText(String.valueOf(order.getDate()));
        orderQuantity_Details.setText(String.valueOf(order.getQuantity()));
        orderValue_Details.setText(order.getTotal_value()+"đ");
        orderAddress_Details.setText(order.getAddress());
        if(order.getStatus() == false){
            orderStatus_Details.setText("Delivered");
            orderStatus_Details.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            orderStatus_Details.setText("Delivering");
            orderStatus_Details.setTextColor(getResources().getColor(R.color.red));
        }
        close_order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        orderID_Details = findViewById(R.id.orderID_Detail);
        orderDate_Details = findViewById(R.id.orderDate_Detail);
        orderQuantity_Details = findViewById(R.id.orderQuantity_Detail);
        orderValue_Details = findViewById(R.id.orderValue_Detail);
        orderAddress_Details = findViewById(R.id.orderAddress_Detail);
        close_order_details = findViewById(R.id.close_order_details);
        orderStatus_Details = findViewById(R.id.orderStatus_Detail);
    }
}