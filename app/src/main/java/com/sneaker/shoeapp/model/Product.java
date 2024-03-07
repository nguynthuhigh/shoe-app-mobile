package com.sneaker.shoeapp.model;

import java.io.Serializable;


public class Product implements Serializable {
    private String ProName, color,id;
    private Double Price;
    private String Category;
    private String Image;
    private int type;
    boolean favourite;
    public Product(String proName, Double price, String category, String image, String color, int type, String id) {
    }

    public Product(String proName, Double price, String category, String image, String color, int type, String id, boolean favourite) {
        ProName = proName;
        Price = price;
        Category = category;
        Image = image;
        this.color = color;
        this.type = type;
        this.id = id;
        this.favourite=favourite;
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

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
