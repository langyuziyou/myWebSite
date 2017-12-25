// 返回顶部
$(window).scroll(function() {
	var sc = $(window).scrollTop();
	var rwidth = $(window).width()
	if(sc > 500) {
		$("#goTopBtn").css("display", "block");
	} else {
		$("#goTopBtn").css("display", "none");
	}
})
$("#goTopBtn").click(function() {
	var sc = $(window).scrollTop();
	$('body,html').animate({
		scrollTop: 0
	}, 1000);
})

function returnTop() {
	var top = document.getElementById("btn-top");
	var bottom = document.getElementById("btn-top");
	top.onclick = function() {
		//alert(11);
		timer = setInterval(function() {
			var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
			var ispeed = Math.floor(-scrollTop / 6);
			console.log(ispeed)
			if(scrollTop == 0) {
				clearInterval(timer);
			}
			document.documentElement.scrollTop = document.body.scrollTop = scrollTop + ispeed;
		}, 30)
	};
}
// 搜索页面
function goSearch() {
	window.location.href = "search.html";
}

// 下拉更新
var x = 0;
var scroll = new auiScroll({
	listen: true,
	distance: 10 //判断到达底部的距离，isToBottom为true
}, function(ret) {
	if(x < 5) {
		if(ret.isToBottom) {
			x++;
			var table = document.getElementById("demoUi");
			for(var i = 0; i < 2; i++) {
				var li = document.createElement('li');
				li.className = 'mui-table-view-cell mui-media mui-col-xs-6';
				var html = '';
				html += '<a href="#"><img class="mui-media-object" src="../images/head/chanpin01.png">';
				html += '<div class="mui-content-padded">';
				html += '<p >OPPO 前后2000万 全面屏拍照手机';
				html += '</br>';
				html += '<span style="font-size: 8px;">￥ <span style="font-size: 15px;">1000</span></span>';
				html += '</p>';
				html += '</div>';
				html += '</a>';
				li.innerHTML = html;
				table.appendChild(li);
			}
			// document.getElementById("demo").textContent = "已到达底部";
		} else {
			//  document.getElementById("demo").textContent = "滚动高度："+ret.scrollTop;
		}
	}
});

// 滑动图片
mui.init({
	swipeBack: true //启用右滑关闭功能
});
var slider = mui("#slider");
slider.slider({
	interval: 5000
});