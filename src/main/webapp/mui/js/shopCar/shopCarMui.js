// mui 相关
(function($) {
	//第一个demo，拖拽后显示操作图标，点击操作图标删除元素；
	$('#OA_task_1').on('tap', '.mui-btn-red', function(event) {
		console.log(this);
		var elem = this;
		var li = elem.parentNode.parentNode;
		mui.confirm('确认删除该条记录？', '提示', btnArray, function(e) {
			if(e.index == 0) {
				li.parentNode.removeChild(li);
			/**
			 * 同步
			 */
			accountPay();
			loadStorage();
			} else {
				setTimeout(function() {
					$.swipeoutClose(li);
				}, 0);
			}
		});
	});
	var btnArray = ['确认', '取消'];
})(mui);