package com.neuedu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	//����ָ�����Ƶ�cookie
	public static String findCookie(String name, HttpServletRequest request){
		
		Cookie[] cookies = request.getCookies();

		if(cookies!=null){
			
			for(Cookie cookie : cookies){

				if(cookie.getName().equals(name)){
					return cookie.getValue();
				}
			}
		}
		
		return null;
		
	}
	
	//���ͳ־û�cookie
	public static void addCookie(String name, String value, int days, HttpServletResponse response){
		
		//����Cookie
		Cookie cookie = new Cookie(name, value);
		
		//����cookie�������ʱ��
		cookie.setMaxAge(60*60*24*days);
		
		//����cookie
		response.addCookie(cookie);
		
	}
	
	//ɾ���־û�Cookie
	public static void deleteCookie(String name, HttpServletResponse response){
		
		//����Cookie
		Cookie cookie = new Cookie(name, "");
		
		//����cookie�������ʱ�� 0��ʾ����ɾ��
		cookie.setMaxAge(0);
		
		//����cookie
		response.addCookie(cookie);
		
	}
	
}
