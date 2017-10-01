
function loadCategory(){
layui.use('form', function(){
var $ = layui.jquery, form = layui.form;
form.render();

	form.on('select(secondSelect)', function(data){
	console.log(data.value); //得到被选中的值
	var secondSelect='';
	secondSelect = '<option value="-1">请选择</option>';
	
	console.log(secondSelect);
	  var id = data.value;
	  if(id == -1){
		return;
		}
	  	console.log(id); //得到被选中的值
	  var data = {
			 id : id
	     }
	
		$.ajax({
	            type: "POST",
	            url: basePath + "category/loadCategorySecond",
	            data:data,
	            datatype: "json",
	            success:function(data){
	            	 var data = eval('(' + data + ')');
	            	if(data.success){
	            		var list = data.attributes.rs;
	            		secondSelect = '<option value="-1">请选择</option>';
	            		for(var i=0;i<list.length;i++){
	            				secondSelect+='<option value="'+list[i].id+'">'+list[i].shop_category_name+'</option>'
	            		}
						
	            	}else{
	            		layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
	            	}
	            	
	            	$("#threeSelect").empty();
	            	$("#threeSelect").append(secondSelect);
					form.render();
	            },
	            error: function(){
	            	layer.alert(data.msg, {
						  title: '提示信息'  
								  }); 
		            }         
		     });
		});  
				
});	
}

layui.use('form', function(){
var $ = layui.jquery, form = layui.form;
form.render();

	form.on('select(firstSelect)', function(data){
	  var id = data.value;
	  var secondSelect='';
	  secondSelect = '<option value="-1">请选择</option>';
	  if(id == -1){
		return;
		}
	  var data = {
			 id : id
	     }
	
		$.ajax({
	            type: "POST",
	            url: basePath + "category/loadCategorySecond",
	            data:data,
	            datatype: "json",
	            success:function(data){
	            	 var data = eval('(' + data + ')');
	            	if(data.success){
	            		var list = data.attributes.rs;
	            		
	            		for(var i=0;i<list.length;i++){
	            				secondSelect+='<option value="'+list[i].id+'">'+list[i].shop_category_name+'</option>'
	            		}
	            		
	            	}else{
	            		layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
	            	}
	            	$("#secondSelect").empty();
	            	$("#secondSelect").append(secondSelect);
	            		//
	            	loadCategory();
	            	form.render();
	            },
	            error: function(){
	            	layer.alert(data.msg, {
						  title: '提示信息'  
								  }); 
		            }         
		     });
		});  
				
});