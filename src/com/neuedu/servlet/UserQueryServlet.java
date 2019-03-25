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
	
	    //�������ļ��ж�ȡ�ַ�����
		//String charSet = this.getServletContext().getInitParameter("charSet");
		//request.setCharacterEncoding(charSet); 
		
		//��������
		String username = request.getParameter("username");
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		String regtime = request.getParameter("regtime");
		String begin = "";
		String end = "";
		
		//Ĭ��ֵ
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
			regtime = "ȫ��ʱ��"; 
		}
		
		if("ȫ��ʱ��".equals(regtime)){
			begin = "";
			end = "";
		}else{
			begin = request.getParameter("begin");
			end = request.getParameter("end");
		}
		
		UserService userService = new UserServiceImpl();
		
		List<User> list = userService.getUserList(username, gender, job, begin, end);

		//��request���Է�Χ�б����û��б�
		request.setAttribute("list", list);
		
		//��request���Է�Χ�б����û���,�Ա�,ְҵ,ע��ʱ��
		request.setAttribute("username", username);
		request.setAttribute("gender", gender);
		request.setAttribute("job", job);
		request.setAttribute("regtime", regtime);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		
		//ת������ѯҳ��
		request.getRequestDispatcher("user_query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
