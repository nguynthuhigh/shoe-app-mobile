package com.sneaker.shoeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    private Context context;
    private List<Product> ListProduct;

    public ProductAdapter(Context context) {
        this.context = context;
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
        holder.proImg.setImageResource(pro.getImage());
        holder.proName.setText(pro.getProName());
        holder.proCategory.setText(pro.getCategory());

        holder.proPrice.setText(pro.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        if(ListProduct !=null){
            return ListProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView proImg;
        TextView proName;
        TextView proCategory;
        TextView proPrice;
       public ProductViewHolder(@NonNull View itemView) {
           super(itemView);
           proImg = itemView.findViewById(R.id.proImg);
           proName = itemView.findViewById(R.id.proName);
            proCategory = itemView.findViewById(R.id.proCategory);
            proPrice = itemView.findViewById(R.id.proPrice);


       }
   }
}