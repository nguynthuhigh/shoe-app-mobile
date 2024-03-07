package com.sneaker.shoeapp;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

//application class rún before your launcher activity
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }



    public static void addToFavourite(Context context, String id, String ProName, String Image, String Category, Double Price){
        //you can add only if user logged in
        //1. Check if user is logged in
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            //not logged in, cant add to favourite
            Toast.makeText(context, "You're not logged in", Toast.LENGTH_SHORT).show();
        }
        else {
            long timestamp = System.currentTimeMillis();
            //setup data to add in firebase db of current user for favourite
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id"," "+id);
            hashMap.put("proName"," "+ProName);
            hashMap.put("image"," "+Image);
            hashMap.put("category"," "+Category);
            hashMap.put("price"," "+Price);

            //save to db
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
            ref.child(firebaseAuth.getUid()).child("Favourites").child(id)
                    .setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Add to favourites", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Add to favourites failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        }

    public  static  void removeFavourite(Context context, String id, String ProName, String Image, String Category, Double Price){
        //you can remove only if user logged in
        //1. Check if user is logged in
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            //not logged in, cant remove from favourite
            Toast.makeText(context, "You're not logged in", Toast.LENGTH_SHORT).show();
        }
        else {
            //remove from db
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
            ref.child(firebaseAuth.getUid()).child("Favourites").child(id)
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Remove from your favourites", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Remove from favourites failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        }
    }

