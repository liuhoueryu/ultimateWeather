<%@page import="com.neuedu.entity.User"%>
<%@page import="com.neuedu.entity.CitySet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高级查询</title>

<!-- 引入 echarts.js -->
<script type="text/javascript" src="echarts/echarts.min.js"></script>
<!-- 引入jquery.js -->
<script type="text/javascript" src="echarts/jquery.min.js"></script>
<link rel="stylesheet" href="css/index.css"/>
<link rel="icon" href="images/favicon.ico"/>
</head>
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
<body style="margin: 0px;background-color: #FFE4C4">
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <%
    	CitySet c=CitySet.getInstance();
	%>
	<br>
	<br>
    <div id="main" style="width: 1200px;height:400px;"></div>
    
    <script type="text/javascript">
        
        var myChart = echarts.init(document.getElementById('main'));
         // 显示标题，图例和空的坐标轴
         myChart.setOption({
             title: {
                 text: '<%=c.getCity()%>天气分析'
             },
             tooltip: {},
             legend: {
                 data:['PM10','PM2.5','温度','AQI']
             },
             xAxis: {
                 data: []
             },
             yAxis: {},
             series: [{
                 name: 'PM10',
                 type: 'bar',
                 data: []
             },
             {
            	 name: 'PM2.5',
                 type: 'bar',
                 data: []
             },
             {
            	 name: 'AQI',
                 type: 'line',
                 data: []
             },
             {
            	 name: '温度',
                 type: 'line',
                 data: []
             }
             ]
         });
         
         myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
         
         var names=[];    //类别数组（实际用来盛放X轴坐标值）
         var nums=[];    //销量数组（实际用来盛放Y坐标值）
         var pm2=[];
         var aqi=[];
         var temp=[];
         
         $.ajax({
         type : "post",
         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "Bar",    //请求发送到TestServlet处
         data : {},
         dataType : "json",        //返回数据形式为json
         success : function(result) {
             //请求成功时执行该函数内容，result即为服务器返回的json对象
             if (result) {
                    for(var i=0;i<result.length;i++){       
                       names.push(result[i].date);    //挨个取出类别并填入类别数组
                     }
                    for(var i=0;i<result.length;i++){       
                        nums.push(result[i].pm);    //挨个取出销量并填入销量数组
                      }
                    for(var i=0;i<result.length;i++){       
                        aqi.push(result[i].aqi);    //挨个取出销量并填入销量数组
                      }
                    for(var i=0;i<result.length;i++){       
                        temp.push(result[i].temp);    //挨个取出销量并填入销量数组
                      }
                    for(var i=0;i<result.length;i++){       
                        pm2.push(result[i].pm2);    //挨个取出销量并填入销量数组
                      }
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                    	xAxis: {
                            data: names
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: 'PM10',
                            data: nums
                        },
                        {
                            // 根据名字对应到相应的系列
                            name: 'AQI',
                            data: aqi
                        },
                        {
                        	name:'温度',
                        	data:temp
                        },
                        {
                        	name:'PM2.5',
                        	data:pm2
                        }
                        ]
                    });
                    
             }
         
        },
         error : function(errorMsg) {
             //请求失败时执行该函数
         alert("图表请求数据失败!");
         myChart.hideLoading();
         }
    })

         
    </script>
    <form action="Bar" style="text-align: center;"> 
    			<button name="prepage" style="width: 70px;height: 30px;border: none;background: rgba(100,100,100,0.3);color: #FFFFFF;outline: none;cursor: pointer;">上一页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="index"   style="width: 70px;height: 30px;border: none;background: rgba(100,100,100,0.3);color: #FFFFFF;outline: none;cursor: pointer;">首 页</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button name="nextpage" style="width: 70px;height: 30px;border: none;background: rgba(100,100,100,0.3);color: #FFFFFF;outline: none;cursor: pointer;">下一页</button>
    </form>
    <br>
    <div align="center">
    	<input type="button" value="重新选择" onclick="window.location.href='chooseCity.jsp'" style="width: 300px;height: 50px;border: none;background: rgba(255,69,0,1);color: #FFFFFF;outline: none;cursor: pointer;font-size: medium;"/>
    </div>
</body>
</html>
