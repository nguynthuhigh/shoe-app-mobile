package com.sneaker.shoeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.sneaker.shoeapp.FirebaseManager;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sneaker.shoeapp.MainActivity;
import com.sneaker.shoeapp.ProductDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Activity context;

    ArrayList<Product>arr_favourite = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    public FavoriteAdapter(Activity context,ArrayList<Product>arr_favorite){
        this.context=context;
        this.arr_favourite=arr_favorite;
    }


    public FavoriteAdapter(ArrayList<Product> arrFavourite) {
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewFavourite=layoutInflater.inflate(R.layout.item_card,parent,false);
        ViewHolder viewHolderFV=new ViewHolder(viewFavourite);
        return viewHolderFV;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product =arr_favourite.get(position);
        Glide.with(context).load(product.getImage()).into(holder.proImg);
        holder.proName.setText(product.getProName());
        holder.proCategory.setText(product.getCategory());
        holder.proPrice.setText(product.getPrice()+"");
        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_product",product);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.removeToFVbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("User")
                        .document(user.getUid())
                        .collection("Favorite")
                        .document(product.getId())
                        .delete();
                arr_favourite.remove(position);
                notifyItemRemoved(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return arr_favourite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        FrameLayout productCard;
        ImageView proImg;
        TextView proPrice,proName,proCategory;
        ImageButton removeToFVbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productCard = itemView.findViewById(R.id.productCard);
            proImg=itemView.findViewById(R.id.proImg);
            proName=itemView.findViewById(R.id.proName);
            proCategory=itemView.findViewById(R.id.proCategory);
            proPrice=itemView.findViewById(R.id.proPrice);
            removeToFVbtn=(ImageButton) itemView.findViewById(R.id.removeToFVbtn);

        }
    }
}
