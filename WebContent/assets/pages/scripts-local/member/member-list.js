/** member_list.jsp */

/* list news - init */
var MemberList = function () {

    var initPickers = function () {
        $('.date-picker').datepicker({
//          rtl: Metronic.isRTL(),
            rtl: App.isRTL(),			//edited by Athens 
            autoclose: true
        });
    }

    var handleProducts = function() {
        var grid = new Datatable();

        grid.init({
            src: $("#datatable_memberList"),	//should be the id of div element in member_list.jsp
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
                "pageLength": 20, 	// default record count per page
                "ajax": {
                    "url": "/acp/member/list", // ajax source
                },
                "order": [
                    [1, "asc"]		// set first column as a default sort by asc
                ] 
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
                var memberArray = grid.getSelectedRows();
                var memberStatus = action.val();
                groupUpdateStatus(memberArray,memberStatus);		//reload
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


/* list member - button:group update status */
function groupUpdateStatus(memberArray,memberStatus){
	
    //execute saving
    $.ajax({
        type    :	"post",
        url     :	"/acp/member/updategroup",
        data	:	"memberArray="+memberArray+"&memberStatus="+memberStatus,
//        dataType:	"json",
        timeout :   30000,
        
        success:function(msg){
        	
            location.href="/acp/member/member_list.html";			//should be specified with your destination url
        	//alert("INFO: News status updated.");
        },
        error:function(){
            alert("ERROR: Updating failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/* list member - datatable:button:filter search */
function filterSearch(){
	
//	alert("do filterSearch()");
	
    var p1 = $("#acctName").val();
    var p2 = $("#name1").val();        
    var p3 = $("#name2").val();
    var p4 = $("#gender").val();
    //var p5 = $("#nationality").val();
    var p6 = $("#phone1").val();
    var p7 = $("#phone2").val();
    var p8 = $("#wechat").val();
    var p9 = $("#email").val();
    var p10 = $("#memberStatus").val();
    
    

//	validate
//	if(!isNonNegativeInteger(p6a)){
//		p6a = "";
//		$("#viewNumFrom").val("");
//	}
//	if(!isNonNegativeInteger(p6b)){
//		p6b = "";
//		$("#viewNumTo").val("");
//	}
//	isNonNegativeInteger(p6b);
  
    var businessObject =
    {
    //		globalId    :	p1,
    		acctName   	:	p1,
    		name1    	:	p2,
    		name2    	:	p3,
    		gender		:	p4,            
    		//nationality	: 	p5,            
    		phone1		:	p6,
    		phone2		:	p7,
    		wechat		:	p8,
    		email		:	p9,
//    		degree		:	p11,
//    		occupation	:	p12,
//    		dob			:	p13,
//    		pobProvince	:	p14,
//    		pobCity		:	p15,
//    		homeAddress	:	p16,
//    		postalcode	:	p17,
//    		hobbies		:	p18,
//    		memberActiveDate	:	p19,
//    		memberLevel	:	p20,
    		memberStatus:	p10
    };

    var dt = $("#datatable_memberList").DataTable();					//your code
    
    //mended on 2018-0218 for Tomcat 8.5 stricter request charset
    //var encoded_param = encodeURIComponent(JSON.stringify(businessObject)); 
    var param = JSON.stringify(businessObject);
    //by Athens Temperorily  comment this
    var x = dt.ajax.url("/acp/member/search?jsonObjString="+param).load();		//your code
    
}


/* list news - datatable:button:filter reset */
function filterReset(){
//	alert("do filterReset()");
	$("#acctName").val("");
    $("#name1").val("");        
    $("#name2").val("");
    $("#gender").val(0);
    $("#nationality").val("");
    $("#phone1").val("");
    $("#phone2").val("");
    $("#wechat").val("");
    $("#email").val("");
    $("#memberStatus").val(0);
}