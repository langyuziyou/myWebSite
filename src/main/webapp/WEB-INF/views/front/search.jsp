<%@ page language="java" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>如意中秋礼盒套装 -古韵礼品网(www.lipin-bj.cn)</title>
<meta name="Description" content="古韵礼品网(www.lipin-bj.cn)提供如意中秋礼盒套装,市场价格:1790元,古韵网价格:1180元,可个性定制免费设计,批量订购价格更低更优惠！">
<meta name="keywords" content="如意中秋礼盒套装">
<!--<base href="http://www.lipin-bj.cn/">--><base href="."> 
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/front/layout.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/front/content.css">
<!--[if lte IE 7]><link rel="stylesheet" href="/themes/guyun/style/ie7.css" type="text/css"/><![endif]-->
<!--[if lte IE 6]><link rel="stylesheet" href="/themes/guyun/style/ie6.css" type="text/css"/><![endif]-->
<script src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script src="<%=basePath%>js/front/hm.js"></script>
<script type="text/javascript" src="<%=basePath%>js/front/nav.js"></script>
<script type="text/javascript" src="<%=basePath%>js/front/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/front/transport.js"></script>
<script type="text/javascript" src="<%=basePath%>js/front/utils.js"></script>
<script type="text/javascript"> 
var process_request = "正在处理您的请求...";
</script>
</head>
<body style="cursor: auto;">
<script type="text/javascript">
var process_request = "正在处理您的请求...";
</script>
<!-- header
	作者：54087810@qq.com
	时间：2017-09-26
	描述：
-->
<jsp:include page="/WEB-INF/views/front/header.jsp" flush="true"></jsp:include>  


<div class="navline">
您的位置：<a href="http://www.lipin-bj.cn/">古韵礼品网</a> &gt; 高级搜索</div>


