
/*create news - button:back */
function backToNewsList(){
	location.href = "/acp/events/eventsNewsList";
}

/*create news - button:reset */
function resetCreateNews(){
	var p1 = $("#globalId").val("");
    var p2 = $("#eventUUID").val("");        
    var p3 = $("#title").val("");
    var p4 = $("#author").val("");
    //var p5 = $("#postDatetime").val("");
    var p6 = $("#viewNum").val(0);
    var p7 = $("#descShort").val("");
    var p8 = $("#descLong").val("");
    var p9 = $("#eventClass").val(0);
    var p10 = $("#eventStatus").val(0);
    
    //alert("p1="+p1.val());
}