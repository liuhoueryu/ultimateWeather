package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.util.StringUtil;

public class TestUploadSingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象
		PrintWriter out = response.getWriter();		

		//步骤1---创建上传组件---实例化SmartUpload对象
        SmartUpload smartUpload = new SmartUpload("utf-8");

        //步骤2---初始化上传组件---调用initialize()方法
        smartUpload.initialize(getServletConfig(), request, response);
		
        String allowedFilesList = "jpg,gif";
        //String deniedFilesList = "exe,js,bat";
        
        int maxFileSize = 100;
        
        //步骤3---上传文件到服务器的临时内存中---调用upload()方法
        try {
        	
        	//检查2---判断文件的类型
        	smartUpload.setAllowedFilesList(allowedFilesList);
        	//smartUpload.setDeniedFilesList(deniedFilesList);
        	
        	//检查3---判断文件的大小
        	smartUpload.setMaxFileSize(1024*maxFileSize);
        	
        	//上传文件到服务器
			smartUpload.upload();
			
			//接收用户名
			String username = smartUpload.getRequest().getParameter("username");
			System.out.println("username=" + username);
			
			//步骤4---提取上传文件
	        SmartFile smartFile = smartUpload.getFiles().getFile(0);  
	        //检查1---判断文件是否上传
	        if(smartFile.isMissing()){
	        	out.print("<script>alert('文件不能为空');location='upload_single.jsp'</script>");
	        	return;
	        }
	        
	        //步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
	        String serverFilePath = "e:/upload";
	        
	        File dir = new File(serverFilePath);
	        if(!dir.exists()){
	        	dir.mkdir();
	        }
	        
	        //转换文件名
	        String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
	 
	        //步骤6---保存上传文件
	        smartFile.saveAs(serverFilePath + "/" + serverFilename);
	        
	        out.print("<script>alert('文件上传成功');location='upload_single.jsp'</script>");
	        
		} catch (SmartUploadException e) {
			out.print("<script>alert('文件上传失败');location='upload_single.jsp'</script>");
		} catch (SecurityException e) {
			out.print("<script>alert('只能上传"+allowedFilesList+"文件,而且大小不能超过"+maxFileSize+"KB');location='upload_single.jsp'</script>");
			//out.print("<script>alert('不能上传"+deniedFilesList+"文件');location='upload_single.jsp'</script>");
		}
     

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
