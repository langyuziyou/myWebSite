

var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];

function generateMixed(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;
}
	
// 删除图片 
function delImg(id){
	// 看是否是编辑，如果是编辑数据 则需要删除 相关的 表数据
	var title = $("#"+id+"").find("img").attr("title");
	if(title != 'new'){
			var $id = $("#id").val();
			
			var param = {
			id:$id,
			imgId:title
		}
		
			  	$.ajax({
	            type: "POST",
	            url: basePath + "shop/deleteImg",
	            data:param,
	            datatype: "json",
	            success:function(data){
	            	 var data = eval('(' + data + ')');
	            	if(data.success){
	            		 $("#"+id+"").remove();
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
		
		
}else
	{
		$("#"+id+"").remove();
	}
	
	
}


	
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
	            		var res = generateMixed(10);
	            		 var msg = data.msg;
	            		 if(msg.indexOf(1)==0){
	            		 	
	            		 	var html = '';
	            		 	html+='<div id="div'+res+'">';
	            		 	html+='<img src="'+basePath+ msg.substr(1,msg.length) +'" style="max-width:300px;max-height:120px;" title="new" />';
							html+='<button type="button" class="layui-btn layui-btn-danger layui-btn-small" id="img'+res+'"   onClick="delImg(\'div'+res+'\')" ><i class="layui-icon"></i></button>';
							html+='<button type="button" class="layui-btn layui-btn-primary layui-btn-small" id="mainImg'+res+'" onClick="mainImg(\'mainImg'+res+'\')" ><i class="layui-icon"></i></button>';
							html+='</div></br>';
					        $('#demo2').append(html);
					        
	            		 }
	            		 if(msg.indexOf(0)==0){
	            		 	var html = '';
	            		 	html+='<div id="div'+res+'">';
	            		 	html+='已经存在的图片<img src="'+basePath+ msg.substr(1,msg.length) +'" style="max-width:300px;max-height:120px;" title="new" />';
							html+='<button type="button" class="layui-btn layui-btn-danger layui-btn-small" id="img'+res+'"   onClick="delImg(\'div'+res+'\')" ><i class="layui-icon"></i></button>';
							html+='<button type="button" class="layui-btn layui-btn-primary layui-btn-small" id="mainImg'+res+'" onClick="mainImg(\'mainImg'+res+'\')" ><i class="layui-icon"></i></button>';
							html+='</div>';
					        $('#demo2').append(html);
					        	
	            		 }

	            	}else{
	            		layer.alert(data.msg, {
						  title: '提示信息'  
							  }); 
	            	}
    }
  });
  

});



/***
 * 富文本
 */
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

