<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	你好，jsp
	
	<br/>
	
   <%
	int a = 5;
	out.println("a=" + a);
   %>
   
   <br/>
   
   a的平方=<%=a*a %> 
   
    <br/>
    
   <%!
	int a = 10;

	int add(int a, int b){
		return a + b;
	}
    %>

   10 + 20 = <%=add(10, 20)%>
   
   <br/>
   
   	<!-- 这是HTML注释 -->
   	<%-- 这是JSP注释 --%>
</body>
</html>