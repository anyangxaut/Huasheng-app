package com.xaut.layout;

import com.xaut.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ipsetting extends Dialog {
	
	    public static String IPADDRESS = "202.200.112.165";
	    private EditText ipaddress = null;
	    private Button ok = null, cancel = null;
	    Context context;
	  
	    public Ipsetting(Context context) {
	        super(context);
	        // TODO Auto-generated constructor stub
	        this.context = context;
	    }
	    public Ipsetting(Context context, int theme){
	        super(context, theme);
	        this.context = context;
	    }
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.ipsetting);
	        
	          ok = (Button) findViewById(R.id.ok);
	         cancel = (Button) findViewById(R.id.cancel);
	         ipaddress = (EditText) findViewById(R.id.ip);
	         
	         ok.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					IPADDRESS = ipaddress.getText().toString(); 
					 dismiss();
				}});
	         cancel.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 cancel();
				}});
	    }
}
