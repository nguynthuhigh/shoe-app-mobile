package com.sneaker.shoeapp.Adapter;



import androidx.core.content.ContextCompat;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.ProductDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.io.Serializable;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {

    private List<Product> ListProduct;
    private ClickItemProduct clickItemProduct;
    public ProductAdapter(List<Product> ListProduct, ClickItemProduct clickItemProduct) {
        this.clickItemProduct = clickItemProduct;
        this.ListProduct = ListProduct;
    }
    public void setData(List<Product> list){
        this.ListProduct = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product pro = ListProduct.get(position);
        if(pro  == null){
            return;
        }
        //holder.proImg.setLayoutParams(new ViewGroup.LayoutParams(130, 95));

        holder.proName.setText(pro.getProName());
        holder.proCategory.setText(pro.getCategory());

        holder.proPrice.setText("$"+pro.getPrice()+"");
        int color = Color.parseColor("#"+pro.getColor());
        Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_item_card_custom);

        // Check if the drawable is not null before setting the background
        if (drawable instanceof GradientDrawable) {
            setColorBg((GradientDrawable) drawable, pro, holder.bg_pro);
        }

        holder.proImg.setImageResource(pro.getImage());

        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemProduct.onClickItemProduct(pro);
            }
        });
        holder.btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnAddFav.setImageResource(R.drawable.heart);
            }
        });
    }
    public void setColorBg(GradientDrawable gradientDrawable, Product pro, FrameLayout layout){
        int colorInt = Color.parseColor("#" + pro.getColor());
        gradientDrawable.setColors(new int[]{0xFFFFFFFF, colorInt});
        layout.setBackground(gradientDrawable);
    }


    @Override
    public int getItemCount() {
        if(ListProduct !=null){
            return ListProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        FrameLayout bg_pro;
        ImageView proImg;
        TextView proName;
        TextView proCategory;
        TextView proPrice;
        FrameLayout productCard;
        ImageButton btnAddFav;

       public ProductViewHolder(@NonNull View itemView) {
           super(itemView);
           bg_pro = itemView.findViewById(R.id.bg_proImg);
           proImg = itemView.findViewById(R.id.proImg);
           proName = itemView.findViewById(R.id.proName);
           proCategory = itemView.findViewById(R.id.proCategory);
           proPrice = itemView.findViewById(R.id.proPrice);
           productCard = itemView.findViewById(R.id.productCard);
           btnAddFav = itemView.findViewById(R.id.btnAddFav);
       }
   }
}
