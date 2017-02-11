/**
 * Package:	content
 * Module:  event-news system
 */

/*
 * event_news_create.jsp
 * event_news_edit.jsp
 */
function getBusinessObject(){	
//	create a json object
    var p1 = $("#globalId").val();
    var p2 = $("#eventUUID").val();        
    var p3 = $("#title").val();
    var p4 = $("#author").val();
    var p5 = $("#postDatetime").val();
    var p6 = $("#viewNum").val();
    var p7 = $("#descShort").val();
    var p8 = $("#descLong").val();
    var p9 = $("#eventClass").val();
    var p10 = $("#eventStatus").val();
    
    var businessObject =
    {
    		globalId    :    p1,
    		eventUUID   :    p2,
    		title    	:    p3,
    		author    	:    p4,
    		postDatetime:    p5,            
    		viewNum    	:    p6,            
    		descShort   :    p7,
    		descLong	:    p8,
    		eventClass  :    p9,
    		eventStatus	:    p10
    };
    
    return businessObject;
}



/* event_news_create.jsp */
function createNews() {
    //alert(JSON.stringify(businessObject));
    var businessObject = getBusinessObject();
	
    //execute saving
    $.ajax({
        type    :    "post",
        url        : "createNews?itemJSONString="+JSON.stringify(businessObject),
        dataType:    "html",
        timeout :     30000,
        
        success:function(msg){
            //todo
        },
        error:function(){
            alert("ERROR: News creating failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}



/* event_news_edit.jsp */
function updateNews() {
    
    var businessObject = getBusinessObject();
    alert(JSON.stringify(businessObject));
	
    //execute saving
    $.ajax({
        type    :    "post",
        url        : "updateNews?itemJSONString="+JSON.stringify(businessObject),
        dataType:    "html",
        timeout :     30000,
        
        success:function(msg){
            location.href="eventsNewsList";
        },
        error:function(){
            alert("ERROR: News updating failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}



/* event_news_list.jsp */
function filterSearch(){
	//alert("do filterSearch()");
	//create a json object
    var p2 = $("#eventUUID").val();
    var p3 = $("#eventTitle").val();        
    var p4 = $("#eventAuthor").val();
    var p9 = $("#eventClass").val();
    
    //alert(p1+" "+p2+" "+p3+" "+p4);
    var businessObject =
    {
    //		globalId    :    p1,
    		eventUUID   :    p2,
    		title    	:    p3,
    		author    	:    p4,
    // 		postDatetime:    p5,            
    //		viewNum    	:    p6,            
    //		descShort   :    p7,
    //		descLong	:    p8,
      		eventClass  :    p9
    //		eventStatus	:    p10
    };

    var dt = $("#datatable_products").DataTable();
    //alert("dt"+dt);
    
    var x = dt.ajax.url("eventsNewsSearchFilterData?itemJSONString="+JSON.stringify(businessObject)).load();
    
    
}

function filterReset(){
	alert("do filterReset()");
}