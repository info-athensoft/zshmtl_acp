/** adpost_create.jsp */


/* create adpost - button:back */
function backToAdPostList(){
	//alert("backToAdPostList");
	location.href = "/acp/ad/adpost/list.html";
}


/* create adpost - button:save change,create */
function createAdPost() {
    
	var businessObject = getAdPostObject();
	var param = JSON.stringify(businessObject);
	
    /* validating by business rule */
    
    var adType = businessObject.title;
    if(isEmptyString(adType)){
    	alert("提示: 请选择广告类型!");
    	return;
    }
    
    var acctName = businessObject.acctName;
    if(isEmptyString(acctName)){
    	alert("提示: 请输入会员账号!");
    	return;
    }
    
    var adTitle = businessObject.adTitle;
    if(isEmptyString(adTitle)){
    	alert("提示: 请输入广告标题!");
    	return;
    }
    if(adTitle.length>20){
    	alert("提示: 标题不超过20个字!");
    	return;
    }
    
        
    var adText = businessObject.adText;
    if(isEmptyString(adText)){
    	alert("提示: 请输入广告文字!");
    	return;
    }
    if(adText.length>60){
    	alert("提示: 广告内容不超过60个字!");
    	return;
    }
    
    //execute saving
    $.ajax({
        type    :	"post",
        url     :	"/acp/ad/adpost/create",
        data	:	"jsonObjString="+param,
        dataType:	"json",
        timeout :	30000,
        
        success:function(msg){
        	alert("提示: 广告创建成功!");
        	location.href="/acp/ad/adpost/list.html";
        },
        error:function(XMLHttpRequest, textStatus){
//        	alert("错误: 广告新增失败，请检查输入重新尝试!");
//            alert("ERROR: AdPost creating failed.");   
        	if (XMLHttpRequest.readyState==4 && XMLHttpRequest.status == "200") {
        		alert("提示: 广告创建成功!");
        		location.href="/acp/ad/adpost/list.html";
        	}else{
        		alert("错误: 广告新增失败，请检查输入重新尝试!"); 
        	}
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