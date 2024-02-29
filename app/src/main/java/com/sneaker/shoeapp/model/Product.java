package com.sneaker.shoeapp.model;

import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;

import java.io.Serializable;


public class Product implements Serializable {
    private String ProName, color;

    private Integer Price,ID;
    private String Category;
    private int Image;
    private int type;
    public Product() {
    }

    public Product(String proName, Integer price, String category, int image, String color, int type) {
        ProName = proName;
        Price = price;
        Category = category;
        Image = image;
        this.color = color;
        this.type = type;
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

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    
}
