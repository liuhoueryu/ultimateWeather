<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>找回密码</title>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/font-awesome.min.css" />
</head>
<body>
	<div class="wrap login_wrap">
		<div class="content">

			<div class="logo"></div>

			<div class="login_box">

				<div class="login_form">
					<div class="login_title">更改密码</div>
					<form action="UserRetrievePasswordServlet" method="post">
						<div class="form_text_ipt">
							<input name="username" type="text" placeholder="用户名">
						</div>
						<div class="ececk_warning">
							<span>手机号/邮箱不能为空</span>
						</div>
						<div class="form_text_ipt">
							<input name="phonenumber" type="text" placeholder="手机号/邮箱">
						</div>
						<div class="ececk_warning">
							<span>手机号/邮箱不能为空</span>
						</div>
						<div class="form_text_ipt">
							<input name="password" type="password" placeholder="密码">
						</div>
						<div class="ececk_warning">
							<span>密码不能为空</span>
						</div>
						<div class="form_text_ipt">
							<input name="repassword" type="password" placeholder="重复密码">
						</div>
						<div class="ececk_warning">
							<span>密码不能为空</span>
						</div>

						<div class="form_btn">
							<button type="submit">提交</button>
						</div>
						<div class="form_reg_btn">
							<span>已有帐号？</span><a href="user_login.jsp">马上登录</a>
						</div>
					</form>
					<div class="other_login">
						<div class="left other_left">
							<span>其它登录方式</span>
						</div>
						<div class="right other_right">
							<a href="https://mail.qq.com/cgi-bin/loginpage"><i
								class="fa fa-qq fa-2x"></i></a> <a href="https://wx.qq.com/"><i
								class="fa fa-weixin fa-2x"></i></a> <a href="https://weibo.com"><i
								class="fa fa-weibo fa-2x"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>