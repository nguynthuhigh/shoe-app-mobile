package com.sneaker.shoeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Cart;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    ArrayList<Cart> cartArrayList;
    Context context;
    public CheckoutAdapter(ArrayList<Cart> cartArrayList, Context context) {
        this.cartArrayList = cartArrayList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = cartArrayList.get(position);
        Glide.with(context).load(cart.getImage()).into(holder.proImg_order);
        holder.txtShoename.setText(cart.getProName());
        holder.proPrice_order.setText("$"+cart.getTotal_cart());
        holder.proQuan_order.setText("x"+ (int)cart.getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout pro_order;
        ImageView proImg_order;
        TextView txtShoename,proPrice_order,proQuan_order;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proQuan_order = itemView.findViewById(R.id.proQuan_order);
            pro_order = itemView.findViewById(R.id.pro_order);
            proImg_order = itemView.findViewById(R.id.proImg_order);
            txtShoename = itemView.findViewById(R.id.txtShoename);
            proPrice_order = itemView.findViewById(R.id.proPrice_order);
        }
    }
}
