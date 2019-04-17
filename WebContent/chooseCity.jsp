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
<script type="text/javascript" src="echarts/echarts.min.js"></script>
<!-- 引入jquery.js -->
<script type="text/javascript" src="echarts/jquery.min.js"></script>
<script type="text/javascript" src="echarts/china.js"></script>

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
		tes.value="2018-01-01";
		teb.value="2019-05-01";
	}else{
		timerange.style.visibility = "visible";
		tes.value="";
		teb.value="";
	}
	
}
function showMySelect(){
	
	var map = document.getElementById("map");
	var tab = document.getElementById("tab");
	
	
		main.style.display="none";
		form.style.display="";

	
}
function showMap(){
	
	var map = document.getElementById("map");
	var tab = document.getElementById("tab");

	if(map.checked){
		form.style.display="none";
		main.style.display="";
		map.checked=false;
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
		<span><input id="bts" type="button" value="WEATHER" onclick="window.location.reload()" /></span>
	</div>
</header>
<div id="main" style="width: 1200px;height:690px;background-color: #FFE4C4;" ondblclick="showMySelect()">
</div>
<script type="text/javascript">
function randomData() {
    return Math.round(Math.random()*500);
}
var mydata = [{
    name: '北京',
    value: randomData()
},
{
    name: '天津',
    value: randomData()
},
{
    name: '上海',
    value: randomData()
},
{
    name: '重庆',
    value: randomData()
},
{
    name: '河北',
    value: randomData()
},
{
    name: '河南',
    value: randomData()
},
{
    name: '云南',
    value: randomData()
},
{
    name: '辽宁',
    value: randomData()
},
{
    name: '黑龙江',
    value: randomData()
},
{
    name: '湖南',
    value: randomData()
},
{
    name: '安徽',
    value: randomData()
},
{
    name: '山东',
    value: randomData()
},
{
    name: '新疆',
    value: randomData()
},
{
    name: '江苏',
    value: randomData()
},
{
    name: '浙江',
    value: randomData()
},
{
    name: '江西',
    value: randomData()
},
{
    name: '湖北',
    value: randomData()
},
{
    name: '广西',
    value: randomData()
},
{
    name: '甘肃',
    value: randomData()
},
{
    name: '山西',
    value: randomData()
},
{
    name: '内蒙古',
    value: randomData()
},
{
    name: '陕西',
    value: randomData()
},
{
    name: '吉林',
    value: randomData()
},
{
    name: '福建',
    value: randomData()
},
{
    name: '贵州',
    value: randomData()
},
{
    name: '广东',
    value: randomData()
},
{
    name: '青海',
    value: randomData()
},
{
    name: '西藏',
    value: randomData()
},
{
    name: '四川',
    value: randomData()
},
{
    name: '宁夏',
    value: randomData()
},
{
    name: '海南',
    value: randomData()
},
{
    name: '台湾',
    value: randomData()
},
{
    name: '香港',
    value: randomData()
},
{
    name: '澳门',
    value: randomData()
}
];
var optionMap = {  
        backgroundColor: '#FFE4C4',  
        title: {  
            text: '全国天气实时数据',  
            subtext: '刷新即可获取',  
            x:'center'  
        },  
        tooltip : {  
            trigger: 'item'  
        },  
        
        //侧小导航图标
        visualMap: {  
            show : true,  
            x: 'right', 
            top: 'bottom',
            y: 'center',  
            splitList: [   
                {start: 400, end: 500},  
                {start: 300, end: 400},{start: 200, end: 300},  
                {start: 100, end: 200},{start: 0, end: 100},  
            ],  
            color: ['#5475f5', '#9feaa5', '#85daef','#74e2ca', '#e6ac53', '#9fb5ea']  
        },  
        
        //配置属性
        series: [{  
            name: '数据',  
            type: 'map',  
            mapType: 'china', 
            x: 'center', 
            roam: true,  
            label: {  
                normal: {  
                    show: true  //省份名称  
                },  
                emphasis: {  
                    show: false  
                }  
            },  
            data:mydata  //数据
        }]  
    };  
//初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

//使用制定的配置项和数据显示图表
myChart.setOption(optionMap);

</script>
<br>
<br>
<form id="form" action="CityChoose" style="text-align: center;margin-top: 210px;display: none;" method="post">
    	
    			<input id="tet" type="text" name="city" placeholder="请输入您要查询的城市"/>
    			
				<select id="regtime" name="regtime" onchange="showTime()">
					<option value="全部时间">全部时间</option>
					<option value="指定时间">指定时间</option>
				</select>
				<input id="map" type="radio" onclick="showMap()">实时数据
				<br>
    			<br>
				<span id="timerange" style="visibility: hidden;">
    			<input type="text" id="tes" name="start" value="2018-01-01" placeholder="请输入开始日期" readonly="readonly" >
    			
				<br>
				<br>
				<input type="text" id="teb" name="end" value="2019-05-01" placeholder="请输入结束日期" readonly="readonly">
				</span>
				<br>
				<br>
				<input id="btv" type="submit" value="查询" />
</form>

</body>
</html>