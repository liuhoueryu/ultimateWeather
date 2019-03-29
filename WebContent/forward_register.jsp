<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/popup.css">
	<link rel="icon" href="images/favicon.ico"/>
	<title>跳转中...</title>
</head>
<body>

	<div class="popup-container">
		<div class="img-flex"></div>
	</div>

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/fragment.js"></script>
<script type="text/javascript">
$(function() {
	var fragmentConfig = {
		container : '.img-flex',//显示容器
		line : 10,//多少行
		column : 16,//多少列
		width : 1920,//显示容器的宽度
		animeTime : 6000,//最长动画时间,图片的取值将在 animeTime*0.33 + animeTime*0.66之前取值 
		img : 'img/background.jpg'//图片路径
	};
	fragmentImg(fragmentConfig);
});
window.setTimeout("window.location='user_register.jsp'",6000); 
</script>
</body>
</html>