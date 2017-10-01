<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- page start -->
 <div id="demo2"></div>
<!-- page end --> 

<script>
// page
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;

  
  laypage.render({
    elem: 'demo2'
    ,count: '${allCount}'
    ,pages: '${pageCount}'
    ,curr:'${page}'
    ,first: 1 //将首页显示为数字1,。若不显示，设置false即可
    ,layout: ['count', 'prev', 'page', 'next', 'skip']
    ,jump:function(e,first){
    	if(!first){
    	var nowpage = e.curr; // (被单击的页码)
    	$("#page").val(nowpage);
    	$("#mainForm").submit();
    	}
    }
  });
  
});
</script>