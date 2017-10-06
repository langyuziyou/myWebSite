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
		<jsp:param value="shop1" name="currentId" />
			<jsp:param value="shop" name="liId" />
		</jsp:include>	
		  	<div class="layui-tab layui-tab-brief" lay-filter="demoTitle">
				<div class="layui-body layui-tab-content site-demo site-demo-body">
				    <div class="layui-tab-item layui-show">
				    	<div class="layui-main">
					   		<div class="layui-form">
								<!-- breakLink  -->			   		
								<span class="layui-breadcrumb" lay-separator=">>">
								  <a href="javaScript:;">商品</a>
								  <a href="javaScript:;">新增商品</a>
								</span>
								<div></div>
								</br>

		
							 	<form name="" class="layui-form" action="">
									 <input type="hidden" name="uploadImage" id="uploadImage"/>
									 <input type="hidden" name="id" id="id" value="${result.shop_info_id}"/>
																		<!-- email -->
									<div class="layui-form-item">
										     <div class="layui-inline">
										      <label class="layui-form-label">名称:</label>
										      <div class="layui-input-inline">
										      	 <input type="text" name="name" value="${result.shop_info_name}" id="name" class="layui-input" lay-verify="notNull"/>
										      </div>
										    </div>
										    
										     <div class="layui-inline">
										      <label class="layui-form-label">价格:</label>
										      <div class="layui-input-inline">
										      	 <input type="text" name="price" value="${result.price}" id="price" class="layui-input" lay-verify="notNull"/>
										      </div>
										    </div>
									  </div>
									  
									 <div class="layui-form-item">
									    <div class="layui-inline">
									      <label class="layui-form-label">分类:</label>
									      <div class="layui-input-inline">
									       <select id="firstSelect" name="firstSelect" value="${firstSelect}"  lay-filter="firstSelect" >
												<option value="-1">请选择</option>
												<c:forEach items="${categoryFirstList}" var="row">
													<option value="${row.id}" <c:if test="${row.id eq result.shop_category_id }">selected="selected"</c:if>>${row.shop_category_name}</option>
												</c:forEach>
											</select>
		
									      </div>
									    </div>
									
										 <div class="layui-inline">
											 
										      <div class="layui-input-inline">
										      <select id="secondSelect" name="secondSelect"  lay-filter="secondSelect">
													<option value="-1">请选择</option>
													<c:forEach items="${secondSelectList}" var="row">
														<option value="${row.id}" <c:if test="${row.id eq result.shop_category_id }">selected="selected"</c:if>>${row.shop_category_name}</option>
													</c:forEach>
											  </select>
										      </div>
										</div>
										
										<div class="layui-inline">
											 
										      <div class="layui-input-inline">
										      <select id="threeSelect" name="threeSelect">
													<option value="-1">请选择</option>
													<c:forEach items="${threeSelectList}" var="row">
														<option value="${row.id}" <c:if test="${row.id eq result.shop_category_id }">selected="selected"</c:if>>${row.shop_category_name}</option>
													</c:forEach>
												</select>
										      </div>
										</div>
									</div>
									
										<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
										  <legend>上传多张图片</legend>
										</fieldset>
										 
										<div class="layui-upload">
										  <button type="button" class="layui-btn" name="btnFile" id="btnFile">多图片上传</button> 
										  <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
										    预览图：
										    <div class="layui-upload-list" id="demo2"></div>
										    已经存在：
										    <div class="layui-upload-list" id="demo3"></div> 
										 </blockquote>
										</div>
										
										<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
											<legend>描述</legend>
										</fieldset>
										
										<textarea id="shopDetail" name="shopDetail"  lay-verify="editcontent"  style="display: none;">${result.description}</textarea>
										
										
										<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
										  <legend>提交</legend>
										</fieldset>
			
									 <div class="layui-form-item">
									    
									    
										     <div class="layui-inline">
									
										      
												      <div class="layui-inline">
														  <label class="layui-form-label"></label>
														  <a href="javascript:;" onClick="addShop()" class="layui-btn layui-btn-small" >TMD </a>
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
		<script type="text/javascript" src="<%=basePath %>js/sys/categorySelect.js" ></script>
		<script src="<%=basePath%>js/upload/ajaxfileupload.js" type="text/javascript"></script>	
		
		
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
var uploadImage='';

layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  
  
  //多图片上传
  upload.render({
    elem: '#btnFile'
    ,url: basePath + "shop/fileUpload"
    ,multiple: true
    ,accept : 'images'
    ,before: function(obj){
      //预读本地文件示例，不支持ie8

    }
    ,done: function(data){
      //上传完毕
      console.log(data);
	            	if(data.success){
	            		 var msg = data.msg;
	            		 if(msg.indexOf(1)==0){
					        $('#demo2').append('<img src="'+basePath+ msg.substr(1,msg.length) +'"  style="width:200px;height:200px;" class="layui-upload-img">');
					        uploadImage+=msg.substr(1,msg.length)+"=_=";
					        console.log(uploadImage);
					        $("#uploadImage").val(uploadImage);
	            		 }
	            		 if(msg.indexOf(0)==0){
					        $('#demo3').append('<span>'+msg.substr(1,msg.length)+' 已经存在 ，请勿重复上传，或更换名称再上传</span></br>');
					        	
	            		 }

	            	}else{
	            		layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
	            	}
    }
  });
  

});
</script>

<!--
	作者：54087810@qq.com
	时间：2017-10-01
	描述：富文本
-->
<script>
function addShop(){
	var description = "";
		layui.use(['layer', 'form','layedit'], function(){
   		var $ = layui.jquery, form = layui.form,layedit = layui.layedit;
	
	//注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
		layedit.sync(index);
	description = layedit.getContent(index);

});
	//
		var name = $("#name").val();
		var price = $("#price").val();
		var firstSelect = $("#firstSelect").val();
		var secondSelect = $("#secondSelect").val();
		var threeSelect = $("#threeSelect").val();
		
		if(price == ''){
			layer.alert("请填写价格", {
						  title: '提示信息'  
							  }); 
			return ;
		}
		if(isNaN(price)){
			layer.alert("请填写正确价格", {
						  title: '提示信息'  
							  }); 
			return ;
		}
		
		if(firstSelect == '-1'){
			layer.alert("请选择分类", {
						  title: '提示信息'  
							  }); 
			return ;
		}
		if(name == ''){
			layer.alert("请填写名称", {
						  title: '提示信息'  
							  }); 
			return ;
		}
		
		if(uploadImage.length==0){
			layer.alert("图片", {
						  title: '提示信息'  
							  }); 
			return ;
		}
		
		
		
		var param = {
			uploadImage:uploadImage,
			price:price,
			name:name,
			firstSelect:firstSelect,
			secondSelect:secondSelect,
			threeSelect:threeSelect,
			description:description
		}
		
			  	$.ajax({
	            type: "POST",
	            url: basePath + "shop/addShop",
	            data:param,
	            datatype: "json",
	            success:function(data){
	            	 var data = eval('(' + data + ')');
	            	if(data.success){
	            		
					    layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
/*						setTimeout(function () { 
							window.location.href=basePath+'category/list';
					    }, 2000);*/
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
		
		console.log(param);
		

}


</script>		
		
<script>
var index;
	layui.use(['layer', 'form','layedit'], function(){
   	var $ = layui.jquery, form = layui.form,layedit = layui.layedit;
  
  layedit.set({
  uploadImage: {
     url: basePath + "upload/uploadImag"
    ,type: 'post' //默认post
  }
});
	//注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
	index = layedit.build('shopDetail'); //建立编辑器

	$("#shopDetail").html(layedit.getContent(index));
	form.render();

	//自定义验证规则  
	verifyRule(form);
	     //监听提交  
	  form.on('submit(demo1)', function(data){
	  	layer.msg(JSON.stringify(data.field));
   		 return false;
	
	  var param = JSON.stringify(data.field)
	    console.log(param);
	  	//
	  	$.ajax({
	            type: "POST",
	            url: basePath + "category/addCategoryChild",
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




</script>



	</body>
</html>