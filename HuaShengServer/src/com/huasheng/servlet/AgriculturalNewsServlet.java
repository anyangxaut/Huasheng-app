package com.huasheng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huasheng.dao.HuaShengNewsDao;
import com.huasheng.daoImpl.HuaShengNewsDaoImpl;
/**
 * 农业新闻相关的servlet，处理与农业新闻相关的业务
 * @author anyang
 *
 */
public class AgriculturalNewsServlet extends HttpServlet{

    String sql = " select * from AgriculturalNews order by CreateDate DESC ";
	
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
		
		out.flush();//清理servlet容器的缓冲区
		out.close();//关闭输出流对象，释放输出流资源
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request,response);
		
	}

}
