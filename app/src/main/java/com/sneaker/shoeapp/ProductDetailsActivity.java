package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sneaker.shoeapp.model.Product;

public class ProductDetailsActivity extends AppCompatActivity {
    TextView dt_proName;
    ImageView dt_proImage;
    TextView dt_proPrice;
    TextView dt_proCate;
    FrameLayout bg_pro_details,bg_pro_details_2,bg_pro_details_main;
    CardView proColor;
    Button add_to_cart;
    ImageButton btnBack;
    Product pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        addControls();
        Bundle bundle = getIntent().getExtras();
        pro = (Product) bundle.get("obj_product");
        dt_proPrice.setText(pro.getPrice() + "");
        dt_proCate.setText(pro.getCategory());
        dt_proImage.setImageResource(pro.getImage());
        dt_proName.setText(pro.getProName());

        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_item_2), pro,bg_pro_details);
        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_item_2), pro,bg_pro_details_2);
        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_new), pro,bg_pro_details_main);
        proColor.setCardBackgroundColor(Color.parseColor("#" + pro.getColor()));
        addEvents();

    }
    public void setColorBg(GradientDrawable gradientDrawable, Product pro, FrameLayout layout){
        int colorInt = Color.parseColor("#" + pro.getColor());
        gradientDrawable.setColors(new int[]{0xFFFFFFFF, colorInt});
        layout.setBackground(gradientDrawable);
    }
    private void addEvents() {
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, MyCartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pro_details",pro);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void addControls() {
        dt_proName = findViewById(R.id.dt_proName);
        dt_proCate = findViewById(R.id.dt_proCate);
        dt_proImage = findViewById(R.id.dt_proImage);
        dt_proPrice = findViewById(R.id.dt_proPrice);
        bg_pro_details = findViewById(R.id.bg_pro_details);
        bg_pro_details_2 = findViewById(R.id.bg_pro_details_2);
        bg_pro_details_main = findViewById(R.id.bg_pro_details_main);
        add_to_cart = findViewById(R.id.add_to_cart);
        proColor = findViewById(R.id.proColor);
        btnBack = findViewById(R.id.btnBack);
    }
}