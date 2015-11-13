package com.xaut.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xaut.activity.R;
/**
 * gridview内部布局，控制gridview显示，属于自定义布局，无图片背景
 * @author anyang
 *
 */
public class HuaShengNewsAdapter extends BaseAdapter {

	
	 private Context _ct;
	 private String[] _items;

     public HuaShengNewsAdapter(Context ct,String[] items) 
	  {
	    _ct=ct;
	    _items=items;
	  }
     
	  public int getCount()
	  {
	    return _items.length;
	  }

	  public Object getItem(int arg0)
	  {
	    return _items[arg0];
	  }

	  public long getItemId(int position)
	  {
	    return position;
	  }

	  public View getView(int position, View convertView, ViewGroup parent)
	  {
		  //抽象类LayoutInflater，其作用类似于findViewById()方法。不过它是用来找res/layout下的xml布局文件，并且实例化；
		  //而findViewById()方法仅仅是通过控件Id查找某xml布局文件下具体widget控件（如Button、TextView等）。
	    LayoutInflater factory = LayoutInflater.from(_ct);
	    //对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入
//	    public View inflate (int resource, ViewGroup root) 
//	    reSource：View的layout的ID
//	    root：如果为null，则将此View作为根,此时既可以应用此View中的其他控件了。
//	            如果!null,  则将默认的layout作为View的根。
	    View v = (View) factory.inflate(R.layout.itemadapter, null);
	    ImageView iv = (ImageView) v.findViewById(R.id.icon);
	    TextView tv = (TextView) v.findViewById(R.id.text);
	    tv.setText( "  "+ _items[position]);
	    iv.setImageResource(R.drawable.item);
	    return v;
	  } 
}
