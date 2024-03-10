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
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.model.Cart;
import com.sneaker.shoeapp.model.Order;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PaymentActivity extends AppCompatActivity {
    TextView date_Order,total_Order,cus_Order,address_Order,id_Order;
    FirebaseAuth mauth;
    FirebaseUser user;
    FirebaseFirestore db;
    RadioButton radio_cod,radio_momo;
    RadioGroup radioGroup;
    Button btnPay;
    RadioButton radioButton;
    String orderID,username,order_Date,order_ID,order_Total;
    int order_Quantity;
    Order order;
    ArrayList<Cart> pro;

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
                SendMail();

            }
        });
    }

    private void SendMail(){
        try {
            String stringSenderEmail = "shoeappmobile@gmail.com";
            String stringReceiverEmail =  user.getEmail();
            String stringPasswordSenderEmail = "gwiknbgoqryndvzt";
            String stringHost = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail,stringPasswordSenderEmail);
                }
            });
            MimeMessage mimeMessage =new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(stringReceiverEmail));
            mimeMessage.setSubject(String.format("[Shoe App] Order Successful - %s - %s ",username,order_Date) );
            String content_order = "Order ID: "+order_ID+"\n"
                    +"Email: "+ user.getEmail() +"\n"
                    +"Product: \n";
            for (Cart item: pro
                 ) {
                content_order += " - "+item.getProName() +String.format(" ($%s) ",item.getPrice()+"") + " x" +(int)item.getQuantity()+"\n";
            }
            content_order +="Total Quantity: "+ order_Quantity+"\n"
            +"Total: $"+order_Total;
            mimeMessage.setText(content_order);
            Thread thread =new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
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
                username = task.getResult().getString("username");

            }
        });
        cus_Order.setText(username);
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
                        order_Date = task.getResult().getString("Date");
                        order_ID =task.getResult().getId();
                        order_Quantity = task.getResult().getDouble("Quantity").intValue() ;
                        order_Total = String.valueOf(task.getResult().getDouble("Price"));
                        order = new Order(order_ID,
                                order_Date,
                                task.getResult().getBoolean("status"),
                                 task.getResult().getDouble("Quantity").intValue(),
                                task.getResult().getDouble("Price").intValue(),
                                task.getResult().getString("Address"),username,user.getUid());
                    }
                });
        Intent intent = getIntent();
        pro = (ArrayList<Cart>) intent.getSerializableExtra("pro");

    }
}