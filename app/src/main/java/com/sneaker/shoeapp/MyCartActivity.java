package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Adapter.CartAdapter;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Interface.ClickItemCart;
import com.sneaker.shoeapp.model.Cart;
import com.sneaker.shoeapp.model.ListProduct;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCartActivity extends AppCompatActivity {
    RecyclerView recyclerMyCart;
    CartAdapter cartAdapter;
    ArrayList<Cart> productArrayList;
    ImageButton decreasePro, increasePro;
    ListProduct productList;
    ImageButton btnBack;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    //    ListView listItem_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        Toolbar main_header = findViewById(R.id.menu_header_back);
        setSupportActionBar(main_header);
        if (user == null){
            Intent intent = new Intent(MyCartActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        addControls();
        addEvents();

    }




    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addControls() {

        recyclerMyCart = findViewById(R.id.recyclerMyCart);
        productArrayList = new ArrayList<>();

        db.collection("User").document(user.getUid()).collection("AddToCart").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    Double quantity = dc.getDocument().getDouble("quantity");
                    Double total_price = dc.getDocument().getDouble("total_price");
                    String proID=dc.getDocument().getString("proID");
                    db.collection("Product").document(proID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String namePro =  documentSnapshot.getString("name");
                            String imgPro = documentSnapshot.getString("image");
                            String color = documentSnapshot.getString("color");
                            productArrayList.add(new Cart(namePro,1.1,"FT",imgPro,color,2,"2",quantity,total_price,dc.getDocument().getId()));
                            cartAdapter.notifyDataSetChanged();
                        }
                    });


                }

            }
        });

        cartAdapter = new CartAdapter(this, productArrayList, new ClickItemCart() {
            @Override
            public void increasePro() {

            }

            @Override
            public void decreasePro() {

            }

            @Override
            public void removePro(int position_item, Cart cart) {
                db.collection("User")
                        .document(user.getUid())
                        .collection("AddToCart")
                        .document(cart.getId_cart())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MyCartActivity.this,"Remove successfully!", Toast.LENGTH_SHORT).show();
                                productArrayList.remove(position_item);
                                cartAdapter.notifyItemRemoved(position_item);
                                reloadCart();
                            }
                        });
            }
        });
        recyclerMyCart.setAdapter(cartAdapter);
        recyclerMyCart.setLayoutManager(new LinearLayoutManager(this));
        btnBack = findViewById(R.id.btnBack);
    }

    private void reloadCart() {
        db.collection("User").document(user.getUid()).collection("AddToCart").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    Double quantity = dc.getDocument().getDouble("quantity");
                    Double total_price = dc.getDocument().getDouble("total_price");
                    String proID=dc.getDocument().getString("proID");
                    db.collection("Product").document(proID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String namePro =  documentSnapshot.getString("name");
                            String imgPro = documentSnapshot.getString("image");
                            String color = documentSnapshot.getString("color");
                            productArrayList.add(new Cart(namePro,1.1,"FT",imgPro,color,2,"2",quantity,total_price,dc.getDocument().getId()));
                            cartAdapter.notifyDataSetChanged();
                        }
                    });


                }

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bag_header) {
            Intent intent = new Intent(MyCartActivity.this, MyCartActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.account_header) {
            Intent intent = new Intent(MyCartActivity.this, MyOrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
   }

}