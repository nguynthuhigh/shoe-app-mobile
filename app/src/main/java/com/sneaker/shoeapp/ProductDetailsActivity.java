package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sneaker.shoeapp.model.Product;

public class ProductDetailsActivity extends AppCompatActivity {
    TextView dt_proName;
    ImageView dt_proImage;
    TextView dt_proPrice;
    TextView dt_proCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        addControls();
        Bundle bundle = getIntent().getExtras();
        Product pro = (Product) bundle.get("obj_product");
        dt_proPrice.setText(pro.getPrice() +"");
        dt_proCate.setText(pro.getCategory());
        dt_proImage.setImageResource(pro.getImage());
        dt_proName.setText(pro.getProName());
    }

    private void addControls() {
        dt_proName = findViewById(R.id.dt_proName);
        dt_proCate = findViewById(R.id.dt_proCate);
        dt_proImage = findViewById(R.id.dt_proImage);
        dt_proPrice = findViewById(R.id.dt_proPrice);

    }
}