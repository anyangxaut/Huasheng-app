package com.xaut.activity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.anyang.util.httpUtil;
import com.xaut.entity.Qtkentity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QtkTable extends BaseActivity implements OnScrollListener,
		OnItemClickListener {

	private RadioGroup radiogroup = null;
	private RadioButton day = null, week = null, month = null;
	private TextView tv = null;

	/*----ListView MVC实现----*/
	// model
	private ArrayList<Qtkentity> list = new ArrayList<Qtkentity>();
	private List<String> data = new ArrayList<String>();
	// view
	ListView lv;
	// controller
	MyAdapter adapter;
	Thread currentThread;
	int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qtk_table);

		radiogroup = (RadioGroup) findViewById(R.id.radiogroup1);
		day = (RadioButton) findViewById(R.id.day);
		week = (RadioButton) findViewById(R.id.week);
		month = (RadioButton) findViewById(R.id.month);
		tv = (TextView) findViewById(R.id.title);
		tv.setText("      库号                       时间                        状态  ");

		lv = (ListView) findViewById(R.id.list);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

		String starttime = df.format(new Date()).toString() + " 00:00:00";

		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(df.parse(starttime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.add(Calendar.DATE, 1);// 增加1天

		String endtime = df.format(cd.getTime()).toString() + " 00:00:00";

		initData(starttime, endtime);
		lv.setOnScrollListener(this);
		lv.setOnItemClickListener(this);

		radiogroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (checkedId == day.getId()) {
							day();
						} else if (checkedId == week.getId()) {
							week();
						} else if (checkedId == month.getId()) {

							month();
						}
					}
				});
	}

	private void day() {

		list.clear();

		data.clear();

		counter = 0;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

		String starttime = df.format(new Date()).toString() + " 00:00:00";

		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(df.parse(starttime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.add(Calendar.DATE, 1);// 增加1天

		String endtime = df.format(cd.getTime()).toString() + " 00:00:00";

		initData(starttime, endtime);

		adapter.notifyDataSetChanged();

	}

	private void week() {

		list.clear();

		data.clear();

		counter = 0;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

		String endtime = df.format(new Date()).toString() + " 00:00:00";

		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(df.parse(endtime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.add(Calendar.DATE, -7);// 增加-7天

		String starttime = df.format(cd.getTime()).toString() + " 00:00:00";

		initData(starttime, endtime);

		adapter.notifyDataSetChanged();

	}

	private void month() {

		list.clear();

		data.clear();

		counter = 0;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

		String endtime = df.format(new Date()).toString() + " 00:00:00";

		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(df.parse(endtime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.add(Calendar.MONTH, -1);// 增加-1个月

		String starttime = df.format(cd.getTime()).toString() + " 00:00:00";

		initData(starttime, endtime);

		adapter.notifyDataSetChanged();

	}

	// 初始化绑定数据
	private void initData(String starttime, String endtime) {
		if (lv == null)
			return;
		// 第一步：获取数据源（model）
		NameValuePair pair1 = new BasicNameValuePair("starttime", starttime);
		NameValuePair pair2 = new BasicNameValuePair("endtime", endtime);
		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		pairList.add(pair1);
		pairList.add(pair2);

		String url = httpUtil.BASE_URL + "/QtkHistoryServlet";
		String information1 = httpUtil.queryStringForPost(url, pairList);

		if (information1.equals("0")) {
			tv.setText("很抱歉，程序出错了！");
		} else {

			JSONArray json;
			try {

				json = new JSONArray(information1);
				for (int i = 0; i < json.length(); i++) {

					JSONObject object = (JSONObject) json.get(i);

					int idtmp = object.getInt("id");
					int storenumtmp = object.getInt("storenum");
					String timetmp = object.getString("time");
					double t1tmp = object.getDouble("t1");
					double t2tmp = object.getDouble("t2");
					double o2tmp = object.getDouble("o2");
					double co2tmp = object.getDouble("co2");
					double humiditytmp = object.getDouble("humidity");

					list.add(new Qtkentity(idtmp, storenumtmp, timetmp, t1tmp,
							t2tmp, o2tmp, co2tmp, humiditytmp));
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 初始化数据--15个
		appendData();

		// 第二步：new一个适配器（controller）
		// 参数1：Context
		// 参数2：listview的item布局
		// 参数3：数据填充在item布局下的那个控件id
		// 参数4：填充的数据
		// adapter = new ArrayAdapter<String>(this, R.layout.simple_text,
		// R.id.text1, data);
		adapter = new MyAdapter();
		// 第三步：给listview设置适配器（view）

		lv.setAdapter(adapter);
		// 这里的参数null是数据，false说明是不能被选中的
		// lv.addFooterView(footer, null, false);
		// 设置尾部无分割线，头部不想要分割线同理
		lv.setFooterDividersEnabled(false);
	}

	// 添加数据
	private void appendData() {

		if (data == null)
			return;

		for (int i = 0; i < 15; i++) {
			if (counter < list.size()) {

				data.add("      " + list.get(counter).getStorenum()
						+ "         " + list.get(counter).getTime()
						+ "          " + "正常");
				counter++;
			}
		}
	}

	// 模拟加载数据
	class DataLoadThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				appendData();
				// 因为Android控件只能通过主线程（ui线程）更新，所以用此方法
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// 当数据改变时调用此方法通知view更新
						adapter.notifyDataSetChanged();
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 自定义基础适配器
	class MyAdapter extends BaseAdapter {

		// listview显示的个数，如果有数据源有10条，而返回5，那么lv永远只能显示5条
		// 所以最好就返回数据源的条数就好了
		@Override
		public int getCount() {
			return data.size();
		}

		// 获取item绑定的数据时调用
		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		// itemId
		@Override
		public long getItemId(int position) {
			return position;
		}

		// lv显示几个item就会调用几次此方法，然后返回一个view对象显示
		// position：位置
		// convertView：如果lv不能显示全部的数据，那么滚动后会把从显示到不显示的View传进来复用
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.simple_text, null);
			} else {
				view = convertView;
			}
			TextView tv = (TextView) view.findViewById(R.id.text1);
			tv.setText(data.get(position).toString());
			// 隔行变色，可以随心所欲

			return view;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		DecimalFormat sdf = new DecimalFormat("0.00");
		double tmp1 = 0, T1 = 0, T2 = 0, O2 = 0, Co2 = 0, Humidity = 0;

		tmp1 = list.get(position).getT1();
		T1 = Double.parseDouble(sdf.format(tmp1));
		tmp1 = list.get(position).getT2();
		T2 = Double.parseDouble(sdf.format(tmp1));
		tmp1 = list.get(position).getO2();
		O2 = Double.parseDouble(sdf.format(tmp1));
		tmp1 = list.get(position).getCo2();
		Co2 = Double.parseDouble(sdf.format(tmp1));
		tmp1 = list.get(position).getHumidity();
		Humidity = Double.parseDouble(sdf.format(tmp1));

		Toast.makeText(
				this,
				"库号： " + list.get(position).getStorenum() + "\n" + "时间： "
						+ list.get(position).getTime() + "\n" + "T1： " + T1
						+ "（摄氏度）\n" + "T2： " + T2 + "（摄氏度）\n" + "O2： " + O2
						+ "（百分比）\n" + "Co2： " + Co2 + "（百分比）\n" + "湿度： "
						+ Humidity + "（百分比）", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState) {
		// 手指接触屏幕滑动
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			// 手指离开屏幕做惯性滑动
		case OnScrollListener.SCROLL_STATE_FLING:
			// 当滑动要最后一行时加载数据
			if (view.getLastVisiblePosition() >= view.getCount() - 2) {
				// 可以通过网络加载数据等。
				// 判断是否还是在加载中
				if (currentThread == null || !currentThread.isAlive()) {
					// 启动线程加载数据
					currentThread = new DataLoadThread();
					currentThread.start();
				}
			}
			break;
		// 不滑动
		case OnScrollListener.SCROLL_STATE_IDLE:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Group ID
		int groupId = 0;
		// The order position of the item
		int menuItemOrder = Menu.NONE;

		menu.add(groupId, 0, menuItemOrder, "首页").setIcon(R.drawable.icon);
		menu.add(groupId, 1, menuItemOrder, "气调库首页").setIcon(R.drawable.icon);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0: {
			Intent intent = new Intent();
			intent.setClass(QtkTable.this, FirstPage.class);
			startActivity(intent);
			break;
		}
		case 1: {
			Intent intent = new Intent();
			intent.setClass(QtkTable.this, Qtkshow.class);
			startActivity(intent);
			break;
		}
		default:
			break;
		}
		return true;
	}

}
