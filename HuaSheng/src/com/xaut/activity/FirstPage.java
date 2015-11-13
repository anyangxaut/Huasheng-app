package com.xaut.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.xaut.layout.AboutDialog;
import com.xaut.layout.HelpDialog;
import com.xaut.layout.Ipsetting;
import com.xaut.layout.MyAdapter;

public class FirstPage extends BaseActivity {

	private GridView gridview1;
	private MyAdapter adapter1;
	private static boolean tag = true;
	private int[] icons = { R.drawable.huasheng, R.drawable.nongyexinwei,
			R.drawable.jishufangan, R.drawable.jishuzhidao,
			R.drawable.shiyongjishu, R.drawable.nongzi,
			R.drawable.xingweiguifan,R.drawable.jsfa, R.drawable.temp, R.drawable.qitiaoku};
	private String[] items1 = { "行业新闻", "农业新闻", "技术方案", "技术指导", "实用技术", "农资介绍", "规范与标准", "专家介绍", "天气预报", "气调库"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_page);

		if(tag){
		Toast.makeText(FirstPage.this, "欢迎使用！", Toast.LENGTH_SHORT).show();
		tag = false;
		}
		adapter1 = new MyAdapter(this, items1, icons);
		
		gridview1 = (GridView) findViewById(R.id.gridview1);
		gridview1.setAdapter(adapter1);
		
		//判断手机是否联网，如果没有联网则给出提示。
		//监视网络连接状态 
       ConnectivityManager cManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    // 获取正连接的网络的信息
		NetworkInfo info = cManager.getActiveNetworkInfo();
		
		  if (info != null && info.isAvailable()){
			  
		  }else{
		       
			  new AlertDialog.Builder(this)
				.setTitle("提示框")
				.setMessage("该应用需要连接网络，请您确保网络正常连接！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						exit();
						
					}}).show();

		  } 
		
		gridview1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				switch (arg2) {
				case 0: {
					Intent intent = new Intent(FirstPage.this,HuaShengNews.class);
					startActivity(intent);
					break;
				}
				case 1: {
					Intent intent = new Intent(FirstPage.this,AgriculturalNews.class);
					startActivity(intent);
					break;
				}
				case 2: {
					Intent intent = new Intent(FirstPage.this,TechMethod.class);
					startActivity(intent);
					break;
				}
				case 3: {
					Intent intent = new Intent(FirstPage.this,TechGuide.class);
					startActivity(intent);
					break;
				}
				case 4: {
					Intent intent = new Intent(FirstPage.this,ShiYongJiShu.class);
					startActivity(intent);
					break;
				}
				case 5: {
					Intent intent = new Intent(FirstPage.this,NongZiJieShao.class);
					startActivity(intent);
					break;
				}
				case 6: {
					Intent intent = new Intent(FirstPage.this,Standard.class);
					startActivity(intent);
					break;
				}
                case 7: {
                	Intent intent = new Intent(FirstPage.this,Expert.class);
					startActivity(intent);
					break;
				}
                case 8: {
                	Intent intent = new Intent(FirstPage.this,Weather.class);
					startActivity(intent);
					break;
				}
                case 9: {
                	Intent intent = new Intent(FirstPage.this, Qtkshow.class);
					startActivity(intent);
					break;
				}
				default:break;
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Group ID
				int groupId = 0;
				// The order position of the item
				int menuItemOrder = Menu.NONE;

				menu.add(groupId, 0, menuItemOrder, "关于").setIcon(R.drawable.icon);
				menu.add(groupId, 1, menuItemOrder, "帮助").setIcon(R.drawable.icon);
				menu.add(groupId, 2, menuItemOrder, "IP设置").setIcon(R.drawable.icon);
				menu.add(groupId, 3, menuItemOrder, "退出").setIcon(R.drawable.icon);
				
				return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:{
			AboutDialog dialog = new AboutDialog(FirstPage.this, R.style.MyDialog);
			dialog.show();
			break;
		}
		case 1:{
			HelpDialog dialog = new HelpDialog(FirstPage.this, R.style.MyDialog);
			dialog.show();
			break;
		}
		case 2:{
			Ipsetting dialog = new Ipsetting(FirstPage.this, R.style.MyDialog);
			dialog.show();
			break;
		}
		case 3:{
			exit();
			break;
		}
		default:break;
		}
		return true;
	}

}
