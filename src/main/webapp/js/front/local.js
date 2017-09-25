// JavaScript Document
//首页导航用
/*function ResumeError() {
return true;
}*/
//window.onerror = ResumeError;
//-----------------------------------
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("' + objName + '")');}else if(document.layers){return eval("document.layers['" + objName +"']");}else{return eval('document.all.' + objName);}}
function Show_TabMenuForArea(tabscount,tabName,tabadid_num,tabadnum){
	var tabscount=parseInt(tabscount);
	if(!Number(tabscount)){return false;}
	for(var i=1;i<=tabscount;i++){GetObj("submenu"+tabscount+"_"+tabName+"_"+tabadid_num+i).style.display="none";}
	for(var i=1;i<=tabscount;i++){GetObj("menu"+tabscount+"_"+tabName+"_"+tabadid_num+i).className="normal";}
	GetObj("menu"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).className="act";
	GetObj("submenu"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).style.display="block";
}

//---------------------------------
function GetObja(objName){if(document.getElementById){return eval('document.getElementById("' + objName + '")');}else if(document.layers){return eval("document.layers['" + objName +"']");}else{return eval('document.all.' + objName);}}
function Show_TabMenuForAreaa(tabscount,tabName,tabadid_num,tabadnum){
	var tabscount=parseInt(tabscount);
	if(!Number(tabscount)){return false;}
	for(var i=1;i<=tabscount;i++){GetObja("submenu"+tabscount+"_"+tabName+"_"+tabadid_num+i).style.display="none";}
	for(var i=1;i<=tabscount;i++){GetObja("menu"+tabscount+"_"+tabName+"_"+tabadid_num+i).className="menu"+tabscount+"TabsOff_"+tabName;}
	GetObja("menu"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).className="menu"+tabscount+"TabsOn_"+tabName;
	GetObja("submenu"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).style.display="block";
}

//----------------------------------------
function GetObjb(objName){if(document.getElementById){return eval('document.getElementById("' + objName + '")');}else if(document.layers){return eval("document.layers['" + objName +"']");}else{return eval('document.all.' + objName);}}
function Show_TabMenuForAreab(tabscount,tabName,tabadid_num,tabadnum){
	var tabscount=parseInt(tabscount);
	if(!Number(tabscount)){return false;}
	for(var i=1;i<=tabscount;i++){GetObjb("submenub"+tabscount+"_"+tabName+"_"+tabadid_num+i).style.display="none";GetObjb("submenub"+tabscount+"_"+tabName+"_"+tabadid_num+i).className="leftText";}	for(var i=1;i<=tabscount;i++){GetObjb("menub"+tabscount+"_"+tabName+"_"+tabadid_num+i).className="normal";}
	GetObjb("menub"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).className="act";
	GetObjb("submenub"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).style.display="block";  
}

//----------------------------------------
function GetObjc(objName){if(document.getElementById){return eval('document.getElementById("' + objName + '")');}else if(document.layers){return eval("document.layers['" + objName +"']");}else{return eval('document.all.' + objName);}}
function Show_TabMenuForAreac(tabscount,tabName,tabadid_num,tabadnum){
	var tabscount=parseInt(tabscount);
	if(!Number(tabscount)){return false;}
	for(var i=1;i<=tabscount;i++){GetObjc("submenuc"+tabscount+"_"+tabName+"_"+tabadid_num+i).style.display="none";GetObjc("submenuc"+tabscount+"_"+tabName+"_"+tabadid_num+i).className="act";}	for(var i=1;i<=tabscount;i++){GetObjc("menub"+tabscount+"_"+tabName+"_"+tabadid_num+i).className="normal";}
	GetObjc("menub"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).className="act";
	GetObjc("submenuc"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).style.display="block";  
}
//-------------------------------------
function GetObjd(objName){if(document.getElementById){return eval('document.getElementById("' + objName + '")');}else if(document.layers){return eval("document.layers['" + objName +"']");}else{return eval('document.all.' + objName);}}
function Show_TabMenuForAread(tabscount,tabName,tabadid_num,tabadnum){
	var tabscount=parseInt(tabscount);
	if(!Number(tabscount)){return false;}
	for(var i=1;i<=tabscount;i++){GetObjd("submenud"+tabscount+"_"+tabName+"_"+tabadid_num+i).style.display="none";}
	for(var i=1;i<=tabscount;i++){GetObjd("menud"+tabscount+"_"+tabName+"_"+tabadid_num+i).className="normal";}
	GetObjd("menud"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).className="act";
	GetObjd("submenud"+tabscount+"_"+tabName+"_"+tabadid_num+tabadnum).style.display="block";
}