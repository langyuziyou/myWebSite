<%@ page language="java" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

	request.setAttribute("basePath", basePath);
%>
<div class="footer">
   <div align="center">
   <p>北京古韵华礼文化发展有限公司 版权所有 2008-2015 京ICP备12041176号-11 
   	<a href="http://www.cnzz.com/stat/website.php?web_id=1880666" target="_blank" title="站长统计">
   		<img border="0" hspace="0" vspace="0" src="<%=basePath%>css/images/pic.gif"></a>
   </p>
   <p>古韵礼品网 Copyright © www.lipin-bj.cn All Rights Reserved.
   <script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?e466d1f5c9b18d04d3e48bd876bff9cb";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script></p> 
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/front/view.css">
<script language="javascript">
// JavaScript Document
<!--
var delta=0.15;
var collection;
var closeB=false;
function floaters() 
{
  this.items=[];
  this.addItem=function(id,x,y,contentF,contentS)
  {
  	var str = "";
  	if(document.all){
  			str = '<IFRAME style="position:absolute; visibility:inherit; top:0px; left:0px; width:80px; z-index:-1;opacity:0;filter: Alpha(Opacity=0); -moz-opacity: 0;" src="javascript:void(0);" frameBorder="0" scrolling="no"></IFRAME>';
  	}
  	if(choose())
    {    	
    	document.write('<DIV  kk="kk" id='+id+' style="Z-INDEX:10;POSITION:absolute;width:150px;left:'+(typeof(x)=='string'?eval(x):x)+';top:'+(typeof(y)=='string'?eval(y):y)+'">'+str+contentF+'</DIV>');
    }else{
    	document.write('<DIV id='+id+' style="Z-INDEX:10;POSITION:absolute;width:150px;left:'+(typeof(x)=='string'?eval(x):x)+';top:'+(typeof(y)=='string'?eval(y):y)+'">'+str+contentS+'</DIV>');
    }     
    var newItem={};
    newItem.object=document.getElementById(id);
    newItem.x=x;
    newItem.y=y;
    newItem.close = false;
    newItem.name = id;
    this.items[this.items.length]=newItem;
  }
  this.play=function()
  {
     collection=this.items
     setInterval('play()',10);
  }
}
  function play()
  {
    for(var i=0;i<collection.length;i++)
   {
   	 if(collection[i].close){	
	     collection[i].object.style.display = 'none';
	     continue;
     }
    var followObj=collection[i].object;
    var followObj_x=(typeof(collection[i].x)=='string'?eval(collection[i].x):collection[i].x);
    var followObj_y=(typeof(collection[i].y)=='string'?eval(collection[i].y):collection[i].y);
    if(followObj.offsetLeft!=(getScrollXY().x+followObj_x))
    {
     var dx=(getScrollXY().x+followObj_x-followObj.offsetLeft)*delta;
     dx=(dx>0?1:-1)*Math.ceil(Math.abs(dx));
     followObj.style.left=followObj.offsetLeft+dx+"px";
    }
    if(followObj.offsetTop!=(getScrollXY().y+followObj_y)) 
    {
    	var dy=(getScrollXY().y+followObj_y-followObj.offsetTop)*delta;
        dy=(dy>0?1:-1)*Math.ceil(Math.abs(dy));
        followObj.style.top=followObj.offsetTop+dy+"px";
    }
    followObj.style.display = '';
   }
  }
  function getScrollXY(){
	var x,y;
	if(document.body.scrollTop){
	  x=document.body.scrollLeft;
	  y=document.body.scrollTop;
	}
	else{
	  x=document.documentElement.scrollLeft;
	  y=document.documentElement.scrollTop;
	}
	return {x:x,y:y};
	} 
  function closeBanner(name)
  {
    for(var i=0;i<collection.length;i++)
    {
     if(collection[i].name == name){	
     collection[i].object.style.display = 'none';
     collection[i].close = true;
     return;
     }
    }
  }
  function cls()
  {  	
  }
  function choose()
  {
  	var number=Math.ceil(Math.random()*10);
    number=number%2;
    if(number==0)return true;
    else return false;
  }
	function correctPNG(myImage) // correctly handle PNG transparency in Win IE 5.5 & 6.
{
    var arVersion = navigator.appVersion.split("MSIE")
    var version = parseFloat(arVersion[1])
    if ((version >= 5.5) && (document.body.filters))
    {
     		var imgID = (myImage.id) ? "id='" + myImage.id + "' " : "";
		    var imgClass = (myImage.className) ? "class='" + myImage.className + "' " : "";
		    var imgTitle = (myImage.title) ?  "title='" + myImage.title  + "' " : "title='" + myImage.alt + "' ";
		    var imgStyle = "display:inline-block;" + myImage.style.cssText;
		    if (myImage.align == "left") imgStyle = "float:left;" + imgStyle;
        if (myImage.align == "right") imgStyle = "float:right;" + imgStyle;
        if (myImage.parentElement.href) imgStyle = "cursor:pointer;" + imgStyle;
		    var strNewHTML = "<span " + imgID + imgClass + imgTitle
		                  + " style=\"" + "width:" + myImage.width 
		                  + "px; height:" + myImage.height 
		                  + "px;" + imgStyle + ";"
		                  + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
		                  + "(src=\'" + myImage.src + "\', sizingMethod='scale');\"></span>";
		    myImage.outerHTML = strNewHTML;  
    }    
} 

//-->
</script>

</div> 


<div>


<script type="text/javascript">
var goods_id = 8180;
var goodsattr_style = 1;
var gmt_end_time = 0;
var day = "天";
var hour = "小时";
var minute = "分钟";
var second = "秒";
var end = "结束";
var goodsId = 8180;
var now_time = 1506322399;
onload = function(){
  fixpng();
  try {onload_leftTime();}
  catch (e) {}
}
/**
 * 接收返回的信息
 */
function changePriceResponse(res)
{
  if (res.err_msg.length > 0)
  {
    alert(res.err_msg);
  }
  else
  {
    document.forms['ECS_FORMBUY'].elements['number'].value = res.qty;
    if (document.getElementById('ECS_GOODS_AMOUNT'))
      document.getElementById('ECS_GOODS_AMOUNT').innerHTML = res.result;
  }
}
</script>
</div>
</div>