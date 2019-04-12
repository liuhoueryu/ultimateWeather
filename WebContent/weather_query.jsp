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
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

html, body {
	width: 100%;
	height: 100%;
}

div {
	width: 100%;
	height: 100%;
}
</style>
<body
	style="background-image: url('img/bg_weather.jpg'); background-repeat: no-repeat; background-position: center;">
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
		if (session.getAttribute("user") == null) { //未登录
	%>

	<script>
		alert("对不起，您尚未登录，请先登录");
		location = "user_login.jsp";
	</script>

	<%
		return;
		}
	%>	
		<div id="table" class="wall" align="center">
			<a style="background: rgba(255,255,255,0.5)" href="index.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返回主页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<a style="background: rgba(255,255,255,0.5)" href="weather_networking_query.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在线天气查询&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<br> <br> <br>
			<form action="WeatherQueryServlet" method="post">
				<input id="text" type="text" name="province" value="<%=province%>" placeholder="请输入您要查询的省份"/> 
				<input id="text" type="text" name="city" value="<%=city%>" placeholder="请输入您要查询的城市"/> 
				<input id="btn" type="submit" value="查询" /><br /> <br> <br>
				
				<table border="1" style="background-color: white; text-align: center;">
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
				<button id="btn" name="prepage" >上一页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="btn" name="index" >首 页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="btn" name="nextpage" >下一页</button>

			</form>

		</div>
		<div id="midground" class="wall"></div>
		<div id="foreground" class="wall"></div>
		<div id="top" class="wall"></div>

</body>
</html>