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
								  <a href="javaScript:;">列表</a>
								</span>
								<div></div>
								</br>
								<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
									  <button data-method="offset" data-type="auto" class="layui-btn layui-btn-normal" onClick="addCategory">新增</button>
									</div>
		
							 	<form name="" id="mainForm" action="<%=request.getContextPath()%>category/contractList/threeId/${threeId }" method="post">
									<input type="hidden" value="${page}" id="page" name="page" /> 
									<input type="hidden" value="${pageCount}" id="pageCount" name="pageCount" /> 
									<input type="hidden" value="${pageSize}" id="pageSize" name="pageSize" />
									<%@ include file="/WEB-INF/views/sys/token.jsp"%>
								</form>
								<div class="gdTable2">
										<table id="tabletree" class="layui-table">
											<tr>
												<th>分类名称</th>
												<th>层级</th>
												<th>父级ID</th>
												<th>操作</th>
											</tr>
											<c:forEach items="${list}" var="model" varStatus="num">
												<c:if test="${model.level == 1 }">
													<tr id="${model.id}" pId="0" hasChild="true">
														<td><span controller="true">${model.shop_category_name }</span></td>
														<td>${model.level }</td>
														<td>${model.parentId }</td>
														<td>
															
																<a href="<%=basePath%>category/preAdd/id/${model.id}" class="layui-btn layui-btn-normal"> 新增下级分类
																</a>
																<a href="<%=basePath%>category/preEdit/id/${model.id}" class="layui-btn"> 编辑
																</a>
																<a href="javascript:;" class="layui-btn  layui-btn-normal" id="addBtn" onClick="productImport('${model.id}')">导入</a>
																<a class="layui-btn layui-btn-danger" onclick="del('${model.id}')"> 删除
																</a>
														
														</td>
													</tr>
													</c:if>
												</c:forEach>
											
										</table>
								  </div>
								  
							</div>
				    	</div>
				    </div>

				</div>
		  	</div>
		</div>
		

</body>

		<script type="text/javascript" src="<%=basePath%>treeTable/jquery.treeTable.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/init.js" ></script>
		
		
<script>
/**
 * 信息导入
 */
function productImport(id) {
	layui.use(['layer', 'upload'], function() {
		var $ = layui.jquery, layer = layui.layer;
		layer.open({
			area : [ '90px', '180px' ],
			offset : '250px',
			title : '产品信息导入',
			content : '<div style="text-align: center"><button type="button" class="layui-btn" id="btnFile"><i class="layui-icon"></i>商品信息导入</button></div>'
		});
		
		var upload = layui.upload;
		upload.render({
			url : basePath + "shop/excelInId",
			elem : '#btnFile',
			accept : 'file',
			data:{category:id},
			exts : 'xlsx|xls',
			before : function(input) {
				layer.open({type : 3});
			},
			done : function(data) {
				layer.closeAll();
				var jsonObj = data;
				if (jsonObj.success) {
					 layer.alert(data.msg, {
					  title: '提示信息'  
						  }); 
				} else {
					 layer.alert(data.msg, {
					  title: '提示信息'  
						  }); 
				}
			},
			error : function(data) {
				layer.closeAll();
				tipGo('解析文件错误！');
				return;
			}
		});
	});
}
	






var html = "";
$(document).ready(function() {
    var option = {
	theme : 'default',
	expandLevel : 1,
	beforeExpand : function($treeTable, id) {
	    // 判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
	    if ($('.' + id, $treeTable).length) {
		return;
	    }
	    showTree(id);
	    $treeTable.addChilds(html);
	    html = "";
	},
	onSelect : function($treeTable, id) {
	}
    };
    $('#tabletree').treeTable(option);
});

	/**
	 * * 下级数展示
	 */
	function showTree(id) {
	    $.ajax({
		type : "Post",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		url : basePath + "category/getListByParentId",
		dataType : 'json',
		data : {
		    "id" : id
		},
		async : false,
		success : function(data) {
		    if (data.success) {
			var list = data.attributes.rs;
			if (list.length > 0) {
			    for (var i = 0; i < list.length; i++) {
				html += '<tr id="'+list[i].id+'" pId="'+list[i].parentId+'"';
				html += 'hasChild="true">';
				html += '<td><span controller="true">'+list[i].shop_category_name+'</span></td>';
				html += '<td>' + list[i].level + '</td>';
				html += '<td>' + list[i].parentId + '</td>';
				html += '<td>';
				if(list[i].level!=3){
					html += '<a  href="' + basePath + 'category/preAdd/id/'+list[i].id+'" class="layui-btn layui-btn-normal"> 新增下级分类 </a>';
					
				}
				
				html += '<a  href="' + basePath + 'category/preEdit/id/'+list[i].id+'" class="layui-btn "> 编辑 </a>';	
				html += '<a href="javascript:;" class="layui-btn  layui-btn-normal" id="addBtn" onClick="productImport(\''+list[i].id+'\')">导入</a>';
				
				html += '<a class="layui-btn layui-btn-danger "  onclick="del(' + list[i].id + ')" > 删除 </a></td>';
				html += '</tr>';
			    }
			}
			

		    }
		},
		beforeSend : function() {

		},
		error : function() {
		    return false;
		}
	    });

	}
