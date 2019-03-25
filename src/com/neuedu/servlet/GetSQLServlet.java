package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSQLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象
		PrintWriter out = response.getWriter();		
		
		//获取session对象
	    HttpSession session = request.getSession();	
	    
		int direction = Integer.parseInt(request.getParameter("direction"));
		
		int index = (Integer)session.getAttribute("index");
		
		List<String> list = (List<String>)session.getAttribute("list");
		
		if(direction==1){  //下一条
			
			if(index<list.size()-1){
				index++;
			}
			
		}else{   //上一条
			
			if(index>0){
				index--;
			}
		}
		
		session.setAttribute("index", index);
		
		out.print(list.get(index));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
