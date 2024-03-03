package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
TextView contentSearch;
RecyclerView rcv_search;
ProductAdapter productAdapter;
List<Product> productList;
FirebaseFirestore db;

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
        db = FirebaseFirestore.getInstance();
        contentSearch = findViewById(R.id.contentSearch);
        Intent searchData = getIntent();
        String data = searchData.getStringExtra("dataSearch");
        contentSearch.setText("Search: " +data);
        rcv_search = findViewById(R.id.rcv_search);
        productAdapter = new ProductAdapter(productList, new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {

            }
        },this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this,2);
        rcv_search.setLayoutManager(gridLayoutManager);
        rcv_search.setAdapter(productAdapter);
    }
    private void loadData(){
        Intent intent = getIntent();
        String query = intent.getStringExtra("dataSearch");
        CollectionReference collectionReference = db.collection("Product");
        Query query_db = collectionReference.document().getParent().whereArrayContains("namePro",query);
        query_db.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot dc: task.getResult()
                     ) {
                    productList.add(new Product(dc.getString("namePro"),Double.valueOf(dc.getString("price")),dc.getString("category"),dc.getString("image"),dc.getString("color"),3,dc.getId()));
                    productAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}