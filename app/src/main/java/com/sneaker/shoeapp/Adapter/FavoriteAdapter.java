package com.sneaker.shoeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.sneaker.shoeapp.FirebaseManager;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Activity context;
    Context mcontext;
    ArrayList<Product>arr_favourite = new ArrayList<>();
    List<String> myFavouriteProductIds = new ArrayList<>();
    public FavoriteAdapter(Activity context,ArrayList<Product>arr_favorite, Context mcontext){
        this.context=context;
        this.arr_favourite=arr_favorite;
        this.mcontext=context;
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
        //holder.proImg.setImageResource(pd.getImage());
        holder.proName.setText(product.getProName());
        holder.proCategory.setText(product.getCategory());
        holder.proPrice.setText(product.getPrice()+"");
    }


    @Override
    public int getItemCount() {
        return arr_favourite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView proImg;
        TextView proPrice,proName,proCategory;
        ImageButton removeToFVbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proImg=itemView.findViewById(R.id.proImg);
            proName=itemView.findViewById(R.id.proName);
            proCategory=itemView.findViewById(R.id.proCategory);
            proPrice=itemView.findViewById(R.id.proPrice);
            removeToFVbtn=(ImageButton) itemView.findViewById(R.id.removeToFVbtn);

        }
    }
}
