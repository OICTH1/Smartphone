package com.example.pizza.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pizza.OrderLine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderLineModel {

	private SQLiteDatabase db = null;

	public OrderLineModel(Context context){
		db = new DBHelper(context).getWritableDatabase();
	}

	public void insert(JSONObject json){
		try {
			insertOrderLine(json.getString("ORDER_ID"), json.getString("CUSTOMER_NAME"), json.getString("CUSTOMER_TEL"), json.getString("DESTINATION"));
			JSONArray order_list = json.getJSONArray("ORDER_LIST");

			for (int i = 0; i < order_list.length(); i++) {
				JSONObject obj = order_list.getJSONObject(i);
				insertOrder(json.getString("ORDER_ID"), obj.getString("ITEM_NAME"), obj.getInt("NUM"));
			}
		} catch (JSONException e){

		}
	}

	private void insertOrderLine(String order_id,String customer_name,String customer_tel,String destination){
		ContentValues cv = 	new ContentValues();
		cv.put("ORDER_ID",order_id);
		cv.put("CUSTOMER_NAME",customer_name);
		cv.put("CUSTOMER_TEL",customer_tel);
		cv.put("DESTINATION",destination);
		db.insert("OrderLine",null,cv);
	}

	private void insertOrder(String order_id,String item_name,int num)
	{
		ContentValues cv = new ContentValues();
		cv.put("ORDER_ID",order_id);
		cv.put("ITEM_NAME",item_name);
		cv.put("NUM","" + num);
		db.insert("ORDER",null,cv);
	}

	public ArrayList<OrderLine> getOrderLineList(){
		ArrayList<OrderLine> list = new ArrayList<OrderLine>();
		String sql_select = "SELECT * FROM ORDERLINE";
		Cursor cursor = db.rawQuery(sql_select,null);
		for (boolean next = cursor.moveToFirst(); next; next = cursor.moveToNext()) {
			String order_id = cursor.getString(cursor.getColumnIndex("ORDER_ID"));
			String destination = cursor.getString(cursor.getColumnIndex("DESTINATION"));
			list.add(new OrderLine(order_id,destination));
		}
		return  list;
	}

}