<div class="content">
	
		<div class="giftBox">
			<div class="giftTop"><span>高级搜索</span></div>
			<div class="giftCon">
			
			<form action="http://www.lipin-bj.cn/search.php" method="get" name="advancedSearchForm" id="advancedSearchForm">
		<table border="0" align="center" cellpadding="3" style="width:700px;">
		  <tbody><tr>
			<td width="166" align="right" valign="top">关键字：</td>
			<td width="516">
			  <input name="keywords" id="keywords" type="text" size="40" maxlength="120" class="inputBg" value="">			</td>
		  </tr>
		  <tr>
			<td align="right">分类：</td>
			<td><select name="category" id="select" style="border:1px solid #ccc;">
				<option value="0">所有分类</option><option value="26">奖品礼品</option><option value="92">&nbsp;&nbsp;&nbsp;&nbsp;奖杯</option><option value="378">&nbsp;&nbsp;&nbsp;&nbsp;奖牌</option><option value="104">开业礼品</option><option value="139">&nbsp;&nbsp;&nbsp;&nbsp;庆典礼品</option><option value="180">&nbsp;&nbsp;&nbsp;&nbsp;开业庆典礼品</option><option value="46">办公礼品</option><option value="10">&nbsp;&nbsp;&nbsp;&nbsp;名片夹</option><option value="60">&nbsp;&nbsp;&nbsp;&nbsp;便签纸</option><option value="47">&nbsp;&nbsp;&nbsp;&nbsp;记事本</option><option value="51">&nbsp;&nbsp;&nbsp;&nbsp;礼品笔</option><option value="18">水晶礼品</option><option value="25">&nbsp;&nbsp;&nbsp;&nbsp;水晶工艺品</option><option value="23">&nbsp;&nbsp;&nbsp;&nbsp;水晶模型</option><option value="20">&nbsp;&nbsp;&nbsp;&nbsp;水晶奖杯</option><option value="19">&nbsp;&nbsp;&nbsp;&nbsp;水晶奖牌</option><option value="211">&nbsp;&nbsp;&nbsp;&nbsp;水晶摆件</option><option value="36">促销礼品</option><option value="389">&nbsp;&nbsp;&nbsp;&nbsp;创意礼品</option><option value="155">&nbsp;&nbsp;&nbsp;&nbsp;修容礼品</option><option value="412">特色礼品</option><option value="379">&nbsp;&nbsp;&nbsp;&nbsp;生肖礼品</option><option value="114">&nbsp;&nbsp;&nbsp;&nbsp;外事礼品</option><option value="7">皮具礼品</option><option value="52">&nbsp;&nbsp;&nbsp;&nbsp;男包</option><option value="53">&nbsp;&nbsp;&nbsp;&nbsp;女包</option><option value="112">&nbsp;&nbsp;&nbsp;&nbsp;包包</option><option value="80">数码礼品</option><option value="241">&nbsp;&nbsp;&nbsp;&nbsp;电子礼品</option><option value="90">&nbsp;&nbsp;&nbsp;&nbsp;数码相框</option><option value="82">&nbsp;&nbsp;&nbsp;&nbsp;礼品u盘</option><option value="411">高档礼品</option><option value="83">&nbsp;&nbsp;&nbsp;&nbsp;金属工艺品</option><option value="69">&nbsp;&nbsp;&nbsp;&nbsp;贵金属礼品</option><option value="102">&nbsp;&nbsp;&nbsp;&nbsp;收藏品</option><option value="33">&nbsp;&nbsp;&nbsp;&nbsp;玉石</option><option value="31">工艺礼品</option><option value="78">&nbsp;&nbsp;&nbsp;&nbsp;木制工艺品</option><option value="32">&nbsp;&nbsp;&nbsp;&nbsp;琉璃工艺品</option><option value="35">&nbsp;&nbsp;&nbsp;&nbsp;工艺品</option><option value="238">广告礼品</option><option value="413">&nbsp;&nbsp;&nbsp;&nbsp;广告小礼品</option><option value="66">小礼品</option><option value="88">&nbsp;&nbsp;&nbsp;&nbsp;水杯</option><option value="414">&nbsp;&nbsp;&nbsp;&nbsp;促销小礼品</option><option value="40">商务礼品</option><option value="72">&nbsp;&nbsp;&nbsp;&nbsp;节日礼品</option><option value="41">&nbsp;&nbsp;&nbsp;&nbsp;高档商务礼品</option><option value="111">&nbsp;&nbsp;&nbsp;&nbsp;瓷器</option><option value="42">&nbsp;&nbsp;&nbsp;&nbsp;茶具</option><option value="113">&nbsp;&nbsp;&nbsp;&nbsp;会议礼品</option></select>			</td>
		  </tr>
		  <tr>
			<td align="right">品牌：</td>
			<td><select name="brand" id="brand" style="border:1px solid #ccc;">
				<option value="0">所有品牌</option>
				<option value="26">礼想家</option><option value="24">毕加索</option><option value="22">皮尔·卡丹</option><option value="19">潘祥记</option><option value="18">稻香村</option><option value="17">花花公子</option><option value="15">肖邦</option><option value="14">迪斯尼</option><option value="9">英国保罗 POLOMEISDO</option><option value="13">伊莱克斯</option><option value="10">派克</option><option value="11">爱国者</option><option value="8">梦特娇</option><option value="12">爱尔兰保罗</option><option value="21">777</option><option value="16">时代华纳猫和老鼠</option><option value="23">洁祺</option><option value="20">宫颐府</option>			  </select>			</td>
		  </tr>
		  <tr>
			<td align="right">价格：</td>
			<td><input name="min_price" type="text" id="min_price" class="inputBg" value="0" size="10" maxlength="8">
			  -
			  <input name="max_price" type="text" id="max_price" class="inputBg" value="0" size="10" maxlength="8">			</td>
		  </tr>
		  		  	
		  	
		  <tr>
			<td align="center"><input type="hidden" name="action" value="form"></td>
		    <td align="left"><input type="submit" name="Submit" class="button" value=" 立即搜索 "></td>
		    <td align="center">&nbsp;</td>
		    <td align="center">&nbsp;</td>
		  </tr>
		</tbody></table>
	  </form>
			
			</div>
			<div class="giftTitle">
				<span class="giftName">搜索结果</span>
				<ul class="pagination">
 				&nbsp;
				</ul>
			</div>
			<div class="giftListBox">
				<ul class="productList">
				  					<li>
						<a href="http://www.lipin-bj.cn/lipin/8202" target="_blank"><img src="" alt="梅开五福吉祥平安扣"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8202" target="_blank">梅开五福吉祥平安扣</a></span>
						<span>价格：<font>1580元</font></span>
					</li>
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8201" target="_blank"><img src="" alt="富贵牡丹花开富贵平安扣玉石摆件"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8201" target="_blank">富贵牡丹花开富贵平安扣玉石摆件</a></span>
						<span>价格：<font>1580元</font></span>
					</li>
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8200" target="_blank"><img src="" alt="百福笔筒,百福图琉璃旋转笔筒"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8200" target="_blank">百福笔筒,百福图琉璃旋转笔筒</a></span>
						<span>价格：<font>298元</font></span>
					</li>
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8199" target="_blank"><img src="" alt="兰亭序旋转琉璃笔筒"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8199" target="_blank">兰亭序旋转琉璃笔筒</a></span>
						<span>价格：<font>298元</font></span>
					</li>
					<li class="line"></li>									<li>
						<a href="http://www.lipin-bj.cn/lipin/8198" target="_blank"><img src="" alt="马到成功旋转琉璃笔筒"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8198" target="_blank">马到成功旋转琉璃笔筒</a></span>
						<span>价格：<font>298元</font></span>
					</li>
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8197" target="_blank"><img src="" alt="论语琉璃笔筒镇尺组合 马到成功琉璃笔筒镇尺套装"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8197" target="_blank">论语琉璃笔筒镇尺组合 马到成功琉璃笔筒镇尺套装</a></span>
						<span>价格：<font>398元</font></span>
					</li>
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8196" target="_blank"><img src="" alt="招财金龙鱼"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8196" target="_blank">招财金龙鱼</a></span>
						<span>价格：<font>598元</font></span>
					</li>
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8195" target="_blank"><img src="" alt="五福临门琉璃瓶"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8195" target="_blank">五福临门琉璃瓶</a></span>
						<span>价格：<font>798元</font></span>
					</li>
					<li class="line"></li>									<li>
						<a href="http://www.lipin-bj.cn/lipin/8194" target="_blank"><img src="" alt="梅开五福珐珐彩琉璃花瓶"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8194" target="_blank">梅开五福珐珐彩琉璃花瓶</a></span>
						<span>价格：<font>1398元</font></span>
					</li>
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8193" target="_blank"><img src="" alt="中秋茶礼,中秋花好月圆茶礼"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8193" target="_blank">中秋茶礼,中秋花好月圆茶礼</a></span>
						<span>价格：<font>296元</font></span>
					</li>
					
														<li>
						<a href="http://www.lipin-bj.cn/lipin/8163" target="_blank"><img src="" alt="花香蝶自来宫廷绣中国画艺术抱枕枕套"></a>
						<span class="two-line"><a href="http://www.lipin-bj.cn/lipin/8163" target="_blank">花香蝶自来宫廷绣中国画艺术抱枕枕套</a></span>
						<span>价格：<font>260元</font></span>
					</li>
					<li class="line"></li>								</ul>
				<ul class="pagination" id="page-table">
				
				
