package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.pizza.adpter.OrderListAdpter;

import java.util.ArrayList;

public class DetailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Intent intent = getIntent();
		String id = intent.getStringExtra("id");

		ListView listView = (ListView)findViewById(R.id.orderlist);

		String json = "{\"id\":\"1234567890123\","
					+ "\"customer_name\":\"山田太郎\","
					+ "\"customer_tel\":\"080-xxx-xxx\","
					+ "\"orderlist\":["
					+ "			{\"item_name\":\"aaaa\",\"num\":2},"
					+ "			{\"item_name\":\"bbbb\",\"num\":3}"
					+ "],"
					+ "\"address\":\"大阪府大阪市天王寺区上本町6-8-4\"}";
		ArrayList<Order> list = new ArrayList<Order>();
		list.add(new Order("aaaaa",3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaaaaaaaaaaa", 20));
		list.add(new Order("aaaaa",3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaaaaaaaaaaa", 20));
		list.add(new Order("aaaaa",3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaaaaaaaaaaa", 20));
		list.add(new Order("aaaaa",3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaaaaaaaaaaa", 20));
		list.add(new Order("aaaaa",3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaa", 3));
		list.add(new Order("aaaaaaaaaaaaaa", 20));

		OrderListAdpter adapter = new OrderListAdpter(DetailActivity.this);
		adapter.setOrderList(list);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
