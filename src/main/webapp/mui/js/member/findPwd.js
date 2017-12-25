function findPwd(){
	$.ajax({
		type: 'post',
		url: basePath+'member/findPwd',
		data: {
			token: localStorage.token != undefined ? localStorage.token : "",
			src: 4,
			msgType:3,
			msgSrc:'H5',
			phone:$('#account').val(),
			pwd:hex_md5($('#pwd').val()),
			code:$('#codeTxt').val()
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				mui.alert(data.msg);
				window.location.href = "login.html";  
			} else {
				mui.alert(data.msg);
			}
		}
	});   
}
