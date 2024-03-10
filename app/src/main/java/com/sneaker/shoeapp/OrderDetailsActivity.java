package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.CheckoutAdapter;
import com.sneaker.shoeapp.model.Cart;
import com.sneaker.shoeapp.model.Order;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView orderID_Details,orderDate_Details,orderQuantity_Details,orderValue_Details,orderAddress_Details,orderStatus_Details;
    Order order;
    Button close_order_details;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user;
    CheckoutAdapter checkoutAdapter;
    ArrayList<Cart> cartArrayList;
    RecyclerView rcv_order_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        addControls();
        addEvents();
        loadProduct();
    }

    private void loadProduct() {
        db.collection("User").document(user.getUid()).collection("Order").document(order.getId())
                .collection("listPro").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot dc:task.getResult()
                        ) {
                            db.collection("Product").document(dc.getString("ID")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    String namePro = documentSnapshot.getString("proName");
                                    String imgPro = documentSnapshot.getString("image");
                                    String color = documentSnapshot.getString("color");
                                    Double price = Double.valueOf(documentSnapshot.getString("price")) ;
                                    cartArrayList.add(new Cart(namePro,price ,
                                            dc.getString("category"),
                                            imgPro,
                                            color, 0,
                                            dc.getString("ID"),
                                            dc.getDouble("Quantity"),
                                            dc.getDouble("Total"),"0"));
                                    checkoutAdapter.notifyDataSetChanged();
                                }
                            });

                        }
                    }
                });
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
        rcv_order_details = findViewById(R.id.rcv_order_details);
        cartArrayList = new ArrayList<>();
        checkoutAdapter = new CheckoutAdapter(cartArrayList,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcv_order_details.setLayoutManager(gridLayoutManager);
        rcv_order_details.setAdapter(checkoutAdapter);
    }
}