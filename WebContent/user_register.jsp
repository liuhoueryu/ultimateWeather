<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="icon" href="images/favicon.ico"/>
<script>
	var xhr = null;

	//检查用户名---字段级
	function checkUsername() {

		var username = document.getElementById("username");
		var usernameResult = document.getElementById("usernameResult");

		if (username.value.length<2 || username.value.length>10) {
			usernameResult.innerHTML = "<img src='image/no.gif'/><font color='red'><b>用户名的长度必须在2-10个字符之间</b></font>";
			return false;
		} else {

			//return true;

			//用户名是否存在
			usernameResult.innerHTML = "<img src='image/loading.gif'/><font color='blue'><b>正在检查用户名是否可用...</b></font>";

			//创建xhr对象
			xhr = new XMLHttpRequest();

			//建立连接
			xhr.open("get", "CheckUsernameServlet?username="
					+ encodeURI(username.value) + "&time="
					+ new Date().getTime(), true);

			//发出请求
			xhr.send(null);

			//设置回调函数
			xhr.onreadystatechange = checkUsernameResult;

		}

	}

	function checkUsernameResult() {

		if (xhr.readyState == 4) {

			if (xhr.status == 200) {

				var usernameResult = document.getElementById("usernameResult");

				var responseText = xhr.responseText;

				if (responseText == "no") {
					usernameResult.innerHTML = "<img src='image/no.gif'/><font color='red'><b>对不起，用户名已存在</b></font>";
				} else {
					usernameResult.innerHTML = "<img src='image/yes.gif'/><font color='green'><b>恭喜，用户名可用</b></font>";
				}
			}
		}
	}

	//检查密码---字段级
	function checkPassword() {

		var password = document.getElementById("password");
		var passwordResult = document.getElementById("passwordResult");

		//if(password.value.length==0){
		if (password.value == "") {
			passwordResult.innerHTML = "<img src='image/no.gif'/><font color='red'><b>密码不能为空</b></font>";
			return false;
		} else {
			passwordResult.innerHTML = "";
			return true;
		}

	}

	//检查确认密码---字段级
	function checkPassword2() {

		var password = document.getElementById("password");
		var password2 = document.getElementById("password2");
		var passwordResult2 = document.getElementById("passwordResult2");

		if (password.value != password2.value) {
			passwordResult2.innerHTML = "<img src='image/no.gif'/><font color='red'><b>两次密码必须一致</b></font>";
			return false;
		} else {
			passwordResult2.innerHTML = "";
			return true;
		}

	}

	//表单级校验
	function checkData() {

		var photo = document.getElementById("photo");
		var file = document.getElementById("file");

		if (photo.checked) {
			if (file.value == "") {
				alert("必须要选择自定义头像");
				return false;
			}
		}

		return checkUsername() && checkPassword() && checkPassword2();

	}

	//显示或隐藏文件选择框
	function changePhoto() {

		var photo = document.getElementById("photo");
		var file = document.getElementById("file");

		if (photo.checked) {
			file.style.visibility = "visible";
		} else {
			file.style.visibility = "hidden";
		}
	}
</script>
<style>
* {
	padding: 5px;
}

html, body {
	height: 97%;
}
div {
	width: 100%;
	height: 100%;
}

</style>

</head>

<body style="background-image: url('img/background.jpg');background-repeat: no-repeat;background-position: center;">
<script type="text/javascript">
    // borwserRedirect
    (function browserRedirect(){
      var sUserAgent = navigator.userAgent.toLowerCase();
      var bIsIpad = sUserAgent.match(/ipad/i) == 'ipad';
      var bIsIphone = sUserAgent.match(/iphone os/i) == 'iphone os';
      var bIsMidp = sUserAgent.match(/midp/i) == 'midp';
      var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == 'rv:1.2.3.4';
      var bIsUc = sUserAgent.match(/ucweb/i) == 'web';
      var bIsCE = sUserAgent.match(/windows ce/i) == 'windows ce';
      var bIsWM = sUserAgent.match(/windows mobile/i) == 'windows mobile';
      var bIsAndroid = sUserAgent.match(/android/i) == 'android';
 
      if(bIsIpad || bIsIphone || bIsMidp || bIsUc7 || bIsUc || bIsCE || bIsWM || bIsAndroid ){
        window.location.href = 'user_register_forPhone.jsp';
      }
    })();
 </script>
 	<div>
			<br>
			<h2 align="center">新用户注册</h2>
			<br>
			<form action="UserRegisterServlet" method="post"
				enctype="multipart/form-data" onsubmit="return checkData()">

				<table align="center">

					<tr>
						<td>用户名</td>
						<td width="500px"><input type="text" name="username"
							id="username" onblur="checkUsername()" placeholder="请输入2-10个字符" /> <span
							id="usernameResult"></span></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" name="password" id="password"
							onblur="checkPassword()" /> <span id="passwordResult"></span></td>
					</tr>

					<tr>
						<td>确认密码</td>
						<td><input type="password" id="password2"
							onblur="checkPassword2()" /> <span id="passwordResult2"></span></td>
					</tr>
					
					<tr>
						<td>手机号码</td>
						<td width="500px"><input type="text" name="phonenumber"
							id="phonenumber" autocomplete="off" placeholder="填写手机号便于密码找回"/> <span
							id="phonenumber"></span></td>
					</tr>

					<tr>
						<td>性别</td>
						<td><input type="radio" name="gender" value="男" checked />男
							<input type="radio" name="gender" value="女" />女</td>
					</tr>

					<tr>
						<td>职业</td>
						<td><select name="job">
								<option value="程序员" selected>程序员</option>
								<option value="美工">美工</option>
								<option value="项目经理">项目经理</option>
								<option value="运动员">运动员</option>
						</select></td>
					</tr>

					<tr>
						<td>兴趣爱好</td>
						<td><input type="checkbox" name="interest" value="唱歌" />唱歌 <input
							type="checkbox" name="interest" value="跳舞" />跳舞 <input
							type="checkbox" name="interest" value="跑步" />跑步 <input
							type="checkbox" name="interest" value="游泳" />游泳</td>
					</tr>

					<tr>
						<td>头像</td>
						<td><input type="radio" name="photo" value="1.gif"
							onclick="changePhoto()" checked /> <img src="image/photo/1.gif" />
							<input type="radio" name="photo" value="2.gif"
							onclick="changePhoto()" /> <img src="image/photo/2.gif" /> <input
							type="radio" name="photo" value="3.gif" onclick="changePhoto()" />
							<img src="image/photo/3.gif" /> <input type="radio" name="photo"
							value="4.gif" onclick="changePhoto()" /> <img
							src="image/photo/4.gif" /> <input type="radio" name="photo"
							value="5.gif" onclick="changePhoto()" /> <img
							src="image/photo/5.gif" /> <br> <input type="radio"
							name="photo" id="photo" value="0.gif" onclick="changePhoto()" />
							自定义头像上传 <input type="file" name="file" id="file"
							style="visibility: hidden" /></td>
					</tr>

					<tr>
						<td>验证码</td>
						<td><input type="text" name="valCode" /> <img
							src="ValCodeServlet" id="imgValCode"
							onclick="this.src=this.src + '?'" /> <input type="button"
							value="看不清，换一张"
							onclick="document.getElementById('imgValCode').src=document.getElementById('imgValCode').src + '?'" />
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="立即注册" /> <input type="reset" value="重新填写" /></td>
					</tr>

				</table>

			</form>
	</div>
</body>

</html>
