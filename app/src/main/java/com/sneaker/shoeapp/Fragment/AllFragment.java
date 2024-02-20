package com.sneaker.shoeapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.ProductDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;


public class AllFragment extends Fragment {
    RecyclerView rcv_all;
    View view;
    ProductAdapter productAdapter;


    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all, container, false);
        rcv_all = view.findViewById(R.id.rcv_all);
        productAdapter = new ProductAdapter(getList(), new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_product",product);
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        },getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcv_all.setLayoutManager(gridLayoutManager);
        productAdapter.setData(getList());
        rcv_all.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_all.setAdapter(productAdapter);
        return view;
    }

    private List<Product> getList() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Hello",300,"hello's shoe",R.drawable.shoe2,"FF422B",true));
        list.add(new Product("Hehe boi",300,"nguyn's shoe",R.drawable.shoe7,"5D90DD",true));
        list.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220,"Men's Football Cleats",R.drawable.shoe5,"A59D2D",true));
        list.add(new Product("Nike Vapor Edge Elite 360 2",2200,"Hello's Football Cleats",R.drawable.shoe9,"585858",true));
        return list;
    }
}