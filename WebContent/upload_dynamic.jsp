<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function addMore(){
		
		//创建文件选择框
		var txtFile = document.createElement("input");
		txtFile.type = "file";
		txtFile.name = "file";
		
		//创建删除按钮
		var btnDelete = document.createElement("input");
		btnDelete.type = "button";
		btnDelete.value = "删除";
		
		//创建换行符
		var br =  document.createElement("br");
		
		var divAddMore = document.getElementById("divAddMore");
		
		//添加节点
		divAddMore.appendChild(txtFile);
		divAddMore.appendChild(btnDelete);
		divAddMore.appendChild(br);
		
		//给删除按钮添加事件处理
		btnDelete.onclick = function(){
			divAddMore.removeChild(txtFile);
			divAddMore.removeChild(btnDelete);
			divAddMore.removeChild(br);
		}
	}
</script>
</head>
<body>

<form action="TestUploadMultiServlet" method="post" enctype="multipart/form-data">
	<input type="file" name="file"/>
	<input type="button" value="更多" onclick="addMore()"/>
	<div id="divAddMore"></div>
	<input type="submit" value="上传"/>
</form>

</body>
</html>