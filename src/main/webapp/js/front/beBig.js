$(function(){
	$.fn.magnifying = function(){
		var that = $(this),
		 $imgCon = that.find('.imgConter'),//正常图片容器
		 	$Img = $imgCon.children('img'),//正常图片，还有放大图片集合
		   $Drag = that.find('.imgConterM'),//拖动滑动容器
		   $show = that.find('.imgConterB'),//放大镜显示区域
		$showIMg = $show.find('img'),//放大镜图片
		$ImgList = $(".imgList li img"),//图片列表
		multiple = $show.width()/$Drag.width();
		
		$imgCon.mousemove(function(e){
			$Drag.css('display','block');
			$show.css('display','block');
		    //获取坐标的两种方法
		   	// var iX = e.clientX - this.offsetLeft - $Drag.width()/2,
		   	// 	iY = e.clientY - this.offsetTop - $Drag.height()/2,	
		   	var iX = e.pageX - $(this).offset().left - $Drag.width()/2,
		   		iY = e.pageY - $(this).offset().top - $Drag.height()/2,	
		   		MaxX = $imgCon.width()-$Drag.width(),
		   		MaxY = $imgCon.height()-$Drag.height();
				
  	   	    /*这一部分可代替下面部分，判断最大最小值
		   	var DX = iX < MaxX ? iX > 0 ? iX : 0 : MaxX,
		   		DY = iY < MaxY ? iY > 0 ? iY : 0 : MaxY;
		   	$Drag.css({left:DX+'px',top:DY+'px'});	   		
		   	$showIMg.css({marginLeft:-3*DX+'px',marginTop:-3*DY+'px'});*/

		   	iX = iX > 0 ? iX : 0;
		   	iX = iX < MaxX ? iX : MaxX;
		   	iY = iY > 0 ? iY : 0;
		   	iY = iY < MaxY ? iY : MaxY;	
		   	$Drag.css({left:iX+'px',top:iY+'px'});	   		
		   	$showIMg.css({marginLeft:-multiple*iX+'px',marginTop:-multiple*iY+'px'});
		   	//return false;
		});
		$imgCon.mouseout(function(){
		   	$Drag.css('display','none');
			$show.css('display','none');
		});
	
		$ImgList.click(function(){
			$Img.attr('src',$(this).attr("src"));
			$showIMg.attr('src',$(this).attr("src"));
			$(this).parent().addClass("on").siblings().removeClass("on");
		});	
	}

	$(".left").magnifying();
});