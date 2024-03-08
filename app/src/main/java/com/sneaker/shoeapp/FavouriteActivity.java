package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sneaker.shoeapp.Adapter.FavoriteAdapter;
import com.sneaker.shoeapp.model.Product;


import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    RecyclerView recyclerFavorite ;
    FavoriteAdapter favoriteAdapter;
    ArrayList<Product> arr_Favourite;
    DatabaseReference favouriteRef;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    ImageButton btnBack, removeToFVbtn;
    Product product=new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        // Firebase
        favouriteRef = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        // RecyclerView
        RecyclerView recyclerFavorite = findViewById(R.id.recyclerFavorite);
        recyclerFavorite.setLayoutManager(new LinearLayoutManager(this));

        arr_Favourite = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(arr_Favourite);
        recyclerFavorite.setAdapter(favoriteAdapter);
        addControls();
        addEvents();
        // Hiển thị danh sách yêu thích
        displayFavourites(product);
    }

    private void displayFavourites(Product pro) {
        // Đọc danh sách yêu thích từ Firebase
        favouriteRef.child("favourites").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arr_Favourite.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    arr_Favourite.add(product);
                }

                // Kiểm tra và thêm product mới vào danh sách nếu không tồn tại
                if (pro != null && !arr_Favourite.contains(pro)) {
                    arr_Favourite.add(pro);
                }

                // Thông báo adapter về sự thay đổi trong dữ liệu
                favoriteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi đọc dữ liệu
            }
        });
    }

    // Attach a ValueEventListener to fetch favorite products from Firebase

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavouriteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    // Người dùng đã đăng nhập
                    String uid = currentUser.getUid();
                    // Tiếp tục xử lý...
                } else {
                    // Người dùng chưa đăng nhập
                    // ...
                }
            }
        };
    }

    private void addControls() {
        recyclerFavorite = findViewById(R.id.recyclerFavorite);
        recyclerFavorite.setLayoutManager(new LinearLayoutManager(this));
        btnBack = findViewById(R.id.btnBack);

        arr_Favourite = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(arr_Favourite);
        //favouriteAdapter = new FavoriteAdapter(this, arr_Favourite);
        recyclerFavorite.setAdapter(favoriteAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerFavorite.setLayoutManager(gridLayoutManager);
    }
}