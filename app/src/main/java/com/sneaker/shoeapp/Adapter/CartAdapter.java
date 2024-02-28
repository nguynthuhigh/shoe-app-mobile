package com.sneaker.shoeapp.Adapter;


import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sneaker.shoeapp.Interface.ClickItemCart;
import com.sneaker.shoeapp.MyCartActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    //    Activity context;
    Product product;
    ArrayList<Product> productArrayList;
    Integer resource;
    Activity context;
    TextView proName_cart, proPrice_cart, viewQuantity;
    ImageView proImg_cart;
    FrameLayout bg_item_card_custom;
    ImageButton increasePro, decreasePro;
    ClickItemCart clickItemCart;
    public CartAdapter(Activity context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }
    //    public CartAdapter(@NonNull Activity context, int resource) {
//        super(context, resource);
//        this.context = context;
//        this.resource = resource;
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewProduct = layoutInflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(viewProduct);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        holder.proImg_cart.setImageResource(product.getImage());
        holder.proName_cart.setText(product.getProName());
        holder.proPrice_cart.setText(product.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView proName_cart, proPrice_cart, viewQuantity;
        ImageView proImg_cart;
        ImageButton decreasePro, increasePro;
        FrameLayout bg_item_card_custom;
        Product product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proName_cart = itemView.findViewById(R.id.proName_cart);
            proPrice_cart = itemView.findViewById(R.id.proPrice_cart);
            viewQuantity = itemView.findViewById(R.id.viewQuantity);
            proImg_cart = itemView.findViewById(R.id.proImg_cart);
            decreasePro = itemView.findViewById(R.id.decreasePro);
            increasePro = itemView.findViewById(R.id.increasePro);
            bg_item_card_custom = itemView.findViewById(R.id.bg_item_card_custom);
        }
    }




//    @NonNull
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater layoutInflater = this.context.getLayoutInflater();
//        View viewCustom = layoutInflater.inflate(this.resource, null);
//
//        proName_cart = viewCustom.findViewById(R.id.proName_cart);
//        proPrice_cart = viewCustom.findViewById(R.id.proPrice_cart);
//        proImg_cart = viewCustom.findViewById(R.id.proImg_cart);
//        FrameLayout bg_item_card_custom;
//        increasePro = viewCustom.findViewById(R.id.increasePro);
//        decreasePro = viewCustom.findViewById(R.id.decreasePro);
//        viewQuantity = viewCustom.findViewById(R.id.viewQuantity);
//        Product pro = getItem(position);
//        if (pro == null) {
//            return layoutInflater.inflate(R.layout.layout_cart_null, null);
//        }
//        proName_cart.setText(pro.getProName());
//        proPrice_cart.setText(pro.getPrice() + "");
//        proImg_cart.setImageResource(pro.getImage());

//        setColorBg((GradientDrawable) context.getResources().getDrawable(R.drawable.bg_item_card_custom), pro, bg_item_card_custom);
//        increasePro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int qty = Integer.parseInt(viewQuantity.getText().toString());
//                qty++;
//                viewQuantity.setText(qty + "");
//            }
//        });
//        decreasePro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int qty = Integer.parseInt(viewQuantity.getText().toString());
//                if (qty > 1) {
//                    qty--;
//                    viewQuantity.setText(qty + "");
//                } else {
////                    Toast.makeText(getContext(), "Remove", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        return viewCustom;
//    }

    public void setColorBg(GradientDrawable gradientDrawable, Product pro, FrameLayout layout) {
        int colorInt = Color.parseColor("#" + pro.getColor());
        gradientDrawable.setColors(new int[]{0xFFFFFFFF, colorInt});
        layout.setBackground(gradientDrawable);
    }
}
