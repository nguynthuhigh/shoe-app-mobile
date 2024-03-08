package com.sneaker.shoeapp.model;

import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import java.io.Serializable;


public class Product implements Serializable {
    private String ProName, color,id;
    private Double Price;
    private String Category;
    private String Image;
    private int type;
    public Product() {
    }

    public Product(String proName, Double price, String category, String image, String color, int type, String id) {
        ProName = proName;
        Price = price;
        Category = category;
        Image = image;
        this.color = color;
        this.type = type;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


    
}
