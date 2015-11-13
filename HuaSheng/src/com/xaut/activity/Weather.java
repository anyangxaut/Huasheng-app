package com.xaut.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xaut.layout.HuaShengNewsAdapter;

public class Weather extends BaseActivity {

	private GridView gridview1;
	private HuaShengNewsAdapter adapter1;
	private final int NUM = 6;
	private String[] items1 = new String[NUM];
	public BDLocationListener myListener = new MyLocationListener();
	private LocationClient mLocClient = null;
	private boolean mIsStart = false;
	private String city = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);

		gridview1 = (GridView) findViewById(R.id.gridview1);
		adapter1 = new HuaShengNewsAdapter(this, items1);
		gridview1.setAdapter(adapter1);
		
		mLocClient = new LocationClient(this); // 声明LocationClient类
		mLocClient.setAK("46123ffaa716e79d7c23bbbbf3de11ca");
		mLocClient.registerLocationListener(myListener); // 注册监听函数
		if (!mIsStart) {
			setLocationOption();
			mLocClient.start();
			if (mLocClient != null && mLocClient.isStarted()) {
				mLocClient.requestLocation(); // 发起定位请求。请求过程是异步的，定位结果在监听函数onReceiveLocation中获取。
				mIsStart = true;
			} else
				Log.d("LocSDK3", "locClient is null or not started");

		}	
		
	
		Time t = new Time();
		t.setToNow();
		int year = t.year;
		int month = t.month + 1;
		int date = t.monthDay;
		int mWeek = t.weekDay;
		int monthday = t.getActualMaximum(Time.MONTH_DAY);
		String mWeektmp = null;

		for (int i = 0; i < NUM; i++) {

			
			if (0 == mWeek) {

				mWeek = 7;

			}
			switch(mWeek){
			case 1:{mWeektmp = "一"; break;}
			case 2:{mWeektmp = "二"; break;}
			case 3:{mWeektmp = "三"; break;}
			case 4:{mWeektmp = "四"; break;}
			case 5:{mWeektmp = "五"; break;}
			case 6:{mWeektmp = "六"; break;}
			case 7:{mWeektmp = "日"; break;}
			default:break;
			}
			items1[i] = year + "年" + month + "月" + date + "日" + "   " + "星期"
					+ mWeektmp;
			date++;
			if(date > monthday){
				month++;
				date = date % monthday;
			}
			mWeek = (mWeek + 1) % 7;

		}
		gridview1.setOnItemClickListener(new OnItemClickListener() {

			// parent The AdapterView where the click happened.
			// view The view within the AdapterView that was clicked (this will
			// be a view provided by the adapter)
			// position The position of the view in the adapter.
			// id The row id of the item that was clicked.
			// 注：这些数值都是从0开始的。

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				switch (arg2) {
				case 0: {
					showweather(1);
					break;
				}
				case 1: {
					showweather(2);
					break;
				}
				case 2: {
					showweather(3);
					break;
				}
				case 3: {
					showweather(4);
					break;
				}
				case 4: {
					showweather(5);
					break;
				}
				case 5: {
					showweather(6);
					break;
				}
				default:
					break;
				}

			}
		});
	}

	// 设置相关参数
			private void setLocationOption() {
				LocationClientOption option = new LocationClientOption();
				option.setOpenGps(true); // 打开gps
				option.setAddrType("all"); // 返回的定位结果包含地址信息
				option.setScanSpan(3000); // 设置发起定位请求的间隔时间为3000ms
				option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
				option.setPriority(LocationClientOption.GpsFirst); // 设置Gps优先
				option.disableCache(true); // 禁止启用缓存定位
				// option.setPoiNumber(10); //最多返回POI个数
				// option.setPoiDistance(1000); //poi查询距离
				// option.setPoiExtraInfo(true); //是否需要POI的电话和地址等详细信息
				mLocClient.setLocOption(option);
			}
			
			
			/**
			 * 监听函数，有更新位置的时候，格式化成字符串，输出到屏幕中 BDLocationListener接口有2个方法需要实现：
			 * 1.接收异步返回的定位结果，参数是BDLocation类型参数。 2.接收异步返回的POI查询结果，参数是BDLocation类型参数。
			 */
			public class MyLocationListener implements BDLocationListener {
				@Override
				public void onReceiveLocation(BDLocation location) {
					if (location == null)
						return;

					if (location.getCity() != null) {
						city = location.getCity().replaceAll("市", "");
					}else{
						city = "error";
					}
					
				}

				public void onReceivePoi(BDLocation poiLocation) {
					if (poiLocation == null) {
						return;
					}
				}
			}

	
	public void showweather(int i) {
		
		if(mLocClient != null){
			mLocClient.stop();
			}
		mIsStart = false;
		Intent intent = new Intent();
		intent.setClass(Weather.this, ShowWeather.class);

		Bundle bundle = new Bundle();
		bundle.putInt("flag", i);
		bundle.putString("city", city);
		intent.putExtras(bundle);
		startActivity(intent);
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
		case 0: {
			if(mLocClient != null){
				mLocClient.stop();
				}
			mIsStart = false;
			Intent intent = new Intent();
			intent.setClass(Weather.this, FirstPage.class);
			startActivity(intent);
			break;
		}
		default:
			break;
		}
		return true;
	}
			}

