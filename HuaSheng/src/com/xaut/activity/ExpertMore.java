package com.xaut.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ExpertMore extends BaseActivity {

	private ListView mListView = null;
	private TextView title = null;
	private final int NUMBER = 7;
	private String[]  name= new String[NUMBER];
	private String[] intro = new String[NUMBER];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expert_more);
		
		Bundle bundle = this.getIntent().getExtras();
		String resulttmp = bundle.getString("inform");
		
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
			title = (TextView)findViewById(R.id.title);
			mListView = (ListView) findViewById(R.id.content);
			title.setText("专家介绍");
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.my_simple_list_item,name);
			mListView.setAdapter(adapter);
			mListView.setDividerHeight(10);
			mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					 shownews(arg2);
				}
			});
	}

	 public void shownews(int i){
			
			Intent intent=new Intent();
			intent.setClass(ExpertMore.this, ShowExpert.class);
			
			Bundle bundle=new Bundle();
			bundle.putString("name", name[i]);
			bundle.putString("intro", intro[i]);
			bundle.putString("image", String.valueOf(i));
			intent.putExtras(bundle);
//			intent.putExtra("one", one);
//			intent.putExtra("two", two);
			startActivity(intent);	
		}


		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.show_news_more, menu);
			return true;
		}

}
