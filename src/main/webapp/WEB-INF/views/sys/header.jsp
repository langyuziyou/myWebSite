<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="<%=basePath %>layui/css/layui.css" />
		<link rel="stylesheet" href="<%=basePath %>css/sys/mian.css" />
		<link rel="stylesheet" href="<%=basePath %>treeTable/themes/default/treeTable.css" />
		<script src="<%=basePath %>js/jquery.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>layui/layui.js"  charset="utf-8"></script>
		<script src="<%=basePath%>layui/lay/modules/upload.js" charset="utf-8"></script>
		<script>
					var basePath = '<%=basePath%>';
		</script>
	</head>
	<body>
		<div class="layui-layout layui-layout-admin">
			<header class="layui-header header">
				<div class="layui-main">
				    <a class="logo" href="/">
				      <img src="" alt="bps">
					       <span>伟仕进出口报关系统</span>
				    </a>
				    <div class="user">
					       <a href="${ sessionScope.basePath}orgGroup/firstMenu" class="layui-btn layui-btn-small">传送门
						</a>
				    	<span>${ sessionScope.user.trueName}</span>
				    	<a href="${ sessionScope.basePath}/loginIt.do?loginOut" class="layui-btn layui-btn-small"> 修改密码
						</a>
				    	<a href="${ sessionScope.basePath}/loginIt.do?loginOut" class="layui-btn layui-btn-small"> 登出
						</a>
				    </div>
				</div>
			</header>

