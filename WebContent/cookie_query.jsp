<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

服务器端产生的Sessionid = <%=session.getId() %>
<br/>

<%
	Cookie[] cookies = request.getCookies();

	if(cookies!=null){
		for(Cookie cookie : cookies){
			out.print("name=" + cookie.getName() + " value=" + cookie.getValue() + "<br/>");
		}
	}else{
		out.print("没有任何的cookie");
	}
%>
</body>
</html>