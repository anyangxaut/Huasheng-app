package com.xaut.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class InterceptScrollContainer extends LinearLayout {

	public InterceptScrollContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public InterceptScrollContainer(Context context) {
		super(context);
		
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		
		return true;
		
	
	}
	
}
