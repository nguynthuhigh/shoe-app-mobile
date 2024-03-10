package com.sneaker.shoeapp.model;

import java.io.Serializable;

public class Order implements Serializable{
    private String id;
    private String date;
    private Boolean status;
    private Integer quantity;
    private Integer total_value;

    private String cusName,cusID;

    private String address;

    public Order() {
    }

    public Order(String id, String date, Boolean status, Integer quantity, Integer total_value, String address, String cusName, String cusID) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.quantity = quantity;
        this.total_value = total_value;
        this.cusName = cusName;
        this.cusID = cusID;
        this.address = address;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal_value() {
        return total_value;
    }

    public void setTotal_value(Integer total_value) {
        this.total_value = total_value;
    }
}
