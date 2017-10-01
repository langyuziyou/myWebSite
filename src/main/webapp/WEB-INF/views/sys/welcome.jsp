<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--
	作者：54087810@qq.com
	时间：2017-08-09
	描述：header.jsp
-->
<jsp:include page="/WEB-INF/views/sys/header.jsp" flush="true"/>
	
<!--
	作者：54087810@qq.com
	时间：2017-08-09
	描述：leftMenu.jsp
-->
<jsp:include page="/WEB-INF/views/sys/leftMenu.jsp" flush="true">
		<jsp:param value="${threeId }" name="currentId" />
			<jsp:param value="${secondId }" name="liId" />
	</jsp:include>	
		</div>
		<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
	
		<script>
		layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  
  //监听导航点击
  element.on('nav(demo)', function(elem){
    //console.log(elem)
    layer.msg(elem.text());
  });
});
layui.use('form', function(){
  var $ = layui.jquery, form = layui.form;
  
  //全选
  form.on('checkbox(allChoose)', function(data){
    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
    child.each(function(index, item){
      item.checked = data.elem.checked;
    });
    form.render('checkbox');
  });
  
	 });	/*
		layui.use('element', function(){
			
		 	 var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块
		  
		 	//监听导航点击
		 	element.on('nav(demo)', function(elem){
		    	//console.log(elem)
		    	layer.msg(elem.text());
		  	});
		});
		layui.use('layer', function(){
		  	var layer = layui.layer;
		  	layer.open({
			  	content: 'test'
			  	,btn: ['按钮一', '按钮二', '按钮三']
			  	,yes: function(index, layero){
			    	//按钮【按钮一】的回调
			  	}
			  	,btn2: function(index, layero){
			    	//按钮【按钮二】的回调
			    
			    	//return false 开启该代码可禁止点击该按钮关闭
			  	}
			  	,btn3: function(index, layero){
			   	 	//按钮【按钮三】的回调
			    
			    	//return false 开启该代码可禁止点击该按钮关闭
			  	}
			  	,cancel: function(){ 
			    	//右上角关闭回调
			    
			    	//return false 开启该代码可禁止点击该按钮关闭
			  	}
			});
			
			layer.open({
		  		type: 2,
		  		title: '新增',
		  		shadeClose: true,
		  		shade: 0.8,
		  		area: ['500px', '90%'],
		  		content: 'add.html' //iframe的url
			});
		});
		 */
		layui.use('form', function(){
  var $ = layui.jquery, form = layui.form;
  
  //全选
  form.on('checkbox(allChoose)', function(data){
    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
    child.each(function(index, item){
      item.checked = data.elem.checked;
    });
    form.render('checkbox');
  });
  
});
		</script>
	</body>
</html>