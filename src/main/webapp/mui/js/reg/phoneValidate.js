var myreg = /^1[34578]\d{9}$/;
var countdown = 60;
/**
 *  click 验证码 
 */
function slideOpen() {
	if(!myreg.test($("#account").val())) {
		//
		mui.alert('请输入有效的手机号码！');
		return false;
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
	mui.alert('验证成功,发送短信');
	countdown = 60;
	settime();
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
		//window.location.href = "http://www.baidu.com"
	});
	slider.init();
})