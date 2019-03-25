<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
	String scount = request.getParameter("count");

	//默认值处理
	if(scount==null){
		scount="10";
	}
	
	//数据转型
	int count = Integer.parseInt(scount);
%>

<form action="draw_line.jsp" method="post">
	画<input type="text" name="count" value="<%=count%>"/>条
	<input type="submit" value="绘制"/>
</form>

<%
	for(int i=1; i<=count; i++){
		
		if(i%2==0){
			out.println("<hr color='red' width='"+i+"%'/>");
		}else{
			out.println("<hr color='yellow' width='"+i+"%'/>");
		}
	}
%>


<%-- 
<%
	for(int i=1; i<=100; i++){
		
		String color = null;
		
		if(i%2==0){
			color="blue";
		}else{
			color="green";
		}
%>

		<hr color='<%=color %>' width="<%=i%>%"/>

<%
	}
%>
 --%>
</body>
</html>