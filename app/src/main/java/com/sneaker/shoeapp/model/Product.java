package com.sneaker.shoeapp.model;

public class Product {
    private String ProName;
    private Integer Price;
    private String Category;
    private int Image;

    public Product(String proName, Integer price, String category, int image) {
        ProName = proName;
        Price = price;
        Category = category;
        Image = image;
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
