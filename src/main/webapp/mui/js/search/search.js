$(function(){
		var lol = localStorage.getItem("127");
		if(lol!=''){
			document.getElementById('hisP').innerHTML='';
			//$("#hisP").empty();
			
			var arry = lol.split(',');
			 //console.log(arry);
			 for(var i=0;i<arry.length;i++)
			 {
			 	var btn = document.createElement('button');
			 	btn.className='mui-btn';
			 	btn.innerHTML=arry[i];
			 	document.getElementById('hisP').appendChild(btn);
			 	// console.log(arry[i]);
			 }
		}
		 localStorage.setItem("127",lol);
		 //localStorage.removeItem("127");
})
/**
 * 删除 本地搜索记录
 */
function delHistory(){
	localStorage.setItem("127",'');
	document.getElementById('hisP').innerHTML='';
}

//
function clickType(obj){
	switch(obj){
		case 1:
		$("#click1").css("color","red");
		$("#click2").css("color","#000000");
		$("#click3").css("color","#000000");
		break;
		
		case 2:
		$("#click1").css("color","#000000");
		$("#click2").css("color","red");
		$("#click3").css("color","#000000");
		break;
		
		case 3:
		$("#click1").css("color","#000000");
		$("#click2").css("color","#000000");
		$("#click3").css("color","red");
		//
		var table = document.getElementById("click3");
		var x = $("#click3").children("p").hasClass("mui-icon-arrowup");
		console.log(x);
		var p = document.createElement('p');
		if(x){
			p.className = 'mui-icon mui-icon-arrowdown';
		}else
		{
			p.className = 'mui-icon mui-icon-arrowup';
		}
		
		
		$("#click3").empty();
		$("#click3").html("价格");
		table.appendChild(p);
		break;
	}
}
$("#contentDiv").hide();
$(document).keyup(function(event) {

	if(event.keyCode == 13) {
		var searchInput = $("#searchInput").val();
		
		if(searchInput=='')
		{
			return;
		}
		$("#history").hide();
		$("#hot").hide();
		$("#contentDiv").show();
		//
		
		var lol = localStorage.getItem("127");
		 console.log(lol);
		if(lol==''||lol==null||lol=='null'){
			lol = '';
			lol = searchInput;
		}else{
			 lol+=','+searchInput;
		}
		 localStorage.setItem("127",lol);
	}

});