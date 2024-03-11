package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.sneaker.shoeapp.Adapter.CheckoutAdapter;
import com.sneaker.shoeapp.model.Cart;
import com.sneaker.shoeapp.model.Order;

import com.sneaker.shoeapp.model.Product;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {
     ArrayList<Cart> pro;
    EditText edtAddress;
    TextView txtCustomer, txtQuantity, txtDiscount, txtTotalPrice;
    Button btnToPayment;
    RecyclerView recyclerCheckout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    CheckoutAdapter checkoutAdapter;
    Bundle bundle;
    String orderID = "",username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        addControls();
        txtQuantity.setText(bundle.getInt("quantity")+"");
        txtTotalPrice.setText("$"+bundle.getDouble("total"));


        btnToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Get the address entered by the user
                String address = edtAddress.getText().toString().trim();

                // Create order data
                Map<String, Object> orderInfo = new HashMap<>();
                orderInfo.put("Date", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
                orderInfo.put("Quantity", bundle.getInt("quantity"));
                orderInfo.put("Price", bundle.getDouble("total"));
                orderInfo.put("status", false);
                orderInfo.put("Address", address);
                orderInfo.put("username",username);
                orderInfo.put("userID",user.getUid());

                CollectionReference collectionOrderReference = db.collection("User").document(user.getUid())
                        .collection("Order");
                collectionOrderReference.add(orderInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        for (Cart pro_item:pro
                             ) {
                            orderID = task.getResult().getId();
                            Map<String, Object> productInfo = new HashMap<>();
                            productInfo.put("ID", pro_item.getId());
                            productInfo.put("Quantity", pro_item.getQuantity());
                            productInfo.put("Total",pro_item.getTotal_cart());
                            collectionOrderReference.document(task.getResult().getId())
                                    .collection("listPro").document(pro_item.getId()).set(productInfo);
                            db.collection("User").document(user.getUid()).collection("AddToCart").document(pro_item.getId()).delete();

                        }
                        Toast.makeText(CheckoutActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CheckoutActivity.this,PaymentActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("orderID",orderID);
                        intent.putExtra("pro",(Serializable) pro);
                        intent.putExtras(bundle1);
                        startActivity(intent);
                        finish();
                    }
                });



            }
        });
    }

    private void addControls() {
        recyclerCheckout = findViewById(R.id.recyclerCheckout);
        bundle = getIntent().getExtras();
        Intent intent = getIntent();
        pro = (ArrayList<Cart>) intent.getSerializableExtra("pro");
        checkoutAdapter = new CheckoutAdapter(pro,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerCheckout.setLayoutManager(gridLayoutManager);
        edtAddress = findViewById(R.id.edtAddress);
        txtCustomer = findViewById(R.id.txtCustomer);
        db.collection("User").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 username = task.getResult().getString("username");
                txtCustomer.setText(task.getResult().getString("username"));
            }
        });

        txtQuantity = findViewById(R.id.txtQuantity);

        txtDiscount = findViewById(R.id.txtDiscount);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnToPayment = findViewById(R.id.btnToPayment);


        recyclerCheckout.setAdapter(checkoutAdapter);
    }
}
