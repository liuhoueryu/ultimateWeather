package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAjaxServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���������������
		response.setContentType("text/html;charset=utf-8");	

		//��ȡout�������
		PrintWriter out = response.getWriter();		
		
		String username = request.getParameter("username");
		
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		
		System.out.println("username=" + username);
		
		if("neuedu".equals(username)){
			out.print("yes");   //��Ӧ�ı�
		}else{
			out.print("no");   //��Ӧ�ı�
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
