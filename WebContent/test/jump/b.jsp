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
	//接收数据	
	String username = request.getParameter("username");
	
	//请求转发到c.jsp
	//request.getRequestDispatcher("/c.jsp").forward(request, response);
	
	//重定向到c.jsp
	response.sendRedirect("c.jsp?username=" + username);
	//response.sendRedirect("http://www.baidu.com");

%>
</body>
</html>