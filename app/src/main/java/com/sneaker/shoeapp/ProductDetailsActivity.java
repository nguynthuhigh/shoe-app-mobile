package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sneaker.shoeapp.Adapter.CartAdapter;
import com.sneaker.shoeapp.Adapter.FavoriteAdapter;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.databinding.ActivityProductDetailsBinding;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailsActivity extends AppCompatActivity {
    //view binding
    private ActivityProductDetailsBinding binding;
    TextView dt_proName;
    ImageView dt_proImage,favouriteBtn;
    TextView dt_proPrice;
    TextView dt_proCate,txtTextSize;
    FrameLayout bg_pro_details,bg_pro_details_2,bg_pro_details_main;
    CardView proColor;
    Button add_to_cart;
    ImageButton btnBack,btnPopupSize,btnPopupColor;
    Product pro;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    Integer quantity;
    String id, ProName, Category, Image;
    Double Price;
    boolean isInMyFavourite = false;
    private FirebaseAuth firebaseAuth;
    //ArrayList to hold the products
    ArrayList<Product> arr_favorite;
    //adapter to set in recyclerview
    FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        addControls();
        Bundle bundle = getIntent().getExtras();
        pro = (Product) bundle.get("obj_product");
        dt_proPrice.setText(pro.getPrice() + "");
        dt_proCate.setText(pro.getCategory());
        Glide.with(ProductDetailsActivity.this).load(pro.getImage()).into(dt_proImage);

        dt_proName.setText(pro.getProName());

        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_item_2), pro,bg_pro_details);
        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_item_2), pro,bg_pro_details_2);
        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_new), pro,bg_pro_details_main);
        proColor.setCardBackgroundColor(Color.parseColor("#" + pro.getColor()));


        //binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        //get data from intent e.g. ProName
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            checkIsFavourite();
        }

        addEvents();
        xuLyPopupSize();
        xulyPopupColor();

    }

    private void xulyPopupColor() {
        btnPopupColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(ProductDetailsActivity.this,btnPopupColor);
                MenuInflater mnPopupColor=popupMenu.getMenuInflater();
                mnPopupColor.inflate(R.menu.popupcolor,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.contextRed){
                            proColor.setCardBackgroundColor(Color.RED);return true;
                        }
                        if(item.getItemId()==R.id.contextBlue){
                            proColor.setCardBackgroundColor(Color.BLUE);return true;
                        }
                        if(item.getItemId()==R.id.contextBlack){
                            proColor.setCardBackgroundColor(Color.BLACK);return true;
                        }
                        if(item.getItemId()==R.id.contextWhite){
                            proColor.setCardBackgroundColor(Color.WHITE);return true;
                        }
                        if(item.getItemId()==R.id.contextYellow){
                            proColor.setCardBackgroundColor(Color.YELLOW);return true;
                        }
                        if(item.getItemId()==R.id.contextGray){
                            proColor.setCardBackgroundColor(Color.GRAY);return true;
                        }
                        return true;
                    }
                });
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xuLyPopupSize() {
        btnPopupSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(ProductDetailsActivity.this,btnPopupSize);
                MenuInflater mnPopupSize=popupMenu.getMenuInflater();
                mnPopupSize.inflate(R.menu.popupsize,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.contextsize38){
                            txtTextSize.setText("Size: 38");
                            return true;
                        }
                        if (item.getItemId()==R.id.contextsize39){
                            txtTextSize.setText("Size: 39");
                            return true;
                        }
                        if (item.getItemId()==R.id.contextsize40){
                            txtTextSize.setText("Size: 40");
                            return true;
                        }
                        if (item.getItemId()==R.id.contextsize41){
                            txtTextSize.setText("Size: 41");
                            return true;
                        }
                        if (item.getItemId()==R.id.contextsize42){
                            txtTextSize.setText("Size: 42");
                            return true;
                        }
                        if (item.getItemId()==R.id.contextsize43){
                            txtTextSize.setText("Size: 43");
                            return true;
                        }
                        if (item.getItemId()==R.id.contextsize44){
                            txtTextSize.setText("Size: 44");
                            return true;
                        }
                        if (item.getItemId()==R.id.contextsize45){
                            txtTextSize.setText("Size: 45");
                            return true;
                        }


                        return true;
                    }
                });
            }
        });
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
                Map<String, Object> data = new HashMap<>();
                data.put("quantity", 1);
                data.put("total_price", pro.getPrice());
                data.put("proID", pro.getId());

                DocumentReference documentReference = db.collection("User").document(user.getUid());
                CollectionReference newCollection = documentReference.collection("AddToCart");

                newCollection.document(pro.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Object quantityObject = documentSnapshot.get("quantity");

                            int currentQuantity = ((Long) quantityObject).intValue();
                            double total_cart = (double) (pro.getPrice() * (currentQuantity + 1));
                            newCollection.document(pro.getId()).update("total_price", total_cart);
                            newCollection.document(pro.getId()).update("quantity", currentQuantity + 1);
                            Intent intent = new Intent(ProductDetailsActivity.this, MyCartActivity.class);
                            startActivity(intent);
                        } else {
                            newCollection.document(pro.getId()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent intent = new Intent(ProductDetailsActivity.this, MyCartActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
            }
        });

        binding.favouriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser()==null){
                    Toast.makeText(ProductDetailsActivity.this, "You're not logged in", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (isInMyFavourite){
                        //in favourite, remove from favourite
                        MyApplication.removeFavourite(ProductDetailsActivity.this,id,ProName,Image,Category,Price);
                    }
                    else {
                        // not in favourite, af to favourite
                        MyApplication.addToFavourite(ProductDetailsActivity.this, id,ProName,Image,Category,Price);
                    }
                }
            }
        });
    }



    private void checkIsFavourite(){
            //logged in check if its in favourite list or not
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(firebaseAuth.getUid());
        reference.child("Favourites");
        reference.child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isInMyFavourite = snapshot.exists(); //true: if exists, false: if not exists

                if (isInMyFavourite) {

                    //exists in favourite
                    binding.favouriteBtn.setImageDrawable(getDrawable(R.drawable.heart));

                }
                else {
                    //not exists in favourite
                    binding.favouriteBtn.setImageDrawable(getDrawable(R.drawable.favorite));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addControls() {
        dt_proName = findViewById(R.id.dt_proName);
        dt_proCate = findViewById(R.id.dt_proCate);
        dt_proImage = findViewById(R.id.dt_proImage);
        favouriteBtn = findViewById(R.id.favouriteBtn);
        dt_proPrice = findViewById(R.id.dt_proPrice);
        bg_pro_details = findViewById(R.id.bg_pro_details);
        bg_pro_details_2 = findViewById(R.id.bg_pro_details_2);
        bg_pro_details_main = findViewById(R.id.bg_pro_details_main);
        add_to_cart = findViewById(R.id.add_to_cart);
        proColor = findViewById(R.id.proColor);
        btnBack = findViewById(R.id.btnBack);
        btnPopupSize=findViewById(R.id.btnPopupSize);
        txtTextSize=findViewById(R.id.txtTextSize);
        btnPopupColor=findViewById(R.id.btnPopupColor);

    }
}