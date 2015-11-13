package com.xaut.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.anyang.util.httpUtil;
import com.xaut.entity.Qtkentity;

public class Gas extends BaseActivity {

	private Context context;
	private Timer timer = new Timer();
	private TimerTask task;
	private Handler handler;
	private XYMultipleSeriesRenderer mRenderer;
	private XYMultipleSeriesDataset mDataset;
	private XYSeries seriesOne, seriesTwo;
	private GraphicalView view;
	private final int NUM = 10;
	private ArrayList<Qtkentity> list;
	private String url;
	private String title1, title2;
	private double tmp1 = 0;
	private int count, addx, flag;
	private LinearLayout layout;
	private String[] substr = null;
	// 保留两位小数
	private DecimalFormat df = new DecimalFormat("0.00");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gas);

		context = getApplicationContext();
		// 这里获得activity_main界面上的布局，下面会把图表画在这个布局里面
		layout = (LinearLayout) findViewById(R.id.achartengine);

		getdata(); // 初始化数据集

		title1 = "气调库氧气变化图" + " (单位：%)";
		title2 = "气调库二氧化碳变化图" + " (单位：%)";

		mDataset = new XYMultipleSeriesDataset();
		// t1,t2图表数据信息
		seriesOne = new XYSeries(title1);
		seriesTwo = new XYSeries(title2);
		count = 1;
		flag = 0;

		for (int i = 0; i < NUM; i++) {
			tmp1 = list.get(i).getO2();
			seriesOne.add(count, Double.parseDouble(df.format(tmp1)));
			tmp1 = list.get(i).getCo2();
			seriesTwo.add(count, Double.parseDouble(df.format(tmp1)));
			count++;
		}

		mDataset.addSeries(seriesOne);
		mDataset.addSeries(seriesTwo);

		mRenderer = new XYMultipleSeriesRenderer();
		// 设置图表的X轴的当前方向
		mRenderer
				.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
		mRenderer.setXTitle("时间");// 设置为X轴的标题
		mRenderer.setYTitle("百分比");// 设置y轴的标题
		mRenderer.setAxisTitleTextSize(25);// 设置轴标题文本大小
		mRenderer.setChartTitle("气调库氧气,二氧化碳变化图");// 设置图表标题
		mRenderer.setChartTitleTextSize(30);// 设置图表标题文字的大小
		mRenderer.setLabelsTextSize(20);// 设置标签的文字大小
		mRenderer.setLegendTextSize(22);// 设置图例文本大小
		mRenderer.setPointSize(5f);// 设置点的大小
		mRenderer.setYAxisMin(0);// 设置y轴最小值是-1
		mRenderer.setYAxisMax(100);
		mRenderer.setAxesColor(Color.BLACK);// 设置坐标轴的颜色
		mRenderer.setGridColor(Color.GRAY);// 设置网格线的颜色
		mRenderer.setLabelsColor(Color.BLACK);// 设置轴标题的颜色
		mRenderer.setXLabelsColor(Color.BLACK); // 設定X軸文字顏色
		mRenderer.setYLabelsColor(0, Color.BLACK); // 設定Y軸文字顏色
		mRenderer.setXLabelsAlign(Align.CENTER); // 設定X軸文字置中
		mRenderer.setYLabelsAlign(Align.CENTER); // 設定Y軸文字置中
		mRenderer.setShowGrid(true);// 显示网格
		mRenderer.setPanEnabled(false, false);// 设置视图能否拖动
		mRenderer.setZoomButtonsVisible(false);// 设置放大缩小按钮是否可见
		mRenderer.setApplyBackgroundColor(true);// 设置是否显示背景色
		mRenderer.setBackgroundColor(Color.argb(0, 220, 228, 234));// 设置背景色
		mRenderer.setMarginsColor(Color.argb(0, 220, 228, 234));// 设置报表周边颜色
		// 将x标签栏目按要求显示
		addx = 1;
		for (int i = 0; i < list.size(); i = i + 2) {

			substr = list.get(i).getTime().split(" ");
			mRenderer.addXTextLabel(addx, substr[1]);
			addx = addx + 2;
		}

		mRenderer.setXLabels(0);// 设置只显示如1月，2月等替换后的东西，不显示1,2,3等
		mRenderer.setMargins(new int[] { 60, 40, 50, 30 });// 设置视图位置 (上/左/下/右)

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
		// view = ChartFactory.getLineChartView(this, mDataset, mRenderer);
		// view.setBackgroundColor(Color.BLACK);
		// setContentView(view);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 刷新图表
				updategas();
				super.handleMessage(msg);
			}
		};

		task = new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};

		timer.schedule(task, 1000, 20000);
	}
	
	@Override
	public void onPause() {
		// 当结束程序时关掉Timer
		if(timer != null){
		timer.cancel();
		}
		super.onPause();
	}
	
	// 气体更新
	private void updategas() {

		String latesttime = list.get(list.size() - 1).getTime();
		NameValuePair pair1 = new BasicNameValuePair("latesttime", latesttime);
		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		pairList.add(pair1);

		url = httpUtil.BASE_URL + "/AchartengineServlet";
		String information1 = httpUtil.queryStringForPost(url, pairList);

		if (information1.equals("0")) {

		} else {
			try {
				JSONObject object = new JSONObject(information1);

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

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mDataset.removeSeries(seriesOne);
			mDataset.removeSeries(seriesTwo);
			seriesOne.clear();
			seriesTwo.clear();
			count = 1;
			flag++;

			for (int i = flag; i < NUM + flag; i++) {

				tmp1 = list.get(i).getO2();
				seriesOne.add(count, Double.parseDouble(df.format(tmp1)));
				tmp1 = list.get(i).getCo2();
				seriesTwo.add(count, Double.parseDouble(df.format(tmp1)));
				count++;
			}

			addx = 1;
			for (int i = flag; i < NUM + flag; i = i + 2) {

				substr[0] = list.get(i).getTime().substring(11, 19);
				mRenderer.addXTextLabel(addx, substr[0]);
				addx = addx + 2;
			}
			// }
			mDataset.addSeries(seriesOne);
			mDataset.addSeries(seriesTwo);

			// 视图更新，没有这一步，曲线不会呈现动态
			// 如果在非UI主线程中，需要调用postInvalidate()，具体参考api
			view.invalidate();
		}

	}

	private void getdata() {

		// 初始化数据。。。十个点,T1=T2=0
		list = new ArrayList<Qtkentity>();
		list.add(new Qtkentity(0, 1, "2012/10/20 11:50:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 12:00:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 12:10:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 12:20:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 12:30:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 12:40:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 12:50:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 13:00:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 13:10:00", 0, 0, 0, 0, 0));
		list.add(new Qtkentity(0, 1, "2012/10/20 13:20:00", 0, 0, 0, 0, 0));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Group ID
		int groupId = 0;
		// The order position of the item
		int menuItemOrder = Menu.NONE;

		menu.add(groupId, 0, menuItemOrder, "首页").setIcon(R.drawable.icon);
		menu.add(groupId, 1, menuItemOrder, "气调库首页").setIcon(R.drawable.icon);
		menu.add(groupId, 2, menuItemOrder, "温度变化图").setIcon(R.drawable.icon);
		menu.add(groupId, 3, menuItemOrder, "湿度变化图").setIcon(R.drawable.icon);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0: {
			Intent intent = new Intent();
			intent.setClass(Gas.this, FirstPage.class);
			startActivity(intent);
			timer.cancel();
			break;
		}
		case 1: {
			Intent intent = new Intent();
			intent.setClass(Gas.this, Qtkshow.class);
			startActivity(intent);
			timer.cancel();
			break;
		}
		case 2: {
			Intent intent = new Intent();
			intent.setClass(Gas.this, Qtktest.class);
			startActivity(intent);
			timer.cancel();
			break;
		}
		case 3: {
			Intent intent = new Intent();
			intent.setClass(Gas.this, Humidity.class);
			startActivity(intent);
			timer.cancel();
			break;
		}
		default:
			break;
		}
		return true;
	}


}
