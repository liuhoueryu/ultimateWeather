<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="icon" href="images/favicon.ico"/>
<link rel="stylesheet" href="css/weatherbox.css"/>
<link rel="stylesheet" href="css/weatherbox1.css"/>
<link rel="stylesheet" href="css/weatherbox2.css"/>
<link rel="stylesheet" href="css/index.css"/>
</head>
<body style="background-image: url('img/bg_index.jpg');background-repeat: no-repeat;background-size: 100%;margin: 0">

<%
	//登录检查
	if(session.getAttribute("user")==null){   //未登录
%>
		
		<script>
			alert("对不起，您尚未登录，请先登录");
			location = "user_login.jsp";
		</script>	
	 
<%		
		return;
	}
%>

<%
	//从session属性范围中取出用户名，积分,头像
	/* String username = ( String)session.getAttribute("username");
	int score = (Integer)session.getAttribute("score");
	String photo = (String)session.getAttribute("photo"); */
	
	//从session属性范围中取出user对象
	User user = (User)session.getAttribute("user");
	
	//从application属性范围中取出在线人数
%>

<header style="height: 50px;background: rgba(100, 100, 100, 0.6);color:white">
	<div id="user">
		<img src="image/photo/<%=user.getPhoto()%>" height="33" width="33"> 【<%=user.getUsername() %>】，您好！
		<span><input id="btn" type="button" value="注销" onclick="window.location.href='UserLogoutServlet'"/></span>
	</div>
	
	<div id="index">
		<span><input id="btt" type="button" value="在线查询" onclick="window.location.href='weather_networking_query.jsp'"/></span>
		<span><input id="btt" type="button" value="数据分析" onclick="window.location.href='echart_city.jsp'"/></span>
		<span><input id="btt" type="button" value="天气大全" onclick="window.location.href='WeatherQueryServlet'"/></span>
		<span><input id="bts" type="button" value="WEATHER" onclick="window.location.href='index.jsp'"/></span>
	</div>
</header>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="box1" title="天气大全" onclick="javascript:location='WeatherQueryServlet'" style="cursor:pointer;float:left;margin-left: 200px">
	<ul class="minbox1">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<ol class="maxbox1">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ol>
</div>
<div class="box2" title="在线查询" onclick="javascript:location='weather_networking_query.jsp'" style="cursor:pointer;float:right;margin-right: 200px">
	<ul class="minbox2">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<ol class="maxbox2">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ol>
</div>
<div class="box" title="数据分析" onclick="javascript:location='echart_city.jsp'" style="cursor:pointer">
	<ul class="minbox">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<ol class="maxbox">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ol>
</div>
</body>
</html>