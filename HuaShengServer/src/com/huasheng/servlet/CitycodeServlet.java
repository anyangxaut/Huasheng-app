package com.huasheng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huasheng.dao.Citycode;
import com.huasheng.daoImpl.CitycodeImpl;

public class CitycodeServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		Citycode dao = new CitycodeImpl();
		
		//http request默认传输方式为iso-8859-1，而浏览器与数据库编码方式为UTF-8，因此需要转换编码格式，否则会乱码（原理：统一编码）
		String title = new String(request.getParameter("title").getBytes("iso-8859-1"), "UTF-8");
				
		String result = dao.search(title);

		if(result!=null){
			
			out.print(result);
			
		}else{

			out.print("0");
		}
		
		out.flush();//清理servlet容器的缓冲区
		out.close();//关闭输出流对象，释放输出流资源
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request,response);
		
	}

	
}
