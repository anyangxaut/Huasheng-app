package com.huasheng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huasheng.dao.QtkSearch;
import com.huasheng.daoImpl.QtkSearchImpl;

public class AchartengineServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		QtkSearch dao = new QtkSearchImpl();
		
		String latesttime = request.getParameter("latesttime");
			
		String result = dao.Search(latesttime);

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
