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

		// 获取out输出对象
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String phonenumber = request.getParameter("phonenumber");
		String password = request.getParameter("password");
		System.out.println("password:" + password);
		String repassword = request.getParameter("repassword");
		UserService userService = new UserServiceImpl();
		boolean user = userService.retrievePassword(username, phonenumber);
		if ("".equals(phonenumber))
			out.print("<script>alert('手机号不能为空！');history.back()</script>");
		else {
			if (user) {
				if ("".equals(password))
					out.print("<script>alert('密码不能为空');history.back()</script>");
				else if (password.equals(repassword)) {
					userService.retrieveUserPassword(username, password);
					out.print("<script>alert('成功！！！');location='user_login.jsp'</script>");
				}

				else if (!password.equals(repassword))
					out.print("<script>alert('两次密码不一致');history.back()</script>");
			} else
				out.print("<script>alert('没有该用户，或手机号错误！');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
