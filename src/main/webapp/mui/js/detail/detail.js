var goodsId, storeId, skuId;
goodsId = 15; //商品id
storeId = 1; // 店铺id
$(function() {
	//获取   基本信息
	BrowserType();
	loadSku(); // sku
});

/**
 * 加载基本信息
 */
function loadDetail() {
	$.ajax({
		type: 'post',
		url: basePath + 'detail/loadDetail',
		data: {
			id: goodsId
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			if(data.success) {
				var rs = jQuery.parseJSON(data.attributes.rs);
				console.log(rs);
				//页面赋值
				$("#name").html(rs.goodsName);
			} else {

			}
		}
	});
}

/**
 * loadSku
 */
function loadSku() {
	var token = localStorage.getItem("token");
	$.ajax({
		type: 'post',
		url: basePath + 'detail/loadSku',
		data: {
			id: goodsId,
			storeId: storeId,
			token: token
		},
		cache: false,
		async: false,
		dataType: 'JSONP',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(data) {
			// 展示价格开始
			var hei = 243;
			var price = 0;
			//console.log(data);
			var skuList = data[0].skuList;
			var toPageSkuList = data[1].toPageSkuList;
			var imgList = data[2].imgList;
			var detailRs = data[3].detailRs.attributes.rs;
			var count = data[4].count;
			console.log(count);
			//是否关注
			if(count == '1') {
				$("#collect").removeClass("mui-icon-extra-hetao-wdsc-default");
				$("#collect").addClass("mui-icon-extra-hetao-wdsc");
			}
			detailRs = jQuery.parseJSON(detailRs);
			console.log(detailRs);
			//页面赋值
			$("#name").html(detailRs.goodsName);
			$(".mui-content-padded").html(detailRs.remarks);
			console.log(detailRs.remarks);
			//console.log(imgList);
			//console.log(toPageSkuList);
			if(skuList.length > 1) {
				price = "￥" + skuList[0].price + " - " + skuList[skuList.length - 1].price
			} else {
				if(skuList.length != 0) {
					price = "￥" + skuList[0].price;
				} else {
					price = "暂无价格";
				}

			}
			$("#price").html(price);
			// 展示价格结束

			/**
			 *获取skuIds 开始
			 * 先排序 再 保存到document
			 */
			var div = document.getElementById("skuListDiv");
			var defaultSku = '';
			var defaultSkuName = ' ';
			for(var i = 0; i < skuList.length; i++) {
				var skuArry = new Array(skuList[i].attrSkuIds);
				var changeArry = skuArry.join(",").split(',');
				//console.log(changeArry.sort().join(","));
				// 创建 
				var input = document.createElement('input');
				input.id = changeArry.sort().join(",");
				input.value = skuList[i].price;
				input.alt = skuList[i].mallGoodsSkuId;
				div.appendChild(input);

				// 获取默认属性,价格
				if(skuList[i].isDefault == '1') {
					defaultSku = skuList[i].attrSkuIds;
					$("#skuPrice").html(skuList[i].price);
					skuId = skuList[i].mallGoodsSkuId;

				}
			}
			//获取skuIds 结束 

			// 填充sku
			$("#skuUl").empty();
			for(var i = 0; i < toPageSkuList.length; i++) {
				// 弹出层高度 
				hei += 76;
				var html = '';
				html += '<li class="mui-table-view" style="text-align:left;background-color: #FFFFFF;margin-top:10px ;">';
				html += '<p>' + toPageSkuList[i].name + '</p>';
				html += '<p id="sku' + toPageSkuList[i].id + '" alt="' + toPageSkuList[i].id + '">';
				var list = toPageSkuList[i].list;
				console.log(list);
				for(var j = 0; j < list.length; j++) {
					if(j == 0) {
						html += '<span style="margin-left:0%;">';
					} else {
						html += '<span style="margin-left:8%;">';
					}
					var tempSku = toPageSkuList[i].id + ":" + list[j].id;
					if(defaultSku.indexOf(tempSku) >= 0) {
						defaultSkuName += list[j].name + " ";
						html += '<button id="' + list[j].id + '" type="button" class="mui-btn mui-btn-outlined mui-btn-danger" onClick="skuClick(this)">';
					} else {
						html += '<button id="' + list[j].id + '" type="button" class="mui-btn mui-btn-outlined" onClick="skuClick(this)">';
					}

					html += list[j].name;
					html += '</button>';
					html += '</span>';
				}
				html += '</p>';
				html += '</li>';

				$("#skuUl").append(html);

				//
			}
			$("#selectedSku").append(defaultSkuName);
			//
			$("#picture").css("height", hei + "px");

			// imgList start
			var imgHtml = '';
			var imgDiv = '';
			for(var i = 0; i < imgList.length; i++) {
				imgHtml += ' <div class="mui-slider-item mui-slider-item-duplicate">';
				imgHtml += '<a>';
				imgHtml += '<img src="' + imgPath + imgList[i] + '">';
				imgHtml += '</a>';
				imgHtml += '</div>';
				//
				if(i == 0) {
					imgDiv += '<div class="mui-indicator mui-active"></div>';
				} else {
					imgDiv += '<div class="mui-indicator"></div>';
				}
			}
			$("#imgList").append(imgHtml);
			$("#imgDiv").append(imgDiv);

			/**
			 * 轮播图
			 */
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			var slider = mui("#slider");
			slider.slider({
				interval: 5000
			});

			// imgList end
		}
	});
}

