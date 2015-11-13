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

public class Expert extends BaseActivity {

	private final int NUMBER = 4;
	private GridView gridview1;
	private HuaShengNewsAdapter adapter1;
	private String[]  name= new String[NUMBER];
	private String[] intro = new String[NUMBER];
	private String resulttmp = null, url = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expert);
		
		
		 url = httpUtil.BASE_URL +"/ExpertServlet";
		 resulttmp = httpUtil.queryStringForGet(url);
		
		try {

			JSONArray json = new JSONArray(resulttmp);
			
			for(int i = 0; i < NUMBER; i++){
				
				JSONObject object = (JSONObject)json.get(i);
				name[i] = object.getString("ExpertName");
				intro[i] = object.getString("ExpertIntro");
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gridview1 = (GridView) findViewById(R.id.gridview1);
		adapter1 = new HuaShengNewsAdapter(this, name);
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
		intent.setClass(Expert.this, ShowExpert.class);
		
		Bundle bundle=new Bundle();
		bundle.putString("name", name[i]);
		bundle.putString("intro", intro[i]);
		bundle.putString("image", String.valueOf(i));
		intent.putExtras(bundle);
//		intent.putExtra("one", one);
//		intent.putExtra("two", two);
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
			intent.setClass(Expert.this, ExpertMore.class);
			Bundle bundle=new Bundle();
  			bundle.putString("inform", resulttmp);
  			intent.putExtras(bundle);
			startActivity(intent);	
			break;
		}
		case 1:{
			Intent intent=new Intent();
			intent.setClass(Expert.this, FirstPage.class);
			startActivity(intent);	
			break;
		}
		default:break;
		}
		return true;
	}

}
