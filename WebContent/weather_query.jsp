<%@page import="com.neuedu.entity.User"%>
<%@page import="com.neuedu.entity.Weather"%>
<%@page import="java.util.List"%>
<%@page import="com.neuedu.util.StringUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>天气大全</title>
<link rel="icon" href="images/favicon.ico" />
<link rel="stylesheet" href="css/starlight.css">
<link rel="stylesheet" href="css/weather_query.css">
<link rel="stylesheet" href="css/index.css">
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

a{
 text-decoration: none;

}
tr{
  text-align: center;
  width: 100%;
}


</style>
<body
	style="background-image: url('img/bg_weather.jpg'); background-repeat: no-repeat;background-size: 100%;margin: 0">
	<%
		if (request.getAttribute("list") == null) {
			response.sendRedirect("WeatherQueryServlet");
			return;
		}
	%>
	<%
		List<Weather> list = (List<Weather>) request.getAttribute("list");
		String province = (String) request.getAttribute("province");
		String city = (String) request.getAttribute("city");
	%>
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
<div id="table" class="wall" align="center">
	<header id="table" style="height: 50px;background: rgba(100, 100, 100, 0.5);color:white;">
		<div id="user">
			<img src="image/photo/<%=user.getPhoto()%>" height="33" width="33"> 【<%=user.getUsername() %>】，您好！
			<span><input id="btn" type="button" value="注销" onclick="window.location.href='UserLogoutServlet'"/></span>
		</div>
	
		<div id="index" style="z-index: 5;">
			<span><input id="btt" type="button" value="在线查询" onclick="window.location.href='weather_networking_query.jsp'" style="z-index: 5;"/></span>
			<span><input id="btt" type="button" value="数据分析" onclick="window.location.href='echart_city.jsp'"/></span>
			<span><input id="btt" type="button" value="天气大全" onclick="window.location.href='WeatherQueryServlet'"/></span>
			<span><input id="bts" type="button" value="WEATHER" onclick="window.location.href='index.jsp'"/></span>
		</div>
	</header>	
		
			
			<br> <br> <br>
			<form action="WeatherQueryServlet" method="post">
				<input id="text" type="text" name="province" value="<%=province%>" placeholder="请输入您要查询的省份"/> 
				<input id="text" type="text" name="city" value="<%=city%>" placeholder="请输入您要查询的城市"/> 
				<input id="btv" type="submit" value="查询" /><br /> <br> <br>
				
				<table border="1" style="width:90%;">
					<tr>
						<td>省份</td>
						<td>城市</td>
						<td>日期</td>
						<td>天气</td>
						<td>温度</td>
						<td>降水量</td>
						<td>空气质量指数</td>
						<td>PM2.5</td>
						<td>PM10</td>
						<td>CO</td>
						<td>NO2</td>
						<td>O3</td>
						<td>SO2</td>
					</tr>
					<%
						for (Weather weather : list) {
					%>
					<tr>
						<td><%=StringUtil.convertKeyword(weather.getProvince(), province)%></td>
						<td><%=StringUtil.convertKeyword(weather.getCity(), city)%></td>
						<td><%=weather.getDate()%></td>
						<td><%=weather.getWeather()%></td>
						<td><%=weather.getTemperature()%></td>
						<td><%=weather.getRainfall()%></td>
						<td><%=weather.getAqi() %></td>
						<td><%=weather.getPm25() %></td>
						<td><%=weather.getPm10() %></td>
						<td><%=weather.getCo() %></td>
						<td><%=weather.getNo2() %></td>
						<td><%=weather.getO3() %></td>
						<td><%=weather.getSo2() %></td>
					</tr>
					<%
						}
					%>


				</table>
				<br> <br>
				<button id="btv" name="prepage" >上一页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="btv" name="index" >首 页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="btv" name="nextpage" >下一页</button>

			</form>

		</div>
		<div id="midground" class="wall"></div>
		<div id="foreground" class="wall"></div>
		<div id="top" class="wall"></div>

</body>
</html>