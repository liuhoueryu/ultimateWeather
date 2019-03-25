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
	//设置字符编码
	request.setCharacterEncoding("utf-8");

	//接收数据
	String username = request.getParameter("username");
	out.print("用户名=" + username);
	

	String password = request.getParameter("password");
	out.print("密码=" + password);
%>


</body>
</html>