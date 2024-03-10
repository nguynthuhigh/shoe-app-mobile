package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.sneaker.shoeapp.model.Order;

public class PaymentActivity extends AppCompatActivity {
    TextView date_Order,total_Order,cus_Order,address_Order,id_Order;
    FirebaseAuth mauth;
    FirebaseUser user;
    FirebaseFirestore db;
    RadioButton radio_cod,radio_momo;
    RadioGroup radioGroup;
    Button btnPay;
    RadioButton radioButton;
    String orderID;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        db = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        user = mauth.getCurrentUser();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                db.collection("User")
                        .document(user.getUid())
                        .collection("Order")
                        .document(orderID).update("payment",radioButton.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putSerializable("order_obj", order);
                Intent intent = new Intent(PaymentActivity.this,OrderDetailsActivity.class);
                intent.putExtras(bundle);
                finish();
                startActivity(intent);


            }
        });
    }

    private void addControls() {
        btnPay = findViewById(R.id.btnPay);
        radioGroup = findViewById(R.id.radioGroup);
        radio_cod = findViewById(R.id.radio_cod);
        radio_momo = findViewById(R.id.radio_momo);
        date_Order = findViewById(R.id.date_Order);
        total_Order = findViewById(R.id.total_Order);
        cus_Order = findViewById(R.id.cus_Order);
        db.collection("User").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                cus_Order.setText(task.getResult().getString("username"));
            }
        });
        address_Order = findViewById(R.id.address_Order);
        id_Order = findViewById(R.id.id_Order);
        Bundle bundle =getIntent().getExtras();
        orderID = bundle.getString("orderID");
        id_Order.setText(orderID);
        db.collection("User")
                .document(user.getUid())
                .collection("Order")
                .document(orderID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        address_Order.setText(task.getResult().getString("Address"));
                        date_Order.setText(task.getResult().getString("Date"));
                        total_Order.setText("$"+task.getResult().getDouble("Price"));
                        order = new Order(task.getResult().getId(),
                                task.getResult().getString("Date"),
                                task.getResult().getBoolean("status"),
                                 task.getResult().getDouble("Quantity").intValue(),
                                task.getResult().getDouble("Price").intValue(),
                                task.getResult().getString("Address"));
                    }
                });


    }
}