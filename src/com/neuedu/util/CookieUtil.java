package com.neuedu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	//查找指定名称的cookie
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
	
	//发送持久化cookie
	public static void addCookie(String name, String value, int days, HttpServletResponse response){
		
		//创建Cookie
		Cookie cookie = new Cookie(name, value);
		
		//设置cookie的最大存活时间
		cookie.setMaxAge(60*60*24*days);
		
		//发送cookie
		response.addCookie(cookie);
		
	}
	
	//删除持久化Cookie
	public static void deleteCookie(String name, HttpServletResponse response){
		
		//创建Cookie
		Cookie cookie = new Cookie(name, "");
		
		//设置cookie的最大存活时间 0表示立即删除
		cookie.setMaxAge(0);
		
		//发送cookie
		response.addCookie(cookie);
		
	}
	
}
