var myreg = /^1[34578]\d{9}$/;
var countdown = 60;
/**
 *  click 验证码 
 */
function slideOpen(type) {
	if(!myreg.test($("#account").val())) {
		//
		mui.alert('请输入有效的手机号码！');
		return false;
	}
	if(validatePhone(type) != true){
		return;
	}
	if($("#slide").is(":hidden")) {
		$("#slide").show();
	} else {
		var x = $("#labelTip").html();
		if(x == '验证成功'){
			settimeInit();
		}
		
	}
}

/**
 * 获取验证码
 * @param {Object} val
 */
function settimeInit() {
	$.ajax({
		type: 'post',
		url: basePath+'member/sendMsg',
		data: {
			phone:$('#account').val(),
			msgType:$('#msgType').val(),
			src:'H5',
			token: localStorage.token != undefined ? localStorage.token : ""
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				countdown = 60;
				settime();
			} else {
				mui.alert(data.msg);
			}
		}
	}); 
}


/**
 * 发送短信验证
 * @param {Object} val
 */
function settime(val) {
	var val = document.getElementById("msgCode");
	console.log(countdown);
	if(countdown == 0) {
		val.removeAttribute("disabled");
		val.className = "mui-btn mui-btn-danger mui-btn-outlined";
		val.innerHTML = "获取验证码";
	} else {
		val.setAttribute("disabled", true);
		val.innerHTML = "(" + countdown + ")s后重发";
		val.className = "mui-btn mui-btn-outlined";
		countdown--;
		setTimeout(function() {
			settime(val)
		}, 1000)
	}

}

$(function() {
	var slider = new SliderUnlock("#slider", {
		successLabelTip: "验证成功"
	}, function() {
		settimeInit();
		
	});
	slider.init();
});

//验证手机号
function validatePhone(type){
	var flag = true;
	$.ajax({
		type: 'post',
		url: basePath+'member/valiPhone',
		data: {
			phone:$('#account').val(),
			token: localStorage.token != undefined ? localStorage.token : "",
			checkType:type
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			console.log(data)
			if(data.success) {
				if(type==1){//注册请求
					if(data.errorCode=='002'){
						flag = false;
						mui.alert(data.msg);
					}else if(data.errorCode=='001'){
						
					}
				}else{//其他
					if(data.errorCode=='000'){
						flag = false;
						mui.alert("手机号未注册");
					}
				}
			} else {
				flag = false;
				mui.alert(data.msg);
			}
		}
	});   
	return flag;
}
