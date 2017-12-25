mui.init();
var btnArray = ['确认', '取消'];
//
$(".mui-input-row").hide();
$(".aui-bar").hide();

function subM() {
	console.log(1);
}

/**
 * 切换 编辑 还是完成
 */
function edit() {
	var v = $("#edit").html();
	if(v == "完成") {
		//
		$("#edit").html("编辑");
		$(".mui-input-row").hide();
		$(".aui-bar").hide();

	} else {
		//
		$("#edit").html("完成");
		$(".mui-input-row").show();
		$(".aui-bar").show();
	}
}
$(function() {
	loadData();
	$("#checkboxAll").click(function() {
		//全选 反选
		var ch = this.checked;
		$("input[type=checkBox][name=sccheckbox]").each(function() {
			this.checked = ch;
		});

		//
		if(this.checked) {
			styleChange(1);
		} else {
			styleChange(2);
		}

	});

	function bandcheck(){
		//监听  点击 如果有选中 
		$("input[type=checkBox][name=sccheckbox]").click(function() {
			//
			var l = 0;
			if(this.checked) {
				l++;
			}
	
			$("input[type=checkBox][name=sccheckbox]").each(function() {
				if(this.checked == true) {
					l++;
				}
			});
	
			if(l > 0) {
				styleChange(1);
			} else {
				styleChange(2);
	
			}
	
		});
	}
	
	setInterval(bandcheck,3000);

	// type 1:选中     2:未选中
	function styleChange(type) {
		switch(type) {
			case 1:
				$("#last_buy").show();
				$("#last_hide").hide();
				break;
			case 2:
				$("#last_buy").hide();
				$("#last_hide").show();
				break;
		}

	}

	//del
	$("#last_buy").click(function() {
		//全选 反选
		var isDel = false;
		$("input[type=checkBox][name=sccheckbox]").each(function() {
			if(this.checked) {
				isDel = true;
				return;
			}
		});

		if(isDel) {
			mui.confirm('确认删除记录？', '浏览足迹', btnArray, function(e) {
				if (e.index == 0) {
					var ids = new Array();
					$("input[type=checkBox][name=sccheckbox]").each(function() {
						if(this.checked) {
							ids.push($(this).val());
						}
					});
					batchDelLook(ids.toString());
				}
			});
		}
	});
});

(function($) {
	//第二个demo，向右拖拽后显示操作图标，释放后自动触发的业务逻辑
	$('.mui-btn mui-btn-red').on('slideright', '.mui-table-view-cell', function(event) {
		var elem = this;
		mui.confirm('确认删除该条记录？', 'Hello MUI', btnArray, function(e) {
			if(e.index == 0) {
				elem.parentNode.removeChild(elem);
			} else {
				setTimeout(function() {
					$.swipeoutClose(elem);
				}, 0);
			}
		});
	});
})(mui);

function loadData(){
	$.ajax({
		type: 'post',
		url: basePath+'goods/getAllLookList',
		data: {
			token: localStorage.token != undefined ? localStorage.token : ""
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				var list = jQuery.parseJSON(data.obj);
				var html ="";
				for (var i = 0; i < list.length; i++) {
					html+='<ul id="" class="mui-table-view" style="margin-top:0px;">';
					html+='<li class="mui-table-view-cell" style="text-align: left;">';
					html+=''+list[i].dayTime+'';
					html+='</li>';
					var look = list[i].lookList;
					for (var j = 0; j < look.length; j++) {
						html+='<li class="mui-table-view-cell" style="">';
						html+='<div class="mui-slider-right mui-disabled">';
						html+='<a class="mui-btn mui-btn-red" onclick="delLook('+look[j].mallGoodsLookId+')">删除</a>';
						html+='</div>';
						html+='<div class="mui-slider-handle">';
						html+='<div class="mui-input-row mui-checkbox mui-left" style="float:left;display:inline;height:50px;width:30px;">';
						html+='<input name="sccheckbox" value="'+look[j].mallGoodsLookId+'" type="checkbox" style="left:-1px;top: 20px;">';
						html+='</div>';
						html+='<a href="javascript:;">';
						html+='<img class="mui-media-object mui-pull-left" src="'+look[j].defaultImage+'">';
						html+='<div class="mui-media-body">';
						html+='<p class="mui-ellipsis" style="font-weight: bold;">'+look[j].title+'</p>';
						//html+='<p class="mui-ellipsis">品牌：A+ 颜色：英雄黄</p>';
						html+='<p class="pMoney" style="">￥'+look[j].price+'</p>';
						html+='</div>';
						html+='</a>';
						html+='</div>';
						html+='</li>';
					}
					html+='</ul>';
				}
				$('.mui-content').prepend(html);
			} else {
				mui.alert(data.msg);
			}
		}
	}); 
}

function delLook(id){
	mui.confirm('确认删除该记录？', '浏览记录', btnArray, function(e) {
		if (e.index == 0) {
            $.ajax({
				type: 'post',
				url: basePath+'goods/delLook',
				data: {
					token: localStorage.token != undefined ? localStorage.token : "",
					lookId: id
				},
				cache: false,
				async: false,
				dataType: 'JSONP',
				contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
				success: function(data) {
					if(data.success) {
						window.location.href = "readed.html";  
					} else {
						mui.alert(data.msg);
					}
				}
			});    
        } 
	});
}
function batchDelLook(ids){
	$.ajax({
		type: 'post',
		url: basePath+'goods/batchDelLook',
		data: {
			token: localStorage.token != undefined ? localStorage.token : "",
			lookIds: ids
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				window.location.href = "readed.html";  
			} else {
				mui.alert(data.msg);
			}
		}
	});    
}

