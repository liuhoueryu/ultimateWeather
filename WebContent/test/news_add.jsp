<%@page import="com.neuedu.entity.NewsType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<%
	//登录检查
	if(session.getAttribute("user")==null){   //未登录	
		
		String uri = request.getRequestURI();
		
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		
		session.setAttribute("prevURL", uri);

%> 
	           对不起，您尚未登录，请先<a href="user_login.jsp">登录</a>
<%		
		return;
	}
%>

<form action="NewsAddServlet" method="post">

	<table align="center">
	
		<tr><td colspan="2" align="center"><font color="red"><b>添加新闻</b></font></td></tr>
		
		<tr>     	
		   <td>新闻类型</td>
		   <td>
		   	  <select name="typeid">
		   	  
		   	  <c:forEach items="${applicationScope.list}" var="newsType">
		   	  		    <option value="${newsType.typeid }">${newsType.typename }</option>
		   	  </c:forEach>
		   	  </select>
		   </td>
		 </tr>	
		 	
		<tr>
			<td>新闻主题</td>
			<td>
				<input type="text" name="title"/>
			</td>  
		</tr>
		
		<tr>
			<td>新闻内容</td>
			<td>
				<textarea rows="3" cols="30" name="content"></textarea>
			</td>  
		</tr>
						  	 	
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="添加新闻" />
				<input type="reset" value="重新填写" />
			</td>
		</tr>
		
	</table>	

</form>

</body>
</html>