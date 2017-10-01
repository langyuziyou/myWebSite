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
								  <a href="javaScript:;">商品列表</a>
								</span>
								<div></div>
								</br>
								<a href="<%=basePath%>shop/preAdd" class="layui-btn  layui-btn-small layui-btn-normal" id="addBtn">新增</a>
							 	<form name="" id="mainForm" action="<%=basePath%>shop/list" method="post">
									<input type="hidden" value="${page}" id="page" name="page" /> 
									<input type="hidden" value="${pageCount}" id="pageCount" name="pageCount" /> 
									<input type="hidden" value="${pageSize}" id="pageSize" name="pageSize" />
									<%@ include file="/WEB-INF/views/sys/token.jsp"%>
									
									 <div class="layui-form-item">
										   <div class="layui-inline">
										      <label class="layui-form-label">名称:</label>
										      <div class="layui-input-inline">
										      	 <input type="text" name="shopName" value="${shopName}" id="shopName" class="layui-input"/>
										      </div>
										    </div>

										      <div class="layui-inline">
												  <label class="layui-form-label"></label>
											     <button class="layui-btn layui-btn-small" lay-submit="">查询</button>
		     									 <button type="reset" class="layui-btn layui-btn-small layui-btn-primary">重置</button>
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
									  
								</form>
								<div class="gdTable2">
								  	<table class="layui-table" style="min-width:1100px;">
								
								    <thead>
								      <tr>
							<!--	       <th><input name="" lay-skin="primary" lay-filter="allChoose" type="checkbox"></th>-->
								        <th style="min-width:80px;">ID</th>
								        <th style="min-width:230px;">名称</th>
								        <th style="min-width:80px;">分类</th>
								        <th style="min-width:80px;">图片</th>
								        <th>操作</th>
								      </tr> 
								    </thead>
								    <tbody id="mainTable">
								    	<c:forEach items="${list}" var="row">  
															 	<tr id="tr${row.shop_info_id}">
																	<td>
																		${row.shop_info_id }
																	</td>
																	<td>${row.shop_info_name }</td>
																	<td> ${row.shop_category_name }</td>
																	<td>${row.shop_info_image }</td>
																	<td>
																	<a href="javascript:;" style="margin-left: 10px;"id="edit${row.sysAdminId}" class="layui-btn  layui-btn-small layui-btn-warm" onClick="edit('${row.contractCategoryId}','${row.categoryName}','${row.description }','${row.receiveUnit }','${row.upCode }','${row.sourceCode }')">编辑</a>
																	<a href="javascript:;" id="hide${row.contractCategoryId}" class="layui-btn  layui-btn-small layui-btn-disabled" style="display:none;">编辑</a>	
																	</td>
																</tr>
										 </c:forEach>
								    	
								     
								    
								    </tbody>
								  </table>
								  </div>
								<jsp:include page="/WEB-INF/views/sys/pagination.jsp" flush="true"/>
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
<!--
	作者：54087810@qq.com
	时间：2017-08-15
	描述：逻辑 js
-->


