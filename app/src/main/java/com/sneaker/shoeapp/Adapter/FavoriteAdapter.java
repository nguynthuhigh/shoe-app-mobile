package com.sneaker.shoeapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Activity context;
    ArrayList<Product>arr_favorite;
    public FavoriteAdapter(Activity context,ArrayList<Product>arr_favorite){
        this.context=context;
        this.arr_favorite=arr_favorite;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewFavo=layoutInflater.inflate(R.layout.item_card,parent,false);
        ViewHolder viewHolderFV=new ViewHolder(viewFavo);
        return viewHolderFV;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product pd=arr_favorite.get(position);
        holder.proImg.setImageResource(pd.getImage());
        holder.proName.setText(pd.getProName());
        holder.proCategory.setText(pd.getCategory());
        holder.proPrice.setText(pd.getPrice()+"");
        holder.proHint.setText(pd.getProName());
    }

    @Override
    public int getItemCount() {
        return arr_favorite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView proImg;
        TextView proHint, proPrice,proName,proCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proImg=itemView.findViewById(R.id.proImg);
            proName=itemView.findViewById(R.id.proName);
            proCategory=itemView.findViewById(R.id.proCategory);
            proPrice=itemView.findViewById(R.id.proPrice);
            proHint=itemView.findViewById(R.id.proHint);

        }
    }
}
