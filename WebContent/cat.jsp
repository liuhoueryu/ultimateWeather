<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/cat.css"/>
</head>
<body>
<div class="cat">
<div class="head_content">
	<div class="ear_left"></div>
	<div class="ear_right"></div>
	<div class="head"></div>
	<div class="brow_left"></div>
	<div class="brow_right"></div>
	<div class="eye_left">
		<div id="pL" class="pupil"></div>
	</div>
	<div class="eye_right">
		<div id="pR" class="pupil"></div>
	</div>
	<div class="beard_left1"></div>
	<div class="beard_left2"></div>
	<div class="beard_left3"></div>
	<div class="beard_right1"></div>
	<div class="beard_right2"></div>
	<div class="beard_right3"></div>
	<div class="nose"></div>
	<div class="mouse">
		<div class="mouse_top"></div>
		<div class="mouse_left"></div>
		<div class="mouse_right"></div>
		<div class="mouse_bottom"></div>
		<div class="mouse_bottom_show"></div>
		<div class="miao">喵</div>
	</div>
</div>
<div class="body_content">
	<div class="body"></div>
	<div class="arm_left"></div>
	<div class="arm_right"></div>
	<div class="leg_left"></div>
	<div class="leg_right"></div>
	<div class="tail">
		<div class="tail1"></div>
		<div class="tail2"></div>
		<div class="tail3"></div>
	</div>
</div>
</div>


<script>
//获取cat所在div的位置
var catx=document.getElementsByClassName("cat")[0].offsetLeft;
var pL=document.getElementById("pL");
var pR=document.getElementById("pR");
var r=17;

document.onmousemove=function(ev) {
	var e = ev || event;
	var m=[];
	m.x=e.clientX-catx;  //鼠标坐标能落在左边等位置
	m.y=e.clientY-170;   //head_content、head、eye离上方的距离，使鼠标能落在上方区域
	var s=[];
	s.x=pL.offsetLeft;
	s.y=pL.offsetTop;
	var mosx = Math.abs(m.x - s.x);
	var mosy = Math.abs(m.y - s.y);
	var angle = Math.atan(mosy / mosx);
	var cx = 0, cy = 0;
	if (m.x >= s.x && m.y <= s.y) {
		cx = Math.cos(angle) * r;
		cy = Math.sin(angle) * -r;
//            console.log("右下");
	}
	if (m.x < s.x && m.y < s.y) {
		cx = Math.cos(angle) * -r;
		cy = Math.sin(angle) * -r;
//            console.log("左下");
	}
	if (m.x < s.x && m.y > s.y) {
		cx = Math.cos(angle) * -r;
		cy = Math.sin(angle) * r;
//            console.log("左上");
	}
	if (m.x > s.x && m.y > s.y) {
		cx = Math.cos(angle) * r;
		cy = Math.sin(angle) * r;
//            console.log("右上");
	}

	if((m.x+catx)>=catx && (m.x+catx)<=(catx+345) && (m.y+170)>=100 && (m.y+170)<=360){
		pL.style.top =15+'px';
		pR.style.top =15+'px';
		pL.style.left =18+'px';
		pR.style.left =18+'px';
	}else{
		pL.style.top = 15+ cy + 'px';
		pR.style.top = 15+ cy + 'px';
		pL.style.left =17+cx + 'px';
		pR.style.left =17+cx + 'px';
	}
}
</script>
</body>
</html>