/**
 * 属性按钮点击 
 * 1.当前颜色变成 选中
 * 2.同级下其他变成 非选中
 */
function skuClick(obj) {
	$(obj).parent().parent().find("button").each(function() {
		$(this).removeClass("mui-btn-danger");
	});

	if(!$(obj).hasClass("mui-btn-danger")) {
		$(obj).addClass("mui-btn-danger")
	}

	findCheckedBtn();

}

/**
 * 遍历所有 选中的按钮
 */
function findCheckedBtn() {
	var selectedSku = '';
	$("#skuUl").find("button").each(function() {
		if($(this).hasClass("mui-btn-danger")) {
			selectedSku += '  ' + $(this).html();
		}
	});
	$("#selectedSku").html(selectedSku);
	//  所有组合 都选中后 查询 sku
	// 获取skuIds
	var skuIds = new Array();
	$("p[id^='sku']").each(function(i) {
		var att = $(this).attr("alt");
		//console.log("att = " +att);
		$(this).find("button").each(function() {
			if($(this).hasClass("mui-btn-danger")) {
				console.log("this = " + $(this));
				skuIds.push(att + ":" + $(this).attr("id"));
			}
		});

	});
	//排序  
	skuIds = skuIds.sort();
	//console.log(skuIds.join(","));
	var input = document.getElementById(skuIds.join(","));
	if(input != null) {
		$("#skuPrice").html(input.value);
		skuId = input.alt;
	}

}

function shopShow(obj) {
	if(obj == 1) {
		$("#shopDiv").addClass("aui-active");
		$("#detailDiv").removeClass("aui-active");
		$("#detail").hide();
		$("#shop").show();
	} else {
		$("#detailDiv").addClass("aui-active");
		$("#shopDiv").removeClass("aui-active");
		$("#detail").show();
		$("#shop").hide();
	}
}

/**
 * 1 购物车
 * 2购买
 * @param {Object} obj
 */
function showSku(obj) {
	// 展示 sku
	if($(".mui-backdrop").is(":hidden")) {
		$(".mui-backdrop").show();
		$("#picture").slideToggle();
	} else {
		// 提交购买逻辑 1= 加入购物车 2= 立即购买
		var number = $("#number").val();
		switch(obj) {
			case 1:
				console.log('加入购物车');
				var sessionState = isLogin();
				/**
				 * 如果登陆状态下 加入购物车表
				 * 
				 */
				if(sessionState) {
					var token = localStorage.getItem("token");
					$.ajax({
						type: 'post',
						url: basePath + 'shopCar/add',
						data: {
							id: goodsId,
							storeId: storeId,
							skuId: skuId,
							number: number,
							token: token
						},
						cache: false,
						async: false,
						dataType: 'JSONP',
						contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
						success: function(data) {
							if(data.success) {
								mui.alert('添加成功');
								//window.location.href = 'shopCar.html';
							} else {
								console.log(data);
							}
						}
					});
				}
				// 非登陆状态下 加入session 
				else {

					var local = localStorage.getItem("detail_");
					console.log(local);
					if(local != null) {
						local = goodsId + ":" + storeId + ":" + skuId + ":" + number + ',' + local;
					} else {
						local = goodsId + ":" + storeId + ":" + skuId + ":" + number;
					}
					localStorage.setItem("detail_", local);
					//window.location.href = 'shopCar.html';
					//localStorage.removeItem("detail_");
					mui.alert('添加成功');
				}
				break;
			case 2:
				console.log('立即购买');
				break;
		}

	}

}

function hideSku() {
	$(".mui-backdrop").hide();
	$("#picture").slideToggle();
}