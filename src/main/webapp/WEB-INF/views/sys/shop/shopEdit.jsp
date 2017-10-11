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
													<option value="${row.id}" <c:if test="${row.id eq firstSelect }">selected="selected"</c:if>>${row.shop_category_name}</option>
												</c:forEach>
											</select>
		
									      </div>
									    </div>
									
										 <div class="layui-inline">
											 
										      <div class="layui-input-inline">
										      <select id="secondSelect" name="secondSelect"  lay-filter="secondSelect">
													<option value="-1">请选择</option>
													<c:forEach items="${secondSelectList}" var="row">
														<option value="${row.id}" <c:if test="${row.id eq secondSelect }">selected="selected"</c:if>>${row.shop_category_name}</option>
													</c:forEach>
											  </select>
										      </div>
										</div>
										
										<div class="layui-inline">
											 
										      <div class="layui-input-inline">
										      <select id="threeSelect" name="threeSelect">
													<option value="-1">请选择</option>
													<c:forEach items="${threeSelectList}" var="row">
														<option value="${row.id}" <c:if test="${row.id eq threeSelect }">selected="selected"</c:if>>${row.shop_category_name}</option>
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
										    <div class="layui-upload-list" id="demo2">
										    <c:forEach items="${imgList}" var="row" varStatus="status">  
										    <div id="div${row.shop_img_id}">
						            		 	<img src="${row.shop_info_image}" style="max-width:300px;max-height:120px;" title="${row.shop_img_id}" />
												<button type="button" class="layui-btn layui-btn-danger layui-btn-small" id="img${row.shop_img_id}"   onClick="delImg('div${row.shop_img_id}')" ><i class="layui-icon"></i></button>
												<c:choose>
													<c:when test="${status.count == 1}">
														<button type="button" class="layui-btn layui-btn-normal layui-btn-small" id="mainImg${row.shop_img_id}" onClick="mainImg('mainImg${row.shop_img_id}')" ><i class="layui-icon"></i></button>
													</c:when>
													<c:otherwise>
														<button type="button" class="layui-btn layui-btn-primary layui-btn-small" id="mainImg${row.shop_img_id}" onClick="mainImg('mainImg${row.shop_img_id}')" ><i class="layui-icon"></i></button>
													</c:otherwise>
												</c:choose>
												
											</div></br>
										    </c:forEach>
										    
										    </div>
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
														  <a href="javascript:;" onClick="editShop()" class="layui-btn layui-btn-small" >提交 </a>
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
		<script type="text/javascript" src="<%=basePath %>js/shop/shop.js" ></script>
		<script type="text/javascript" src="<%=basePath %>js/valadate.js" ></script>
		<script type="text/javascript" src="<%=basePath %>js/sys/categorySelect.js" ></script>
		<script src="<%=basePath%>js/upload/ajaxfileupload.js" type="text/javascript"></script>	
		
		


<!--
	作者：54087810@qq.com
	时间：2017-10-01
	描述：富文本
-->
<script>
// 设置主图  
function mainImg(ids){
	if($("#"+ids+"").hasClass("layui-btn-normal")){
		$("#"+ids+"").removeClass("layui-btn-normal");
		$("#"+ids+"").addClass("layui-btn-primary");
		console.log(1);
	}
	else
	{
		$("#demo2").find("button[id^='mainImg']").each(function(i){
			var $id = this.id;
   			if($(this).hasClass("layui-btn-normal"))
   				{
   						$("#"+$id+"").removeClass("layui-btn-normal");
   						$("#"+$id+"").addClass("layui-btn-primary");
   				}
   		});
   		
		$("#"+ids+"").removeClass("layui-btn-primary");
		$("#"+ids+"").addClass("layui-btn-normal");
	}
}	
	




var imgCount = 0;
function editShop(){
	var description = "";
		layui.use(['layer', 'form','layedit'], function(){
   		var $ = layui.jquery, form = layui.form,layedit = layui.layedit;
	
	//注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
		layedit.sync(index);
	description = layedit.getContent(index);

});
	//
			var imgStr = '';
			var imgStrFirst = '';
		$("#demo2").find("img").each(function(i){
			imgCount++;
				var $src = this.currentSrc;
				var $at = $(this).next().next().attr("id");
				
				if($("#"+$at+"").hasClass("layui-btn-normal")){
					imgStrFirst+= '{"src":"'+$src+'"}';
				  	imgStrFirst+= ',';
					
				}else{
					console.log($at);
					console.log($src);
					imgStr+= '{"src":"'+$src+'"}';
				  	imgStr+= ',';
				}
		

			
   			
   	});
   		if(imgStr.length>0){
   			imgStr = imgStr.substring(0,imgStr.length-1);
   			uploadImage = imgStrFirst+imgStr;
   			console.log(imgStrFirst+imgStr);
   		}else
   		{
   			imgStrFirst = imgStrFirst.substring(0,imgStrFirst.length-1);
   			uploadImage = imgStrFirst;
   			console.log(imgStrFirst);
   		}
   		
   		
		
		var name = $("#name").val();
		var price = $("#price").val();
		var firstSelect = $("#firstSelect").val();
		var secondSelect = $("#secondSelect").val();
		var threeSelect = $("#threeSelect").val();
		
		var firstSelectVal = $("#firstSelect").find("option:selected").text();
		var secondSelectVal = $("#secondSelect").find("option:selected").text();
		var threeSelectVal = $("#threeSelect").find("option:selected").text();
		
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
		
		
		

		
		if(imgCount==0){
			layer.alert("需要上传图片", {
						  title: '提示信息'  
							  }); 
			return ;
		}
		
		
		var id = $("#id").val();
		var param = {
			uploadImage:"["+uploadImage+"]",
			id:id,
			price:price,
			name:name,
			firstSelect:firstSelect,
			secondSelect:secondSelect,
			threeSelect:threeSelect,
			firstSelectVal:firstSelectVal,
			secondSelectVal:secondSelectVal,
			threeSelectVal:threeSelectVal,
			description:description
		}
		
			  	$.ajax({
	            type: "POST",
	            url: basePath + "shop/editShop",
	            data:param,
	            datatype: "json",
	            success:function(data){
	            	 var data = eval('(' + data + ')');
	            	if(data.success){
	            		
					    layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
						setTimeout(function () { 
							window.location.href=basePath+'shop/list';
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
		
		console.log(param);
		

}


</script>


	</body>
</html>