/* 
 * 描述：判断浏览器信息 
 * 编写：LittleQiang_w 
 * 日期：2016.1.5 
 * 版本：V1.1 
 */
//判断当前浏览类型  
function BrowserType() {
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串 
	var isQQMobile = userAgent.indexOf("QQBrowser") > -1; //判断是否QQ浏览器  
	var isBaiduApp = userAgent.indexOf("baiduboxapp") > -1; //判断是否百度APP浏览器  
	if(isQQMobile) {
		$("#shareBtn").hide();
	}
	if(isBaiduApp) {
		$("#shareBtn").hide();
	}
}

document.getElementById('shareBtn').addEventListener('click', function() {
	soshm.popIn({
		title: '小米',
		sites: ['qzone', 'qq', 'weixin']
	});
}, false);