</script>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
var  uiHtml = '';
	 uiHtml+='<div class="layui-form-item">';
   	 uiHtml+='<div class="layui-inline">';
	 uiHtml+='<label class="layui-form-label">名称:</label>';
	 uiHtml+='<div class="layui-input-inline">';
	 uiHtml+='<input type="text" name="name" id="name" value="" class="layui-input" lay-verify="notNull"/> ';
	 uiHtml+='</div>';
	 uiHtml+='<a class="layui-btn   layui-btn-normal" lay-submit="" lay-filter="demo1" >新增</a> </div>';
	 uiHtml+='</div>';


layui.use('layer', function(){ //独立版的layer无需执行这一句
  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

  //触发事件
  var active = {
    setTop: function(){
      var that = this; 
      //多窗口模式，层叠置顶

    }
    ,offset: function(othis){
      var type = othis.data('type')
      ,text = othis.text();
      
      
      layer.open({
        type: 1
        ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        ,id: 'layerDemo'+type //防止重复弹出
        ,content: '<div style="padding: 20px 100px;">'+ uiHtml +'</div>'
        ,btn: '关闭全部'
        ,btnAlign: 'c' //按钮居中
        ,shade: 0 //不显示遮罩
        ,area: ['800px', '300px']
        ,yes: function(){
          layer.closeAll();
          
        }
      });
      
      
    }
  };
  
  
  
  $('#layerDemo .layui-btn').on('click', function(){
    var othis = $(this), method = othis.data('method');
    active[method] ? active[method].call(this, othis) : '';
  });
  

});


/**
 * 
 */
layui.use('form', function(){
var $ = layui.jquery, form = layui.form;
form.render();

 //监听提交  
  form.on('submit(demo1)', function(data){
  var name = $("#name").val();
  if(name == '')
  {
  	alert("name is null");
  	return;
  }
  var data = {
		 name : name,
		 pId : 0,
		 level:1
     }

  var param = JSON.stringify(data.field)
    console.log(param);
  	//
  	$.ajax({
            type: "POST",
            url: basePath + "category/addCategory",
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

	    return false;  
	  }); 
	
});
  
</script>


<script>
/**
 * 新增一级分类 
 */
function addCategory(){
// 重新 渲染 

	
}

/**
 * 编辑 
 * @param {Object} id
 */
function editCategory(id,name,level,pId){
	var  uiHtml = '';
	 uiHtml+='<div class="layui-form-item">';
   	 uiHtml+='<div class="layui-inline">';
	 uiHtml+='<label class="layui-form-label">名称:</label>';
	 uiHtml+='<div class="layui-input-inline">';
	 uiHtml+='<input type="text" name="name" id="name" value="'+name+'" class="layui-input" lay-verify="notNull"/> ';
	 uiHtml+='</div>';
	 uiHtml+='<a class="layui-btn   layui-btn-normal" lay-submit="" lay-filter="demo1" >新增</a> </div>';
	 uiHtml+='</div>';


}

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




	/**
	 * 删除
	 */
	function del(id){
		layer.confirm("确认要删除吗，删除后不能恢复", { title: "删除确认" }, function (index) {  
               		 layer.close(index); 
				  	//
				  	$.ajax({
	 	    	            type: "POST",
	 	    	            url: basePath + "category/delCategory",
	 	    	            data:{
	 	    	            	id:id
	 	    	            },
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
               			
            });   
	}

</script>


	</body>
</html>