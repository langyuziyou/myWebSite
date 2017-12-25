var mylogin = /^1[34578]\d{9}$/;
function login(){
	/**
	 * 登陆后需要把未登陆时做的购物车操作同步到redis，同时删除本地数据
	 * @author yzj 2017年12月20日 14点04分
	 * @returns
	 */ 
	var local = localStorage.getItem("detail_");
	
	if(!mylogin.test($("#account").val())) {
		mui.alert('请输入有效的手机号码！');
		return false;
	}
	
	if($("#password").val() == '') {
		mui.alert('密码不能为空！');
		return false;
	}
	
	$.ajax({
		type: 'post',
		url: basePath+'member/login',
		data: {
			account:$('#account').val(),
			password:hex_md5($('#password').val()),
			local:local
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				localStorage.removeItem("detail_");//删除detail
				localStorage.setItem("token", data.obj);
				window.location.href = "first.html";  
			} else {
				mui.alert(data.msg);
			}
		}
	});   
}

function val() {
	if(!mylogin.test($("#account").val())) {
		mui.alert('请输入有效的手机号码！');
		return false;
	}
}

