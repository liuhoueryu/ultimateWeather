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
	pageContext.setAttribute("username", "neuedu_pagecontext");
	request.setAttribute("username", "neuedu_request");
	session.setAttribute("username", "东软_session");
	application.setAttribute("username", "东软_application");

	request.getRequestDispatcher("b.jsp").forward(request, response);	
%>

</body>
</html>