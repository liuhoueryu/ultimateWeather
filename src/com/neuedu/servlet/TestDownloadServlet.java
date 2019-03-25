package com.neuedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class TestDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//接收文件名
		String filename = request.getParameter("filename");
		
		String filepath = request.getServletContext().getRealPath("download");
		
		//步骤1---创建上传组件对象
		SmartUpload smartUpload = new SmartUpload("utf-8");

	    //步骤2---初始化上传组件对象
		smartUpload.initialize(getServletConfig(), request, response);

	    //步骤3---设置对内容的处理方式---阻止浏览直接显示文件内容
		smartUpload.setContentDisposition(null);   

		//步骤4---下载文件
	    try {
			smartUpload.downloadFile(filepath + "/" + filename, null, "xxx.txt");
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}   

	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
