package com.sneaker.shoeapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.OrderAdapter;
import com.sneaker.shoeapp.Interface.ClickItemOrder;
import com.sneaker.shoeapp.MyOrderActivity;
import com.sneaker.shoeapp.OrderDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class AdminOrderActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user;
    List<Order> orderList;
    OrderAdapter orderAdapter;
    RecyclerView rcv_order_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        setContentView(R.layout.activity_admin_order);
        addControls();
    }

    private void addControls() {
        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(orderList);
        orderAdapter.setData(orderList, new ClickItemOrder() {
            @Override
            public void onClickedItem(Order order) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("order_obj", order);
                Intent intent = new Intent(AdminOrderActivity.this, AdminODActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        rcv_order_admin = findViewById(R.id.rcv_order_admin);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcv_order_admin.setLayoutManager(gridLayoutManager);
        rcv_order_admin.setAdapter(orderAdapter);
        db.collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot dc:task.getResult()
                ) {
                    Query query = db.collection("User").document(dc.getId()).collection("Order").orderBy("Date",Query.Direction.DESCENDING);
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (QueryDocumentSnapshot dc:task.getResult()
                            ) {
                                Integer quantity = dc.getDouble("Quantity").intValue();
                                Integer price = dc.getDouble("Price").intValue();
                                orderList.add(new Order(dc.getId(),dc.getString("Date"),dc.getBoolean("status"),quantity,price,dc.getString("Address"),dc.getString("username"),dc.getString("userID")));
                                orderAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        });

    }

}