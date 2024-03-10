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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.MyCartActivity;
import com.sneaker.shoeapp.ProductDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Product> ListProduct;
    private ClickItemProduct clickItemProduct;
    private static int TYPE_USER_POPULAR =1;
    private static int TYPE_USER_CATE =2;
    private static int TYPE_PRO_BANNER = 3;
    private static int TYPE_PRO_ORDER = 4;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mauth = FirebaseAuth.getInstance();
    private FirebaseUser user = mauth.getCurrentUser();
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
        }else if(TYPE_PRO_BANNER == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_home,parent,false);
            return new ProBannerViewHolder(view);
        }
        else if(TYPE_PRO_ORDER == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_details,parent,false);
            return new ProductOrderViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product pro = ListProduct.get(position);
        if(TYPE_USER_CATE == holder.getItemViewType()){
            ProductViewHolder productViewHolder = (ProductViewHolder) holder;
            if(pro  == null){
                return;
            }
            Glide.with(context)
                    .load(pro.getImage())
                    .into(productViewHolder.proImg);

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
            if(user !=null){
                DocumentReference documentReference = db.collection("User").document(user.getUid());
                CollectionReference newCollection = documentReference.collection("Favorite");
                newCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {

                            return;
                        }
                        for (DocumentSnapshot document : value.getDocuments()) {
                            if (document.getId().equals(pro.getId())) {
                                productViewHolder.btnAddFav.setImageResource(R.drawable.heart);
                                break;
                            }
                            productViewHolder.btnAddFav.setImageResource(R.drawable.favorite_white);
                        }
                    }
                });
                productViewHolder.btnAddFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DocumentReference documentReference = db.collection("User").document(user.getUid());
                        CollectionReference newCollection = documentReference.collection("Favorite");
                        Map<String,Object> data = new HashMap<>();
                        data.put("id",pro.getId());
                        newCollection.document(pro.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    newCollection.document(pro.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context,"Remove Favorite",Toast.LENGTH_SHORT).show();
                                            productViewHolder.btnAddFav.setImageResource(R.drawable.favorite);
                                        }
                                    });
                                }
                                else{
                                    newCollection.document(pro.getId()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
                                            productViewHolder.btnAddFav.setImageResource(R.drawable.heart);
                                        }
                                    });
                                }
                            }
                        });
                    }

                });
            }
        }
        else if(TYPE_USER_POPULAR == holder.getItemViewType()){

            ProductPopularViewHolder productPopularViewHolder = (ProductPopularViewHolder) holder;
            Glide.with(context)
                    .load(pro.getImage())
                    .into(productPopularViewHolder.proImg_popular);


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
            if(user !=null ) {
                DocumentReference documentReference = db.collection("User").document(user.getUid());
                CollectionReference newCollection = documentReference.collection("AddToCart");
                newCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {

                            return;
                        }
                        for (DocumentSnapshot document : value.getDocuments()) {
                            if (document.getId().equals(pro.getId())) {
                                productPopularViewHolder.add_to_cart_popular.setImageResource(R.drawable.check_circle);
                                break;
                            }
                            productPopularViewHolder.add_to_cart_popular.setImageResource(R.drawable.add);
                        }
                    }
                });


                productPopularViewHolder.add_to_cart_popular.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> data = new HashMap<>();
                        data.put("quantity",1);
                        data.put("total_price",pro.getPrice());
                        data.put("proID",pro.getId());
                        DocumentReference documentReference = db.collection("User").document(user.getUid());
                        CollectionReference newCollection = documentReference.collection("AddToCart");
                        newCollection.document(pro.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    Object quantityObject = documentSnapshot.get("quantity");

                                    Toast.makeText(context, "Product is available", Toast.LENGTH_SHORT).show();


                                }
                                else{
                                    newCollection.document(pro.getId()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show();
                                            productPopularViewHolder.add_to_cart_popular.setImageResource(R.drawable.check_circle);
                                        }
                                    });
                                }

                            }
                        });
                    }
                });
                //load Img Added Fav
                DocumentReference documentFav = db.collection("User").document(user.getUid());
                CollectionReference collectionFav = documentFav.collection("Favorite");
                collectionFav.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {

                            return;
                        }
                        for (DocumentSnapshot document : value.getDocuments()) {
                            if (document.getId().equals(pro.getId())) {
                                productPopularViewHolder.addFav.setImageResource(R.drawable.heart);
                                break;
                            }
                            productPopularViewHolder.addFav.setImageResource(R.drawable.favorite);
                        }
                    }
                });
                productPopularViewHolder.addFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DocumentReference documentReference = db.collection("User").document(user.getUid());
                        CollectionReference newCollection = documentReference.collection("Favorite");
                        Map<String,Object> data = new HashMap<>();
                        data.put("id",pro.getId());
                        newCollection.document(pro.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    newCollection.document(pro.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context,"Remove Favorite",Toast.LENGTH_SHORT).show();
                                            productPopularViewHolder.addFav.setImageResource(R.drawable.favorite);
                                        }
                                    });
                                }
                                else{
                                    newCollection.document(pro.getId()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
                                            productPopularViewHolder.addFav.setImageResource(R.drawable.heart);
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        }
        if(TYPE_PRO_BANNER == holder.getItemViewType()){
            ProBannerViewHolder proBannerViewHolder = (ProBannerViewHolder) holder;
            Glide.with(context)
                    .load(pro.getImage())
                    .into(proBannerViewHolder.item_banner_img);
            proBannerViewHolder.item_banner_name.setText(pro.getProName());
            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    new int[]{ Color.parseColor("#" + pro.getColor()),Color.WHITE});
            proBannerViewHolder.item_banner.setBackground(gradientDrawable);
            proBannerViewHolder.item_banner_price.setText("$"+pro.getPrice());
            proBannerViewHolder.item_banner.setOnClickListener(new View.OnClickListener() {
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
        if(product.getType() == 1) return TYPE_USER_POPULAR;
        else if(product.getType() == 2) {
            return TYPE_USER_CATE;
        }
        else if(product.getType() == 3){
            return TYPE_PRO_BANNER;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if(ListProduct !=null){
            return ListProduct.size();
        }
        return 0;
    }
    public class ProBannerViewHolder extends  RecyclerView.ViewHolder{
        RelativeLayout item_banner;
        TextView item_banner_name,item_banner_price;
        ImageView item_banner_img;

        public ProBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            item_banner_img = itemView.findViewById(R.id.item_banner_img);
            item_banner_name = itemView.findViewById(R.id.item_banner_name);
            item_banner_price = itemView.findViewById(R.id.item_banner_price);
            item_banner = itemView.findViewById(R.id.item_banner);
        }
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
        ImageButton add_to_cart_popular;
        ImageButton addFav;

        public ProductPopularViewHolder(@NonNull View itemView) {
            super(itemView);
            addFav = itemView.findViewById(R.id.addFav);
            proPrice_popular = itemView.findViewById(R.id.proPrice_popular);
            proCate_popular = itemView.findViewById(R.id.proCate_popular);
            proName_popular =itemView.findViewById(R.id.proName_popular);
            proImg_popular = itemView.findViewById(R.id.proImg_popular);
            proBg_popular = itemView.findViewById(R.id.probg_popular);
            item_popular = itemView.findViewById(R.id.item_popular);
            add_to_cart_popular = itemView.findViewById(R.id.add_to_cart_popular);
        }

    }
    public class ProductOrderViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout pro_order;
        ImageView proImg_order;
        TextView txtShoename,proPrice_order,proQuan_order;


        public ProductOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            proQuan_order = itemView.findViewById(R.id.proQuan_order);
            pro_order = itemView.findViewById(R.id.pro_order);
            proImg_order = itemView.findViewById(R.id.proImg_order);
            txtShoename = itemView.findViewById(R.id.txtShoename);
            proPrice_order = itemView.findViewById(R.id.proPrice_order);

        }

    }

}
