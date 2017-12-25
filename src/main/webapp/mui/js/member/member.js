function editMemberName(){
	$.ajax({
		type: 'post',
		url: basePath+'member/editAccountName',
		data: {
			memberName: $('#memberName').val(),
			token: localStorage.token != undefined ? localStorage.token : ""
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				mui.alert(data.msg);
			} else {
				mui.alert(data.msg);
			}
		}
	});
}

function editNickName(){
	$.ajax({
		type: 'post',
		url: basePath+'member/editNickName',
		data: {
			nickName: $('#nickName').val(),
			token: localStorage.token != undefined ? localStorage.token : ""
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				mui.alert(data.msg);
			} else {
				mui.alert(data.msg);
			}
		}
	});
}

function editPwd(){
	if(hex_md5($('#newPwd').val()) != hex_md5($('#rePwd').val())){
		mui.alert("两次输入密码不一致");
		return;
	}
	$.ajax({
		type: 'post',
		url: basePath+'member/editPwd',
		data: {
			oldPwd: hex_md5($('#oldPwd').val()),
			newPwd: hex_md5($('#newPwd').val()),
			token: localStorage.token != undefined ? localStorage.token : ""
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				mui.alert(data.msg);
			} else {
				mui.alert(data.msg);
			}
		}
	});
}
