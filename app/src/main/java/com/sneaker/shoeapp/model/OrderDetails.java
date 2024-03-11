package com.sneaker.shoeapp.model;

public class OrderDetails extends Order{

    public OrderDetails(String id, String date, Boolean status, Integer quantity, Integer total_value, String address,String cusName, String cusID) {
        super(id, date, status, quantity, total_value, address,cusName,cusID);

    }
}
