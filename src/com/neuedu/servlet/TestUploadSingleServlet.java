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
	
		//���������������
		response.setContentType("text/html;charset=utf-8");	

		//��ȡout�������
		PrintWriter out = response.getWriter();		

		//����1---�����ϴ����---ʵ����SmartUpload����
        SmartUpload smartUpload = new SmartUpload("utf-8");

        //����2---��ʼ���ϴ����---����initialize()����
        smartUpload.initialize(getServletConfig(), request, response);
		
        String allowedFilesList = "jpg,gif";
        //String deniedFilesList = "exe,js,bat";
        
        int maxFileSize = 100;
        
        //����3---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
        try {
        	
        	//���2---�ж��ļ�������
        	smartUpload.setAllowedFilesList(allowedFilesList);
        	//smartUpload.setDeniedFilesList(deniedFilesList);
        	
        	//���3---�ж��ļ��Ĵ�С
        	smartUpload.setMaxFileSize(1024*maxFileSize);
        	
        	//�ϴ��ļ���������
			smartUpload.upload();
			
			//�����û���
			String username = smartUpload.getRequest().getParameter("username");
			System.out.println("username=" + username);
			
			//����4---��ȡ�ϴ��ļ�
	        SmartFile smartFile = smartUpload.getFiles().getFile(0);  
	        //���1---�ж��ļ��Ƿ��ϴ�
	        if(smartFile.isMissing()){
	        	out.print("<script>alert('�ļ�����Ϊ��');location='upload_single.jsp'</script>");
	        	return;
	        }
	        
	        //����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
	        String serverFilePath = "e:/upload";
	        
	        File dir = new File(serverFilePath);
	        if(!dir.exists()){
	        	dir.mkdir();
	        }
	        
	        //ת���ļ���
	        String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
	 
	        //����6---�����ϴ��ļ�
	        smartFile.saveAs(serverFilePath + "/" + serverFilename);
	        
	        out.print("<script>alert('�ļ��ϴ��ɹ�');location='upload_single.jsp'</script>");
	        
		} catch (SmartUploadException e) {
			out.print("<script>alert('�ļ��ϴ�ʧ��');location='upload_single.jsp'</script>");
		} catch (SecurityException e) {
			out.print("<script>alert('ֻ���ϴ�"+allowedFilesList+"�ļ�,���Ҵ�С���ܳ���"+maxFileSize+"KB');location='upload_single.jsp'</script>");
			//out.print("<script>alert('�����ϴ�"+deniedFilesList+"�ļ�');location='upload_single.jsp'</script>");
		}
     

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
