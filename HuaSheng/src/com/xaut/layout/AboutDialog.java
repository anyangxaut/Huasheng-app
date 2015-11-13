package com.xaut.layout;

import com.xaut.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
/**
 * 关于界面的显示布局，自定义布局
 * @author anyang
 *
 */
public class AboutDialog extends Dialog{
	
	  Context context;
	    public AboutDialog(Context context) {
	        super(context);
	        // TODO Auto-generated constructor stub
	        this.context = context;
	    }
	    public AboutDialog(Context context, int theme){
	        super(context, theme);
	        this.context = context;
	    }
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.about);
	    }

}
