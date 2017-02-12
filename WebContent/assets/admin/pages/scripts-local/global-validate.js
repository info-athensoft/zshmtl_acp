/**
 * unified validation API in JavaScript
 */


function isNonNegativeInteger(value){
	var reg = new RegExp("^[0-9]*$");
    if(!reg.test(value)){
    	alert("WARNING: Not a Non-negative integer" + "@global-validate.js");
    	return false;
    }else{
/*    	alert("INFO: A Non-negative integer detected"); */
    	return true;
    }
}