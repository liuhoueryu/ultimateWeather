package com.neuedu.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取session对象
        HttpSession session = request.getSession();	

        //获取application对象
        ServletContext application = this.getServletContext();
	
		//从session属性范围中删除user对象
		session.removeAttribute("user");
		
		//在application属性范围中更新在线用户人数 -1
		application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount") - 1);
		
		//重定向到登录页面
		response.sendRedirect("user_login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
