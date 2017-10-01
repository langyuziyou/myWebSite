<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="<%=basePath %>layui/css/layui.css" />
		<link rel="stylesheet" href="<%=basePath %>css/sys/mian.css" />
	</head>
	<body>
		<div class="layui-layout layui-layout-admin">
			<header class="layui-header header">
				<div class="layui-main">
				    <a class="logo" href="/">
				      <img src="" alt="bps">
				      <span>伟仕进出口报关系统</span>
				    </a>
				    <!--<div class="user">
				    	<span>毛娟</span>
				    	<button class="layui-btn layui-btn-small">修改密码</button>
				    	<button class="layui-btn layui-btn-small">退出</button>
				    </div>-->
				</div>
			</header>
			
			<fieldset class="layui-elem-field loginMain">
					<legend>登陆</legend>
			  	<div class="layui-field-box">
					<form class="layui-form" id="login-form" action="<%=basePath %>login/login" method="post">
						<div class="layui-form-item">
						   	<input name="username" lay-verify="" autocomplete="off" placeholder="账号" class="layui-input" type="text" onChange="initLoginWay();">
						</div>
						<div class="layui-form-item">
						    <input name="password" lay-verify="pass" placeholder="密码" autocomplete="off" class="layui-input" type="password" onChange="initLoginWay();">
						</div>
						<div class="layui-form-item">
						    <button class="layui-btn"  onClick="checkCodePaper();" >登陆</button>
						</div>
						    <input type="checkbox" name="remember" value="1" id="remember"/>
	                        <input type="hidden" id="setCookie" value="0" name="setCookie"/>
	                        <input type="hidden" id="cookieLogin" value="0" name="cookieLogin"/>
					</form>
				</div>
			</fieldset>
		<script src="<%=basePath %>js/jquery-1.9.1.js" type="text/javascript"></script>
<!--		<script src="<%=basePath %>layui/layui.js" charset="utf-8"></script>-->
				<!-- 自定义 js 存放点 -->
		 <script src="<%=basePath %>js/sys/cookieHandel.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/sys/login.js" type="text/javascript"></script>
		
		<script>
		var basePath = '<%=basePath %>';

</script>
	</body>
</html>
