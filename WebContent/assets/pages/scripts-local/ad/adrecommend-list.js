/** adpost_list.jsp */

/* list news - init */
var AdRecommendList = function () {

    var initPickers = function () {
        //init date pickers
        $('.date-picker').datepicker({
//            rtl: Metronic.isRTL(),
            rtl: App.isRTL(),
            autoclose: true
        });
    }

    var handleProducts = function() {
        var grid = new Datatable();

        grid.init({
            src: $("#datatable_adRecommendList"),
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
                "pageLength": 50, // default record count per page
                "ajax": {
                    "url": "/acp/ad/adrcmd/list", // ajax source
                    //"url": "http://localhost:8080/acp/events/eventsNewsListData?length=3", // ajax source
                },
                "order": [
                    [2, "asc"]	//order by modify date
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
                var adUUIDArray = grid.getSelectedRows();
                var adStatus = action.val();
                groupUpdateStatus(adUUIDArray,adStatus);
                //end-of-modified
                
                //grid.getDataTable().ajax.reload();
                //grid.getDataTable().ajax.url("/acp/events/eventsNewsListData").load();
                //grid.getDataTable().ajax.reload();
                grid.clearAjaxParams();
                
                
            } else if (action.val() == "") {
                Metronic.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'Please select an action',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            } else if (grid.getSelectedRowsCount() === 0) {
                Metronic.alert({
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


/* list news - button:group update status */
function groupUpdateStatus(adUUIDArray,adStatus){
	
	var jsonObjString = {
		adUUIDArray:adUUIDArray,
		adStatus:adStatus
	};
		
	var param = JSON.stringify(jsonObjString);
	
    //execute saving
    $.ajax({
        type    :	"post",
        url     :	"/acp/ad/adrcmd/updategroup",		
        data	:	param,
        dataType:   "json",
        timeout :   30000,
        
        success:function(msg){
        	alert("提示: 修改成功!");
            location.href="/acp/ad/adrcmd/list.html";
        },
        error:function(XMLHttpRequest, textStatus){
            alert("ERROR: adrcommend updating failed.");     
        },            
        complete:function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/* list news - datatable:button:filter search */
function filterSearch(){
	
//	alert("do filterSearch()");
    var p1 = $("#adUUID").val();
    var p2 = $("#pageId").val();
    var p3 = $("#pageName").val();
    var p4 = $("#rcmdRank").val();
    var p5 = $("#rcmdStatus").val();

    //validate
    if(p2!="" && (!isNonNegativeInteger(p2))){
    	filterReset();
    	return ;
    }
    
    if(p4!="" && (!isNonNegativeInteger(p4))){
    	filterReset();
    	return ;
    }
    
    if(p2==""){
    	p2=0;
    }
    
    if(p4==""){
    	p4=0;
    }
    
    
//	validate
    var businessObject =
    {
    		adUUID:p1,
    		pageId:p2,
    		pageName:p3,
    		rcmdRank:p4,
    		rcmdStatus:p5
    };

    var dt = $("#datatable_adRecommendList").DataTable();
    
    //mended on 2018-0218 for tomcat 8.5 sticter request charset
    //var encoded_param = encodeURIComponent(JSON.stringify(businessObject)); 
    var param = JSON.stringify(businessObject); 
    
    var x = dt.ajax.url("/acp/ad/adrcmd/search?jsonObjString="+param).load();
}


/* list news - datatable:button:filter reset */
function filterReset(){
//	alert("do filterReset()");
	var p1 = $("#adUUID").val("");
    var p2 = $("#pageId").val("");
    var p3 = $("#pageName").val("");
    var p4 = $("#rcmdRank").val("");
    var p5 = $("#rcmdStatus").val(0);
}