/** event_news_list.jsp */

/* remove news - init */
var EventNewsDelete = function () {

    var initPickers = function () {
        //init date pickers
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            autoclose: true
        });
    }

    var handleProducts = function() {
        var grid = new Datatable();

        grid.init({
            src: $("#datatable_eventNewsList"),
            onSuccess: function (grid) {
            	//alert("success");
            },
            onError: function (grid) {
                // execute some code on network or other general error
            	alert("onError");
            },
            loadingMessage: 'Loading ...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 

                // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
                // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/scripts/datatable.js). 
                // So when dropdowns used the scrollable div should be removed. 
                //"dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r>t<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>",

                "lengthMenu": [
                    [2,10, 20, 50, 100, 150, 200],
                    [2,10, 20, 50, 100, 150, 200] // change per page values here 
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    "url": "/acp/events/news/deletelist", // ajax source
                    //"url": "http://localhost:8080/acp/events/eventsNewsListData?length=3", // ajax source
                    //"dataSrc": "data"
                },
                "order": [
                    [1, "asc"]
                ] // set first column as a default sort by asc
            }
        });

         // handle group action submit button click
        grid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
        	//alert("getTableWrapper");
            e.preventDefault();
            var action = $(".table-group-action-input", grid.getTableWrapper());
            
            //alert(action.val());
            
            if (action.val() != "" && grid.getSelectedRowsCount() > 0) {
                grid.setAjaxParam("customActionType", "group_action");
                grid.setAjaxParam("customActionName", action.val());
                grid.setAjaxParam("id", grid.getSelectedRows());
                
                //modified by Athens
                var eventUUIDArray = grid.getSelectedRows();
                var newsStatus = action.val();
                groupDeleteNews(eventUUIDArray,newsStatus);
                //end-of-modified
                
                //grid.getDataTable().ajax.reload();
                //grid.getDataTable().ajax.url("/acp/events/eventsNewsListData").load();
                //grid.getDataTable().ajax.reload();
                grid.clearAjaxParams();
                
                
            } else if (action.val() == "") {
                App.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'Please select an action',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            } else if (grid.getSelectedRowsCount() === 0) {
            	App.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'No record selected',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            }
        });
    }

    return {

        //main function to initiate the module
        init: function () {
            handleProducts();
            initPickers();
        }

    };

}();


/* remove news - button:group delete news permanently */
function groupDeleteNews(eventUUIDArray,newsStatus){
	//alert("groupUpdateStatus()");
	
	var jsonObjString = {
			eventUUIDArray:eventUUIDArray,
			newsStatus:newsStatus
		};
	var param = JSON.stringify(jsonObjString);
	
    //execute saving
    $.ajax({
        type    :	"post",
        url     :	"/acp/events/news/deletegroup",
        data	:	param,
        contentType:"application/json",
        timeout :	30000,
        
        success:function(msg){
        	alert("提示: 批量删除成功!");
            location.href="/acp/events/deletelist.html";
        	//alert("INFO: News status updated.");
        },
        error:function(){
        	alert("错误: 批量删除失败，请重新尝试!");
//            alert("ERROR: News deleting failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/* remove news - button:group delete news permanently */
function deleteNews(){
	
	var eventUUID = $("#eventUUID").val();
	
    //execute deleting
    $.ajax({
        type    :	"post",
        url     :	"/acp/events/news/delete",
        data	:	"eventUUID="+eventUUID,
//        dataType:	"json",
        timeout :	30000,
        
        success:function(msg){
        	alert("提示: 删除成功!");
            location.href="/acp/events/deletelist.html";
        	//alert("INFO: News status updated.");
        },
        error:function(){
//        	alert("错误: 删除失败，请重新尝试!");
//            alert("ERROR: News deleting failed."); 
        	if (XMLHttpRequest.readyState==4 && XMLHttpRequest.status == "200") {
        		alert("提示: 删除成功!");
        		location.href="/acp/events/deletelist.html";
        	}else{
        		alert("错误: 删除失败，请重新尝试!"); 
        	}
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}




/* list news - datatable:button:filter search */
function filterSearch(){
	
//	alert("do filterSearch()");
//	create a json object
    var p2 = $("#eventUUID").val();
    var p3 = $("#eventTitle").val();
    var p4 = $("#eventAuthor").val();
    var p5a = $("#postDatetimeFrom").val();
    var p5b = $("#postDatetimeTo").val();
    var p6a = $("#viewNumFrom").val();
    var p6b = $("#viewNumTo").val();
    var p9 = $("#eventClass").val();
    var p10 = $("#eventStatus").val();

//    alert(p5a+" -- "+p5b);
    
//	validate
	if(!isNonNegativeInteger(p6a)){
		p6a = "";
		$("#viewNumFrom").val("");
	}
	if(!isNonNegativeInteger(p6b)){
		p6b = "";
		$("#viewNumTo").val("");
	}
//	isNonNegativeInteger(p6b);
//	alert(p5a+" "+p5b);
  
    var businessObject =
    {
    //		globalId    :    p1,
    		eventUUID   :    p2,
    		title    	:    p3,
    		author    	:    p4,
     		postDatetimeFrom:  p5a,            
     		postDatetimeTo:    p5b,            
    		viewNumFrom :    p6a,            
    		viewNumTo 	:    p6b,            
    //		descShort   :    p7,
    //		descLong	:    p8,
      		eventClass  :    p9,
    		eventStatus	:    p10
    };

    var dt = $("#datatable_eventNewsList").DataTable();
    var param = JSON.stringify(businessObject);
    var x = dt.ajax.url("/acp/events/news/deletesearch?jsonObjString="+param).load();
}


/* list news - datatable:button:filter reset */
function filterReset(){
//	alert("do filterReset()");
	var p2 = $("#eventUUID").val("");
    var p3 = $("#eventTitle").val("");        
    var p4 = $("#eventAuthor").val("");
    var p5a = $("#postDatetimeFrom").val("");
    var p5b = $("#postDatetimeTo").val("");
    var p6a = $("#viewNumFrom").val("");
    var p6b = $("#viewNumTo").val("");
    var p9 = $("#eventClass").val(0);
    var p10 = $("#eventStatus").val(0);
}