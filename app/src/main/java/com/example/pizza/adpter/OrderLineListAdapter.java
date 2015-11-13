package com.example.pizza.adpter;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pizza.R;

public class OrderLineListAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater = null;
	ArrayList<JSONObject> OrderLineList = null;

	public OrderLineListAdapter(Context context) {
		this.context = context;
		this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return this.OrderLineList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.OrderLineList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	 public void setOrderLineList(ArrayList<JSONObject> orderLineList) {
		OrderLineList = orderLineList;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = layoutInflater.inflate(R.layout.orderlinelist,parent,false);

		((TextView)convertView.findViewById(R.id.index)).setText( "" + (position + 1));
		try {
			String id = OrderLineList.get(position).getString("id");
			((TextView)convertView.findViewById(R.id.orderline_id)).setText(id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			String address =OrderLineList.get(position).getString("address");
			((TextView)convertView.findViewById(R.id.address)).setText(address);
		} catch (JSONException e) {
			e.printStackTrace();
		}


		return convertView;
	}

}
