function upLoad(){
	$("#upFile").click();
}
$(function() {
	$("#head_img_change").change(function() {　　　　　　
		var $file = $(this);　　　　　　
		var fileObj = $file[0];　　　　　　
		var windowURL = window.URL || window.webkitURL;　　　　　　
		var dataURL;　　　　　　
		var $img = $("#headimg");　　　　　　
		if(fileObj && fileObj.files && fileObj.files[0]) {　　　　　　　　
			dataURL = windowURL.createObjectURL(fileObj.files[0]);　　　　　　　　
			$img.attr('src', dataURL);　　　　　　
		} else {　　　　　　　　
			dataURL = $file.val();　　　　　　　　
			var imgObj = document.getElementById("headimg");　　　　　　　　 // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性再加入，无效；
			　　　　　　　　 // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
			imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";　　　　　　　　
			imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;　　　　　　
		}　　　　
	});
})