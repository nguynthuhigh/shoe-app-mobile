package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        textView = findViewById(R.id.textView);
        Intent searchData = getIntent();
        String data = searchData.getStringExtra("dataSearch");
        textView.setText(data);
    }
}