<%@page import="com.neuedu.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.neuedu.util.StringUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>

<script src="js/user_query.js"></script>

<script src="js/laydate/laydate.js"></script>
 
</head>
<body>
<div align="center">
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

<%
	if(request.getAttribute("list")==null){
		response.sendRedirect("UserQueryServlet");
		return;
	}
%>

<%
	//从request属性范围中取出用户列表
	List<User> list = (List<User>)request.getAttribute("list");

	//从request属性范围中取出用户名，性别，职业,注册时间
	String username = (String)request.getAttribute("username");
	String gender = (String)request.getAttribute("gender");
	String job = (String)request.getAttribute("job");
	String regtime = (String)request.getAttribute("regtime");
	String begin = (String)request.getAttribute("begin");
	String end = (String)request.getAttribute("end");
%>


<form action="UserQueryServlet" method="post">

	用户名<input type="text" name="username" value="<%=username%>"/>
	
	性别
	<select name="gender">
		<option value="" <%= "".equals(gender) ? "selected" : "" %> >全部</option>
		<option value="男" <%= "男".equals(gender) ? "selected" : "" %> >男</option>
		<option value="女" <%= "女".equals(gender) ? "selected" : "" %> >女</option>
	</select>
	
	职业
	<select name="job">
		<option value="" <%= "".equals(job) ? "selected" : "" %> >全部</option>
		<option value="程序员" <%= "程序员".equals(job) ? "selected" : "" %> >程序员</option>
		<option value="美工" <%= "美工".equals(job) ? "selected" : "" %> >美工</option>
		<option value="项目经理" <%= "项目经理".equals(job) ? "selected" : "" %> >项目经理</option>
	</select>
	
	<input type="submit" value="查询"/>
	
	<br/>
	
	注册时间
	<select id="regtime" name="regtime" onchange="showTime()">
		<option value="全部时间" <%= "全部时间".equals(regtime) ? "selected" : "" %> >全部时间</option>
		<option value="指定时间" <%= "指定时间".equals(regtime) ? "selected" : "" %> >指定时间</option>
	</select>
	
	<span id="timerange" style="visibility:<%= "全部时间".equals(regtime) ? "hidden" : "visible" %>">
		从<input type="text" name="begin" value="<%=begin%>" class="laydate-icon" onfocus="laydate()" readonly="readonly" />
		到<input type="text" name="end" value="<%=end%>" class="laydate-icon" onfocus="laydate()" readonly="readonly"/>
	</span>
	
</form>

<form action="UserDeleteBatchServlet" method="post" onsubmit="return checkData()">

<table border="1">

	<tr><td>选择</td><td>用户头像</td><td>用户名</td><td>积分</td><td>性别</td><td>职业</td><td>注册时间</td><td>操作</td></tr> 
	

<%
	for(User user : list){
%>
		<tr>
			  <td><input type="checkbox" name="userids" value="<%=user.getUserid()%>" onclick="checkAll()"/></td>
			  <td><img src="image/photo/<%=user.getPhoto()%>" height="44" width="44"/></td>
			  <td><%=StringUtil.convertKeyword(user.getUsername(), username) %></td>
			  <td><%=user.getScore() %></td>
			  <td><%=user.getGender() %></td>
			  <td><%=user.getJob() %></td>
			  <td><%=StringUtil.convertDatetime(user.getRegtime()) %></td>
			  <td><a href="UserDeleteServlet?userid=<%=user.getUserid()%>" onclick="return confirm('是否确认删除用户【<%=user.getUsername()%>】')">删除</a></td>
		</tr>
<%
	}
	
%>


	<tr>
		<td>
			<input type="checkbox" id="chkAll" onclick="selectAll()"/>全选
			<br/>
			<input type="button" value="反选" onclick="selectReverse()"/>
		</td>
		<td colspan="7" align="center">
			<input type="submit" value="批量删除" />
		</td>
	</tr>

</table>
</form>
</div>
</body>
</html>