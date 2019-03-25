<%@page import="java.util.Date"%>
<%@page import="com.neuedu.entity.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.util.DBManager"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	//转码
	request.setCharacterEncoding("utf-8");

	//接收数据
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String photo = request.getParameter("photo");
	
	String gender = request.getParameter("gender");
	String job = request.getParameter("job");
	String[] interests = request.getParameterValues("interest");
	
	//字符串数组拼接字符串
	String interest = "";
	if(interests!=null){
		for(String s : interests){
			interest += s + " ";
		}
		interest = interest.trim();
	}
	
	//新用户积分默认为10分
	int registerScore = 10;
	
	//服务端数据检验
	if(username.length()<5 || username.length()>10){
		out.print("<script>alert('server:用户名的长度必须在5-10个字符之间');history.back()</script>");
		return;
	}
	
	DBManager dbManager = DBManager.getInstance();
	
	String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	boolean flag = dbManager.execUpdate(sql, username, password, registerScore, photo, gender, job, interest, new Date());
	
	if(flag){   //插入成功
		
		sql = "select * from user where username = ? and password = ?";
		ResultSet rs = dbManager.execQuery(sql, username, password);
		
		if(rs.next()){   //登录成功
				
			//取出用户编号
			int userid = rs.getInt(1);
	
			//创建并填充user对象
			User user = new User();
			user.setUserid(userid);
			user.setUsername(username);
			user.setScore(registerScore);
			user.setPhoto(photo);
			
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
		}
		
	}else{     //插入失败
		
		//使用JS实现客户端重定向
		out.print("<script>alert('注册失败，请重新输入');history.back()</script>");
	}
	
	dbManager.closeConnection();
	
%>
</body>
</html>