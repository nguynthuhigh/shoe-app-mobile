package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    Button btnEdit_profile,btnView_order,btnLog_out,btnView_favourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addControls();
        addEvents();
    }

    private void addEvents() {

        btnEdit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        btnView_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });
        btnView_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MyOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        btnEdit_profile = findViewById(R.id.btnEdit_profile);
        btnView_order = findViewById(R.id.btnView_order);
        btnLog_out = findViewById(R.id.btnLog_out);
        btnView_favourite = findViewById(R.id.btnView_favourite);
    }
}