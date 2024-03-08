package com.sneaker.shoeapp.model;

public class OrderDetails extends Order{

    public OrderDetails(String id, Integer date, Boolean status, Integer quantity, Integer total_value, String address) {
        super(id, date, status, quantity, total_value, address);

    }
}
