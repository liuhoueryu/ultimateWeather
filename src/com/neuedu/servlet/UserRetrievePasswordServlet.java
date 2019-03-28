package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserRetrievePasswordServlet
 */
public class UserRetrievePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String phonenumber = request.getParameter("phonenumber");
		String password = request.getParameter("password");
		System.out.println("password:" + password);
		String repassword = request.getParameter("repassword");
		UserService userService = new UserServiceImpl();
		boolean user = userService.retrievePassword(username, phonenumber);
		if ("".equals(phonenumber))
			out.print("<script>alert('�ֻ��Ų���Ϊ�գ�');history.back()</script>");
		else {
			if (user) {
				if ("".equals(password))
					out.print("<script>alert('���벻��Ϊ��');history.back()</script>");
				else if (password.equals(repassword)) {
					userService.retrieveUserPassword(username, password);
					out.print("<script>alert('�ɹ�������');location='user_login.jsp'</script>");
				}

				else if (!password.equals(repassword))
					out.print("<script>alert('�������벻һ��');history.back()</script>");
			} else
				out.print("<script>alert('û�и��û������ֻ��Ŵ���');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
