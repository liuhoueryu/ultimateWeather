<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>

	var xhr = null;
	
	function testAjax(){
		
		//获取输入框的内容
		var username = document.getElementById("username").value;
		
		//声明并创建XMLHttpRequest对象
		xhr = new XMLHttpRequest();	
		
		//调用open()方法建立与服务器的连接
		xhr.open("get", "TestAjaxServlet?username=" + encodeURI(username), true);
		
		//调用send()方法发送请求	
		xhr.send(null);
		
		//绑定回调函数
		xhr.onreadystatechange = testAjaxResult;
		
	}
	
	//回调函数
	function testAjaxResult(){
		
		//alert("readyState=" + xhr.readyState);
		
		if(xhr.readyState==4){
			//alert("status = " + xhr.status);
			
			if(xhr.status==200){
				
				//局部刷新
				alert(xhr.responseText);
			}
		}
	}
</script>
</head>
<body>

用户名<input type="text" id="username"/>
<button onclick="testAjax()">Ajax访问Servlet</button>

</body>
</html>