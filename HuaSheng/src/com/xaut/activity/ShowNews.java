package com.xaut.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.anyang.util.jsouParse;
/**
 * 新闻显示，包括标题，作者，正文部分
 * @author anyang
 *
 */
public class ShowNews extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_news);
		
		TextView title = (TextView)findViewById(R.id.title);
		TextView author = (TextView)findViewById(R.id.author);
		TextView content = (TextView)findViewById(R.id.content);
		content.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		Bundle bundle = this.getIntent().getExtras();
		String titletmp = bundle.getString("title");
		String datetmp = bundle.getString("CreateDate").substring(0, 10);
		String contenttmp = bundle.getString("content");
		
        author.setText(datetmp);
		title.setText(titletmp);
	
		//判断新闻内容是否包含html标签，若包含，则解析，否则直接输出
		Boolean isHTML = contenttmp.contains("<");
		String tmp = "";
		if (isHTML) {
			jsouParse jsoup = new jsouParse();
			tmp = jsoup.parse(contenttmp);
		}
		else
		{
			tmp = contenttmp;
		}
		content.setText(tmp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 // Group ID
		int groupId = 0;
		// The order position of the item
		int menuItemOrder = Menu.NONE;

		menu.add(groupId, 0, menuItemOrder, "首页").setIcon(R.drawable.icon);
		menu.add(groupId, 1, menuItemOrder, "华圣新闻首页").setIcon(R.drawable.icon);
		menu.add(groupId, 2, menuItemOrder, "农业新闻首页").setIcon(R.drawable.icon);
		menu.add(groupId, 3, menuItemOrder, "技术方案首页").setIcon(R.drawable.icon);
		menu.add(groupId, 4, menuItemOrder, "技术指导首页").setIcon(R.drawable.icon);
		menu.add(groupId, 5, menuItemOrder, "规范与标准首页").setIcon(R.drawable.icon);
		menu.add(groupId, 6, menuItemOrder, "实用技术首页").setIcon(R.drawable.icon);
		menu.add(groupId, 7, menuItemOrder, "农资介绍首页").setIcon(R.drawable.icon);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, FirstPage.class);
			startActivity(intent);
			break;
		}
		case 1:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, HuaShengNews.class);
			startActivity(intent);
			break;
		}
		case 2:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, AgriculturalNews.class);
			startActivity(intent);
			break;
		}
		case 3:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, TechMethod.class);
			startActivity(intent);
			break;
		}
		case 4:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, TechGuide.class);
			startActivity(intent);
			break;
		}
		case 5:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, Standard.class);
			startActivity(intent);
			break;
		}
		case 6:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, ShiYongJiShu.class);
			startActivity(intent);
			break;
		}
		case 7:{
			Intent intent=new Intent();
			intent.setClass(ShowNews.this, NongZiJieShao.class);
			startActivity(intent);
			break;
		}
		default:break;
		}
		return true;
	}


}
