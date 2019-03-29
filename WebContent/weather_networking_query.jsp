<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>全国天气在线查询</title>
<link rel="stylesheet" href="css/weather.css" />
<link rel="icon" href="images/favicon.ico" />
</head>
<body>
	<header>
		<h1><font color="white">WEATHER</font></h1>
		<div id="weather_search">
			<span><input id="text" type="text" placeholder="请输入您要查询的城市" /></span>
			<span><input id="btn" type="button" value=" 查询天气" /></span>
		</div>
	</header>
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
	<font color="white">
		<section>
			<div id="today_container">
				<div>
					<img src="./images/weather_icon/1.png" alt="今日天气" />
				</div>
				<div>
					<div class="main_info">
						<span class="info">城市</span>|<span class="info">201X-XX-XX</span>|<span
							class="info">星期X</span>|<span class="info">---</span>
					</div>
					<div class="more_info">
						今日温度：<span class="info">-----</span>风力：<span class="info">-----</span>风向：<span
							class="info">-----</span>PM2.5：<span class="info">--</span>
					</div>
					<div class="more_info">
						紫外线强度：<span class="info">---</span>洗车指数：<span class="info">---</span>感冒指数：<span
							class="info">---</span>穿衣指数：<span class="info">---</span>
					</div>
				</div>
			</div>
			<div id="future_container">
				<div class="future_box">
					<img src="./images/weather_icon/1.png" alt="天气" /> <span
						class="future_info">201X-XX-XX</span> <span class="future_info">星期X</span>
					<span class="future_info">--</span><span class="future_info">12-16℃</span>
				</div>
				<div class="future_box">
					<img src="./images/weather_icon/3.png" alt="天气" /> <span
						class="future_info">201X-XX-XX</span> <span class="future_info">星期X</span>
					<span class="future_info">--</span><span class="future_info">12-16℃</span>
				</div>
				<div class="future_box">
					<img src="./images/weather_icon/2.png" alt="天气" /> <span
						class="future_info">201X-XX-XX</span> <span class="future_info">星期X</span>
					<span class="future_info">--</span><span class="future_info">12-16℃</span>
				</div>
				<div class="future_box">
					<img src="./images/weather_icon/4.png" alt="天气" /> <span
						class="future_info">201X-XX-XX</span> <span class="future_info">星期X</span>
					<span class="future_info">--</span><span class="future_info">12-16℃</span>
				</div>
				<div class="future_box">
					<img src="images/weather_icon/5.png" alt="天气" /> <span
						class="future_info">201X-XX-XX</span> <span class="future_info">星期X</span>
					<span class="future_info">-</span><span class="future_info">12-16℃</span>
				</div>
			</div>
		</section> <script src="js/weather.js"></script> 
		</font>
	<br>
	<br>
	<br>
		<div align="center">
			<input id="index" type="button" value=" 返回主页" 
			style="width: 70px;height: 30px;border: none;background: rgba(255,255,255,0.5);color: #333;outline: none;cursor: pointer;"
			onclick="window.location.href='index.jsp'"/>
		</div>
</body>
</html>