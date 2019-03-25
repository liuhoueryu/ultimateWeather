package com.neuedu.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������������ΪjpegͼƬ
		response.setContentType("image/jpeg");

		//�������
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");

		//*************������֤���****************

		//ָ����֤�����ݵ����ݷ�Χ   �ɸ�����Ҫ�޸�
		//String scope = "123456789ABCDEFGHIJKLMNPQRSTUV��֤�����ݵ�����WXYZabcdefghijklmnpqrstuvwxyz";  
		String scope = "123456";  

		//��֤�볤��Ϊ4���ַ�    �ɸ�����Ҫ�޸�
		int length = 4;

		//ָ����֤��ͼƬ�Ĵ�С    �ɸ�����Ҫ�޸�
		int width = 80;
		int height = 25;

		//�����������----�ο�Random��
		Random rd = new Random();

		//������֤����ַ���
		String valCode = "";
		
		for(int i=0; i<length; i++){
			valCode += scope.charAt(rd.nextInt(scope.length()));
		}
		
		//��ȡsession����
	  	HttpSession session = request.getSession();

		//����֤��𰸱�����session���Է�Χ��
		session.setAttribute("valCodeInSession", valCode);

		//*************������֤��ͼƬ****************

		//��������
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		//�ӻ����ϻ�ȡ����
		Graphics g = img.getGraphics();

		//������ɫΪǳ��ɫ�ľ���   �ɸ�����Ҫ�޸�
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);

		//����10�������ߣ���ɫ�����λ�����   �ɸ�����Ҫ�޸�
		for(int i=0; i<10; i++){
			g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
			g.drawLine(rd.nextInt(width), rd.nextInt(height), rd.nextInt(width), rd.nextInt(height));
		}

		Font[] fonts = {new Font("����", Font.BOLD, 20), new Font("����", Font.BOLD, 18), new Font("����", Font.BOLD, 22)};
		
		//������֤���ÿ���ַ�
		for(int i=0; i<length; i++){

			//���������ɫ   �ɸ�����Ҫ�޸�
			g.setColor(new Color(rd.nextInt(100), rd.nextInt(100), rd.nextInt(100)));

			//�����������   �ɸ�����Ҫ�޸�
			g.setFont(fonts[rd.nextInt(fonts.length)]);
										
			//��ת����    �ɸ�����Ҫ�޸�
			Graphics2D g2d = (Graphics2D)g;
			AffineTransform trans = new AffineTransform();
			trans.rotate(rd.nextInt(45)*3.14/180, 15*i+10, 7);
			g2d.setTransform(trans);
			
			//���������ַ�    ��������ַ��߶�  �ɸ�����Ҫ�޸�
			g.drawString(valCode.charAt(i) + "", width * i / length + 5, rd.nextInt(5) + 15);
		}

		//�ͷŻ���
		g.dispose();

		//����ͼƬ
		ImageIO.write(img, "JPEG", response.getOutputStream());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
