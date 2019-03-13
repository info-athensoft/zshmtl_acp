function showtagstring(){
	var tags = $("#eventTags").val();
	alert(tags);
}

/* tag - button:save change,update and create if not exists */
function saveNewsTags() {
	
	p1 = $("#eventTags").val();
	if(p1.trim().length==0){
		alert("提示: 新闻标签清空成功!");
		return;
	}
	
    var businessObject = getBusinessObject();
	var param = JSON.stringify(businessObject);
	
	//alert("param="+param);
	
    //execute saving
    $.ajax({
    	async	:	false,
    	type    :   "post",
        url     :	"/acp/event/tag/save",
        data	:	param,
        contentType:"application/json",
        timeout :   30000,
        
        success:function(msg){
        	alert("提示: 新闻标签保存成功!");
//            location.href="/acp/events/news/list.html";
        },
        error:function(XMLHttpRequest, textStatus){
//        	alert("错误: 新闻签保存失败，请重新尝试!");
//            alert("ERROR: News updating failed."); 
        	
        	if (XMLHttpRequest.readyState==4 && XMLHttpRequest.status == "200") {
        		alert("提示: 新闻标签保存成功!");
        		//location.href="/acp/member/list.html";
        	}else{
        		alert("错误: 新闻签保存失败，请重新尝试!"); 
        	}
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}

function getBusinessObject(){
	p1 = $("#eventTags").val();
	p2 = $("#globalId").val();	//event global id
	
	
	
	var businessObject = {
			tagNameArray: p1,
			objectId: p2
	}
		
	return businessObject;
}