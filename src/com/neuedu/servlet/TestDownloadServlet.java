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
	
		//�����ļ���
		String filename = request.getParameter("filename");
		
		String filepath = request.getServletContext().getRealPath("download");
		
		//����1---�����ϴ��������
		SmartUpload smartUpload = new SmartUpload("utf-8");

	    //����2---��ʼ���ϴ��������
		smartUpload.initialize(getServletConfig(), request, response);

	    //����3---���ö����ݵĴ���ʽ---��ֹ���ֱ����ʾ�ļ�����
		smartUpload.setContentDisposition(null);   

		//����4---�����ļ�
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
