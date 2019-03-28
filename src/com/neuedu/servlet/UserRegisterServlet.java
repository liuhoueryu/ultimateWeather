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
	
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象
		PrintWriter out = response.getWriter();		

		//获取session对象
	    HttpSession session = request.getSession();	

	    //获取application对象
		ServletContext application = this.getServletContext();

		//从配置文件中读取字符编码
		//String charSet = this.getServletContext().getInitParameter("charSet");
		//request.setCharacterEncoding(charSet); 
		
		//******************文件上传******************

		//步骤1---创建上传组件---实例化SmartUpload对象
        SmartUpload smartUpload = new SmartUpload("utf-8");

        //步骤2---初始化上传组件---调用initialize()方法
        smartUpload.initialize(getServletConfig(), request, response);
		
        String allowedFilesList = "jpg,gif";
        
        int maxFileSize = 10;
        
        String photo = null;
        
        //步骤3---上传文件到服务器的临时内存中---调用upload()方法
        try {
        	
        	//检查2---判断文件的类型
        	smartUpload.setAllowedFilesList(allowedFilesList);
        	
        	//检查3---判断文件的大小
        	smartUpload.setMaxFileSize(1024*1024*maxFileSize);
        	
        	//上传文件到服务器
			smartUpload.upload();
			
			photo = smartUpload.getRequest().getParameter("photo");
			
			if("0.gif".equals(photo)){   //选择自定义头像
				
				//步骤4---提取上传文件
		        SmartFile smartFile = smartUpload.getFiles().getFile(0); 
		        
		        //检查1---判断文件是否上传
		        if(smartFile.isMissing()){
		        	out.print("<script>alert('自定义头像不能为空');history.back()</script>");
		        	return;
		        }
		        
		        //步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
		        String serverFilePath = request.getServletContext().getRealPath("image/photo");
		        
		        File dir = new File(serverFilePath);
		        if(!dir.exists()){
		        	dir.mkdir();
		        }
		        
		        //转换文件名
		        String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
		 
		        //步骤6---保存上传文件
		        smartFile.saveAs(serverFilePath + "/" + serverFilename);
		        
		        //更新头像的文件名
		        photo = serverFilename;
			}
			
		} catch (SmartUploadException e) {
			out.print("<script>alert('文件上传失败');history.back()</script>");
			return;
		} catch (SecurityException e) {
			out.print("<script>alert('只能上传"+allowedFilesList+"文件,而且大小不能超过"+maxFileSize+"MB');history.back()</script>");
			return;
		}
     
		//******************文件上传******************
		
		//接收验证码
		String valCode = smartUpload.getRequest().getParameter("valCode");
		
		//从session属性范围中取出验证码的答案
		String valCodeInSession = (String)session.getAttribute("valCodeInSession");
		
		if(!valCode.equalsIgnoreCase(valCodeInSession)){
			out.print("<script>alert('server:验证码输入错误，请重新输入');history.back()</script>");
			return;
		}
		
		//接收数据
		String username = smartUpload.getRequest().getParameter("username");
		String password = smartUpload.getRequest().getParameter("password");
		String phonenumber=smartUpload.getRequest().getParameter("phonenumber");
		
		String gender = smartUpload.getRequest().getParameter("gender");
		String job = smartUpload.getRequest().getParameter("job");
		String[] interests = smartUpload.getRequest().getParameterValues("interest");
		
		//字符串数组拼接字符串
		String interest = "";
		if(interests!=null){
			for(String s : interests){
				interest += s + " ";
			}
			interest = interest.trim();
		}
		
		//从配置文件中读取新用户积分
		int registerScore = Integer.parseInt(this.getInitParameter("registerScore"));
		
		//服务端数据检验
		if(username.length()<2 || username.length()>10){
			out.print("<script>alert('server:用户名的长度必须在2-10个字符之间');history.back()</script>");
			return;
		}
		
		UserService userService = new UserServiceImpl();
		
		//服务端数据检验---用户名是否存在
		if(userService.checkUsername(username)){
			out.print("<script>alert('server:用户名已存在');history.back()</script>");
			return;
		}
		
		//创建并填充user对象
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
			
			//将user对象保存到session属性范围中
			session.setAttribute("user", user);
			
			//在application属性范围中更新在线用户人数 +1
			if(application.getAttribute("onlineCount")==null){   //第一次访问
				application.setAttribute("onlineCount", 1);
			}else{
				int onlineCount = (Integer)application.getAttribute("onlineCount");
				application.setAttribute("onlineCount", onlineCount + 1);
			}
			
			//延迟跳转到主页
			//response.sendRedirect("index.jsp");
			response.sendRedirect("user_register_result.jsp");
		}else{
			//使用JS实现客户端重定向
			out.print("<script>alert('注册失败，请重新输入');history.back()</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
