package com.sneaker.shoeapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sneaker.shoeapp.Adapter.ProductAdapter;
import com.sneaker.shoeapp.Interface.ClickItemProduct;
import com.sneaker.shoeapp.MainActivity;
import com.sneaker.shoeapp.ProductDetailsActivity;
import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FootballFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FootballFragment extends Fragment {
    RecyclerView rcv_ft;
    ProductAdapter productAdapter;
    View view;
    public FootballFragment() {
        // Required empty public constructor
    }
    public static FootballFragment newInstance() {
        return new FootballFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_football, container, false);
        rcv_ft = view.findViewById(R.id.rcv_ft);
        productAdapter = new ProductAdapter(getListPro(), new ClickItemProduct() {
            @Override
            public void onClickItemProduct(Product product) {
                IntentDetails(product);
            }
        },getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcv_ft.setLayoutManager(gridLayoutManager);
        productAdapter.setData(getListPro());
        rcv_ft.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_ft.setAdapter(productAdapter);
        return view;
    }
    private void IntentDetails(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_product",product);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private List<Product> getListPro() {
        List<Product> listPro= new ArrayList<Product>();
        listPro.add(new Product("Hello",300,"hello's shoe",R.drawable.shoe6,"FF422B",2));
        listPro.add(new Product("Hehe boi",300,"nguyn's shoe",R.drawable.shoe7,"5D90DD",2));
        listPro.add(new Product("Nike Vapor Edge Elite 360 2 NRG",220,"Men's Football Cleats",R.drawable.shoe8,"A59D2D",2));
        listPro.add(new Product("Nike Vapor Edge Elite 360 2",2200,"Hello's Football Cleats",R.drawable.shoe10,"FF422B",2));
        return listPro;
    }
}