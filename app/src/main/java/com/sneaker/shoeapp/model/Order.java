package com.sneaker.shoeapp.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer id;
    private Integer date;
    private Boolean status;
    private Integer quantity;
    private Integer total_value;



    private String address;


    public Order(Integer id, Integer date, Boolean status, Integer quantity, Integer total_value,String address) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.quantity = quantity;
        this.total_value = total_value;
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
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