<script>
	

	
	
	
	
	
	
	
	
     var isAdd = true; 
     var isEdit = false;
     
	 /**
	 * 验证规则 
	 * @param {Object} form
	 */
	function verifyRule(form){
			  form.verify({
				selectIt: function(value){ 
			      if(value == '-1' ){  
			        return '请选择';  
			       }
			    }, 
			    notNull: function(value){ 
			      if(value.length <1 ){  
			        return '请输入';  
			       }  
			      if(value.length > 50){  
			        return '不能超过50个字符啊';  
			      }  
			    }, 
			    address: function(value){
			     if(value.length <1 ){  
			        return '请输入';  
			       }  
			      if(value.length > 200){  
			        return '不能超过200个字符';  
			      }  
			    }  
			    ,phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！']
			    ,code: [/^\d{4}$/, '4位，只能是数字！比如0001 或 0100']
			    ,email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']  
			  }); 
	
}
	     
     
     
     /**
      * 创建一个tr
      */
	function addData(){
		if(isAdd){
			editModel();
			var html="";
			html+='<tr id="addTr">';
			html+='<td> <input type="text" name="categoryName" lay-verify="notNull" placeholder="" class="layui-input"></td>';
			html+='<td> <input type="text" name="description" lay-verify="address" placeholder="可支持200个汉字" class="layui-input"></td>';
			html+='<td> <input type="text" name="receiveUnit"   class="layui-input"></td>';
			html+='<td> </td>';
			html+='<td> <input type="text" name="sourceCode" lay-verify="code"  class="layui-input"></td>';
			html+='<td>';
			html+='	<a href="javascript:;" style="margin-left: 10px;" class="layui-btn  layui-btn-small layui-btn-normal" lay-submit="" lay-filter="demo1" >保存</a>';
			html+=' <a href="javascript:;" class="layui-btn  layui-btn-small layui-btn-danger" onClick="delTr()">取消</a>';
			html+='</td>';
			html+='</tr>';
			
			$("#mainTable").prepend(html);
			
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
	 	    	            url: basePath + "contractCategory/contractAdd",
	 	    	            data:"param="+param,
	 	    	            datatype: "json",
	 	    	            success:function(data){
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

				    return false;  
				  }); 
				
			});
			isAdd = false;
		}
		
	}
	
	/**
	 * 删除一个dr
	 */
	function delTr(){
		$("#addTr").remove();
		isAdd = true;
		norModel();
	}
	
	
	/**
	 * 编辑模式 
	 * 新增 和其他编辑 不可用 
	 */
	function editModel(){
		//新增按钮
		$("#addBtn").removeClass("layui-btn-normal");
		$("#addBtn").addClass("layui-btn-disabled");
		$("#addBtn").removeAttr("onClick");
		
		//编辑按钮
		 $("a[id^='edit']").each(function(i){
	  			$(this).hide();
		  	});
		  	 $("a[id^='hide']").each(function(i){
	  			$(this).show();
		  	});
	}

	
	/**
	 * 非编辑模式 
	 * 新增 和其他编辑 不可用 
	 */
	function norModel(){
	// 新增按钮
		$("#addBtn").addClass("layui-btn-normal");
		$("#addBtn").removeClass("layui-btn-disabled");
		$("#addBtn").attr("onClick","addData()");
		
	//编辑按钮
		 $("a[id^='edit']").each(function(i){
	  			$(this).show();
		  	});
		  	 $("a[id^='hide']").each(function(i){
	  			$(this).hide();
		  	});	
	}
	
	
	
	/**
	 * 准备编辑
	 * trId 当前tr 的id 
	 * type 1.买方 2 卖方
	 */
	function edit(id,categoryName,description,receiveUnit,upCode,sourceCode){
			isEdit = true;
			
			var html='';
			html+='<td ><input type="hidden" name="id"  value="'+id+'" />'+categoryName+'</td>';
			html+='<td> '+description+'</td>';
			html+='<td> '+receiveUnit+'</td>';
			html+='<td> '+categoryName+upCode+'</td>';
			html+='<td> <input type="text" name="sourceCode" lay-verify="code" value="'+sourceCode+'" class="layui-input"> </td>';

			html+='<td>	<a href="javascript:;" style="margin-left: 10px;" class="layui-btn  layui-btn-small layui-btn-normal" lay-submit="" lay-filter="demo2" >保存修改</a>';
			html+=' <a href="javascript:;" class="layui-btn  layui-btn-small layui-btn-danger" onClick="cancel(\''+id+'\',\''+categoryName+'\',\''+description+'\',\''+receiveUnit+'\',\''+upCode+'\',\''+sourceCode+'\')">取消</a>';
			html+='</td>';
	
		$("#tr"+id).empty();
		$("#tr"+id).append(html);
		// 选中
		
		if(isEdit){
			editModel();
		}
			
			// 重新 渲染 
			layui.use('form', function(){
			  	var $ = layui.jquery, form = layui.form;
				form.render();
				
			//自定义验证规则  
				verifyRule(form);
			  	
			  
			     //监听提交  
				  form.on('submit(demo2)', function(data){
				
				  var param = JSON.stringify(data.field)
				    console.log(param);
				  	//
				  	$.ajax({
	 	    	            type: "POST",
	 	    	            url: basePath + "contractCategory/contractEdit",
	 	    	            data:"param="+param,
	 	    	            datatype: "json",
	 	    	            success:function(data){
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

				    return false;  
				  }); 
				
			});
		
			
	}
	
	
	/**
	 * 非编辑模式 
	 * 新增 和其他编辑 不可用 
	 */
	function cancel(id,categoryName,description,receiveUnit,upCode,sourceCode){
		norModel();
		var html="";
			html+='<td >'+categoryName+'</td>';
			html+='<td> '+description+'</td>';
			html+='<td> '+receiveUnit+'</td>';
			html+='<td> '+categoryName+upCode+'</td>';
			html+='<td> '+categoryName+sourceCode+'</td>';
			html+='<td>';
			html+='	<a href="javascript:;" style="margin-left: 10px;" class="layui-btn  layui-btn-small layui-btn-warm" id="edit'+id+'" onClick="edit(\''+id+'\',\''+categoryName+'\',\''+description+'\',\''+receiveUnit+'\',\''+upCode+'\',\''+sourceCode+'\')">编辑</a>';
			html+='<a href="javascript:;" id="hide'+id+'" class="layui-btn  layui-btn-small layui-btn-disabled" style="display:none;">编辑</a>';
			html+='</td>';
		$("#tr"+id).empty();
		$("#tr"+id).append(html);
	}
</script>
	</body>
</html>