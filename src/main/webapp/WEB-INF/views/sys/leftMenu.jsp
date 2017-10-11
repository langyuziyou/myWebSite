<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="layui-side layui-bg-black hg-side">
	<div class="layui-side-scroll">
		<ul class="layui-nav layui-nav-tree" lay-filter="demo">

			
		<!--	<li class="layui-nav-item">
			    <a href="javascript:;">备货订单111</a>
			</li>
			<li class="layui-nav-item">
			    <a href="javascript:;">制单</a>
			</li>
			<li class="layui-nav-item">
			    <a href="javascript:;">物流</a>
			</li>-->
			<li class="layui-nav-item <c:if test="${param.liId=='category'}">layui-nav-itemed</c:if>">
			    <a href="javascript:;">分类</a>
			    <dl class="layui-nav-child">
			      	<dd><a href="<%=basePath %>category/list"  class="<c:if test="${param.currentId=='category1'}">layui-this</c:if>">分类列表</a></dd>
<!--			      	<dd><a href="javascript:;">产品信息维护</a></dd>
			      	<dd><a href="javascript:;">订车信息维护</a></dd>
			      	<dd><a href="javascript:;">用户及权限管理</a></dd>-->
			    </dl>
			</li>
			<li class="layui-nav-item <c:if test="${param.liId=='shop'}">layui-nav-itemed</c:if>">
			    <a href="javascript:;">商品</a>
			    <dl class="layui-nav-child">
			      	<dd><a href="<%=basePath %>shop/list"  class="<c:if test="${param.currentId=='shop1'}">layui-this</c:if>">商品列表</a></dd>
	<!--		      	<dd><a href="javascript:;">产品信息维护</a></dd>
			      	<dd><a href="javascript:;">订车信息维护</a></dd>
			      	<dd><a href="javascript:;">用户及权限管理</a></dd>-->
			    </dl>
			</li>
		</ul>
	</div>
</div>