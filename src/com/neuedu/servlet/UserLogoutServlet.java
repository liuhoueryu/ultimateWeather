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

		//��ȡsession����
        HttpSession session = request.getSession();	

        //��ȡapplication����
        ServletContext application = this.getServletContext();
	
		//��session���Է�Χ��ɾ��user����
		session.removeAttribute("user");
		
		//��application���Է�Χ�и��������û����� -1
		application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount") - 1);
		
		//�ض��򵽵�¼ҳ��
		response.sendRedirect("user_login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
