package com.sneaker.shoeapp.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.ProductDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.zip.GZIPInputStream;


public class AllFragment extends Fragment {
    RecyclerView rcv_all;
    View view;
    ProductAdapter productAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Product> productList;
    StorageReference storageRef;
    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all, container, false);
        rcv_all = view.findViewById(R.id.rcv_all);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_product",product);
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        },getContext());

        CollectionReference collection = db.collection("Product");
        Query query = collection.document().getParent().limit(4);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
               if(error !=null){
                   return;
               }
               for(DocumentChange dc: value.getDocumentChanges()){

                   String namePro = dc.getDocument().getString("proName");
                //    Double price = Double.valueOf(dc.getDocument().getString("price"));
                   String color = dc.getDocument().getString("color");
                   String image = dc.getDocument().getString("image");
                   String cate = dc.getDocument().getString("category");
                   String id = dc.getDocument().getId();
                   Double price = Double.valueOf(dc.getDocument().getString("price"));
                   productList.add(new Product(namePro, price, cate, image, color, 2,id));
               }
               productAdapter.notifyDataSetChanged();
           }
       });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcv_all.setLayoutManager(gridLayoutManager);
        productAdapter.setData(productList);
        rcv_all.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_all.setAdapter(productAdapter);

        return view;
    }


}
