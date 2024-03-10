package com.sneaker.shoeapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.CheckoutAdapter;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Cart;
import com.sneaker.shoeapp.model.Order;

import java.util.ArrayList;

public class AdminODActivity extends AppCompatActivity {
    TextView orderID_Details,orderDate_Details,orderQuantity_Details,orderValue_Details,orderAddress_Details,orderStatus_Details,nameCus,MethodPayment;
    Order order;
    Button close_order_details,confirm_order_details;
    FirebaseFirestore db;

    CheckoutAdapter checkoutAdapter;
    ArrayList<Cart> cartArrayList;
    RecyclerView rcv_order_details;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_odactivity);
        db = FirebaseFirestore.getInstance();

        addControls();
        addEvents();
        loadProduct();
    }

    private void addEvents() {
        Bundle bundle = getIntent().getExtras();
        order = (Order) bundle.get("order_obj");

        orderID_Details.setText("Order ID #"+order.getId());
        orderDate_Details.setText(String.valueOf(order.getDate()));
        orderQuantity_Details.setText(String.valueOf(order.getQuantity()));
        orderValue_Details.setText(order.getTotal_value()+"đ");
        orderAddress_Details.setText(order.getAddress());
        if(order.getStatus() == true){
            orderStatus_Details.setText("Delivered");
            orderStatus_Details.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            orderStatus_Details.setText("Delivering");
            orderStatus_Details.setTextColor(Color.parseColor("#FFC107"));
        }
        nameCus.setText(order.getCusName());
        close_order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm_order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("User").document(order.getCusID()).collection("Order").document(order.getId()).update("status", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AdminODActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }

    private void addControls() {
        MethodPayment = findViewById(R.id.MethodPayment);
        nameCus = findViewById(R.id.nameCus);
        confirm_order_details = findViewById(R.id.confirm_order_details);
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


    private void loadProduct() {
        db.collection("User").document(order.getCusID()).collection("Order").document(order.getId())
            .collection("listPro").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot dc : task.getResult()
                    ) {
                        db.collection("Product").document(dc.getString("ID")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String namePro = documentSnapshot.getString("proName");
                                String imgPro = documentSnapshot.getString("image");
                                String color = documentSnapshot.getString("color");
                                Double price = Double.valueOf(documentSnapshot.getString("price"));
                                cartArrayList.add(new Cart(namePro, price,
                                        dc.getString("category"),
                                        imgPro,
                                        color, 0,
                                        dc.getString("ID"),
                                        dc.getDouble("Quantity"),
                                        dc.getDouble("Total"), "0"));
                                checkoutAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                }
            });
    }
}