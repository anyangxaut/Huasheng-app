package com.xaut.tsupervise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
/**
 * 默认启动的activity：启动动画
 * @author anyang
 *
 */
public class MainActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_main);
		
		new Handler().postDelayed(new Runnable() {

			public void run() {
				
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, FirstPage.class);
				startActivity(intent);	
				finish();
			}

		}, SPLASH_DISPLAY_LENGHT);

	}

}
