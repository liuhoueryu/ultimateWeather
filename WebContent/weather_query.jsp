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
<link rel="icon" href="images/favicon.ico"/>
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
<body style="background-image: url('img/background.jpg');background-repeat: no-repeat;background-position: center;">
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
	<div>
		<div align="center">
		<a href="index.jsp">返回主页</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="weather_networking_query.jsp">在线天气查询</a>
			<br> <br> <br>
			<form action="WeatherQueryServlet" method="post">
				请输入省份<input type="text" name="province" value="<%=province%>" />
				请输入城市<input type="text" name="city" value="<%=city%>" /> <input
					type="submit" value="查询" /><br /> <br> <br>
				<table border="1">
					<tr>
						<td>省份</td>
						<td>城市</td>
						<td>天气</td>
						<td>温度</td>
						<td>降水量</td>
						<td>日期</td>
					</tr>
					<%
						for (Weather weather : list) {
					%>
					<tr>
						<td><%=StringUtil.convertKeyword(weather.getProvince(), province)%></td>
						<td><%=StringUtil.convertKeyword(weather.getCity(), city)%></td>
						<td><%=weather.getWeather()%></td>
						<td><%=weather.getTemperature()%></td>
						<td><%=weather.getRainfall()%></td>
						<td><%=weather.getDate()%></td>
					</tr>
					<%
						}
					%>


				</table>
				<br> <br>
				<button name="prepage">上一页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="index">首 页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="nextpage">下一页</button>
			</form>
		</div>
	</div>
</body>
</html>