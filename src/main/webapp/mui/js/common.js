//表单序列化json格式
function getFormJson(form) {
	var obj = {};
	var formArr = $(form).serializeArray();
	$.each(formArr, function() {
		if(obj[this.name] !== undefined) {
			if(!obj[this.name].push) {
				obj[this.name] = [obj[this.name]];
			}
			obj[this.name].push(this.value || '');
		} else {
			obj[this.name] = this.value || '';
		}
	});
	return obj;
}


function getRegion(){
	for (var i = 0; i < allArea.length; i++) {
		if(allArea[i].sysAreaId == $('#sysAreaId').val()){
			return allArea[i];
		}
	}
}

function getParentArea(parentId){
	for (var i = 0; i < allArea.length; i++) {
		if(allArea[i].sysAreaId == parentId){
			return allArea[i];
		}
	}
}

function getAreaName(){
	var regionName = getRegion();
	var city = getParentArea(regionName.parentId);
	var province = getParentArea(city.parentId);
	return province.name+' '+city.name+' '+regionName.name;
}
