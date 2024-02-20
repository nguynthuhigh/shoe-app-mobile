package com.sneaker.shoeapp.Adapter;



import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.io.Serializable;

public class CartAdapter extends ArrayAdapter<Product> {
    Integer resource;
    Activity context;
    TextView proName_cart,proPrice_cart;
    ImageView proImg_cart;
    FrameLayout bg_item_card_custom;
    public CartAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View viewCustom =layoutInflater.inflate(this.resource,null);

        proName_cart = viewCustom.findViewById(R.id.proName_cart);
        proPrice_cart = viewCustom.findViewById(R.id.proPrice_cart);
        proImg_cart = viewCustom.findViewById(R.id.proImg_cart);
        bg_item_card_custom = viewCustom.findViewById(R.id.bg_item_card_custom);
        Product pro = getItem(position);
        if(pro == null){
            return layoutInflater.inflate(R.layout.layout_cart_null,null);
        }
        proName_cart.setText(pro.getProName());
        proPrice_cart.setText(pro.getPrice()+"");
        proImg_cart.setImageResource(pro.getImage());
        setColorBg((GradientDrawable) context.getResources().getDrawable(R.drawable.bg_item_card_custom), pro,bg_item_card_custom);
        return viewCustom;
    }
    public void setColorBg(GradientDrawable gradientDrawable, Product pro, FrameLayout layout){
        int colorInt = Color.parseColor("#" + pro.getColor());
        gradientDrawable.setColors(new int[]{0xFFFFFFFF, colorInt});
        layout.setBackground(gradientDrawable);
    }
}
