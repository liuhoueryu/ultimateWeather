package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.util.CookieUtil;
import com.neuedu.util.DBManager;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//���������������
		response.setContentType("text/html;charset=utf-8");	

		//��ȡout�������
		PrintWriter out = response.getWriter();		

		//��ȡsession����
	    HttpSession session = request.getSession();	

	    //��ȡapplication����
		ServletContext application = this.getServletContext();

	    //�������ļ��ж�ȡ�ַ�����
		//String charSet = this.getServletContext().getInitParameter("charSet");
		//request.setCharacterEncoding(charSet); 
		
		//��������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String autoLogin = request.getParameter("autoLogin");
		
		//�������ļ��ж�ȡ��¼�ӷ�
		int loginScore = Integer.parseInt(this.getInitParameter("loginScore"));
		
		UserService userService = new UserServiceImpl();
		
		//����ҵ�񷽷�
		User user = userService.login(username, password, loginScore);
		
		if(user!=null){  //��¼�ɹ�
			
			//��¼�ɹ������ѡ�и�ѡ�򣬷���һ���־û�cookie
			if(autoLogin!=null){
				CookieUtil.addCookie("userinfo", username + ":" + password, 7, response);
			}
			else {
				CookieUtil.deleteCookie("userinfo", response);
			}
			
			//��user���󱣴浽session���Է�Χ��
			session.setAttribute("user", user);
			
			//��application���Է�Χ�и��������û����� +1
			if(application.getAttribute("onlineCount")==null){   //��һ�η���
				application.setAttribute("onlineCount", 1);
			}else{
				int onlineCount = (Integer)application.getAttribute("onlineCount");
				application.setAttribute("onlineCount", onlineCount + 1);
			}
			
			//�ض�����ҳ
			//response.sendRedirect(response.encodeRedirectURL("index.jsp"));
			
			if(session.getAttribute("prevURL")==null){
				response.sendRedirect("index.jsp");
			}else{
				String prevURL = (String)session.getAttribute("prevURL");
				response.sendRedirect(prevURL);
			}
			
		}else{
			
			//ʹ��JSʵ�ֿͻ����ض���
			out.print("<script>alert('�û�����������������µ�¼');location='user_login.jsp'</script>");
		}
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
