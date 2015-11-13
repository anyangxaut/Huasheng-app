package com.huasheng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huasheng.dao.HuaShengNewsDao;
import com.huasheng.daoImpl.HuaShengNewsDaoImpl;

public class ChemicalFertilizerServlet extends HttpServlet{

	 String sql = " select * from ChemicalFertilizer order by CreateDate DESC ";
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			response.setContentType("application/json;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			HuaShengNewsDao dao = new HuaShengNewsDaoImpl();
			
			String result = dao.searchlatest(sql);

			if(result!=null){
				
				out.print(result);
				
			}else{

				out.print("0");
			}
			
			out.flush();
			out.close();
		}
		
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			doGet(request,response);
			
		}
		
}
