<%@ page language="java" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

	request.setAttribute("basePath", basePath);
%>

<div class="header">
		<div class="headerCon marginUp">
			<div class="logo">
				<a class="logoH" href="http://www.lipin-bj.cn/"></a>
				<div class="contact">				
				</div>
			</div>
			<div class="right">
			您好，欢迎来到古韵礼品网，买礼品首选礼品网站！ <div id="append_parent"></div>
&nbsp;&nbsp;&nbsp;&nbsp;
 <a href="http://www.lipin-bj.cn/user.php" rel="nofollow">登陆</a>&nbsp;/&nbsp;<a href="http://www.lipin-bj.cn/user.php?act=register" rel="nofollow">注册</a>
			&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="http://www.lipin-bj.cn/flow.php" rel="nofollow">购物车</a>
			&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="http://www.lipin-bj.cn/" rel="nofollow" onclick="window.external.addFavorite(this.href,this.title);return false;" title="古韵礼品网 - 礼品公司第一品牌">收藏本站</a>
			</div>	
            <div class="search">
				<form action="" method="get" name="advancedSearchForm" id="advancedSearchForm">
					<input type="text" class="input" id="keywords" value="" name="keywords" style="width:90px;">
					<select id="stype" name="stype">
					<option value="p">搜索礼品</option>
					<option value="a">搜索文章</option>
					</select>
					<input type="hidden" name="action" value="form">
					<input type="image" src="<%=basePath%>css/images/search_btn.png" class="btn">
				</form>
								
				<div class="keyword"><span class="text" "font:12px"="">&nbsp;热门：&nbsp;</span>
                                <a href="http://www.lipin-bj.cn/f-chuanyilipin/">创意礼品</a><span>|</span>
				<a href="http://www.lipin-bj.cn/f-shangwulipin/">商务礼品</a><span>|</span>
				<a href="http://www.lipin-bj.cn/f-xiaolipin/">小礼品</a><span>|</span>
				<a href="http://www.lipin-bj.cn/f-jiangpinlipin/">奖品</a>
				</div>
				
			</div>
		</div>
		
		<div class="headerNav">
			<ul>
			<li class="home"><a href="http://www.lipin-bj.cn/" rel="nofollow" class="active">首页</a></li>
			
						<li><a href="http://www.lipin-bj.cn/f-shangwulipin/">商务礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-xiaolipin/">小礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-guanggaolipin/">广告礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-gongyilipin/">工艺礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-gaodanglipin/">高档礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-teselipin/">特色礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-cuxiaolipin/">促销礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-shuijinglipin/">水晶礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-bangonglipin/">办公礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-jiajulipin/">家居礼品</a></li>
						<li><a href="http://www.lipin-bj.cn/f-kaiyelipin/">开业礼品</a></li>
									</ul>
			
				
			
						
			
					<div style="display:block;" id="leftDiv">
															
					<a href="http://www.lipin-bj.cn/f-qingdianlipin/">庆典礼品</a><span>|</span>				
					<a href="http://www.lipin-bj.cn/f-diqiuyi/">地球仪</a><span>|</span>						
					<a href="http://www.lipin-bj.cn/k-qiaoqianlipin/">乔迁</a><span>|</span>							
					<a href="http://www.lipin-bj.cn/f-gufaliuli/">古法琉璃</a><span>|</span>						
					<a href="http://www.lipin-bj.cn/f-dianzilipin/">电子礼品</a><span>|</span>					
					<a href="http://www.lipin-bj.cn/f-jianpai/">奖牌</a><span>|</span>						
					<a href="http://www.lipin-bj.cn/f-jianbei/">奖杯</a><span>|</span>							
					<a href="http://www.lipin-bj.cn/f-guci/">骨瓷</a><span>|</span>						
					<a href="http://www.lipin-bj.cn/k-hunqinglipin/">婚庆礼品</a><span>|</span>				
					<a href="http://www.lipin-bj.cn/f-ciqi/">瓷器</a><span>|</span>					
					<a href="http://www.lipin-bj.cn/f-tandiao/">炭雕</a><span>|</span>				
					<a href="http://www.lipin-bj.cn/f-chaju/">茶具</a><span>|</span>						
					<a href="http://www.lipin-bj.cn/f-lipinupan/">礼品u盘</a><span>|</span>			
					<a href="http://www.lipin-bj.cn/k-lipindingzhi/">定制礼品</a><span>|</span>		
					<a href="http://www.lipin-bj.cn/wd/">送礼</a><span>|</span>							
					<a href="http://www.lipin-bj.cn/tupianpindao/">礼品图片</a><span>|</span>				
					<a href="http://www.lipin-bj.cn/">礼品公司</a>										
					</div>
				
				
								
				
				
						
		</div>
        
	</div>
<div class="classBg" id="classBg"></div>
<div class="classBox" id="classBox">
</div>
<script language="javascript">
function sclass(){
	var filter = 0;
	Ajax.call('/class.php?act=sclass', filter, sclassResponse, 'GET', 'JSON');
}
/*function sclassResponse(result){
	document.getElementById('classBox').style.display = 'block';
	document.getElementById('classBox').innerHTML = result.content;
}
function sclassclose(){
	document.getElementById('class').style.display = 'none';
}*/
function closeClass(){
	document.getElementById("classBox").style.display="none";
	document.getElementById("classBg").style.display="none";
}
function sclassResponse(result){
	document.getElementById('classBox').innerHTML = result.content;
	var bg = document.getElementById("classBg");
	var box = document.getElementById("classBox");
	bg.style.display="block";
	box.style.display="block";
	box.style.left=((document.body.clientWidth-760+12)/2)+"px";
}
</script>
