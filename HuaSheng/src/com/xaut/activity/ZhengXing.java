package com.xaut.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.anyang.util.httpUtil;
import com.xaut.layout.HuaShengNewsAdapter;

public class ZhengXing extends BaseActivity {

	private final int NUMBER = 4;
	private GridView gridview1;
	private HuaShengNewsAdapter adapter1;
	private String[] content1 = new String[NUMBER];
	private String[] title1 = new String[NUMBER];
	private String[] date1 = new String[NUMBER];
	private String resulttmp = null, url = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zheng_xing);
		
		 url = httpUtil.BASE_URL +"/ZhengXingServlet";
         resulttmp = httpUtil.queryStringForGet(url);
		
		try {

			JSONArray json = new JSONArray(resulttmp);
			
			for(int i = 0; i < NUMBER; i++){
				
				JSONObject object = (JSONObject)json.get(i);
				
				content1[i] = object.getString("Content");
				title1[i] = object.getString("Title");
				date1[i] = object.getString("CreateDate");
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gridview1 = (GridView) findViewById(R.id.gridview1);
		adapter1 = new HuaShengNewsAdapter(this, title1);
		gridview1.setAdapter(adapter1);
		
		gridview1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				switch (arg2) {
				case 0: {
					shownews(0);
					break;
				}
				case 1: {
					shownews(1);
					break;
				}
				case 2: {
					shownews(2);
					break;
				}
				case 3: {
					shownews(3);
					break;
				}
				default:break;
				}

			}
		});
	}

public void shownews(int i){
		
		Intent intent=new Intent();
		intent.setClass(ZhengXing.this, ShowNews.class);
		
		Bundle bundle=new Bundle();
		bundle.putString("title", title1[i]);
		bundle.putString("CreateDate", date1[i]);
		bundle.putString("content", content1[i]);
		intent.putExtras(bundle);
		startActivity(intent);	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 // Group ID
		int groupId = 0;
		// The order position of the item
		int menuItemOrder = Menu.NONE;
	
		menu.add(groupId, 0, menuItemOrder, "更多信息").setIcon(R.drawable.icon);
		menu.add(groupId, 1, menuItemOrder, "首页").setIcon(R.drawable.icon);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:{
			Intent intent=new Intent();
			intent.setClass(ZhengXing.this, ZhengXingMore.class);
  			Bundle bundle=new Bundle();
  			bundle.putString("inform", resulttmp);
  			intent.putExtras(bundle);
			startActivity(intent);	
			break;
		}
		case 1:{
			Intent intent=new Intent();
			intent.setClass(ZhengXing.this, FirstPage.class);
			startActivity(intent);	
			break;
		}
		default:break;
		}
		return true;
	}

}
