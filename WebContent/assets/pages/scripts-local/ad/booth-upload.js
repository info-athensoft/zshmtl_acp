/**
 * Trigger : button link [Upload] @ Section: User Ad
 * @Author Athens
 */
function clickUpload(acctName,adUUID) {
//alert("INFO: clickUpload(id)");	
		
	var path =  $("#ufile").val();	
	console.log("path from file chooser at web page : "+path);
	
	var filename = path.replace(/^.*[\\\/]/, '');
	console.log("file name after clean : "+filename);
	
	//getting the properties of file from file field
	var file_data = $("#ufile").prop("files")[0]; 
	
	//creating object of FormData class
	var form_data = new FormData(); 
	
	//adding extra parameters to form_data
	form_data.append("name", filename); 

	//Appending parameter named file with properties of file_field to form_data
	form_data.append("file", file_data); 
	
	//langNo
//	var langNo = $("#langNo").val();
//	if(langNo==0){
//		alert("WARNING: Please choose a language for your booth first!");
//		return;
//	}
		  
	//execute file upload
	$.ajax({
		
//		url			:	'/acp/advertiser/adpost/imageUpload?eventUUID='+id,
		url			:	'/acp/resource/image/upload/USER_AD?acctName='+acctName+'&adUUID='+adUUID,
	    cache		: 	false,
	    contentType	: 	false,
	    processData	: 	false,
	    data		: 	form_data, // setting the data attribute of AJAX with file_data
	    type		: 	'post',
	    error		: 	function (xhr, status) {
	        				alert(status);
	    				},
	    success		: 	function(data) {
	    					//alert("INFO: clickUpload success");
	    	
	    					//BlockUI
					    	App.blockUI({
					    		target	: '#div_user_ad',
					    		message	: 'UPLOADING ......  '
					    	});
	
					    	//UnblockUI
					    	window.setTimeout(function() {
					    		App.unblockUI('#div_user_ad');
					    	}, 4000);
	        
					    	//set image URL value into input element
					    	$("#booth-cover-img-url").val(data["url"]);
					    	$("#fileinput-new-img").attr("src",data["url"]);
	    				}
	  });	//end of AJAX
}




/**
 * !!!!!!! SHOULD UPDATE THIS FUNCTION !!!!!!!
 * 
 * Trigger : button link [Upload] @ Section: Booth Cover Image
 * @Author Athens
 */
function clickUploadUserProfile(acctName,adUUID) {
//alert("INFO: clickUpload(id)");	
		
	var path =  $("#ufile_boothBanner").val();	
	console.log("path from file chooser at web page : "+path);
	
	var filename = path.replace(/^.*[\\\/]/, '');
	console.log("file name after clean : "+filename);
	
	//getting the properties of file from file field
	var file_data = $("#ufile_boothBanner").prop("files")[0]; 
	
	//creating object of FormData class
	var form_data = new FormData(); 
	
	//adding extra parameters to form_data
	form_data.append("name", filename); 

	//Appending parameter named file with properties of file_field to form_data
	form_data.append("file", file_data); 
	
	//langNo
	var langNo = $("#langNo").val();
	if(langNo==0){
		alert("WARNING: Please choose a language for your booth first!");
		return;
	}
		  
	//execute file upload
	$.ajax({
		
//		url			:	'/acp/advertiser/adpost/imageUpload?eventUUID='+id,
		url			:	'/acp/resource/image/upload/BOOTH_BANNER?advertiserId='+advertiserId+'&bizId='+bizId+'&langNo='+langNo,
	    cache		: 	false,
	    contentType	: 	false,
	    processData	: 	false,
	    data		: 	form_data, // setting the data attribute of AJAX with file_data
	    type		: 	'post',
	    error		: 	function (xhr, status) {
	        				alert(status);
	    				},
	    success		: 	function(data) {
	    					//alert("INFO: clickUpload success");
	    	
	    					//BlockUI
					    	App.blockUI({
					    		target	: '#div_boothBanner',
					    		message	: 'UPLOADING ......  '
					    	});
	
					    	//UnblockUI
					    	window.setTimeout(function() {
					    		App.unblockUI('#div_boothBanner');
					    	}, 4000);
	        
					    	//set image URL value into input element
					    	$("#booth-banner-img-url").val(data["url"]);
					    	$("#fileinput-new-img_boothBanner").attr("src",data["url"]);
	    				}
	  });	//end of AJAX
	}