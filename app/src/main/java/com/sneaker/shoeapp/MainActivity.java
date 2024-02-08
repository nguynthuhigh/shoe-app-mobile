package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Fragment.AllFragment;
import com.sneaker.shoeapp.Fragment.FootballFragment;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton btnAddFav, btnSearch;
    Button btnSeller,categoryAll,categoryFootball;
    EditText searchProduct,searchProduct_2;
    FrameLayout productCard;
    ImageButton finishLayout;

    RecyclerView recycleProduct;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        addControls();
        addEvents();

    }



    private void addEvents() {

      btnSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Chưa add được nha e",Toast.LENGTH_SHORT).show();
            }
        });




        categoryFootball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FootballFragment();
                loadFragment(fragment);
            }
        });
        categoryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AllFragment();
                loadFragment(fragment);
            }
        });
    }

    void loadFragment(Fragment fragment){
        FragmentTransaction frgTrans = getSupportFragmentManager().beginTransaction();
        frgTrans.replace(R.id.fragmentCategory,fragment);
        frgTrans.addToBackStack(null);
        frgTrans.commit();
    }
    private void addControls() {
        btnSeller = findViewById(R.id.btnSeller);
        btnAddFav = findViewById(R.id.btnAddFav);
        searchProduct = findViewById(R.id.searchProduct);
        searchProduct_2 = findViewById(R.id.searchProduct_2);
        productCard = findViewById(R.id.productCard);
        btnSearch = findViewById(R.id.btnSearch);
        finishLayout = findViewById(R.id.finishLayout);
        categoryAll = findViewById(R.id.categoryAll);
        categoryFootball = findViewById(R.id.categoryFootball);
        Fragment fragment = new AllFragment();
        loadFragment(fragment);

        recycleProduct = findViewById(R.id.recycleProduct);
        productAdapter = new ProductAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recycleProduct.setLayoutManager(linearLayoutManager);
        productAdapter.setData(getListPro());
        recycleProduct.setAdapter(productAdapter);

    }

    private List<Product> getListPro() {
        List<Product> listPro= new ArrayList<Product>();
        listPro.add(new Product("Dunk nike year of dragon",200,"men's shoe",R.drawable.shoe1));
        listPro.add(new Product("Dunk nike 2024",300,"men's shoe",R.drawable.shoe2));
        listPro.add(new Product("Jordan",300,"men's shoe",R.drawable.shoe3));
        listPro.add(new Product("Hulk",300,"men's shoe",R.drawable.shoe4));
        listPro.add(new Product("White Shoe",300,"men's shoe",R.drawable.shoe5));
        listPro.add(new Product("Hello",300,"men's shoe",R.drawable.shoe6));
        return listPro;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu,menu);
        MenuItem mn_search = menu.findItem(R.id.ic_search);
        SearchView searchView = (SearchView) mn_search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("dataSearch",query);
                startActivity(intent);
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
            Intent intent = new Intent(MainActivity.this,MyCartActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.account_header){
            Intent intent = new Intent(MainActivity.this,MyOrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}