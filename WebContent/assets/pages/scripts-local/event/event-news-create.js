/** event_news_create.jsp */


/* create news - button:back */
function backToNewsList(){
	location.href = "/acp/events/news/list.html";
}


/* create news - button:save change,create */
function createNews() {
    var businessObject = getBusinessObject();
    var param = JSON.stringify(businessObject);
    
    /* validating with business rule */
    //event title
    var eventTitle = businessObject.title;
    if(isEmptyString(eventTitle)){
    	alert("提示: 新闻标题不可为空");
    	return;
    }
    
    //author
    var author = businessObject.author;
    if(isEmptyString(author)){
    	alert("提示: 编辑不可为空");
    	return;
    }
    
    //descShort
    var descShort = businessObject.descShort;
    if(isEmptyString(descShort)){
    	alert("提示: 摘要不可为空");
    	return;
    }
    
    //longShort
    var descLong = businessObject.descLong;
    if(isEmptyString(descLong)){
    	alert("提示: 正文不可为空");
    	return;
    }
    
    
    
    //execute saving
    $.ajax({
        type    :   "post",
        url     :	"/acp/events/news/create",
        data	:	"jsonObjString="+param,
//        dataType:   "json",
        timeout :   30000,
        
        success:function(msg){
        	alert("提示: 新增成功!");
        	location.href="/acp/events/news/list.html";
        },
        error:function(XMLHttpRequest, textStatus){
//        	alert("错误: 新增失败，请重新尝试!");     
//            alert("ERROR: News creating failed.");
        	
        	if (XMLHttpRequest.readyState==4 && XMLHttpRequest.status == "200") {
        		alert("提示: 新增成功!");
        		location.href="/acp/events/news/list.html";
        	}else{
        		alert("错误: 新增失败，请重新尝试!"); 
        	}
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/* create news - button:reset */
function resetCreateNews(){
	var p1 = $("#globalId").val("");
    var p2 = $("#eventUUID").val("");        
    var p3 = $("#title").val("");
    var p4 = $("#author").val("");
    //var p5 = $("#postDatetime").val("");
    var p6 = $("#viewNum").val(0);
    var p7 = $("#descShort").val("");
    var p8 = $("#descLong").val("");
    var p9 = $("#eventClass").val("1");
    var p10 = $("#eventStatus").val();
}