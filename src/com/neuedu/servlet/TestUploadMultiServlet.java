package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.util.StringUtil;

public class TestUploadMultiServlet extends HttpServlet {
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
	      
        //步骤3---准备上传文件的存储路径和文件名---保证文件名唯一
        String serverFilePath = "e:/upload";
        
        File dir = new File(serverFilePath);
        if(!dir.exists()){
        	dir.mkdir();
        }
        
        try {
        	//步骤4---上传文件到服务器的临时内存中---调用upload()方法
			smartUpload.upload();
        }catch (SmartUploadException e) {
			out.print("<script>alert('文件上传失败');location='upload_multi.jsp'</script>");
			return;
		}	
        
        String allowedFilesList = "jpg,gif,bmp";
        int maxFileSize = 100;
        boolean uploadFlag = true;
        
		//步骤5---循环提取每一个上传文件
		for (int i = 0; i < smartUpload.getFiles().getCount(); i++) {
				
	        SmartFile smartFile = smartUpload.getFiles().getFile(i);  
	        
	        //检查1---判断文件是否上传
	        if(smartFile.isMissing()){
	        	out.print("<script>alert('第"+(i+1)+"个文件不能为空')</script>");
	        	uploadFlag = false;
	        	continue;
	        }
	      
	        //检查2---文件类型
	        String extension = smartFile.getFileExt();
	        if(!allowedFilesList.contains(extension)){
	        	out.print("<script>alert('第"+(i+1)+"个文件只能上传"+ allowedFilesList+"')</script>");
	        	uploadFlag = false;
	        	continue;
	        }
	        
	        //检查3---文件大小
	        if(smartFile.getSize()>1024*maxFileSize){
	        	out.print("<script>alert('第"+(i+1)+"个文件大小不能超过="+ maxFileSize+"KB')</script>");
	        	uploadFlag = false;
	        	continue;
	        }
	        
	        //转换文件名
	        String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
	 
	        //步骤6---保存上传文件
	        try {
				smartFile.saveAs(serverFilePath + "/" + serverFilename);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}
		
		if(uploadFlag){
			out.print("<script>alert('全部文件上传成功');location='upload_multi.jsp'</script>");
		}else{
			out.print("<script>alert('部分文件上传成功');location='upload_multi.jsp'</script>");
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
