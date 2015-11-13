package com.xaut.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyang.util.jsouParse;

public class ShowExpert extends BaseActivity {

	private int[] icons = { R.drawable.hanmy, R.drawable.liangj,
			R.drawable.raojp, R.drawable.renxl, R.drawable.yinma,
			R.drawable.fanch,R.drawable.jsfa};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_expert);
		
		TextView name = (TextView)findViewById(R.id.name);
		ImageView image = (ImageView)findViewById(R.id.image);
		TextView intro = (TextView)findViewById(R.id.intro);
		intro.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		Bundle bundle = this.getIntent().getExtras();
		String nametmp = bundle.getString("name");
		String introtmp = bundle.getString("intro");
	    String imagetmp = bundle.getString("image");
	    int position = Integer.parseInt(imagetmp);
		
		name.setText("                " + nametmp);
	
		Boolean isHTML = introtmp.contains("<");
		String tmp = "";
		if (isHTML) {
			jsouParse jsoup = new jsouParse();
			tmp = jsoup.parse(introtmp);
		}
		else
		{
			tmp = introtmp;
		}
		intro.setText(tmp);
		image.setImageResource(icons[position]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 // Group ID
		int groupId = 0;
		// The order position of the item
		int menuItemOrder = Menu.NONE;

		menu.add(groupId, 0, menuItemOrder, "专家介绍首页").setIcon(R.drawable.icon);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:{
			Intent intent=new Intent();
			intent.setClass(ShowExpert.this, Expert.class);
			startActivity(intent);
			break;
		}
		default:break;
		}
		return true;
	}

}
