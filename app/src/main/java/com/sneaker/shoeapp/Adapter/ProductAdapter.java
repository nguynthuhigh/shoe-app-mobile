package com.sneaker.shoeapp.Adapter;



import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.core.content.ContextCompat;


import android.annotation.SuppressLint;
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

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Product> ListProduct;
    private ClickItemProduct clickItemProduct;
    private static int TYPE_USER_POPULAR =1;
    private static int TYPE_USER_CATE =2;
    Context context;
    public ProductAdapter(List<Product> ListProduct, ClickItemProduct clickItemProduct, Context context) {
        this.clickItemProduct = clickItemProduct;
        this.ListProduct = ListProduct;
        this.context = context;
    }
    public void setData(List<Product> list){
        this.ListProduct = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(TYPE_USER_CATE == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_new,parent,false);
            return new ProductViewHolder(view);
        }else if(TYPE_USER_POPULAR == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular,parent,false);
            return new ProductPopularViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(TYPE_USER_CATE == holder.getItemViewType()){
            ProductViewHolder productViewHolder = (ProductViewHolder) holder;
            Product pro = ListProduct.get(position);
            if(pro  == null){
                return;
            }
            productViewHolder.proImg.setImageResource(pro.getImage());
            // @SuppressLint("UseCompatLoadingForDrawables") GradientDrawable gradientDrawable =(GradientDrawable) context.getResources().getDrawable(R.drawable.bg_item_card_custom);


            // int colorInt = Color.parseColor("#" + pro.getColor());
            //  gradientDrawable.setColors(new int[]{ 0xFFFFFFFF, colorInt});
            //  holder.bg_pro.setBackground(gradientDrawable);

            Integer color =Color.parseColor("#" + pro.getColor());
            int originalRed = Color.red(color);
            int originalGreen = Color.green(color);
            int originalBlue = Color.blue(color);
            float brightnessFactor = 2f;
            int brighterRed = Math.min((int) (originalRed * brightnessFactor), 255);
            int brighterGreen = Math.min((int) (originalGreen * brightnessFactor), 255);
            int brighterBlue = Math.min((int) (originalBlue * brightnessFactor), 255);
            int brighterColorInt = Color.rgb(brighterRed, brighterGreen, brighterBlue);

            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[]{ color, brighterColorInt});


            productViewHolder.productCard.setBackground(gradientDrawable);
            productViewHolder.proName.setText(pro.getProName());

            productViewHolder.proCategory.setText(pro.getCategory());
            productViewHolder.proPrice.setText("$"+pro.getPrice()+"");


            productViewHolder.productCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemProduct.onClickItemProduct(pro);
                }
            });
            productViewHolder.btnAddFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productViewHolder.btnAddFav.setImageResource(R.drawable.heart);
                }
            });
        }
        else if(TYPE_USER_POPULAR == holder.getItemViewType()){
            Product pro = ListProduct.get(position);
            ProductPopularViewHolder productPopularViewHolder = (ProductPopularViewHolder) holder;
            productPopularViewHolder.proImg_popular.setImageResource(pro.getImage());
            productPopularViewHolder.proName_popular.setText(pro.getProName());
            productPopularViewHolder.proCate_popular.setText(pro.getCategory());
            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[]{ Color.parseColor("#" + pro.getColor()),Color.WHITE});
            productPopularViewHolder.proBg_popular.setBackground(gradientDrawable);
            productPopularViewHolder.proPrice_popular.setText(pro.getPrice()+"");
            productPopularViewHolder.item_popular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemProduct.onClickItemProduct(pro);
                }
            });
        }
    }





    @Override
    public int getItemViewType(int position) {
        Product product = ListProduct.get(position);
        if(product.isCate()) return TYPE_USER_CATE;
        else return TYPE_USER_POPULAR;
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
       ///    bg_pro = itemView.findViewById(R.id.bg_proImg);
           proImg = itemView.findViewById(R.id.proImg);
           proName = itemView.findViewById(R.id.proName);
           proCategory = itemView.findViewById(R.id.proCategory);
           proPrice = itemView.findViewById(R.id.proPrice);
           productCard = itemView.findViewById(R.id.productCard);
           btnAddFav = itemView.findViewById(R.id.btnAddFav);
       }

   }
    public class ProductPopularViewHolder extends RecyclerView.ViewHolder{

        TextView proPrice_popular,proCate_popular,proName_popular;
        ImageView proImg_popular;
        FrameLayout proBg_popular;
        FrameLayout item_popular;

        public ProductPopularViewHolder(@NonNull View itemView) {
            super(itemView);
            proPrice_popular = itemView.findViewById(R.id.proPrice_popular);
            proCate_popular = itemView.findViewById(R.id.proCate_popular);
            proName_popular =itemView.findViewById(R.id.proName_popular);
            proImg_popular = itemView.findViewById(R.id.proImg_popular);
            proBg_popular = itemView.findViewById(R.id.probg_popular);
            item_popular = itemView.findViewById(R.id.item_popular);
        }

    }

}
