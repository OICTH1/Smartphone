package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pizza.adpter.OrderListAdpter;
import com.example.pizza.db.OrderLineModel;

import java.util.ArrayList;

public class DetailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Intent intent = getIntent();
		String order_id = intent.getStringExtra("ORDERLINE_ID");

		OrderLineModel orderLineModel = new OrderLineModel(DetailActivity.this);
		setDate(orderLineModel.getDetail(order_id));
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

	private void setDate(OrderDetail detail){
		/*顧客情報*/
		((TextView)findViewById(R.id.customer_name)).setText(detail.getCustomer_name());
		((TextView)findViewById(R.id.customer_tel)).setText(detail.getCustomer_tel());

		/*注文一覧*/
		ListView orderList = (ListView)findViewById(R.id.orderlist);
		OrderListAdpter adapter = new OrderListAdpter(DetailActivity.this);
		adapter.setOrderList(detail.getOrderlist());
		orderList.setAdapter(adapter);

		/*配達先住所*/
		((TextView)findViewById(R.id.destination)).setText(detail.getDestination());
	}
}
