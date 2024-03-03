package com.sneaker.shoeapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.RegisterActivity;
import com.sneaker.shoeapp.model.Product;

import java.util.HashMap;
import java.util.Map;

public class AdminCustomerActivity extends AppCompatActivity {
EditText inputName_Pro,inputName_Img,inputPrice_Pro,inputName_Cate,inputName_Discount,inputColor_Pro;
Button btnSave;
FirebaseAuth auth;
FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer);
        firestore = FirebaseFirestore.getInstance();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Map<String,Object> obj = new HashMap<>();
               // obj.put("category",);
               // obj.put("proName",);
               // obj.put("image",);
               // obj.put("color",);
               // obj.put("price",);
              //  obj.put("discount",inputName_Discount.getText());
                Product product = new Product(inputName_Pro.getText().toString(),Double.parseDouble(inputPrice_Pro.getText().toString()),inputName_Cate.getText().toString(),inputName_Img.getText().toString(),inputColor_Pro.getText().toString(),2,"0");
                firestore.collection("Product").add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AdminCustomerActivity.this, "Thanh cong",
                                Toast.LENGTH_SHORT).show();
                        inputName_Pro.setText("");
                        inputName_Img.setText("");
                        inputPrice_Pro.setText("");
                        inputName_Cate.setText("");
                        inputName_Discount.setText("");
                        inputColor_Pro.setText("");
                    }
                });
            }
        });
    }

    private void addControls() {
        inputName_Pro = findViewById(R.id.inputName_Pro);
        inputName_Img = findViewById(R.id.inputName_Img);
        inputPrice_Pro = findViewById(R.id.inputPrice_Pro);
        inputName_Cate= findViewById(R.id.inputName_Cate);
        inputName_Discount= findViewById(R.id.inputName_Discount);
        inputColor_Pro= findViewById(R.id.inputColor_Pro);
        btnSave = findViewById(R.id.btnSave);




    }
}