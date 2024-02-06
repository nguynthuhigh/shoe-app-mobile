package com.sneaker.shoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
TextView contentSearch;
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
        contentSearch = findViewById(R.id.contentSearch);
        Intent searchData = getIntent();
        String data = searchData.getStringExtra("dataSearch");
        contentSearch.setText("Search: " +data);
    }
}