var btnArray = ['确认', '取消'];
$(function() {
	loadList();
});

function loadList() {
	$.ajax({
		type: 'get',
		url: basePath+'addr/getAddrList',
		data: {
			token: localStorage.token != undefined ? localStorage.token : "",
			memberId: 1
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				var list = jQuery.parseJSON(data.attributes.list);
				var html ='';
				for(var i = 0; i < list.length; i++) {
					var re = new RegExp('"', "g");
					var str  = JSON.stringify(list[i]).replace(re, "'");
					html+='<ul class="mui-table-view" style="margin-top: 0px;">';
					html+='<li class="mui-table-view-cell">';
					html+='<div class="mui-slider-handle">';
					html+='<div class="mui-media-body">';
					html+='<p class="mui-ellipsis">' + list[i].receiverName + ' <span style="padding-left: 20%;">' + list[i].receiverPhone + '</span> </p>';
					html+='<a class="mui-ellipsis" style="white-space:normal;">' + list[i].receiverAddress + '</a>';
					html+='</div>';
					html+='</div>';
					html+='</li>';
					html+='<li class="mui-table-view-cell" style="height:39px;top:-10px;">';
					html+='<div class="mui-slider-handle">';
					html+='<div class="mui-input-row mui-radio mui-left" style="height:31px;">';
					html+='<div style="margin-left: 30px;float: left;margin-top: 3px;"><a>设为默认</a>';
					html+='</div>';
					html+='<div style="margin-left:50px;float: right;margin-top: 0px;">';
					html+='<a onclick="goEdit('+str+')"><span class="mui-icon mui-icon-compose"></span>编辑</a>';
					html+='<a onclick="delAddr('+list[i].clientReceiceAddrId+')"><span class="mui-icon mui-icon-trash"></span>删除</a>';
					html+='</div>';
					html+='<input name="radio1" type="radio" checked="" style="left:0px;">';
					html+='</div>';
					html+='</div>';
					html+='</li>';
					html+='</ul>';
				}
				$('.mui-content').append(html);
			} else {

			}
		}
	});
}

function goAdd(){
	window.location.href = "addressEdit.html";
	var map ={};
    map["clientReceiceAddrId"] = 0;
    sessionStorage.addr= JSON.stringify(map);
    sessionStorage.actionType = 1;
}

//删除
function delAddr(id){
	mui.confirm('确认删除记录？', '收货地址', btnArray, function(e) {
		if (e.index == 0) {  
            $.ajax({
				type: 'post',
				url: basePath+'addr/delAddr',
				data: {
					token: localStorage.token != undefined ? localStorage.token : "",
					addrId: id
				},
				cache: false,
				async: false,
				dataType: 'JSONP',
				contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
				success: function(data) {
					if(data.success) {
						window.location.href = "address.html";  
					} else {
						mui.alert(data.msg);
					}
				}
			});    
        } 
	});
}
function goEdit(obj){
	window.location.href ="addressEdit.html";
    sessionStorage.addr= JSON.stringify(obj);
    sessionStorage.actionType = 2;
}
