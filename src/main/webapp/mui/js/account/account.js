/****
 * 个人中心
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
				var memberAccount = data.obj.memberAccount;
				var imageUrl = data.obj.imageUrl;
				$("#account").html(memberAccount);
				$("#nickName").html(nikeName);
				if(imageUrl == null)
				{
					$("#head-img1").attr("src","../images/account/default.png");
				}else
				{
					$("#head-img1").attr("src",imgPath+imageUrl);
				}
				

			} else {
				//alert(data.msg);
			}
		}
	});
}

function upLoad() {
	$("#upFile").click();
}

function doUpload() {
	$("#myArticleForm").submit();
}

/**
 * 上传
 */
$(document).ready(function() {
	var token = localStorage.getItem("token");
	$("#token").val(token);
	$("#myArticleForm").ajaxForm({
		dataType: "json",
/*		beforeSend: function(xhr) {
			xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
		}, //这里设置header*/
		success: function(ret) {
			var rs = jQuery.parseJSON(ret.attributes.rs);
			 console.log(rs);
			var imgName = rs.newFileName;
			var imageId = rs.imageId;
			var notFullUrl = rs.notFullUrl;
			$("#head-img1").attr("src",imgName);
			$("#imageId").val(imageId);
			$("#notFullUrl").val(notFullUrl);
			//mui.alert('上传成功');
			mui.toast('上传成功');
			updateImg();
		
		},
		error: function(ret) {
			console.log(ret);
		}
	});
});

$(function() {
	$("#upFile").change(function() {　　　
		doUpload();
	});
})



function updateImg(){
	var token = localStorage.getItem("token");
	var imageId = $("#imageId").val();
	var notFullUrl = $("#notFullUrl").val();
	$.ajax({
		type: 'post',
		url: basePath + 'setting/updateImg',
		data: {
			token: token,
			imageId:imageId,
			notFullUrl:notFullUrl
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				
			} else {
				console.log(data);
				mui.toast(data);
			}
		}
	});
}
