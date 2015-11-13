package com.huasheng.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.huasheng.dao.Citycode;
import com.huasheng.util.DBOperation;

public class CitycodeImpl implements com.huasheng.dao.Citycode{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Citycode citydao = new CitycodeImpl();
		String u = citydao.search("西安");
		System.out.println(u);
	}

	@Override
	public String search(String title) {
		// TODO Auto-generated method stub
		String result = null;
		JSONObject object = new JSONObject();
		String sql = " select * from city where title= " + "'" + title + "'";
		
		DBOperation dboperation = new DBOperation ();
		ResultSet rs = dboperation.findsqllocal(sql);
		
		try {
			while (rs != null && rs.next() == true){
				
				int idtmp = rs.getInt(1);
				int pidtmp = rs.getInt(2);
				String codetmp = rs.getString(3);
				String code_longtmp = rs.getString(4);
				String titletmp = rs.getString(5);
				
				try {
					
					object.put("id", idtmp);
					object.put("pid", pidtmp);
					object.put("code", codetmp);
					object.put("code_long", code_longtmp);
					object.put("title", titletmp);
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dboperation.closeConn();
			return object.toString();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dboperation.closeConn();
		return result;
	}

}
