package com.huasheng.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.huasheng.dao.HuaShengNewsDao;
import com.huasheng.util.DBOperation;
/**
 * 实现接口类，完成查询功能
 * @author anyang
 *
 */
public class HuaShengNewsDaoImpl implements HuaShengNewsDao{

	//查询最新动态
	public String searchlatest(String sql){
		
		String result = null;
		JSONArray json = new JSONArray();
		
		
		DBOperation dboperation = new DBOperation ();
		ResultSet rs = dboperation.findsql(sql);
		
		try {
			while (rs != null && rs.next() == true){
				
//				int hsnewsidtmp = rs.getInt(1);
				String titletmp = rs.getString(2);
				String contenttmp = rs.getString(3);
				String authortmp = rs.getString(4);
				String createdatetmp = rs.getString(5);
				String clickcounttmp = rs.getString(6);
				
				
				JSONObject object = new JSONObject();
				
				try {
					
//					object.put("HSNewsId", hsnewsidtmp);
					object.put("Title", titletmp);
					object.put("Content",contenttmp);
					object.put("Author", authortmp);
					object.put("CreateDate",createdatetmp);
					object.put("ClickCount", clickcounttmp);
					
					json.put(object);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dboperation.closeConn();
			return json.toString();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dboperation.closeConn();
		return result;
	}
	
	@Override
	public String searchexpert(String sql) {
		// TODO Auto-generated method stub
		String result = null;
		JSONArray json = new JSONArray();
		
		
		DBOperation dboperation = new DBOperation ();
		ResultSet rs = dboperation.findsql(sql);
		
		try {
			while (rs != null && rs.next() == true){
				
				String nametmp = rs.getString(2);
				String introtmp = rs.getString(4);
				
				
				JSONObject object = new JSONObject();
				
				try {
					
					object.put("ExpertName", nametmp);
					object.put("ExpertIntro",introtmp);
					
					json.put(object);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return json.toString();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dboperation.closeConn();
		return result;
	}
	
	//单元测试
	public static void main(String[] args){
		
//		String sql = " select * from HuaShengNews order by CreateDate DESC ";
		String sql = " select * from Expert ";
		
		HuaShengNewsDao dao = new HuaShengNewsDaoImpl();
		String u = dao.searchexpert(sql);
		System.out.println(u);
	}

}
