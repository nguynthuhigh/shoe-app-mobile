package com.sneaker.shoeapp.model;

import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import java.io.Serializable;


public class Cart extends Product implements Serializable {
    private double quantity;
    private double total_cart;
    private String id_cart;

    public Cart(String proName, Double price, String category, String image, String color, int type, String id, double quantity, double total_cart, String id_cart) {
        super(proName, price, category, image, color, type, id);
        this.quantity = quantity;
        this.total_cart = total_cart;
        this.id_cart = id_cart;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal_cart() {
        return total_cart;
    }

    public void setTotal_cart(double total_cart) {
        this.total_cart = total_cart;
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }
}
