package com.sneaker.shoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sneaker.shoeapp.model.Order;
import com.sneaker.shoeapp.model.Product;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailsActivity extends AppCompatActivity {
    TextView dt_proName;
    ImageView dt_proImage;
    TextView dt_proPrice;
    TextView dt_proCate, txtTextSize;
    FrameLayout bg_pro_details, bg_pro_details_2, bg_pro_details_main;
    CardView proColor;
    Button add_to_cart, btnBuyNow;
    ImageButton btnBack, btnPopupSize, btnPopupColor;
    Product pro;
    Order order;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    Integer quantity;
    private static final String TAG = "ProductDetailsActivity";

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

        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_item_2), pro, bg_pro_details);
        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_item_2), pro, bg_pro_details_2);
        setColorBg((GradientDrawable) getResources().getDrawable(R.drawable.bg_details_new), pro, bg_pro_details_main);
        proColor.setCardBackgroundColor(Color.parseColor("#" + pro.getColor()));
        addEvents();
        xuLyPopupSize();
        xulyPopupColor();
    }

    private void xulyPopupColor() {
        btnPopupColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ProductDetailsActivity.this, btnPopupColor);
                MenuInflater mnPopupColor = popupMenu.getMenuInflater();
                mnPopupColor.inflate(R.menu.popupcolor, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.contextRed) {
                            proColor.setCardBackgroundColor(Color.RED);
                            return true;
                        }
                        if (item.getItemId() == R.id.contextBlue) {
                            proColor.setCardBackgroundColor(Color.BLUE);
                            return true;
                        }
                        if (item.getItemId() == R.id.contextBlack) {
                            proColor.setCardBackgroundColor(Color.BLACK);
                            return true;
                        }
                        if (item.getItemId() == R.id.contextWhite) {
                            proColor.setCardBackgroundColor(Color.WHITE);
                            return true;
                        }
                        if (item.getItemId() == R.id.contextYellow) {
                            proColor.setCardBackgroundColor(Color.YELLOW);
                            return true;
                        }
                        if (item.getItemId() == R.id.contextGray) {
                            proColor.setCardBackgroundColor(Color.GRAY);
                            return true;
                        }
                        return true;
                    }
                });
            }
        });
    }

    private void xuLyPopupSize() {
        btnPopupSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ProductDetailsActivity.this, btnPopupSize);
                MenuInflater mnPopupSize = popupMenu.getMenuInflater();
                mnPopupSize.inflate(R.menu.popupsize, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.contextsize38) {
                            txtTextSize.setText("Size: 38");
                            return true;
                        }
                        if (item.getItemId() == R.id.contextsize39) {
                            txtTextSize.setText("Size: 39");
                            return true;
                        }
                        if (item.getItemId() == R.id.contextsize40) {
                            txtTextSize.setText("Size: 40");
                            return true;
                        }
                        if (item.getItemId() == R.id.contextsize41) {
                            txtTextSize.setText("Size: 41");
                            return true;
                        }
                        if (item.getItemId() == R.id.contextsize42) {
                            txtTextSize.setText("Size: 42");
                            return true;
                        }
                        if (item.getItemId() == R.id.contextsize43) {
                            txtTextSize.setText("Size: 43");
                            return true;
                        }
                        if (item.getItemId() == R.id.contextsize44) {
                            txtTextSize.setText("Size: 44");
                            return true;
                        }
                        if (item.getItemId() == R.id.contextsize45) {
                            txtTextSize.setText("Size: 45");
                            return true;
                        }
                        return true;
                    }
                });
            }
        });
    }

    public void setColorBg(GradientDrawable gradientDrawable, Product pro, FrameLayout layout) {
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
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product yeuemkhonganh = new Product();


                CollectionReference collectionOrderReference = db.collection("User").document(user.getUid())
                        .collection("Order");

                Map<String,Object> orderInfo = new HashMap<>();
                orderInfo.put("Date", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
                orderInfo.put("Quantity", 1);
                orderInfo.put("Price", pro.getPrice());
                orderInfo.put("status", false);
                orderInfo.put("Address",123);
                Map<String,Object> productInfo = new HashMap<>();
                productInfo.put("ID", pro.getId());
                productInfo.put("Quantity", 1);
                collectionOrderReference.add(orderInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        collectionOrderReference.document(task.getResult().getId())
                                .collection("listPro").document(pro.getId()).set(productInfo);
                        Toast.makeText(ProductDetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                });
                //                collectionReference.add(data)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w(TAG, "Error adding document", e);
//                            }
//                        });
            }

        });
    }


    private void addControls() {
        dt_proName = findViewById(R.id.dt_proName);
        dt_proCate = findViewById(R.id.dt_proCate);
        dt_proImage = findViewById(R.id.dt_proImage);
        dt_proPrice = findViewById(R.id.dt_proPrice);
        bg_pro_details = findViewById(R.id.bg_pro_details);
        bg_pro_details_2 = findViewById(R.id.bg_pro_details_2);
        bg_pro_details_main = findViewById(R.id.bg_pro_details_main);
        add_to_cart = findViewById(R.id.add_to_cart);
        proColor = findViewById(R.id.proColor);
        btnBack = findViewById(R.id.btnBack);
        btnPopupSize = findViewById(R.id.btnPopupSize);
        txtTextSize = findViewById(R.id.txtTextSize);
        btnPopupColor = findViewById(R.id.btnPopupColor);
        btnBuyNow = findViewById(R.id.btnBuyNow);
    }
}