<form name="selectPageForm" action="http://www.lipin-bj.cn/search.php" method="get">


 <div id="pager" class="pagebar">
  <span class="f_l f6" style="margin-right:10px;">总计 <b>6008</b>  个记录</span>
                      <span class="page_now">1</span>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=2">[2]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=3">[3]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=4">[4]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=5">[5]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=6">[6]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=7">[7]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=8">[8]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=9">[9]</a>
                      <a href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=10">[10]</a>
            
  <a class="next" href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=2">下一页</a>  <a class="last" href="http://www.lipin-bj.cn/search.php?keywords=&amp;category=0&amp;brand=0&amp;sort=goods_id&amp;order=DESC&amp;min_price=0&amp;max_price=0&amp;action=form&amp;intro=&amp;goods_type=0&amp;sc_ds=0&amp;outstock=0&amp;page=151">...最末页</a>                      <input type="hidden" name="keywords" value="">
                            <input type="hidden" name="category" value="0">
                          <input type="hidden" name="brand" value="0">
                          <input type="hidden" name="sort" value="goods_id">
                          <input type="hidden" name="order" value="DESC">
                          <input type="hidden" name="min_price" value="0">
                          <input type="hidden" name="max_price" value="0">
                          <input type="hidden" name="action" value="form">
                          <input type="hidden" name="intro" value="">
                          <input type="hidden" name="goods_type" value="0">
                          <input type="hidden" name="sc_ds" value="0">
                          <input type="hidden" name="outstock" value="0">
              <kbd style="float:left; margin-left:8px; position:relative; bottom:3px;"><input type="text" name="page" onkeydown="if(event.keyCode==13)selectPage(this)" size="3" class="B_blue"></kbd>
    </div>


