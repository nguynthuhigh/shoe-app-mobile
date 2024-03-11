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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;
import com.sneaker.shoeapp.Adapter.OrderAdapter;
import com.sneaker.shoeapp.Interface.ClickItemOrder;
import com.sneaker.shoeapp.model.Order;
import com.sneaker.shoeapp.model.Product;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {
    ImageButton finishLayout;
    RecyclerView rcv_order;
    List<Product> ListProduct;
    OrderAdapter orderAdapter;
    List<Order> orderList;
    FirebaseFirestore db;
    FirebaseAuth mauth;
    FirebaseUser user;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        finishLayout = findViewById(R.id.btnBack);
        finishLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addControl();
        loadData();
    }

    private void loadData() {
        db.collection("User").document(user.getUid()).collection("Order")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot dc: task.getResult()
                     ) {
                    Integer quantity = dc.getDouble("Quantity").intValue();
                    Integer price = dc.getDouble("Price").intValue();
                    orderList.add(new Order(dc.getId(),dc.getString("Date"),dc.getBoolean("status"),quantity,price,dc.getString("Address"),username,user.getUid()));
                    orderAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void addControl() {
        orderList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        user = mauth.getCurrentUser();
        orderAdapter = new OrderAdapter(orderList);
        rcv_order = findViewById(R.id.rcv_order);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcv_order.setLayoutManager(linearLayoutManager);
        db.collection("User").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username = documentSnapshot.getString("username");
            }
        });
        orderAdapter.setData(orderList, new ClickItemOrder() {
            @Override
            public void onClickedItem(Order order) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("order_obj", order);
                Intent intent = new Intent(MyOrderActivity.this,OrderDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        rcv_order.setAdapter(orderAdapter);
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