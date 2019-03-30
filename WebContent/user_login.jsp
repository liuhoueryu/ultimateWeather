<%@page import="com.neuedu.util.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<title>登 录</title>

<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="icon" href="images/favicon.ico"/>
</head>
<body>
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
        window.location.href = 'user_login_forPhone.jsp';
      }
    })();
 </script>
<%
	String userinfo = CookieUtil.findCookie("userinfo", request);

	String username = "";
	String password = "";
	
	if(userinfo!=null){
		String[] userinfos = userinfo.split(":");
		username = userinfos[0];
		password = userinfos[1];
	}
	
%>
	<div class="wrap login_wrap">
		<div class="content">
			<div class="logo"></div>
			<div class="login_box">

				<div class="login_form">
					<div class="login_title">登录</div>
					<form action=UserLoginServlet method="post">

						<div class="form_text_ipt">
							<input name="username" type="text" value="<%=username%>" placeholder="用户名">
						</div>
						<div class="ececk_warning">
							<span>用户名</span>
						</div>
						<div class="form_text_ipt">
							<input name="password" type="password" value="<%=password%>" placeholder="密码">
						</div>
						<div class="ececk_warning">
							<span>密码不能为空</span>
						</div>
						<div class="form_check_ipt">
							<div class="left check_left">
								<label><input type="checkbox" name="autoLogin">记住密码</label>
							</div>
							<div class="right check_right">
								<a href="user_retrieve_password.jsp">忘记密码</a>
							</div>
						</div>
						<div class="form_btn">
							<button type="submit">登录</button>
						</div>
						<div class="form_reg_btn">
							<span>还没有帐号？</span><a href="forward_register.jsp">马上注册</a>
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
	<script type="text/javascript" src="js/jquery.min.js" ></script>
	<script type="text/javascript" src="js/common.js" ></script>
</body>
</html>