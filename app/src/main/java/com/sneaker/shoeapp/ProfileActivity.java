package com.sneaker.shoeapp;

import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;
import com.sneaker.shoeapp.Admin.AdminHomeActivity;

public class ProfileActivity extends AppCompatActivity {
    Button btnEdit_profile,btnView_order,btnLog_out,btnView_favourite,manageAdmin;

    ImageButton btnBack;
    TextView name_user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLog_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        btnEdit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        btnView_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });
        btnView_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MyOrderActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        manageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addControls() {
        manageAdmin = findViewById(R.id.manageAdmin);
        btnEdit_profile = findViewById(R.id.btnEdit_profile);
        btnView_order = findViewById(R.id.btnView_order);
        btnLog_out = findViewById(R.id.btnLog_out);
        btnView_favourite = findViewById(R.id.btnView_favourite);
        btnBack=findViewById(R.id.btnBack);
        name_user = findViewById(R.id.name_user);

        if(user !=null){
            if(user.getEmail().equals("admin@gmail.com")){
                manageAdmin.setVisibility(VISIBLE);
            }

            DocumentReference documentReference =db.collection("User").document(user.getUid());
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    name_user.setText(documentSnapshot.getString("username"));
                }
            });
        }
        else{
            finish();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }
}