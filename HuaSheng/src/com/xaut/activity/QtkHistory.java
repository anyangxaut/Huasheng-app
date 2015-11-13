package com.xaut.activity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anyang.util.httpUtil;
import com.xaut.entity.Qtkentity;

public class QtkHistory extends BaseActivity {

	private RadioGroup radiogroup = null; 
	private RadioButton day = null, week = null, month = null;
	private LinearLayout layout = null;
	private Context context = null;
	private ArrayList<Qtkentity> list = new ArrayList<Qtkentity>();
	private XYMultipleSeriesRenderer mRenderer;
	private XYMultipleSeriesDataset mDataset;
	private XYSeries seriesOne;
	private XYSeries seriesTwo;
	private String title1;
	private String title2;
	private int count;
	private int addx;
	private GraphicalView view;
	private String substr = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qtk_history);
		
		radiogroup = (RadioGroup)findViewById(R.id.radiogroup1);
		day = (RadioButton)findViewById(R.id.day);
		week = (RadioButton)findViewById(R.id.week);
		month = (RadioButton)findViewById(R.id.month);
		context = getApplicationContext();
		layout = (LinearLayout) findViewById(R.id.achartengine);
		
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		
		String starttime = df.format(new Date()).toString() + " 00:00:00";
		
		  Calendar cd = Calendar.getInstance();   
          try {
			cd.setTime(df.parse(starttime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
          cd.add(Calendar.DATE, 1);//增加1天   

		String endtime = df.format(cd.getTime()).toString() + " 00:00:00";
		
		
		
		
		NameValuePair pair1 = new BasicNameValuePair("starttime", starttime);
		NameValuePair pair2 = new BasicNameValuePair("endtime", endtime);
		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		pairList.add(pair1);
		pairList.add(pair2);

		String url = httpUtil.BASE_URL + "/QtkHistoryServlet";
		String information1 = httpUtil.queryStringForPost(url, pairList);
		
		if (information1.equals("0")) {
			TextView tv = (TextView)findViewById(R.id.show);
			tv.setText("很抱歉，程序出错了！");
		} else {
			
			try {
				JSONArray json = new JSONArray(information1);
				
				for(int i = 0; i < json.length(); i++){
					
				JSONObject object = (JSONObject)json.get(i);

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
				title1 = "气调库温度变化图T1" + " (单位：摄氏度)";
				title2 = "气调库温度变化图T2" + " (单位：摄氏度)";

				double[] t1 = new double[list.size()];
				double[] t2 = new double[list.size()];
				// 保留两位小数
				DecimalFormat sdf = new DecimalFormat("0.00");
				double tmp1 = 0;

				for (int i = 0; i < list.size(); i++) {
					tmp1 = list.get(i).getT1();
					t1[i] = Double.parseDouble(sdf.format(tmp1));
					tmp1 = list.get(i).getT2();
					t2[i] = Double.parseDouble(sdf.format(tmp1));
				}

				mDataset = new XYMultipleSeriesDataset();
				// t1,t2图表数据信息
				seriesOne = new XYSeries(title1);
				seriesTwo = new XYSeries(title2);
				count = 1;
				for (int i = 0; i < list.size(); i++) {

					seriesOne.add(count, t1[i]);
					seriesTwo.add(count, t2[i]);
					count++;
				}

				mDataset.addSeries(seriesOne);
				mDataset.addSeries(seriesTwo);

				mRenderer = new XYMultipleSeriesRenderer();
				// 设置图表的X轴的当前方向
				mRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
				mRenderer.setXTitle("时间");// 设置为X轴的标题
				mRenderer.setYTitle("温度");// 设置y轴的标题
				mRenderer.setAxisTitleTextSize(25);// 设置轴标题文本大小
				mRenderer.setChartTitle("气调库温度变化图");// 设置图表标题
				mRenderer.setChartTitleTextSize(28);// 设置图表标题文字的大小
				mRenderer.setLabelsTextSize(20);// 设置标签的文字大小
				mRenderer.setLegendTextSize(22);// 设置图例文本大小
				mRenderer.setPointSize(5f);// 设置点的大小
				mRenderer.setYAxisMin(0);// 设置y轴最小值是0
				mRenderer.setYAxisMax(1.5);
				mRenderer.setAxesColor(Color.BLACK);// 设置坐标轴的颜色
				mRenderer.setGridColor(Color.GRAY);// 设置网格线的颜色
				mRenderer.setLabelsColor(Color.BLACK);// 设置轴标题的颜色
				mRenderer.setXLabelsColor(Color.BLACK); // 設定X軸文字顏色
				mRenderer.setYLabelsColor(0, Color.BLACK); // 設定Y軸文字顏色
				mRenderer.setXLabelsAlign(Align.CENTER); // 設定X軸文字置中
				mRenderer.setYLabelsAlign(Align.CENTER); // 設定Y軸文字置中
				mRenderer.setShowGrid(true);// 显示网格
				mRenderer.setPanEnabled(true, false);// 设置视图能否拖动
				mRenderer.setZoomEnabled(true, false);//设置放大缩小时y轴不变
				mRenderer.setZoomButtonsVisible(true);// 设置放大缩小按钮是否可见
				mRenderer.setApplyBackgroundColor(true);// 设置是否显示背景色
				mRenderer.setBackgroundColor(Color.argb(0, 220, 228, 234));// 设置背景色
				mRenderer.setMarginsColor(Color.argb(0, 220, 228, 234));// 设置报表周边颜色
				mRenderer.setBarSpacing(1);//设置立柱间距
				// 将x标签栏目按要求显示
				addx = 1;
				for (int i = 0; i < list.size(); i = i + 5) {

					substr = list.get(i).getTime().substring(11, 19);
					mRenderer.addXTextLabel(addx, substr);
					addx = addx + 5;
				}

				mRenderer.setXLabels(0);// 设置只显示如1月，2月等替换后的东西，不显示1,2,3等
				mRenderer.setMargins(new int[] { 40, 40, 50, 30 });// 设置视图位置 (上/左/下/右)

				XYSeriesRenderer rOne = new XYSeriesRenderer();// (类似于一条线对象)
				rOne.setColor(Color.BLUE);// 设置颜色
				rOne.setPointStyle(PointStyle.CIRCLE);// 设置点的样式
				rOne.setFillPoints(true);// 填充点（显示的点是空心还是实心）
				rOne.setDisplayChartValues(true);// 将点的值显示出来
				rOne.setChartValuesSpacing(10);// 显示的点的值与图的距离
				rOne.setChartValuesTextSize(20);// 点的值的文字大小

				// r.setFillBelowLine(true);//是否填充折线图的下方
				// r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
				rOne.setLineWidth(3);// 设置线宽
				mRenderer.addSeriesRenderer(rOne);

				XYSeriesRenderer rTwo = new XYSeriesRenderer();// (类似于一条线对象)
				rTwo.setColor(Color.RED);// 设置颜色
				rTwo.setPointStyle(PointStyle.CIRCLE);// 设置点的样式
				rTwo.setFillPoints(true);// 填充点（显示的点是空心还是实心）
				rTwo.setDisplayChartValues(true);// 将点的值显示出来
				rTwo.setChartValuesSpacing(10);// 显示的点的值与图的距离
				rTwo.setChartValuesTextSize(20);// 点的值的文字大小

				// rTwo.setFillBelowLine(true);//是否填充折线图的下方
				// rTwo.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
				rTwo.setLineWidth(3);// 设置线宽

				mRenderer.addSeriesRenderer(rTwo);
				
				// 生成图表
				view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
				// 将图表添加到布局中去
				layout.addView(view);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
            
            @Override  
            public void onCheckedChanged(RadioGroup group, int checkedId) {  
                // TODO Auto-generated method stub  
                if(checkedId == day.getId())  
                {  
                   day(); 
                }else   if(checkedId == week.getId()) 
                {  
                    week();
                } else   if(checkedId == month.getId()){
                	
                	month();
                } 
            }  
        });  
	}

	public void same(String starttime, String endtime){
	
		list.clear();
		mDataset.removeSeries(seriesOne);
		mDataset.removeSeries(seriesTwo);
		seriesOne.clear();
		seriesTwo.clear();
		
		NameValuePair pair1 = new BasicNameValuePair("starttime", starttime);
		NameValuePair pair2 = new BasicNameValuePair("endtime", endtime);
		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		pairList.add(pair1);
		pairList.add(pair2);

		String url = httpUtil.BASE_URL + "/QtkHistoryServlet";
		String information1 = httpUtil.queryStringForPost(url, pairList);
		
		if (information1.equals("0")) {
			TextView tv = (TextView)findViewById(R.id.show);
			tv.setText("很抱歉，程序出错了！");
		} else {
			
			try {
				JSONArray json = new JSONArray(information1);
				
				for(int i = 0; i < json.length(); i++){
					
				JSONObject object = (JSONObject)json.get(i);

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

				double[] t1 = new double[list.size()];
				double[] t2 = new double[list.size()];
				// 保留两位小数
				DecimalFormat sdf = new DecimalFormat("0.00");
				double tmp1 = 0;

				for (int i = 0; i < list.size(); i++) {
					tmp1 = list.get(i).getT1();
					t1[i] = Double.parseDouble(sdf.format(tmp1));
					tmp1 = list.get(i).getT2();
					t2[i] = Double.parseDouble(sdf.format(tmp1));
				}

				count = 1;
				for (int i = 0; i < list.size(); i++) {

					seriesOne.add(count, t1[i]);
					seriesTwo.add(count, t2[i]);
					count++;
				}

				mDataset.addSeries(seriesOne);
				mDataset.addSeries(seriesTwo);

				addx = 1;
				for (int i = 0; i < list.size(); i = i + 5) {

					substr = list.get(i).getTime().substring(11, 19);
					mRenderer.addXTextLabel(addx, substr);
					addx = addx + 5;
				}
				
				
				view.invalidate();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void day(){
		
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			
			String starttime = df.format(new Date()).toString() + " 00:00:00";
			
			  Calendar cd = Calendar.getInstance();   
	          try {
				cd.setTime(df.parse(starttime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
	          cd.add(Calendar.DATE, 1);//增加1天   

			String endtime = df.format(cd.getTime()).toString() + " 00:00:00";
		
		same(starttime, endtime);
		
	}
	
public void week(){
		
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	
	String endtime = df.format(new Date()).toString() + " 00:00:00";
	
	  Calendar cd = Calendar.getInstance();   
      try {
		cd.setTime(df.parse(endtime));
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}   
      cd.add(Calendar.DATE, -7);//增加-7天   

	String starttime = df.format(cd.getTime()).toString() + " 00:00:00";
		
		same(starttime, endtime);
		
	}
	

public void month(){
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	
	String endtime = df.format(new Date()).toString() + " 00:00:00";
	
	  Calendar cd = Calendar.getInstance();   
      try {
		cd.setTime(df.parse(endtime));
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}   
      cd.add(Calendar.MONTH, -1);//增加-1个月   
	String starttime = df.format(cd.getTime()).toString() + " 00:00:00";
	
	same(starttime, endtime);
	
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Group ID
		int groupId = 0;
		// The order position of the item
		int menuItemOrder = Menu.NONE;

		menu.add(groupId, 0, menuItemOrder, "首页").setIcon(R.drawable.icon);
		menu.add(groupId, 1, menuItemOrder, "气调库首页").setIcon(R.drawable.icon);
		menu.add(groupId, 2, menuItemOrder, "氧气，二氧化碳").setIcon(R.drawable.icon);
		menu.add(groupId, 3, menuItemOrder, "湿度").setIcon(R.drawable.icon);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0: {
			Intent intent = new Intent();
			intent.setClass(QtkHistory.this, FirstPage.class);
			startActivity(intent);
			break;
		}
		case 1: {
			Intent intent = new Intent();
			intent.setClass(QtkHistory.this, Qtkshow.class);
			startActivity(intent);
			break;
		}
		case 2: {
			Intent intent = new Intent();
			intent.setClass(QtkHistory.this, GasHistory.class);
			startActivity(intent);
			break;
		}
		case 3: {
			Intent intent = new Intent();
			intent.setClass(QtkHistory.this, HumidityHistory.class);
			startActivity(intent);
			break;
		}
		default:
			break;
		}
		return true;
	}
}
