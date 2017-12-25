/**
 * 主要业务逻辑
 * 加载数据
 */
var l = 0;
$(function() {
	//是否登陆状态下
	var sessionState = isLogin();
	//console.log(login);
	//登陆 状态下
	if(sessionState) {
		showShopCar('shopCarHasSession');
	}
	// 未登陆状态 
	else {
		showShopCar('shopCarNosession');
	}

});

/**
 * 购物车展示逻辑
 */
function showShopCar(url) {
	var local = localStorage.getItem("detail_");
	var token = localStorage.getItem("token");
	//localStorage.removeItem("token");
	$.ajax({
		type: 'post',
		url: basePath + 'shopCar/' + url,
		data: {
			local: local,
			token: token
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			console.log(data[0].list);
			var list = data[0].list;
			isListEmpty(list);
/*			if(list.length < 1) {
				var img = '<img id="emptyImg" src="../images/empty/gouwuche.png" style="width:100%;"/> ';
				$(".mui-content").append(img);
				$(".mui-table-view").hide();
				$(".hetaoFoot").hide();
				$("#edit").hide();
			} else {
				$("#emptyImg").hide();
				$(".mui-table-view").show();
				$(".hetaoFoot").show();
				$("#edit").show();
			}*/
			//$("#OA_task_1").empty();
			for(var i = 0; i < list.length; i++) {
				var html = '';
				html += ' <li class="mui-table-view-cell" id="' + list[i].id + '"> ';
				html += '<div class="mui-slider-right mui-disabled">';
				html += '<a class="mui-btn mui-btn-red">删除</a>';
				html += '</div>';
				html += '<div class="mui-slider-handle">';
				html += '<div class="mui-input-row mui-checkbox mui-left" style="float:left;display:inline;height:50px;width:30px;">';
				html += '<input name="sccheckbox" value="1" type="checkbox" style="left:-1px;top: 20px;">';
				html += '</div>';
				html += '<a href="javascript:;">';
				html += '<img class="mui-media-object mui-pull-left" src="' + imgPath + list[i].defaultImage + '">';
				if(list[i].stock == 0) {
					html += '<em class="imgStr">暂时无货</em>';
				}

				html += '<div class="mui-media-body">';
				html += '<p class="mui-ellipsis" style="font-weight: bold;">' + list[i].goodsName + '</p>';
				html += '<p class="mui-ellipsis">' + list[i].skuName + '</p>';
				html += '<p class="pMoney" style="">￥<span class="price">' + list[i].price + '</span></p>';
				html += '<div class="mui-numbox" data-numbox-min="1" style="float: right;max-height: 30px;">';
				if(list[i].stock == 0) {
					html += '<button class="mui-btn mui-btn-numbox-minus" type="button" style="width:30px;" >-</button>';
					html += '<span class="mui-input-numbox" type="number" value="0"  style="margin-left: -17px;margin-top:3px;">0</span>';
					html += '<button class="mui-btn mui-btn-numbox-plus" type="button" style="width:30px;" >+</button>';
				} else {
					html += '<button class="mui-btn mui-btn-numbox-minus" type="button" style="width:30px;" onClick="reduce(this);">-</button>';
					html += '<input class="mui-input-numbox" type="number" value="' + list[i].number + '" onChange="inNumber(this);" style="margin-left: -17px;">';
					html += '<button class="mui-btn mui-btn-numbox-plus" type="button" style="width:30px;" onClick="add(this);">+</button>';
				}

				html += '</div>';
				html += '</div>';
				html += '</a>';
				html += '</div>';
				html += '</li>';

				$("#OA_task_1").append(html);
			}
			//
			mui.init();
			//监听  点击 如果有选中 
			$("input[type=checkBox][name=sccheckbox]").click(function() {
				//获取 计算
				accountPay();
			});

			//del
			$("#del").click(function() {
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
					mui.confirm('确认删除记录？', '购物车', btnArray, function(e) {
						$("input[type=checkBox][name=sccheckbox]").each(function() {
							if(this.checked) {
								var elem = this.parentNode;
								var li = elem.parentNode.parentNode;
								console.log(e);
								if(e.index == 0) {
									li.parentNode.removeChild(li);
								} else {
									setTimeout(function() {
										$.swipeoutClose(li);
									}, 0);
								}
								console.log(this.checked);
								isDel = true;
								return;
							}
						});

					});
				}
			});

		}
	});
}

/**
 * 计算
 */
