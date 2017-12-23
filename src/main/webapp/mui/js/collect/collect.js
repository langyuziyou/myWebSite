//
$(".mui-input-row").hide();
$(".aui-bar").hide();
function subM() {
	console.log(1);;
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
	$("#checkboxAll").click(function() {
		//全选 反选
		var ch = this.checked;
		console.log(ch);
		console.log(this.checked);
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

		console.log(l);
		if(l > 0) {
			styleChange(1);
		} else {
			styleChange(2);

		}

	});

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

					//this.checked = ch;
				});

			});
		}

	});
});