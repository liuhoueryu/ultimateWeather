<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>高级检索</title>
<link rel="stylesheet" href="css/index.css"/>
<link rel="stylesheet" href="css/weather_query.css"/>
<link rel="icon" href="images/favicon.ico"/>
</head>

<body style="margin: 0px;background-color: #FFE4C4">
<script type="text/javascript" src="laydate/laydate.js"></script>
<script>
//执行一个laydate实例
laydate.render({
  	elem: '#tes' //指定元素
});
laydate.render({
  	elem: '#teb' //指定元素
});
function showTime(){
	
	var regtime = document.getElementById("regtime");
	var timerange = document.getElementById("timerange");
	
	if(regtime.value=="全部时间"){
		timerange.style.visibility = "hidden";
	}else{
		timerange.style.visibility = "visible";
	}
	
}
</script>
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

<header style="height: 50px;background: rgba(100, 100, 100, 0.5);color:white">
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
<form action="CityChoose" style="text-align: center;margin-top: 210px;" method="post">
    	
    			<input id="tet" type="text" name="city" placeholder="请输入您要查询的城市"/>
    			
				<select id="regtime" name="regtime" onchange="showTime()">
					<option value="全部时间">全部时间</option>
					<option value="指定时间">指定时间</option>
				</select>
				<br>
    			<br>
				<span id="timerange" style="visibility: hidden;">
    			<input type="text" id="tes" name="start" placeholder="请输入开始日期" readonly="readonly" >
    			
				<br>
				<br>
				<input type="text" id="teb" name="end" placeholder="请输入结束日期" readonly="readonly">
				</span>
				<br>
				<br>
				<input id="btv" type="submit" value="查询" />
</form>

</body>
</html>