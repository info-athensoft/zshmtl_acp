/** event_news_create.jsp */


/* create news - button:back */
function backToNewsList(){
	location.href = "/acp/events/news/list.html";
}


/* create news - button:save change,create */
function createNews() {
    var businessObject = getBusinessObject();
    var param = JSON.stringify(businessObject);
    
    /* validating by business rule */
    //event title
    var eventTitle = businessObject.title;
    if(isEmptyString(eventTitle)){
    	alert("WARNING: event title must not be empty!");
    	return;
    }
    var author = businessObject.author;
    if(isEmptyString(author)){
    	alert("WARNING: author must not be empty!");
    	return;
    }
    
    //execute saving
    $.ajax({
        type    :   "post",
        url     :	"/acp/events/news/create",
        data	:	"jsonObjString="+param,
        dataType:   "json",
        timeout :   30000,
        
        success:function(msg){
        	alert("提示: 新闻创建成功!");
        	location.href="/acp/events/news/list.html";
        },
        error:function(XMLHttpRequest, textStatus){
        	alert("错误: 新闻创建失败，请重新尝试!");     
//            alert("ERROR: News creating failed.");     
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
    var p9 = $("#eventClass").val();
    var p10 = $("#eventStatus").val();
}