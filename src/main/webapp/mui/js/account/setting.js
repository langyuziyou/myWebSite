/***
 * 个人设置
 */
$(function(){
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
				var memberAccount = data.obj.memberAccount;
				var imageUrl = data.obj.imageUrl;
				var html = nikeName+'<p class="userId">ID： '+memberAccount+'</p>'
				$(".userName").html(html);
				if(imageUrl == null)
				{
					$("#headImg").attr("src","../images/account/default.png");
				}else
				{
					$("#headImg").attr("src",imgPath+imageUrl);
				}
			} else {
				//
				$("#accountA").attr("href","login.html");
				$("#orderA").attr("href","login.html");
				$("#orderADFK").attr("href","login.html");
				$("#collectA").attr("href","login.html");
				$("#readedA").attr("href","login.html");
				$("#addressA").attr("href","login.html");
				$("#setA").attr("href","login.html");
				$("#msgA").attr("href","login.html");
				
				
				
				//alert(data.msg);
			}
		}
	});
}