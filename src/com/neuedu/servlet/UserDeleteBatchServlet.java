package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;

public class UserDeleteBatchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//���������������  
		response.setContentType("text/html;charset=utf-8");	

		//��ȡout�������
		PrintWriter out = response.getWriter();		
		
		//��������
		String[] userids = request.getParameterValues("userids");
		
		
		//����ҵ�񷽷�
		UserService userService = new UserServiceImpl();
		
		if(userService.deleteUser(userids)){
			out.println("<script>alert('����ɾ���ɹ�');location='user_query.jsp'</script>");
		}else{
			out.println("<script>alert('����ɾ��ʧ��');history.back()</script>");
		}
		
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
