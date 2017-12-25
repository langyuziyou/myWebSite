//
$(".mui-input-row").hide();
$(".aui-bar").hide();
function subM() {
	console.log(1);;
}
function pullfresh(){
	var self = this;
	setTimeout(function() {
        var count = $('#page').val()+1;
        console.log(self);
        self.endPullUpToRefresh(true);
        data={
            page:count
        };
        type=2;//代表上拉加载
        //toList(data,type);//具体取数据的方法
    }, 100);
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

var btnArray = ['确认', '取消'];
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
		/*			var ch = this.checked;
					console.log(ch);
					console.log(this.checked);*/
		$("input[type=checkBox][name=sccheckbox]").each(function() {
			if(this.checked) {
				console.log(this.checked);
				isDel = true;
				return;
			}

			//this.checked = ch;
		});

		if(isDel) {
			mui.confirm('确认删除记录？', '收藏记录', btnArray, function(e) {
				if (e.index == 0) {
					var ids = new Array();
					$("input[type=checkBox][name=sccheckbox]").each(function() {
						if(this.checked) {
							ids.push($(this).val());
						}
					});
					batchDelCollect(ids.toString());
				}
			});
		}
	});
});



// 下拉更新
/*var scroll = new auiScroll({
	listen: true,
	distance: 10 //判断到达底部的距离，isToBottom为true
}, function(ret) {
	if(ret.isToBottom) {
		loadData();
		// document.getElementById("demo").textContent = "已到达底部";
	} else {
		//  document.getElementById("demo").textContent = "滚动高度："+ret.scrollTop;
	}
});*/

function loadData(){
	$.ajax({
		type: 'post',
		url: basePath+'goods/getCollectList',
		data: {
			token: localStorage.token != undefined ? localStorage.token : "",
			time:'2017-12-17 12:05:43'
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
					html+='<li class="mui-table-view-cell" style="">';
					html+='<div class="mui-slider-right mui-disabled">';
					html+='<a class="mui-btn mui-btn-red" onclick="deleteCollect('+list[i].mallGoodsCollectId+')">删除</a>';
					html+='</div>';
					html+='<div class="mui-slider-handle">';
					html+='<div class="mui-input-row mui-checkbox mui-left" style="float:left;display:inline;height:50px;width:30px;">';
					html+='<input name="sccheckbox" value="'+list[i].mallGoodsCollectId+'" type="checkbox" style="left:-1px;top: 20px;">';
					html+='</div>';
					html+='<a href="javascript:;">';
					html+='<img class="mui-media-object mui-pull-left" src="'+list[i].defaultImage+'">';
					html+='<div class="mui-media-body">';
					html+='<p class="mui-ellipsis" style="font-weight: bold;">'+list[i].title+'</p>';
					html+='<p class="pMoney" style="">￥'+list[i].price+'</p>';
					html+='</div>';
					html+='</a>';
					html+='</div>';
					html+='</li>';
				}
				$('#collectTab').append(html);
			} else {
				mui.alert(data.msg);
			}
		}
	}); 
}

function deleteCollect(id){
	mui.confirm('确认删除该记录？', '收藏记录', btnArray, function(e) {
		if (e.index == 0) {
            $.ajax({
				type: 'post',
				url: basePath+'goods/deleteCollect',
				data: {
					collectId: id,
					token: localStorage.token != undefined ? localStorage.token : ""
				},
				cache: false,
				async: false,
				dataType: 'JSONP',
				contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
				success: function(data) {
					if(data.success) {
						window.location.href = "collect.html";  
					} else {
						mui.alert(data.msg);
					}
				}
			});    
        } 
	});
}
function batchDelCollect(ids){
	$.ajax({
		type: 'post',
		url: basePath+'goods/batchDelCollect',
		data: {
			token: localStorage.token != undefined ? localStorage.token : "",
			collectIds: ids
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				window.location.href = "collect.html";  
			} else {
				mui.alert(data.msg);
			}
		}
	});    
}