</form>
<script type="Text/Javascript" language="JavaScript">
<!--

function selectPage(sel)
{
  sel.form.submit();
}

//-->
</script>
			  </ul>
			</div>

			<div class="giftbottom"></div>
			
		</div>
		
		<div class="rightBox">
		
	
		    <ul class="rightTab">
      <li class="act" id="rightTab11"><a onmouseover="rightTabTurn1(1,2);">推荐礼品</a></li>
      <li class="normal" id="rightTab12"><a onmouseover="rightTabTurn1(2,2);">热销礼品</a></li>
    </ul>
    <!--[if lte IE 6]><div><![endif]-->
    <div class="boxCon" id="rightDiv11" style="display:block">
      <ul class="hotList">
 				      </ul>
    </div>
    <div class="boxCon" id="rightDiv12" style="display:none">
      <ul class="hotList">
				      </ul>
    </div>
    <!--[if lte IE 6]></div><![endif]-->	
		</div> 
</div>

<div class="content">

	
<div id="footer"> 
	<img src="<%=basePath%>css/images/company.png" style="float:left" ;="" alt="北京古韵礼品公司联系方式">
	<div class="help">
						<dl class="help_menu_item_1">
		  <dt>新手指南</dt>
		  		  <dd><a href="http://www.lipin-bj.cn/help/10/" rel="nofollow" title="注册新用户">注册新用户</a></dd>
		  		  <dd><a href="http://www.lipin-bj.cn/help/11/" rel="nofollow" title="订购流程">订购流程</a></dd>
		  		</dl>
				<dl class="help_menu_item_2">
		  <dt>如何支付</dt>
		  		  <dd><a href="http://www.lipin-bj.cn/help/1/" rel="nofollow" title="付款方式">付款方式</a></dd>
		  		  <dd><a href="http://www.lipin-bj.cn/help/2/" rel="nofollow" title="定制礼品支付">定制礼品支付</a></dd>
		  		</dl>
				<dl class="help_menu_item_3">
		  <dt>礼品定制流程</dt>
		  		  <dd><a href="http://www.lipin-bj.cn/help/3/" rel="nofollow" title="如何定制">如何定制</a></dd>
		  		  <dd><a href="http://www.lipin-bj.cn/help/21/" rel="nofollow" title="定制礼品常见问题">定制礼品常见问题</a></dd>
		  		</dl>
				<dl class="help_menu_item_4">
		  <dt>购物配送</dt>
		  		  <dd><a href="http://www.lipin-bj.cn/help/18/" rel="nofollow" title="配送说明">配送说明</a></dd>
		  		  <dd><a href="http://www.lipin-bj.cn/help/19/" rel="nofollow" title="配送方式">配送方式</a></dd>
		  		  <dd><a href="http://www.lipin-bj.cn/help/20/" rel="nofollow" title="配送时间">配送时间</a></dd>
		  		</dl>
				<dl class="help_menu_item_5">
		  <dt>帮助中心</dt>
		  		  <dd><a href="http://www.lipin-bj.cn/help/4/" rel="nofollow" title="关于我们">关于我们</a></dd>
		  		  <dd><a href="http://www.lipin-bj.cn/help/5/" rel="nofollow" title="厂家加盟">厂家加盟</a></dd>
		  		  <dd><a href="http://www.lipin-bj.cn/help/23/" rel="nofollow" title="联系我们">联系我们</a></dd>
		  		</dl>
					</div>
</div>
</div>
<!--<div class="footer"> &copy; 2005-2017  版权所有，并保留所有权利。 </a><br />-->
<jsp:include page="/WEB-INF/views/front/footer.jsp" flush="true"></jsp:include>  


</body>
</html>