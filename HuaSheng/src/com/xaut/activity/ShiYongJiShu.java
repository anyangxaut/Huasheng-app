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

public class ShiYongJiShu extends BaseActivity {

	private GridView gridview1;
	private MyAdapter adapter1;
	private int[] icons = { R.drawable.zhengxing, R.drawable.feishui,
			R.drawable.chucang, R.drawable.pinzhong,
			R.drawable.bingchong};
	private String[] items1 = { "整形修剪", "肥水管理", "果品储藏", "品种介绍", "病虫害防治"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shi_yong_ji_shu);
		
        adapter1 = new MyAdapter(this, items1, icons);
		
		gridview1 = (GridView) findViewById(R.id.gridview1);
		gridview1.setAdapter(adapter1);
		
		gridview1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				switch (arg2) {
				case 0: {
					Intent intent = new Intent(ShiYongJiShu.this,ZhengXing.class);
					startActivity(intent);
					break;
				}
				case 1: {
					Intent intent = new Intent(ShiYongJiShu.this,FeiShui.class);
					startActivity(intent);
					break;
				}
				case 2: {
					Intent intent = new Intent(ShiYongJiShu.this,FruitStore.class);
					startActivity(intent);
					break;
				}
				case 3: {
					Intent intent = new Intent(ShiYongJiShu.this,VarietyIntro.class);
					startActivity(intent);
					break;
				}
				case 4: {
					Intent intent = new Intent(ShiYongJiShu.this,InsectControl.class);
					startActivity(intent);
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
			intent.setClass(ShiYongJiShu.this, FirstPage.class);
			startActivity(intent);	
			break;
		}
		default:break;
		}
		return true;
	}
}
