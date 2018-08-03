function showtagstring(){
	var tags = $("#eventTags").val();
	alert(tags);
}

/* tag - button:save change,update and create if not exists */
function saveTags() {
    var businessObject = getBusinessObject();
	var param = JSON.stringify(businessObject);
	
	//alert("param="+param);
	
    //execute saving
    $.ajax({
    	async	:	false,
    	type    :   "post",
        url     :	"/acp/tag/save",
        data	:	param,
        contentType:"application/json",
        timeout :   30000,
        
        success:function(msg){
        	alert("提示: 标签保存成功!");
//            location.href="/acp/events/news/list.html";
        },
        error:function(XMLHttpRequest, textStatus){
        	alert("错误: 标签保存失败，请重新尝试!");
//            alert("ERROR: News updating failed."); 
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}

function getBusinessObject(){
	p1 = $("#eventTags").val();
	
	var businessObject = {
			tagNameArray: p1
	}
		
	return businessObject;
}