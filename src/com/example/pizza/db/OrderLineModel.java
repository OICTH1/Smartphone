package com.example.pizza.db;

import android.database.sqlite.SQLiteDatabase;

public class OrderLineModel {


	private SQLiteDatabase db = null;

	public OrderLineModel(SQLiteDatabase db){
		this.db = db;
	}
}

