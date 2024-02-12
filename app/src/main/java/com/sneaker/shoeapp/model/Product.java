package com.sneaker.shoeapp.model;

import java.io.Serializable;

public class Product implements Serializable{
    private String ProName;
    private Integer Price;
    private String Category;
    private int Image;
    private  String color;
    private boolean isCate;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Product(String proName, Integer price, String category, int image, String color, boolean isCate) {
        ProName = proName;
        Price = price;
        Category = category;
        Image = image;
        this.color = color;
        this.isCate = isCate;
    }

    public boolean isCate() {
        return isCate;
    }

    public void setCate(boolean cate) {
        isCate = cate;
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
}
