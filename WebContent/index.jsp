<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="icon" href="images/favicon.ico"/>
<link rel="stylesheet" href="css/weatherbox.css"/>
<link rel="stylesheet" href="css/weatherbox1.css"/>
<link rel="stylesheet" href="css/weatherbox2.css"/>
<link rel="stylesheet" href="css/index.css"/>
</head>
<body style="height: 100%;background-image: url('img/bg_index.jpg');background-repeat: no-repeat;background-size: 100%;margin: 0">

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

<div id="container" style="height: 80%;top: 50px;"></div>

<script type="text/javascript" src="echarts/echarts.min.js"></script>
<script type="text/javascript" src="echarts/jquery.min.js"></script>
       <script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
option = {
	backgroundColor:'rgba(255,255,255, 0.5)',
    title: {
        text: '当地实时数据',
        subtext: '仅供参考'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['PM2.5', '臭氧含量']
    },
    toolbox: {
        show: true,
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        }
    },
    dataZoom: {
        show: false,
        start: 0,
        end: 100
    },
    xAxis: [
        {
            type: 'category',
            boundaryGap: true,
            data: (function (){
                var now = new Date();
                var res = [];
                var len = 10;
                while (len--) {
                    res.unshift(now.toLocaleTimeString());
                    now = new Date(now - 2000);
                }
                return res;
            })()
        },
        {
            type: 'category',
            boundaryGap: true,
            data: (function (){
                var res = [];
                var len = 10;
                while (len--) {
                    res.push(len + 1);
                }
                return res;
            })()
        }
    ],
    yAxis: [
        {
            type: 'value',
            scale: true,
            name: '指数',
            max: 20,
            min: 0,
            boundaryGap: [0.2, 0.2]
        },
        {
            type: 'value',
            scale: true,
            name: 'index',
            max: 1200,
            min: 0,
            boundaryGap: [0.2, 0.2]
        }
    ],
    series: [
        {
            name:'臭氧含量',
            type:'bar',
            xAxisIndex: 1,
            yAxisIndex: 1,
            data:(function (){
                var res = [];
                var len = 10;
                while (len--) {
                    res.push(Math.round(Math.random() * 1000));
                }
                return res;
            })()
        },
        {
            name:'PM2.5',
            type: 'line',
            data:(function (){
                var res = [];
                var len = 0;
                while (len < 10) {
                    res.push((Math.random()*10 + 5).toFixed(1) - 0);
                    len++;
                }
                return res;
            })()
        }
    ]
};
app.count = 11;
setInterval(function (){
    axisData = (new Date()).toLocaleTimeString();

    var data0 = option.series[0].data;
    var data1 = option.series[1].data;
    data0.shift();
    data0.push(Math.round(Math.random() * 1000));
    data1.shift();
    data1.push((Math.random() * 10 + 5).toFixed(1) - 0);

    option.xAxis[0].data.shift();
    option.xAxis[0].data.push(axisData);
    option.xAxis[1].data.shift();
    option.xAxis[1].data.push(app.count++);

    myChart.setOption(option);
}, 2100);
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
</script>
</body>
</html>