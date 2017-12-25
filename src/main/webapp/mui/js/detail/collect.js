/**
 * collect
 * 非登陆状态下不允许操作
 */
function collect() {

	var token = localStorage.getItem("token");
	var sessionState = isLogin();
	if(sessionState) {
		//.mui-icon-extra-hetao-wdsc:before
		//mui-icon-extra-hetao-wdsc-default
		var url = 'goods/saveCollect';
		if($("#collect").hasClass("mui-icon-extra-hetao-wdsc-default")) {
			$.ajax({
				type: 'post',
				url: basePath +  url,
				data: {
					goodsId: goodsId,
					storeId: storeId,
					token: token
				},
				cache: false,
				async: false,
				dataType: 'JSONP',
				contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
				success: function(data) {
					if(data.success) {
						$("#collect").removeClass("mui-icon-extra-hetao-wdsc-default");
						$("#collect").addClass("mui-icon-extra-hetao-wdsc");
					} else {
						console.log(data);
					}
				}
			});
		} else {
			url = 'detail/delCollect';
			$.ajax({
				type: 'post',
				url: basePath +  url,
				data: {
					goodsId: goodsId,
					storeId: storeId,
					token: token
				},
				cache: false,
				async: false,
				dataType: 'JSONP',
				contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
				success: function(data) {
					if(data.success) {
						$("#collect").addClass("mui-icon-extra-hetao-wdsc-default");
						$("#collect").removeClass("mui-icon-extra-hetao-wdsc");
					} else {
						console.log(data);
					}
				}
			});

		}

	}
	// 非登陆
	else {
		var btnArray = ['去登陆', '取消'];
			mui.confirm('请先登陆？', '提示', btnArray, function(e) {
				if(e.index == 0) {
						window.location.href = 'login.html';
				} 
			});
	}
}