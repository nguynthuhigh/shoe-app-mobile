package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.sneaker.shoeapp.model.Order;

import com.sneaker.shoeapp.model.Product;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {
     Product pro;
    EditText edtAddress;
    TextView txtCustomer, txtQuantity, txtDiscount, txtTotalPrice;
    Button btnToPayment;
    RecyclerView recyclerCheckout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        addControls();



        btnToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the address entered by the user
                String address = edtAddress.getText().toString().trim();

                // Create order data
                Map<String, Object> orderInfo = new HashMap<>();
                orderInfo.put("Date", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
                orderInfo.put("Quantity", 11111);
                orderInfo.put("Price", "123");
                orderInfo.put("status", false);
                orderInfo.put("Address", address);

                CollectionReference collectionOrderReference = db.collection("User").document(user.getUid())
                        .collection("Order");

                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("ID", pro.getId());
                productInfo.put("Quantity", 1);


                collectionOrderReference.add(orderInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        collectionOrderReference.document(task.getResult().getId())
                                .collection("listPro").document(pro.getId()).set(productInfo);
                        Toast.makeText(CheckoutActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

    private void addControls() {
        Bundle bundle = getIntent().getExtras();
        pro = (Product) bundle.get("pro");
        edtAddress = findViewById(R.id.edtAddress);
        txtCustomer = findViewById(R.id.txtCustomer);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnToPayment = findViewById(R.id.btnToPayment);
        recyclerCheckout = findViewById(R.id.recyclerCheckout);
    }
}
