/** adpost_create.jsp */


/* create adpost - button:back */
function backToAdPostList(){
	//alert("backToAdPostList");
	location.href = "/acp/ad/adpost/list.html";
}


/* create adpost - button:save change,create */
function createAdPost() {
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
        url        : "/acp/ad/adpost/create?itemJSONString="+JSON.stringify(businessObject),
        dataType:    "json",
        timeout :     30000,
        
        success:function(msg){
        	location.href="/acp/ad/adpost/list.html";
        },
        error:function(XMLHttpRequest, textStatus){
        	alert("错误: 广告新增失败，请检查输入重新尝试!");
//            alert("ERROR: AdPost creating failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/* create adpost - button:reset */
function resetCreateAdPost(){
	var p1 = $("#adType").val(0);
	var p2 = $("#acctName").val("");
	var p3 = $("#adTitle").val("");
	var p4 = $("#adText").val("");
	var p5 = $("#expireDate").val("");
	var p6 = $("#author").val("");
	var p7 = $("#adpostStatus").val(0);
	//var p8 = $("#adUUID").val("");
	var p8 = $("#adLink").val("");
}

function getAdPostObject(){
	
	var p1 = $("#adType").val();
	var p2 = $("#acctName").val();
	var p3 = $("#adTitle").val();
	var p4 = $("#adText").val();
	var p5 = $("#expireDate").val();
	var p6 = $("#author").val();
	var p7 = $("#adpostStatus").val();
	var p8 = $("#adLink").val();
	//var p8 = $("#adUUID").val();
	
	var obj = {
		adType		:p1,
		acctName	:p2,
		adTitle		:p3,
		adText		:p4,
		expireDate	:p5,
		author		:p6,
		adpostStatus:p7,
		adLink		:p8//,
		//adUUID		:p8
	};
	
	return obj;
}