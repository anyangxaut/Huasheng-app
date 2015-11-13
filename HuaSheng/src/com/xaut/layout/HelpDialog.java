package com.xaut.layout;

import com.xaut.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
/**
 * 帮助界面的显示，自定义布局，和关于布局类似
 * @author anyang
 *
 */
public class HelpDialog extends Dialog{

	Context context;
    public HelpDialog(Context context) {
    	super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }
    public HelpDialog(Context context, int theme){
    	super(context, theme);
        this.context = context;
    }
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.help);
    }
	
}
