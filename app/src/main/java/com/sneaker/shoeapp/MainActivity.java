package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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
import com.sneaker.shoeapp.Admin.CategoryAdminActivity;
import com.sneaker.shoeapp.Fragment.AllFragment;
import com.sneaker.shoeapp.Fragment.FootballFragment;
import com.sneaker.shoeapp.Fragment.RunningFragment;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton btnAddFav, btnSearch;
    Button btnSeller,categoryAll,categoryFootball,categoryRunning,btnLogin,btnRegister,btnFav,btnPayment,btnCheckout,btnOrderDetails,inputCate;
    EditText searchProduct,searchProduct_2;
    FrameLayout productCard;
    ImageButton finishLayout;

    RecyclerView rcv_popular,rcv_banner;
    ProductAdapter productAdapter,productAdapter_banner;
    CardView bg_proImg;

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
                changeStateButton(categoryFootball,R.drawable.btn_cate,R.color.white);
                changeStateButton(categoryAll,R.drawable.border_item_card_nonbg,R.color.black);
                changeStateButton(categoryRunning,R.drawable.border_item_card_nonbg,R.color.black);
                Fragment fragment = new FootballFragment();
                loadFragment(fragment);
            }
        });
        categoryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStateButton(categoryAll,R.drawable.btn_cate,R.color.white);
                changeStateButton(categoryFootball,R.drawable.border_item_card_nonbg,R.color.black);
                changeStateButton(categoryRunning,R.drawable.border_item_card_nonbg,R.color.black);
                Fragment fragment = new AllFragment();
                loadFragment(fragment);
            }
        });
        categoryRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStateButton(categoryRunning,R.drawable.btn_cate,R.color.white);
                changeStateButton(categoryFootball,R.drawable.border_item_card_nonbg,R.color.black);
                changeStateButton(categoryAll,R.drawable.border_item_card_nonbg,R.color.black);
                Fragment fragment = new RunningFragment();
                loadFragment(fragment);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(LoginActivity.class);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(RegisterActivity.class);
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(FavouriteActivity.class);
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(PaymentActivity.class);
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(CheckoutActivity.class);
            }
        });
        btnOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(OrderDetailsActivity.class);
            }
        });
        inputCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(CategoryAdminActivity.class);
            }
        });

    }
    private void changeStateButton(Button btn,int drawable,int color){
        Drawable borderDrawable = getResources().getDrawable(drawable);
        btn.setBackground(borderDrawable);
        int colorR = getResources().getColor(color);
        btn.setTextColor(colorR);
    }

    private void changeLayout(Class context){
        Intent intent = new Intent(MainActivity.this,context);
        startActivity(intent);
    }
    void loadFragment(Fragment fragment){
        FragmentTransaction frgTrans = getSupportFragmentManager().beginTransaction();
        frgTrans.replace(R.id.fragmentCategory,fragment);
        frgTrans.addToBackStack(null);
        frgTrans.commit();
    }
    @SuppressLint("ResourceAsColor")
    private void addControls() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnFav = findViewById(R.id.btnFav);
        btnPayment = findViewById(R.id.btnPayment);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnOrderDetails = findViewById(R.id.btnOrderDetails);
        inputCate = findViewById(R.id.inputCate);

        btnSeller = findViewById(R.id.btnSeller);
        btnAddFav = findViewById(R.id.btnAddFav);
        searchProduct = findViewById(R.id.searchProduct);
        searchProduct_2 = findViewById(R.id.searchProduct_2);
        productCard = findViewById(R.id.productCard);
        btnSearch = findViewById(R.id.btnSearch);
        finishLayout = findViewById(R.id.btnBack);
        categoryAll = findViewById(R.id.categoryAll);
        categoryFootball = findViewById(R.id.categoryFootball);
        categoryRunning = findViewById(R.id.categoryRunning);


        Fragment fragment = new AllFragment();
        loadFragment(fragment);
        rcv_popular = findViewById(R.id.rcv_popular);
        productAdapter = new ProductAdapter(getListPro(), new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                IntentDetails(product);
            }
        },this);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        rcv_popular.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_popular.setLayoutManager(linearLayoutManager);
        rcv_popular.setAdapter(productAdapter);
        //Pro Banner
        productAdapter_banner = new ProductAdapter(getListPro_banner(), new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                IntentDetails(product);
            }
        },this);
        rcv_banner = findViewById(R.id.rcv_banner);
        LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager_2.setOrientation(RecyclerView.HORIZONTAL);
        rcv_banner.setLayoutManager(linearLayoutManager_2);
        rcv_banner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_banner.setAdapter(productAdapter_banner);

    }

    private List<Product> getListPro_banner() {
        List<Product> listPro= new ArrayList<Product>();
        listPro.add(new Product("Nike Vapor Edge Elite 360 2",2200.0,"Hello's Football Cleats","1","585858",3,"1"));
        listPro.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220.0,"Men's Football Cleats","1","A59D2D",3,"1"));

        return listPro;
    }

    private void IntentDetails(Product product) {
        Intent intent = new Intent(MainActivity.this, ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_product",product);
        intent.putExtras(bundle);
        startActivity(intent);
    }
  private List<Product> getListPro() {
        List<Product> listPro= new ArrayList<Product>();
        listPro.add(new Product("Dunk nike year of dragon",200.0,"men's shoe","1","CACBCF",1,"1"));
        listPro.add(new Product("Hello",300.0,"hello's shoe","1","FF422B",1,"1"));
        listPro.add(new Product("Hehe boi",300.0,"nguyn's shoe","1","5D90DD",1,"1"));
        listPro.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220.0,"Men's Football Cleats","1","A59D2D",1,"1"));
        listPro.add(new Product("Nike Vapor Edge Elite 360 2",2200.0,"Hello's Football Cleats","1","585858",1,"1"));
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
            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}