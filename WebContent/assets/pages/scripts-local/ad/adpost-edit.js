/** adpost_edit.jsp */


/* create adpost - button:back */
function backToAdPostList(){
	//alert("backToAdPostList");
	location.href = "/acp/ad/adpost/list.html";
}


/* update adpost - button:save change,create */
function updateAdPost() {
    //alert(JSON.stringify(businessObject));
    var businessObject = getAdPostObject();
    
    /* validating by business rule */
    /*
    var eventTitle = businessObject.title;
    if(isEmptyString(eventTitle)){
    	alert("WARNING: event title must not be empty!");
    	return;
    }
    var author = businessObject.author;
    if(isEmptyString(author)){
    	alert("WARNING: author must not be empty!");
    	return;
    }*/
    
    var param = JSON.stringify(businessObject);
    
    //execute saving
    $.ajax({
        type    :    "post",
        url        : "/acp/ad/adpost/update?itemJSONString="+JSON.stringify(businessObject),
        dataType:    "json",
        timeout :     30000,
        
        success:function(msg){
        	location.href="/acp/ad/adpost_list.html";
        },
        error:function(){
            alert("ERROR: AdPost editing failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/* update adpost - button:reset */
function resetUpdateAdPost(){
	var p1 = $("#adType").val(0);
	var p2 = $("#acctName").val("");
	var p3 = $("#adTitle").val("");
	var p4 = $("#adText").val("");
	var p5 = $("#expireDate").val("");
	var p6 = $("#author").val("");
	var p7 = $("#adpostStatus").val(0);
	//var p8 = $("#adUUID").val("");
	var p10 = $("#adLink").val("");
}

function getAdPostObject(){
	var p1 = $("#adType").val();
	var p2 = $("#acctName").val();
	var p3 = $("#adTitle").val();
	var p4 = $("#adText").val();
	var p5 = $("#expireDate").val();
	var p6 = $("#author").val();
	var p7 = $("#adpostStatus").val();
	var p8 = $("#user-ad-img-url").val();
	var p9 = $("#adUUID").val();
	var p10 = $("#adLink").val();
	var p11 = ""; //$("#adUrl").val();
	
	var obj = {
		adType		:p1,
		acctName	:p2,
		adTitle		:p3,
		adText		:p4,
		expireDate	:p5,
		author		:p6,
		adpostStatus:p7,
		
		adImage		:p8,
		adUUID		:p9,
		adLink		:p10,
		adUrl		:p11
	};
	
	return obj;
}