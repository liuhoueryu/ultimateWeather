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
	
		//���������������
		response.setContentType("text/html;charset=utf-8");	

		//��ȡout�������
		PrintWriter out = response.getWriter();		

		//����1---�����ϴ����---ʵ����SmartUpload����
        SmartUpload smartUpload = new SmartUpload("utf-8");

        //����2---��ʼ���ϴ����---����initialize()����
        smartUpload.initialize(getServletConfig(), request, response);
	      
        //����3---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
        String serverFilePath = "e:/upload";
        
        File dir = new File(serverFilePath);
        if(!dir.exists()){
        	dir.mkdir();
        }
        
        try {
        	//����4---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
			smartUpload.upload();
        }catch (SmartUploadException e) {
			out.print("<script>alert('�ļ��ϴ�ʧ��');location='upload_multi.jsp'</script>");
			return;
		}	
        
        String allowedFilesList = "jpg,gif,bmp";
        int maxFileSize = 100;
        boolean uploadFlag = true;
        
		//����5---ѭ����ȡÿһ���ϴ��ļ�
		for (int i = 0; i < smartUpload.getFiles().getCount(); i++) {
				
	        SmartFile smartFile = smartUpload.getFiles().getFile(i);  
	        
	        //���1---�ж��ļ��Ƿ��ϴ�
	        if(smartFile.isMissing()){
	        	out.print("<script>alert('��"+(i+1)+"���ļ�����Ϊ��')</script>");
	        	uploadFlag = false;
	        	continue;
	        }
	      
	        //���2---�ļ�����
	        String extension = smartFile.getFileExt();
	        if(!allowedFilesList.contains(extension)){
	        	out.print("<script>alert('��"+(i+1)+"���ļ�ֻ���ϴ�"+ allowedFilesList+"')</script>");
	        	uploadFlag = false;
	        	continue;
	        }
	        
	        //���3---�ļ���С
	        if(smartFile.getSize()>1024*maxFileSize){
	        	out.print("<script>alert('��"+(i+1)+"���ļ���С���ܳ���="+ maxFileSize+"KB')</script>");
	        	uploadFlag = false;
	        	continue;
	        }
	        
	        //ת���ļ���
	        String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
	 
	        //����6---�����ϴ��ļ�
	        try {
				smartFile.saveAs(serverFilePath + "/" + serverFilename);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}
		
		if(uploadFlag){
			out.print("<script>alert('ȫ���ļ��ϴ��ɹ�');location='upload_multi.jsp'</script>");
		}else{
			out.print("<script>alert('�����ļ��ϴ��ɹ�');location='upload_multi.jsp'</script>");
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
