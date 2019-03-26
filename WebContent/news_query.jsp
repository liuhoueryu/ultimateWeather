<%@page import="com.neuedu.entity.News"%>
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
<div align="center">
<c:if test="${requestScope.newsPage==null }">
	<%
		response.sendRedirect("NewsQueryServlet");
	%>
</c:if>

<c:if test="${requestScope.newsPage!=null }">

<table border="1">

	<tr><td>新闻主题</td><td>新闻内容</td><td>新闻类型</td><td>发布用户</td><td>发布时间</td></tr>
	
	<c:set var="row" value="0"></c:set>
	
	<c:forEach items="${requestScope.newsPage.list }" var="news">
	
		<tr bgcolor="${row==0 ? '#d0d0d0' : '#ffffff'}">
			
				<td>${news.title }</td>
			  	<td>${news.content }</td>
			  	<td>${news.newsType.typename }</td>
			  	<td>${news.user.username }</td>
			  	<td>${news.pubtime }</td>
		</tr>
		
		<c:set var="row" value="${1 - row}"></c:set>
		
	</c:forEach>	
	
	<tr>
		<td colspan="5">
			分页大小【${requestScope.newsPage.pageSize}】
			总记录数【${requestScope.newsPage.totalCount}】
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			【${requestScope.newsPage.currentPage}】 / 【${requestScope.newsPage.totalPage}】
		</td>
	</tr>		
	<tr>
		<td colspan="5" align="center">
		
			<c:if test="${requestScope.newsPage.currentPage==1}">
				首页  上一页
			</c:if>
			<c:if test="${requestScope.newsPage.currentPage!=1}">
				<a href="NewsQueryServlet?currentPage=1"/>首页</a>
				<a href="NewsQueryServlet?currentPage=${requestScope.newsPage.currentPage-1}"/>上一页</a>
			</c:if>
			
			<c:if test="${requestScope.newsPage.currentPage==requestScope.newsPage.totalPage}">
			       下一页 尾页
			</c:if>
			<c:if test="${requestScope.newsPage.currentPage!=requestScope.newsPage.totalPage}">
				<a href="NewsQueryServlet?currentPage=${requestScope.newsPage.currentPage+1}"/>下一页</a>
				<a href="NewsQueryServlet?currentPage=${requestScope.newsPage.totalPage}"/>尾页</a>
			</c:if>
		</td>
	</tr>	
	<tr>
		<td colspan="5" align="center">
		
			<c:forEach var="i" begin="${requestScope.newsPage.begin}" end="${requestScope.newsPage.end}">
				<c:if test="${requestScope.newsPage.currentPage!=i}">
					<a href="NewsQueryServlet?currentPage=${i}"/>${i}</a>
				</c:if>
				<c:if test="${requestScope.newsPage.currentPage==i}">
					${i}
				</c:if>
			</c:forEach>
		</td>
	</tr>	
</table>

<br/>

<a href="index.jsp">返回主页</a>

</c:if>
</div>
</body>
</html>