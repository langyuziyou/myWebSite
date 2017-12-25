/****
 * 个人信息
 */
$(function() {
	loadSetting();
});

/**
 * 加载基本信息
 */
function loadSetting() {
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'post',
		url: basePath + 'setting/loadSetting',
		data: {
			token: token
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				var nikeName = data.obj.nickName;
				$("#nickName").val(nikeName);

			} else {
				//alert(data.msg);
			}
		}
	});
}
