mui.init();
(function($, doc) {
	$.init();
	$.ready(function() {
		/**
		 * 获取对象属性的值
		 * 主要用于过滤三级联动中，可能出现的最低级的数据不存在的情况，实际开发中需要注意这一点；
		 * @param {Object} obj 对象
		 * @param {String} param 属性名
		 */
		var _getParam = function(obj, param) {
			return obj[param] || '';
		};
		//-----------------------------------------

		//-----------------------------------------
		//					//级联示例
		var cityPicker3 = new $.PopPicker({
			layer: 3
		});
		cityPicker3.setData(cityData3);
		var showCityPickerButton = doc.getElementById('showCityPicker3');
		var cityResult3 = doc.getElementById('showCityPicker3');
		showCityPickerButton.addEventListener('tap', function(event) {
			cityPicker3.show(function(items) {
				doc.getElementById('sysAreaId').value = items[2].value;
				cityResult3.innerText = "" + _getParam(items[0], 'text') + " " + _getParam(items[1], 'text') + " " + _getParam(items[2], 'text');
				//返回 false 可以阻止选择框的关闭
				//return false;
			});
		}, false);
		badnForm();
	});
})(mui, document);

function address() {
	if($("#autoA").hasClass("mui-active")) {
		console.log('是');
	} else {
		console.log('否');
	}
}

(function($, doc) {
	$.init({
		statusBarBackground: '#f7f7f7'
	});
}(mui, document));

function saveAddr(){
	var form = JSON.stringify(getFormJson($("#addr-form")));
	var url = "addr/saveAddr";
	if($('#actionType').val()==2){
		url = "addr/editAddr";
	}
	$.ajax({
		type: 'post',
		url: basePath+url,
		data: {
			token: localStorage.token != undefined ? localStorage.token : "",
			para: form
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


function badnForm(){
	
	var addr = jQuery.parseJSON(sessionStorage.addr);
	$('#clientReceiceAddrId').val(addr.clientReceiceAddrId);
	$('#actionType').val(sessionStorage.actionType);
	if($('#actionType').val()==2){
		$('#receiverPhone').val(addr.receiverPhone);
		$('#clientMemberId').val(addr.clientMemberId);
		$('#sysAreaId').val(addr.sysAreaId);
		$('#receiverName').val(addr.receiverName);
		$('#receiverPhone').val(addr.receiverPhone);
		$('#receiverAddress').val(addr.receiverAddress);
		$('#showCityPicker3').text(getAreaName());
	}
	
	//移除storage Key
	sessionStorage.removeItem("addr");
	sessionStorage.removeItem("actionType");
}

