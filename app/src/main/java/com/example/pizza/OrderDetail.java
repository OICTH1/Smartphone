package com.example.pizza;

import java.util.ArrayList;

/**
 * Created by 44 on 2015/11/13.
 */
public class OrderDetail {
    private String orderline_id;
    private String customer_name;
    private String customer_tel;
    private ArrayList<Order> orderlist;
    private String destination;

    public OrderDetail(){

    }

    public String getOrderline_id() {
        return orderline_id;
    }

    public void setOrderline_id(String orderline_id) {
        this.orderline_id = orderline_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_tel() {
        return customer_tel;
    }

    public void setCustomer_tel(String customer_tel) {
        this.customer_tel = customer_tel;
    }

    public ArrayList<Order> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(ArrayList<Order> orderlist) {
        this.orderlist = orderlist;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
