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
	
	DBManager dbManager = DBManager.getInstance();
	String sql = "select * from user where username = ? and password = ?";
	ResultSet rs = dbManager.execQuery(sql, username, password);
	
	if(rs.next()){   //登录成功
			
		//取出用户编号
		int userid = rs.getInt(1);
	
		//取出积分
		int score = rs.getInt(4);
	
		//取出头像
		String photo = rs.getString(5);
		
		//将用户名,积分,头像保存到session属性范围中
		/* session.setAttribute("username", username);
		session.setAttribute("score", score);
		session.setAttribute("photo", photo); */
		
		//创建并填充user对象
		User user = new User();
		user.setUserid(userid);
		user.setUsername(username);
		user.setScore(score);
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
		
		//重定向到主页
		response.sendRedirect("index.jsp");
		
	}else{
		//使用JS实现客户端重定向
		out.print("<script>alert('用户名或密码错误，请重新登录');location='user_login.jsp'</script>");
	}
	
	dbManager.closeConnection();
	
%>
</body>
</html>