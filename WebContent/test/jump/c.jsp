<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

这是c.jsp

<%
String username = request.getParameter("username");
username = new String(username.getBytes("iso8859-1"), "utf-8");
out.print("用户名=" + username);
%>

</body>
</html>