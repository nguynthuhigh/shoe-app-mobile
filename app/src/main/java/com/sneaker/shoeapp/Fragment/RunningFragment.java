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
import com.sneaker.shoeapp.model.ListProduct;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class RunningFragment extends Fragment {

    RecyclerView rcv_running;
    View view;
    ProductAdapter productAdapter;

    public RunningFragment() {
        // Required empty public constructor
    }
    public static RunningFragment newInstance() {
        return new RunningFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_running, container, false);
        rcv_running = view.findViewById(R.id.rcv_running);
        productAdapter = new ProductAdapter(getList(), new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                IntentDetails(product);
            }
        },getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcv_running.setLayoutManager(gridLayoutManager);
        //productAdapter.setData(getList());
        rcv_running.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_running.setAdapter(productAdapter);
        return view;
    }

    private void IntentDetails(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_product",product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<Product> getList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Hello",300,"hello's shoe",R.drawable.shoe1,"CACBCF",true));
        productList.add(new Product("Hehe boi",300,"nguyn's shoe",R.drawable.shoe3,"5D90DD",true));
        productList.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220,"Men's Football Cleats",R.drawable.shoe4,"A59D2D",true));
        productList.add(new Product("Nike Vapor Edge Elite 360 2",2200,"Hello's Football Cleats",R.drawable.shoe5,"585858",true));
        return productList;
    }
}