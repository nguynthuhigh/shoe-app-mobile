package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
String query_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        addControls();
        addEvents();
        loadData();
    }

    private void addEvents() {

    }

    private void addControls() {
        Intent intent = getIntent();
        query_search = intent.getStringExtra("dataSearch").toLowerCase();
        productList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        contentSearch = findViewById(R.id.contentSearch);

        rcv_search = findViewById(R.id.rcv_search);
        productAdapter = new ProductAdapter(productList, new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_product",product);
                Intent intent = new Intent(SearchActivity.this, ProductDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        },this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this,2);
        rcv_search.setLayoutManager(gridLayoutManager);
        rcv_search.setAdapter(productAdapter);

    }
    private void loadData(){

        contentSearch.setText("Search: " +query_search);
        Toast.makeText(this,query_search,Toast.LENGTH_SHORT).show();
        CollectionReference collectionReference = db.collection("Product");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot dc: task.getResult()
                     ) {
                    if((dc.getString("proName").toLowerCase()).contains(query_search) || (dc.getString("category").toLowerCase()).contains(query_search)){
                        productList.add(new Product(dc.getString("proName"),Double.valueOf(dc.getString("price")),dc.getString("category"),dc.getString("image"),dc.getString("color"),2,dc.getId()));
                        productAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu,menu);
        MenuItem mn_search = menu.findItem(R.id.ic_search);
        SearchView searchView = (SearchView) mn_search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                query_search = query;
                productList.clear();
                loadData();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.bag_header){
            Intent intent = new Intent(SearchActivity.this,MyCartActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.account_header){
            Intent intent = new Intent(SearchActivity.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}