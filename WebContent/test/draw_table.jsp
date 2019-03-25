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
	String srow = request.getParameter("row");
	String scol = request.getParameter("col");

	//默认值处理
	if(srow==null){
		srow="10";
	}
	
	if(scol==null){
		scol="10";
	}
	
	//数据转型
	int row = Integer.parseInt(srow); 
	int col = Integer.parseInt(scol);
%>

<form action="draw_table.jsp" method="post">
	<input type="text" name="row" value="<%=row%>"/>行
	<input type="text" name="col" value="<%=col%>"/>列
	<input type="submit" value="绘制"/>
</form>


<table border="1">

<%
	int data = 1;
	for(int i=1; i<=row; i++){
		out.println("<tr>");
		for(int j=1; j<=col; j++){
			out.println("<td>" + (data++) + "</td>");
		}
		out.println("</tr>");
	}
%>

</table>

</body>
</html>