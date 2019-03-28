package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.util.DBManager;
import com.neuedu.util.StringUtil;

public class UserRegisterServlet extends HttpServlet {
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
		
		//******************�ļ��ϴ�******************

		//����1---�����ϴ����---ʵ����SmartUpload����
        SmartUpload smartUpload = new SmartUpload("utf-8");

        //����2---��ʼ���ϴ����---����initialize()����
        smartUpload.initialize(getServletConfig(), request, response);
		
        String allowedFilesList = "jpg,gif";
        
        int maxFileSize = 10;
        
        String photo = null;
        
        //����3---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
        try {
        	
        	//���2---�ж��ļ�������
        	smartUpload.setAllowedFilesList(allowedFilesList);
        	
        	//���3---�ж��ļ��Ĵ�С
        	smartUpload.setMaxFileSize(1024*1024*maxFileSize);
        	
        	//�ϴ��ļ���������
			smartUpload.upload();
			
			photo = smartUpload.getRequest().getParameter("photo");
			
			if("0.gif".equals(photo)){   //ѡ���Զ���ͷ��
				
				//����4---��ȡ�ϴ��ļ�
		        SmartFile smartFile = smartUpload.getFiles().getFile(0); 
		        
		        //���1---�ж��ļ��Ƿ��ϴ�
		        if(smartFile.isMissing()){
		        	out.print("<script>alert('�Զ���ͷ����Ϊ��');history.back()</script>");
		        	return;
		        }
		        
		        //����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
		        String serverFilePath = request.getServletContext().getRealPath("image/photo");
		        
		        File dir = new File(serverFilePath);
		        if(!dir.exists()){
		        	dir.mkdir();
		        }
		        
		        //ת���ļ���
		        String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
		 
		        //����6---�����ϴ��ļ�
		        smartFile.saveAs(serverFilePath + "/" + serverFilename);
		        
		        //����ͷ����ļ���
		        photo = serverFilename;
			}
			
		} catch (SmartUploadException e) {
			out.print("<script>alert('�ļ��ϴ�ʧ��');history.back()</script>");
			return;
		} catch (SecurityException e) {
			out.print("<script>alert('ֻ���ϴ�"+allowedFilesList+"�ļ�,���Ҵ�С���ܳ���"+maxFileSize+"MB');history.back()</script>");
			return;
		}
     
		//******************�ļ��ϴ�******************
		
		//������֤��
		String valCode = smartUpload.getRequest().getParameter("valCode");
		
		//��session���Է�Χ��ȡ����֤��Ĵ�
		String valCodeInSession = (String)session.getAttribute("valCodeInSession");
		
		if(!valCode.equalsIgnoreCase(valCodeInSession)){
			out.print("<script>alert('server:��֤�������������������');history.back()</script>");
			return;
		}
		
		//��������
		String username = smartUpload.getRequest().getParameter("username");
		String password = smartUpload.getRequest().getParameter("password");
		String phonenumber=smartUpload.getRequest().getParameter("phonenumber");
		
		String gender = smartUpload.getRequest().getParameter("gender");
		String job = smartUpload.getRequest().getParameter("job");
		String[] interests = smartUpload.getRequest().getParameterValues("interest");
		
		//�ַ�������ƴ���ַ���
		String interest = "";
		if(interests!=null){
			for(String s : interests){
				interest += s + " ";
			}
			interest = interest.trim();
		}
		
		//�������ļ��ж�ȡ���û�����
		int registerScore = Integer.parseInt(this.getInitParameter("registerScore"));
		
		//��������ݼ���
		if(username.length()<5 || username.length()>10){
			out.print("<script>alert('server:�û����ĳ��ȱ�����5-10���ַ�֮��');history.back()</script>");
			return;
		}
		
		UserService userService = new UserServiceImpl();
		
		//��������ݼ���---�û����Ƿ����
		if(userService.checkUsername(username)){
			out.print("<script>alert('server:�û����Ѵ���');history.back()</script>");
			return;
		}
		
		//���������user����
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setScore(registerScore);
		user.setPhoto(photo);
		user.setGender(gender);
		user.setJob(job);
		user.setInterest(interest);
		user.setRegtime(new Date());
		user.setPhonenumber(phonenumber);
		
		
		user = userService.register(user);
		
		if(user!=null){
			
			//��user���󱣴浽session���Է�Χ��
			session.setAttribute("user", user);
			
			//��application���Է�Χ�и��������û����� +1
			if(application.getAttribute("onlineCount")==null){   //��һ�η���
				application.setAttribute("onlineCount", 1);
			}else{
				int onlineCount = (Integer)application.getAttribute("onlineCount");
				application.setAttribute("onlineCount", onlineCount + 1);
			}
			
			//�ӳ���ת����ҳ
			//response.sendRedirect("index.jsp");
			response.sendRedirect("user_register_result.jsp");
		}else{
			//ʹ��JSʵ�ֿͻ����ض���
			out.print("<script>alert('ע��ʧ�ܣ�����������');history.back()</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
