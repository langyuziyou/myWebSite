function tabTurn(num,allnum){
	for(i=1; i <=allnum; i++){
		if (i == num){
			document.getElementById("listTab"+i).className = "act";
   			document.getElementById("listDiv"+i).style.display = "block";   
		}
		else{	
   			document.getElementById("listTab"+i).className = "normal";
   			document.getElementById("listDiv"+i).style.display = "none";
		}
	}
}

function leftTabTurn(num,allnum){
	for(i=1; i <=allnum; i++){
		if (i == num){
			document.getElementById("leftTab"+i).className = "act";
   			document.getElementById("leftDiv"+i).style.display = "block";   
		}
		else{	
   			document.getElementById("leftTab"+i).className = "normal";
   			document.getElementById("leftDiv"+i).style.display = "none";
		}
	}
}

function rightTabTurn(num,allnum){
	for(i=1; i <=allnum; i++){
		if (i == num){
			document.getElementById("rightTab"+i).className = "act";
   			document.getElementById("rightDiv"+i).style.display = "block";   
		}
		else{	
   			document.getElementById("rightTab"+i).className = "normal";
   			document.getElementById("rightDiv"+i).style.display = "none";
		}
	}
}

function rightTabTurn1(num,allnum){
	for(i=1; i <=allnum; i++){
		if (i == num){
			document.getElementById("rightTab1"+i).className = "act";
   			document.getElementById("rightDiv1"+i).style.display = "block";   
		}
		else{	
   			document.getElementById("rightTab1"+i).className = "normal";
   			document.getElementById("rightDiv1"+i).style.display = "none";
		}
	}
}

function rightTabTurn2(num,allnum){
	for(i=1; i <=allnum; i++){
		if (i == num){
			document.getElementById("rightTab2"+i).className = "act";
   			document.getElementById("rightDiv2"+i).style.display = "block";   
		}
		else{	
   			document.getElementById("rightTab2"+i).className = "normal";
   			document.getElementById("rightDiv2"+i).style.display = "none";
		}
	}
}

function navTabTurn(num,allnum){
	
    if(num)
	{
		for(j=1; j <=allnum; j++){
			if (j == num){
				document.getElementById("leftDiv"+j).style.display = "block";
				document.getElementById("leftDiv").style.display = "none";
			}
			else{	
				document.getElementById("leftDiv"+j).style.display = "none";
			}
		}
	}
	else
	{
		document.getElementById("leftDiv").style.display = "block";
		document.getElementById("leftDiv1").style.display = "none";
	}
}

function openBox(){
	var box = document.getElementById("giftCon");
	var btn = document.getElementById("giftMore");
	if(box.className == "giftConH"){
		box.className = "giftCon";
		btn.className = "moreClose";	
	}
	else if(box.className == "giftCon"){
		box.className = "giftConH";
		btn.className = "moreOpen";
	}
}

	
