package com.example.pizza;

import java.util.ArrayList;

public class OrderLine {
	private String orderline_id;
	private String destination;

	public OrderLine(String orderline_id,String destination){
		this.orderline_id = orderline_id;
		this.destination = destination;
	}

	public String getOrderline_id() {
		return orderline_id;
	}

	public void setOrderline_id(String orderline_id) {
		this.orderline_id = orderline_id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
