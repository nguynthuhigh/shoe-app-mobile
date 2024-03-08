package com.sneaker.shoeapp.model;

import android.view.View;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListProduct extends ViewModel {
    private List<Product> productList = new ArrayList<>();

    public ListProduct() {

    }

    public void add(Product pro){
        productList.add(pro);
    }
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
