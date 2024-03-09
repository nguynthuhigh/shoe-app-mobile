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
<<<<<<< HEAD
=======
import com.sneaker.shoeapp.model.Order;
>>>>>>> main
import com.sneaker.shoeapp.model.Product;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {
     Product pro;
    String leCustomer, leDisCount;
    Double leTotal, leQuantity;
    EditText edtAddress;
    TextView txtCustomer, txtQuantity, txtDiscount, txtTotalPrice;
    Button btnToPayment;
    RecyclerView recyclerCheckout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
<<<<<<< HEAD
    Product pro;
=======

>>>>>>> main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        addControls();
<<<<<<< HEAD
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (intent != null) {
            assert bundle != null;
            String laCustomer = bundle.getString("Customer");
            String totalPrice = bundle.getString("Total Price");
            txtCustomer.setText(laCustomer);
            txtTotalPrice.setText(totalPrice);
        }
        btnToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the address from the EditText
                String address = edtAddress.getText().toString().trim();

                // Create an intent to return the address to the calling activity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("address", address);
                setResult(123, returnIntent);

                Product buyNowProduct = new Product();

                CollectionReference collectionOrderReference = db.collection("User").document("Phong")
                        .collection("Order");

                Map<String, Object> orderInfo = new HashMap<>();
                orderInfo.put("Date", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
                orderInfo.put("Quantity", 1);
                orderInfo.put("Price", 123);////////////////////////////////////////////////////////////////////////////////////////////
                orderInfo.put("status", false);
                orderInfo.put("Address", 123);
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("ID", "123");
                productInfo.put("Quantity", 1);
=======
            Bundle bundle = getIntent().getExtras();
            pro = (Product) bundle.get("pro");



            //txtCustomer.setText(leCustomer);
            //txtTotalPrice.setText(leTotal.toString());
            txtQuantity.setText("1");
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


>>>>>>> main
                collectionOrderReference.add(orderInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        collectionOrderReference.document(task.getResult().getId())
                                .collection("listPro").document(pro.getId()).set(productInfo);
                        Toast.makeText(CheckoutActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                });
<<<<<<< HEAD
=======

//                // Save order data to Firestore
//                db.collection("User").document(user.getUid())
//                        .collection("Order")
//                        .add(orderInfo)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                // Add product information to the order
//                                Map<String, Object> productInfo = new HashMap<>();
//                                productInfo.put("ID", thisIsLaProduct.getId());
//                                productInfo.put("Quantity", 1);
//                                db.collection("User").document(user.getUid())
//                                        .collection("Order").document(documentReference.getId())
//                                        .collection("listPro").document(thisIsLaProduct.getId())
//                                        .set(productInfo)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Toast.makeText(CheckoutActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
//                                                // Optionally, you can redirect to another activity or finish this activity
//                                                // For example:
//                                                finish();
//                                            }
//                                        })
//                                        .addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                // Handle failure
//                                            }
//                                        });
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(CheckoutActivity.this, "Order isn't placed successfully", Toast.LENGTH_SHORT).show();
//                            }
//                        });
>>>>>>> main
            }
        });
    }

    private void addControls() {
        edtAddress = findViewById(R.id.edtAddress);
        txtCustomer = findViewById(R.id.txtCustomer);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnToPayment = findViewById(R.id.btnToPayment);
        recyclerCheckout = findViewById(R.id.recyclerCheckout);
    }
}