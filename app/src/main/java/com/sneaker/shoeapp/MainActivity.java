package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Admin.AdminCustomerActivity;
import com.sneaker.shoeapp.Admin.AdminOrderActivity;
import com.sneaker.shoeapp.Fragment.AllFragment;
import com.sneaker.shoeapp.Fragment.FootballFragment;
import com.sneaker.shoeapp.Fragment.RunningFragment;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageButton btnAddFav, btnSearch;
    Button btnSeller,categoryAll,categoryFootball,categoryRunning,btnPayment,btnCheckout,btnOrderDetails,inputCate;
    EditText searchProduct,searchProduct_2;
    FrameLayout productCard,bs_item;
    ImageButton finishLayout;
    ImageView bs_img;
    TextView bs_name,bs_price,bag_count;
    RecyclerView rcv_popular,rcv_banner;
    ProductAdapter productAdapter,productAdapter_banner;
    CardView bg_proImg;
    FirebaseFirestore db;
    FirebaseAuth mauth;
    FirebaseUser user;
    List<Product>listProBanner;
    Product pro_bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_header = findViewById(R.id.menu_header);
        setSupportActionBar(main_header);
        db = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        user =mauth.getCurrentUser();
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
        bs_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_product",pro_bs);
                Intent intent = new Intent(MainActivity.this, ProductDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
        bag_count = findViewById(R.id.bag_count);

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
        listProBanner = new ArrayList<>();
        productAdapter_banner = new ProductAdapter(listProBanner, new ClickItemProduct() {
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
        Random random = new Random();
        long randomValue = random.nextLong();
        CollectionReference collectionReference = db.collection("Product");
        Query query = collectionReference.document().getParent().orderBy("price").startAt(randomValue).limit(3);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot dc : task.getResult()) {

                        listProBanner.add(new Product(dc.getString("proName"),Double.valueOf(dc.getString("price")),dc.getString("category"),dc.getString("image"),dc.getString("color"),3,dc.getId()));
                        productAdapter_banner.notifyDataSetChanged();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Best Seller Item
        bs_img = findViewById(R.id.bs_img);
        bs_name = findViewById(R.id.bs_name);
        bs_price = findViewById(R.id.bs_price);
        bs_item = findViewById(R.id.bs_item);

        Query bs_query = collectionReference.document().getParent().limit(3);
        bs_query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (QueryDocumentSnapshot dc : task.getResult()) {
                    Glide.with(MainActivity.this).load(dc.getString("image")).into(bs_img);
                    bs_name.setText(dc.getString("proName"));
                    bs_price.setText("$"+dc.getString("price"));
                    pro_bs =new Product(dc.getString("proName"),Double.valueOf(dc.getString("price")),dc.getString("category"),dc.getString("image"),dc.getString("color"),0,dc.getId());
                }
            }
        });
        //Bag count user
      /*  CollectionReference collectionReference1 = db.collection("User").document(user.getUid()).collection("AddToCart");
        collectionReference1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                double quantity =0;
                for (DocumentSnapshot dc: task.getResult()
                     ) {
                     quantity = quantity +dc.getDouble("quantity");
                    bag_count.setText(quantity+"");
                }

            }
        });*/
    }

    private List<Product> getListPro_banners() {
        List<Product> listPro= new ArrayList<Product>();



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
      Random random = new Random();
      int randomValue = random.nextInt(100);
      randomValue +=1;
      CollectionReference collectionReference = db.collection("Product");
      Query query = collectionReference.document().getParent().orderBy("color").startAt(randomValue).limit(5);
      query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
              if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot dc : task.getResult()) {

                      listPro.add(new Product(dc.getString("proName"),Double.valueOf(dc.getString("price")),dc.getString("category"),dc.getString("image"),dc.getString("color"),1,dc.getId()));
                      productAdapter.notifyDataSetChanged();
                  }
              }
              else{
                  Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
              }
          }
      });
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