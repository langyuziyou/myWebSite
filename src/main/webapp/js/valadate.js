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
    fname: function(value){
      if(value.length < 4){  
        return '请输入至少4位的用户名';  
      }  
    }, 
    address: function(value){
     if(value.length <1 ){  
        return '请输入地址';  
       }  
      if(value.length > 100){  
        return '不能超过100个字符';  
      }  
    },
   	editcontent:function (vale) {
        layedit.sync(shopDetail); //同步编辑内容到textarea #10line
    }
    ,phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！']
    ,email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']  
  }); 
	
}
	