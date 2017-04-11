/* event-news-review-edit.js */

/* event_news_review_edit.jsp */
function updateNewsReview() {
    
	//alert("updateNewsReview()");
	
	var p1 = $("#eventUUID").val();
	var p2 = $("#reviewUUID").val();
	var p3 = $("#reviewContent").val();
	var p4 = $("#customerId").val();
	
	//alert("reviewUUID="+p2);
	
    var reviewObject = {
    	eventUUID 		: p1,
    	reviewUUID 		: p2,
    	reviewContent 	: p3,
    	customerId		: p4
    };
    
   //alert(JSON.stringify(reviewObject));
	
    
    
    //execute saving
    $.ajax({
        type    :    "post",
        url        : "updateNewsReview?itemJSONString="+JSON.stringify(reviewObject),
        dataType:    "html",
        timeout :     30000,
        
        success:function(msg){
        	var tabId = "#tab_reviews";
            location.href="eventsNewsEdit?eventUUID="+p1+tabId;
        },
        error:function(){
            alert("ERROR: News review updating failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}

function updateNewsReviewAndContinue(){
	alert("TODO: updateNewsReviewAndContinue()");
}

function backToNewsEdit(){
	var p1 = $("#eventUUID").val();
	var tabId = "#tab_reviews";
	location.href = "/acp/events/eventsNewsEdit?eventUUID="+p1+tabId;
}

function resetNewsReview(){
	alert("resetNewsReview()");
}

