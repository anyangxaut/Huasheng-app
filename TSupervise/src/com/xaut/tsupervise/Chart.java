package com.xaut.tsupervise;

import com.xaut.layout.MyAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class Chart extends BaseActivity {

	private GridView gridview1;
	private MyAdapter adapter1;
	private int[] icons = { R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku
			, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku
			, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku
			, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku
			, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku
			, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku, R.drawable.cangku};
	private String[] items1 = { "仓库1", "仓库2", "仓库3", "仓库4", "仓库5", "仓库6", "仓库7", "仓库8", "仓库9", "仓库10"
			, "仓库11", "仓库12", "仓库13", "仓库14", "仓库15", "仓库16", "仓库17", "仓库18", "仓库19", "仓库20", "仓库21", 
			"仓库22", "仓库23", "仓库24", "仓库25", "仓库26", "仓库27", "仓库28", "仓库29", "仓库30", "仓库31", "仓库32", 
			"仓库33"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		
		    adapter1 = new MyAdapter(this, items1, icons);
			
			gridview1 = (GridView) findViewById(R.id.gridview1);
			gridview1.setAdapter(adapter1);
			
			gridview1.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {

					switch (arg2) {
					case 0: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 1: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 2: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 3: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 4: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 5: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 6: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 7: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 8: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 9: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 10: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 11: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 12: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 13: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 14: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 15: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 16: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 17: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 18: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 19: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 20: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 21: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 22: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 23: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 24: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 25: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 26: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 27: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 28: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 29: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 30: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 31: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
						startActivity(intent);
						break;
					}
					case 32: {
						Intent intent = new Intent(Chart.this,ChartShow.class);
						Bundle bundle=new Bundle();
						bundle.putInt("storenum", arg2+1);
						intent.putExtras(bundle);
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
	  	
	  		menu.add(groupId, 0, menuItemOrder, "气调库首页").setIcon(R.drawable.icon);
	  		
	  		return true;
	  	}
	  	
	  	public boolean onOptionsItemSelected(MenuItem item) {
	  		switch (item.getItemId()) {
	  		case 0:{
	  			Intent intent=new Intent();
	  			intent.setClass(Chart.this, FirstPage.class);
	  			startActivity(intent);	
	  			break;
	  		}
	  		default:break;
	  		}
	  		return true;
	  	}

}
