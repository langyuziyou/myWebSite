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
		<jsp:param value="category1" name="currentId" />
			<jsp:param value="category" name="liId" />
		</jsp:include>	
		  	<div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
				<div class="layui-body layui-tab-content site-demo site-demo-body">
				    <div class="layui-tab-item layui-show">
				    	<div class="layui-main">
					   		<div class="layui-form">
								<!-- breakLink  -->			   		
								<span class="layui-breadcrumb" lay-separator=">>">
								  <a href="javaScript:;">分类</a>
								  <a href="javaScript:;">编辑分类</a>
								</span>
								<div></div>
								</br>

		
							 	<form name="" id="mainForm" action="<%=request.getContextPath()%>category/list" method="post">
									 <input type="hidden" name="id" value="${id}" />
									 <input type="hidden" name="parentId" value="${parentId}" />
									 <input type="hidden" name="level" value="${level}" />
																		<!-- email -->
									<div class="layui-form-item">
										     <div class="layui-inline">
										      <label class="layui-form-label">名称:</label>
										      <div class="layui-input-inline">
										      	 <input type="text" name="name" value="${name}" class="layui-input" lay-verify="notNull"/>
										      </div>
										    </div>
									  </div>
			
			
									 <div class="layui-form-item">
									    
									    
										     <div class="layui-inline">
									
										      
												      <div class="layui-inline">
														  <label class="layui-form-label"></label>
													     <button class="layui-btn layui-btn-small" lay-submit="" lay-filter="demo1" >修改</button>
													  </div>
											</div>
									  </div>    

								</form>
								  
							</div>
				    	</div>
				    </div>

				</div>
		  	</div>
		</div>
		

</body>
		<script type="text/javascript" src="<%=basePath %>js/init.js" ></script>
		<script type="text/javascript" src="<%=basePath %>js/valadate.js" ></script>
		
<script>
	
	// 重新 渲染 
	layui.use('form', function(){
  	var $ = layui.jquery, form = layui.form;
	form.render();
	
	//自定义验证规则  
	verifyRule(form);
  	
  
     //监听提交  
	  form.on('submit(demo1)', function(data){
	
	  var param = JSON.stringify(data.field)
	    console.log(param);
	  	//
	  	$.ajax({
	            type: "POST",
	            url: basePath + "category/editCategory",
	            data:"param="+param,
	            datatype: "json",
	            success:function(data){
	            	 var data = eval('(' + data + ')');
	            	if(data.success){
	            		
					    layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
						setTimeout(function () { 
							window.location.href=basePath+'category/list';
					    }, 2000);
	            	}else{
	            		layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
	            	}
	            },
	            error: function(){
	            	layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
	            }         
         });

	    return false;  
	  }); 
	
});
	

function edit(){
	
   var data = {
	 name : name,
	 pId : pId,
	 level:level,
	 id:id
 }
   	$.ajax({
        type: "POST",
        url: basePath + "category/editCategory",
        data:data,
        datatype: "json",
        success:function(data){
        	 var data = eval('(' + data + ')');
        	if(data.success){
        		
			    layer.alert(data.msg, {
				  title: '提示信息'  
					  }); 
				setTimeout(function () { 
					window.location.reload();
			    }, 2000);
        	}else{
        		layer.alert(data.msg, {
				  title: '提示信息'  
					  }); 
        	}
        },
        error: function(){
        	layer.alert(data.msg, {
				  title: '提示信息'  
						  }); 
            }         
     });
}

</script>


	</body>
</html>