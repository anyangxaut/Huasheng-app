package com.huasheng.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.huasheng.dao.QtkSearch;
import com.huasheng.util.DBOperation;

public class QtkSearchImpl implements QtkSearch{

	@Override
	public String chart(String latesttime, String storenum) {
		// TODO Auto-generated method stub
		
		String result = null;
		String sql = " select * from TCtrl where StoreNum = " + storenum +"order by Time DESC ";
		
		DBOperation dboperation = new DBOperation ();
		ResultSet rs = dboperation.findsql(sql);
		JSONObject object = new JSONObject();
		try {
			if (rs != null && rs.next() == true){
				
				int idtmp = rs.getInt(1);
				int storenumtmp = rs.getInt(2);
				String timetmp = rs.getString(3);
				float t1tmp = rs.getFloat(4);
				float t2tmp = rs.getFloat(5);
				float o2tmp = rs.getFloat(6);
				float co2tmp = rs.getFloat(7);
				float humiditytmp = rs.getFloat(8);
				
				if(timetmp.compareTo(latesttime)>0){
					
				try {
					
					object.put("id", idtmp);
					object.put("storenum", storenumtmp);
					object.put("time", timetmp);
					object.put("t1", t1tmp);
					object.put("t2", t2tmp);
					object.put("o2", o2tmp);
					object.put("co2", co2tmp);
					object.put("humidity", humiditytmp);
					
					dboperation.closeConn();
					return object.toString();
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dboperation.closeConn();
		return result;
	}

	@Override
	public String table(String starttime, String endtime) {
		// TODO Auto-generated method stub
		String result = null;
		
		String sql = " select * from TCtrl where Time between '" + starttime + "' and '" + endtime + "'";
//		String sql = "select * from TCtrl where Time between '2013-12-02 16:49:18' and '2013-12-02 24:00:00'";
		JSONArray json = new JSONArray();
		
		
		DBOperation dboperation = new DBOperation ();
		ResultSet rs = dboperation.findsql(sql);
		
		try {
			while (rs != null && rs.next() == true){
				
				int idtmp = rs.getInt(1);
				int storenumtmp = rs.getInt(2);
				String timetmp = rs.getString(3);
				float t1tmp = rs.getFloat(4);
				float t2tmp = rs.getFloat(5);
				float o2tmp = rs.getFloat(6);
				float co2tmp = rs.getFloat(7);
				float humiditytmp = rs.getFloat(8);

				JSONObject object = new JSONObject();
				
				try {
					
					object.put("id", idtmp);
					object.put("storenum", storenumtmp);
					object.put("time", timetmp);
					object.put("t1", t1tmp);
					object.put("t2", t2tmp);
					object.put("o2", o2tmp);
					object.put("co2", co2tmp);
					object.put("humidity", humiditytmp);
					
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
	public String alarm(String starttime, String endtime) {
		// TODO Auto-generated method stub
		String result = null;
		String sql = " select * from AlertLog where Time between '" + starttime + "'and'" + endtime + "'";
//		String sql = "select * from AlertLog where Time between '2013-12-02 16:49:18' and '2013-12-02 24:00:00'";
		JSONArray json = new JSONArray();
		
		
		DBOperation dboperation = new DBOperation ();
		ResultSet rs = dboperation.findsql(sql);
		
		try {
			while (rs != null && rs.next() == true){
				
				int idtmp = rs.getInt(1);
				int storenumtmp = rs.getInt(2);
				String timetmp = rs.getString(3);
				String reasontmp = rs.getString(4);

				JSONObject object = new JSONObject();
				
				try {
					
					object.put("id", idtmp);
					object.put("storenum", storenumtmp);
					object.put("time", timetmp);
					object.put("reason", reasontmp);
					
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
	
	
	
public static void main(String args[]){
		
		QtkSearch dao = new QtkSearchImpl();
		String u = dao.chart("2012-9-16 00:00:00","1");
		System.out.println(u);
	}
}
