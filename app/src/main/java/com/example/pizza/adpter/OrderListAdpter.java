package com.example.pizza.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pizza.Order;
import com.example.pizza.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 44 on 2015/11/13.
 */
public class OrderListAdpter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Order> OrderList = null;

    public OrderListAdpter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return this.OrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.OrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        OrderList = orderList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.order,parent,false);

        ((TextView)convertView.findViewById(R.id.item_name)).setText(OrderList.get(position).getItem_name());
        ((TextView)convertView.findViewById(R.id.num)).setText(OrderList.get(position).getNum());

        return convertView;
    }
}
