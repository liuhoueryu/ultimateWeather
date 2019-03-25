package com.neuedu.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.util.DBManager;

public class UserQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    //从配置文件中读取字符编码
		//String charSet = this.getServletContext().getInitParameter("charSet");
		//request.setCharacterEncoding(charSet); 
		
		//接收数据
		String username = request.getParameter("username");
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		String regtime = request.getParameter("regtime");
		String begin = "";
		String end = "";
		
		//默认值
		if(username==null){
			username = ""; 
		}
		if(gender==null){
			gender = ""; 
		}
		if(job==null){
			job = ""; 
		}
		if(regtime==null){
			regtime = "全部时间"; 
		}
		
		if("全部时间".equals(regtime)){
			begin = "";
			end = "";
		}else{
			begin = request.getParameter("begin");
			end = request.getParameter("end");
		}
		
		UserService userService = new UserServiceImpl();
		
		List<User> list = userService.getUserList(username, gender, job, begin, end);

		//在request属性范围中保存用户列表
		request.setAttribute("list", list);
		
		//在request属性范围中保存用户名,性别,职业,注册时间
		request.setAttribute("username", username);
		request.setAttribute("gender", gender);
		request.setAttribute("job", job);
		request.setAttribute("regtime", regtime);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		
		//转发到查询页面
		request.getRequestDispatcher("user_query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
