package com.xaut.tsupervise;

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

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anyang.util.httpUtil;
import com.xaut.entity.Qtkalarm;
import com.xaut.layout.MyHScrollView;
import com.xaut.layout.MyHScrollView.OnScrollChangedListener;

public class AlarmNew extends BaseActivity {

	ListView mListView1;
	MyAdapter myAdapter;
	RelativeLayout mHead;
	LinearLayout main;
    ArrayList<Qtkalarm> list = new ArrayList<Qtkalarm>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_new);
		
		mHead = (RelativeLayout) findViewById(R.id.head);
		mHead.setFocusable(true);
		mHead.setClickable(true);
		mHead.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		String endtime = df.format(new Date()).toString();

		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(df.parse(endtime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.add(Calendar.HOUR_OF_DAY, -1);//回退一小时

		String starttime = df.format(cd.getTime()).toString();
		
		Log.i("start", starttime);
		Log.i("end", endtime);
	

		initData(starttime, endtime);
		Log.i("list", list.toString());
		
		mListView1 = (ListView) findViewById(R.id.listView1);
		mListView1.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
		myAdapter = new MyAdapter(this, R.layout.itemalarm);
		mListView1.setAdapter(myAdapter);
	}

	// 初始化绑定数据
			private void initData(String starttime, String endtime) {
				// 第一步：获取数据源（model）
				NameValuePair pair1 = new BasicNameValuePair("starttime", starttime);
				NameValuePair pair2 = new BasicNameValuePair("endtime", endtime);
				List<NameValuePair> pairList = new ArrayList<NameValuePair>();
				pairList.add(pair1);
				pairList.add(pair2);

				String url = httpUtil.BASE_URL + "/AlarmServlet";
				String information1 = httpUtil.queryStringForPost(url, pairList);


					JSONArray json;
					try {

						json = new JSONArray(information1);
						for (int i = 0; i < json.length(); i++) {

							JSONObject object = (JSONObject) json.get(i);

							int idtmp = object.getInt("id");
							int storenumtmp = object.getInt("storenum");
							String timetmp = object.getString("time");
							String reasontmp = object.getString("reason");

							list.add(new Qtkalarm(idtmp, storenumtmp, timetmp, reasontmp));
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
			
			class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					
					HorizontalScrollView headSrcrollView = (HorizontalScrollView) mHead
							.findViewById(R.id.horizontalScrollView1);
					headSrcrollView.onTouchEvent(arg1);
					return false;
				}
			}

			public class MyAdapter extends BaseAdapter {
				public List<ViewHolder> mHolderList = new ArrayList<ViewHolder>();

				int id_row_layout;
				LayoutInflater mInflater;

				public MyAdapter(Context context, int id_row_layout) {
					super();
					this.id_row_layout = id_row_layout;
					mInflater = LayoutInflater.from(context);

				}

				@Override
				public int getCount() {
					// TODO Auto-generated method stub
					return list.size();
				}

				@Override
				public Object getItem(int arg0) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public long getItemId(int arg0) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public View getView(int position, View convertView, ViewGroup parentView) {
					ViewHolder holder = null;
					if (convertView == null) {
						synchronized (AlarmNew.this) {
							convertView = mInflater.inflate(id_row_layout, null);
							holder = new ViewHolder();

							MyHScrollView scrollView1 = (MyHScrollView) convertView
									.findViewById(R.id.horizontalScrollView1);

							holder.scrollView = scrollView1;
							holder.txt1 = (TextView) convertView
									.findViewById(R.id.textView1);
							holder.txt2 = (TextView) convertView
									.findViewById(R.id.textView2);
							holder.txt3 = (TextView) convertView
									.findViewById(R.id.textView3);

							MyHScrollView headSrcrollView = (MyHScrollView) mHead
									.findViewById(R.id.horizontalScrollView1);
							headSrcrollView
									.AddOnScrollChangedListener(new OnScrollChangedListenerImp(
											scrollView1));

							convertView.setTag(holder);
							mHolderList.add(holder);
						}
					} else {
						holder = (ViewHolder) convertView.getTag();
					}
					
					
					holder.txt1.setText(""+list.get(position).getStorenum());
					holder.txt2.setText(list.get(position).getTime().substring(11, 16));
					holder.txt3.setText(list.get(position).getReason());
					
					
					return convertView;
				}


				class OnScrollChangedListenerImp implements OnScrollChangedListener {
					MyHScrollView mScrollViewArg;

					public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
						mScrollViewArg = scrollViewar;
					}

					@Override
					public void onScrollChanged(int l, int t, int oldl, int oldt) {
						mScrollViewArg.smoothScrollTo(l, t);
					}
				};

				class ViewHolder {
					TextView txt1;
					TextView txt2;
					TextView txt3;
					HorizontalScrollView scrollView;
				}

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
			  			intent.setClass(AlarmNew.this, FirstPage.class);
			  			startActivity(intent);	
			  			break;
			  		}
			  		default:break;
			  		}
			  		return true;
			  	}
}
