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
	String username = request.getParameter("username");

	//中文转码
	username = new String(username.getBytes("iso8859-1"), "utf-8");
	out.print("用户名=" + username);
	

	String password = request.getParameter("password");
	
	//中文转码
	password = new String(password.getBytes("iso8859-1"), "utf-8");
	
	out.print("密码=" + password);
%>


</body>
</html>