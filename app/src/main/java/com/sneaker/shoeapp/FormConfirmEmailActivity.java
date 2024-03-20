package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class FormConfirmEmailActivity extends AppCompatActivity {
    Button btnBacktoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_confirm_email);
        addControl();

        btnBacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( FormConfirmEmailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        btnBacktoLogin = findViewById(R.id.btnBacktoLogin);
    }
}