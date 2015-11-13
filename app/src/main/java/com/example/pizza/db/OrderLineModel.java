package com.example.pizza.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pizza.Order;
import com.example.pizza.OrderDetail;
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
			insertOrderLine(json.getString("ORDERLINE_ID"), json.getString("CUSTOMER_NAME"), json.getString("CUSTOMER_TEL"), json.getString("DESTINATION"));
			JSONArray order_list = json.getJSONArray("ORDER_LIST");

			for (int i = 0; i < order_list.length(); i++) {
				JSONObject obj = order_list.getJSONObject(i);
				insertOrder(i,json.getString("ORDERLINE_ID"), obj.getString("ITEM_NAME"), obj.getInt("NUM"));
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
	}

	private void insertOrderLine(String orderline_id,String customer_name,String customer_tel,String destination){
		ContentValues cv = 	new ContentValues();
		cv.put("ORDERLINE_ID",orderline_id);
		cv.put("CUSTOMER_NAME",customer_name);
		cv.put("CUSTOMER_TEL",customer_tel);
		cv.put("DESTINATION",destination);
		db.insert("OrderLine",null,cv);
	}

	private void insertOrder(int idx,String orderline_id,String item_name,int num)
	{
		ContentValues cv = new ContentValues();;
		cv.put("ORDER_ID","" + idx);
		cv.put("ORDERLINE_ID",orderline_id);
		cv.put("ITEM_NAME",item_name);
		cv.put("NUM","" + num);
		db.insert("ORDERINFO",null,cv);
	}

	public ArrayList<OrderLine> getOrderLineList(){
		ArrayList<OrderLine> list = new ArrayList<OrderLine>();
		String sql_select = "SELECT * FROM ORDERLINE";
		Cursor cursor = db.rawQuery(sql_select,null);
		for (boolean next = cursor.moveToFirst(); next; next = cursor.moveToNext()) {
			String order_id = cursor.getString(cursor.getColumnIndex("ORDERLINE_ID"));
			String destination = cursor.getString(cursor.getColumnIndex("DESTINATION"));
			list.add(new OrderLine(order_id,destination));
		}
		return  list;
	}

	public OrderDetail getDetail(String orderLine_id){
		OrderDetail detail = new OrderDetail();
		String orderLine_select = String.format("SELECT * FROM ORDERLINE WHERE ORDERLINE_ID = \"%s\"", orderLine_id );
		Cursor cursor_orderLine = db.rawQuery(orderLine_select, null);
		cursor_orderLine.moveToNext();
		detail.setCustomer_name(cursor_orderLine.getString(cursor_orderLine.getColumnIndex("CUSTOMER_NAME")));
		detail.setCustomer_tel(cursor_orderLine.getString(cursor_orderLine.getColumnIndex("CUSTOMER_TEL")));
		detail.setDestination(cursor_orderLine.getString(cursor_orderLine.getColumnIndex("DESTINATION")));

		String orderList_select = String.format("SELECT * FROM ORDERINFO WHERE ORDERLINE_ID = \"%s\"",orderLine_id);
		Cursor cursor_orderList = db.rawQuery(orderList_select,null);
		ArrayList<Order> orderlist = new ArrayList<Order>();
		for (boolean next = cursor_orderList.moveToFirst(); next; next = cursor_orderList.moveToNext()) {
			String item_name = cursor_orderList.getString(cursor_orderList.getColumnIndex("ITEM_NAME"));
			int num = cursor_orderList.getInt(cursor_orderList.getColumnIndex("NUM"));
			orderlist.add(new Order(item_name,num));
		}
		detail.setOrderlist(orderlist);
		return detail;
	}
}

