function regMember(){
	$.ajax({
		type: 'post',
		url: basePath+'member/regMember',
		data: {
			src: 4,
			type:0,
			msgType:1,
			msgSrc:'H5',
			token: localStorage.token != undefined ? localStorage.token : "",
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


