package com.xaut.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.anyang.util.httpUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowWeather extends BaseActivity {

	private TextView show = null;
	private int flag = 0;
	private StringBuffer sb = new StringBuffer(256);
    private String city = null;
    private ImageView iv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_weather);

		show = (TextView)findViewById(R.id.show);
		iv = (ImageView)findViewById(R.id.imageView);

		Bundle bundle = this.getIntent().getExtras();
		flag = bundle.getInt("flag");
	    city = bundle.getString("city");
		
	    if(city.equals("error")){
	    	
	    	show.setText("出错了：无法获取天气信息，抱歉！");
	    	
	    }else{
		// 查询所在城市编码
		String url1 = httpUtil.BASE_URL + "/CitycodeServlet"
				+ "?title=" + city;

		String citycode = httpUtil.queryStringForGet(url1);
		try {

			JSONObject json = new JSONObject(citycode);
			String code_long = json.getString("code_long");
			// 获取当地天气状况
			String url2 = "http://m.weather.com.cn/data/" + code_long
					+ ".html";
			String weather = httpUtil.queryStringForGet(url2);

			JSONObject weatherinfo = new JSONObject(weather);
			String weatherinfostr = weatherinfo
					.getString("weatherinfo");

			JSONObject today = new JSONObject(weatherinfostr);

			sb.append("\n城市： ");
			sb.append(today.getString("city"));
			sb.append("\n\n气温： ");
			sb.append(today.getString("temp" + flag));
			sb.append("\n\n天气： ");
			sb.append(today.getString("weather" + flag));
			sb.append("\n\n风力：");
			sb.append(today.getString("wind" + flag));
			
			String img = null;
			flag = 2 * flag - 1;
			img = today.getString("img" + flag);
			int tmp = Integer.parseInt(img);
            switch(tmp){
            case 0:{iv.setImageResource(R.drawable.d00); break;}
            case 1:{iv.setImageResource(R.drawable.d01); break;}
            case 2:{iv.setImageResource(R.drawable.d02); break;}
            case 3:{iv.setImageResource(R.drawable.d03); break;}
            case 4:{iv.setImageResource(R.drawable.d04); break;}
            case 5:{iv.setImageResource(R.drawable.d05); break;}
            case 6:{iv.setImageResource(R.drawable.d06); break;}
            case 7:{iv.setImageResource(R.drawable.d07); break;}
            case 8:{iv.setImageResource(R.drawable.d08); break;}
            case 9:{iv.setImageResource(R.drawable.d09); break;}
            default:break;
            }
			
        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.append("\n\n出错了 : ");
			sb.append(e);
		}
		show.setText(sb.toString());
		
	}
	} 

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Group ID
		int groupId = 0;
		// The order position of the item
		int menuItemOrder = Menu.NONE;

		menu.add(groupId, 0, menuItemOrder, "首页").setIcon(R.drawable.icon);
		menu.add(groupId, 1, menuItemOrder, "天气预报首页").setIcon(R.drawable.icon);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0: {
			Intent intent = new Intent();
			intent.setClass(ShowWeather.this, FirstPage.class);
			startActivity(intent);
			break;
		}
		case 1: {
			Intent intent = new Intent();
			intent.setClass(ShowWeather.this, Weather.class);
			startActivity(intent);
			break;
		}
		default:
			break;
		}
		return true;
	}
}
