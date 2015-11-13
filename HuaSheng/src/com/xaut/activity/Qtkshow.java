package com.xaut.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.xaut.layout.MyAdapter;

public class Qtkshow extends BaseActivity {

	private GridView gridview1;
	private MyAdapter adapter1;
	private int[] icons = { R.drawable.shishi, R.drawable.lishi,
			R.drawable.biaoge, R.drawable.jingbao,};
	private String[] items1 = { "实时监测", "历史记录", "表格记录", "报警信息"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qtkshow);
		
		    adapter1 = new MyAdapter(this, items1, icons);
			
			gridview1 = (GridView) findViewById(R.id.gridview1);
			gridview1.setAdapter(adapter1);
			
			gridview1.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {

					switch (arg2) {
					case 0: {
						Intent intent = new Intent(Qtkshow.this,Qtktest.class);
						startActivity(intent);
						break;
					}
					case 1: {
						Intent intent = new Intent(Qtkshow.this,QtkHistory.class);
						startActivity(intent);
						break;
					}
					case 2: {
						Intent intent = new Intent(Qtkshow.this,QtkTable.class);
						startActivity(intent);
						break;
					}
					case 3: {
//						Intent intent = new Intent(Qtkshow.this,VarietyIntro.class);
//						startActivity(intent);
						break;
					}
					default:break;
					}

				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Group ID
				int groupId = 0;
				// The order position of the item
				int menuItemOrder = Menu.NONE;

				menu.add(groupId, 0, menuItemOrder, "首页").setIcon(R.drawable.icon);
				
				return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:{
			Intent intent=new Intent();
			intent.setClass(Qtkshow.this, FirstPage.class);
			startActivity(intent);	
			break;
		}
		default:break;
		}
		return true;
	}

}
