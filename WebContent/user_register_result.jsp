<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

  function showTime(){
	  
	  var time = document.getElementById("time");
	 
	  if(time.innerText>1){
		  time.innerText--;
	  }else{
		  location = "index.jsp";
	  }
	  
	  
  }
</script>
</head>

<body onload="setInterval('showTime()', 1000)">
             
<h2 align="center">恭喜，新用户注册成功</h2>
<h3 align="center"><font size="10" color="red"><span id="time">3</span></font>秒钟后跳转到主页，如果无法跳转，请点击<a href="index.jsp">这里</a></h3>

</body>
</html>