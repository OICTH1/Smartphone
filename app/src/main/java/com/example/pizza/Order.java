package com.example.pizza;


public class Order {
	private String item_name;
	private int num;

	public Order(String item_name,int num){
		this.item_name = item_name;
		this.num = num;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getNum() {
		return "" + num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
