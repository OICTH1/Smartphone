package com.example.pizza;

import java.util.ArrayList;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pizza.adpter.OrderLineListAdapter;
import com.example.pizza.db.OrderLineModel;

public class MainActivity extends ActionBarActivity {

	private ArrayList<OrderLine> list = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView)findViewById(R.id.orderlinelist);
		OrderLineModel orderLineModel = new OrderLineModel(MainActivity.this);
		this.list = orderLineModel.getOrderLineList();

		Uri.Builder builder = new Uri.Builder();
		builder.scheme("http");
		builder.authority("192.168.232.135");
		builder.path("DeliverySupport/public/index.php/api/order/201512060001.json");
		AsyncGetJson a = new AsyncGetJson(MainActivity.this);
		a.doInBackground(builder);


		OrderLineListAdapter adapter = new OrderLineListAdapter(MainActivity.this);
		adapter.setOrderLineList(list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra("ORDERLINE_ID", list.get(position).getOrderline_id());
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_add:
			 // 編集画面への遷移処理
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