function accountPay() {
	var pay = 0;
	$("input[type=checkBox][name=sccheckbox]").each(function() {
		if(this.checked == true) {
			// 获取 数量和价格
			var li = $(this).parents('li');
			var number = li.find('.mui-input-numbox');
			number = number.val();
			//console.log(parseInt(number));
			var price = li.find('.price');
			price = parseFloat(price.html());
			var amount = parseFloat((price * 1000) * number / 1000);
			//console.log(pay);
			pay = parseFloat(pay) + parseFloat((amount * 1000) / 1000);

		}
	});
	if(pay != 0) {
		$("#paybupay").show();
		styleChange(1);
	} else {
		$("#paybupay").hide();
		styleChange(2);
	}
	$("#pay").html(pay.toFixed(2));

}

/***
 * 输入数量
 */
function inNumber(obj) {
	accountPay();
	loadStorage();
}

/****
 * 加减
 */
function add(obj) {
	var number = $(obj).prev().val();
	number = parseInt(number);
	$(obj).prev().val(number + 1);
	accountPay();
	loadStorage();
}

function reduce(obj) {
	var number = $(obj).next().val();
	number = parseInt(number);
	var min = parseInt($(obj).parent().attr("data-numbox-min"));
	min = parseInt(min);
	if(number - 1 > min) {
		$(obj).next().val(number - 1);
	} else {
		$(obj).next().val(min);
	}
	accountPay();
	loadStorage();
}

/**
 * 更新当前缓存
 */
function loadStorage() {
	var local = '';
	$("input[type=checkBox][name=sccheckbox]").each(function(index, element) {
		// 获取 数量和价格
		var li = $(this).parents('li');
		var id = li.attr("id");
		var number = li.find('.mui-input-numbox');
		number = number.val();
		if(number == '') {
			number = li.find('.mui-input-numbox').html();
		}
		if(index == 0) {
			local = id + ":" + number;
		} else {
			local = local + "," + id + ":" + number;
		}
	});
	var sessionState = isLogin();
	/***
	 * 登陆需要同步到redis
	 * 
	 */
	if(sessionState) {
		var token = localStorage.getItem("token");
		$.ajax({
			type: 'post',
			url: basePath + 'shopCar/loadStorage',
			data: {
				local: local,
				token: token
			},
			cache: false,
			async: false,
			dataType: 'JSONP',
			contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
			success: function(data) {
				if(data.success) {
					console.log(data);
				} else {
					console.log(data);
				}
			}
		});

	}
	/**
	 * 非登陆，同步到 localStorage
	 */
	else {
		localStorage.setItem("detail_", local);
	}

}

/***
 * 提交
 */
function subM() {
	var v = $("#edit").html();
	if(v == "完成") {
		//删除
		mui.confirm('确认删除该条记录？', '提示', btnArray, function(e) {
			if(e.index == 0) {
				/**
				 * 遍历选中的数据，删除
				 */
				$("input[type=checkBox][name=sccheckbox]").each(function() {
					if(this.checked == true) {
						// 获取 数量和价格
						var li = $(this).parents('li');
						li.remove();
					}
				});
				/**
				 * 同步
				 */
				accountPay();
				loadStorage();

			} else {

			}
		});

	} else {
		//结算

	}

	console.log(1);
}

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

/**
 * 切换 编辑 还是完成
 */
function edit() {
	var v = $("#edit").html();
	if(v == "完成") {
		//
		$("#edit").html("编辑");
		$("#last_hide").html("去结算");
		$("#last_buy").html("去结算");

	} else {
		//
		$("#edit").html("完成");
		$("#last_hide").html("删除");
		$("#last_buy").html("删除");
	}
}
// 删除
var btnArray = ['确认', '取消'];
$(function() {
	$("#checkboxAll").click(function() {
		//全选 反选
		var ch = this.checked;
		console.log(ch);
		console.log(this.checked);
		$("input[type=checkBox][name=sccheckbox]").each(function() {
			this.checked = ch;
		});
		accountPay();
		//
	});

});

/***
 * 是否有数据
 */
function isListEmpty(list) {
	if(list.length < 1) {
		var img = '<img id="emptyImg" src="../images/empty/gouwuche.png" style="width:100%;"/> ';
		$(".mui-content").append(img);
		$(".mui-table-view").hide();
		$(".hetaoFoot").hide();
		$("#edit").hide();
	} else {
		$("#emptyImg").hide();
		$(".mui-table-view").show();
		$(".hetaoFoot").show();
		$("#edit").show();
	}
}