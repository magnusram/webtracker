/**
 * 
 */
var isEmpty = function(obj){
	if(obj){
		 if (obj.trim().length > 0){
			 return false;
		 }
		 else{
			 return true;
		 }
	}
	else{
		return true;
	}
}

var validateCreate = function(){
	var gname = $('input[name=gname]');
	if (isEmpty(gname.val())){
		//gname.attr('required','true');
		return false;
	}
}