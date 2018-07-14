/** member_edit.jsp 
 * 
 *  images upload processing
 * 
 * 
 */



/* edit news - init */
var MemberEdit = function (option) {
/*
    var handleImages = function(option) {

        // see http://www.plupload.com/
        var uploader = new plupload.Uploader({
            runtimes : 'html5,flash,silverlight,html4',
             
            browse_button : document.getElementById('tab_images_uploader_pickfiles'), // you can pass in id...
            container: document.getElementById('tab_images_uploader_container'), // ... or DOM Element itself
             
            //url : "http://localhost:8080/acp/events/fileUpload", //TODO: Change to relative url
            url : "", //TODO: Change to relative url
             
            filters : {
                max_file_size : '8mb',
                mime_types: [
                    {title : "Image files", extensions : "jpg,gif,png"},
                    {title : "Zip files", extensions : "zip"}
                ]
            },
         
            // Flash settings
            flash_swf_url : '/acp/assets/plugins/plupload/js/Moxie.swf',
     
            // Silverlight settings
            silverlight_xap_url : '/acp/assets/plugins/plupload/js/Moxie.xap',             
         
            init: {
                PostInit: function() {
                    $('#tab_images_uploader_filelist').html("");
         
                    $('#tab_images_uploader_uploadfiles').click(function() {
                        uploader.start();
                        return false;
                    });

                    $('#tab_images_uploader_filelist').on('click', '.added-files .remove', function(){
                        uploader.removeFile($(this).parent('.added-files').attr("id"));    
                        $(this).parent('.added-files').remove();                     
                    });
                },
         
                FilesAdded: function(up, files) {
                    plupload.each(files, function(file) {
                        $('#tab_images_uploader_filelist').append('<div class="alert alert-warning added-files" id="uploaded_file_' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ') <span class="status label label-info"></span>&nbsp;<a href="javascript:;" style="margin-top:-5px" class="remove pull-right btn btn-sm red"><i class="fa fa-times"></i> remove</a></div>');
                    });
                },
         
                UploadProgress: function(up, file) {
                    $('#uploaded_file_' + file.id + ' > .status').html(file.percent + '%');
                },

                FileUploaded: function(up, file, response) {
                    var response = $.parseJSON(response.response);

                    if (response.result && response.result == 'OK') {
                    	//alert(file.id);
                    	
                        var id = response.id; // uploaded file's unique name. Here you can collect uploaded file names and submit an jax request to your server side script to process the uploaded files and update the images tabke

                        $('#uploaded_file_' + file.id + ' > .status').removeClass("label-info").addClass("label-success").html('<i class="fa fa-check"></i> Done'); // set successfull upload
                        
                        //create media record to database
                        alert("图片已上传");
                        
                    } else {
                        $('#uploaded_file_' + file.id + ' > .status').removeClass("label-info").addClass("label-danger").html('<i class="fa fa-warning"></i> Failed'); // set failed upload
                        Metronic.alert({type: 'danger', message: 'One of uploads failed. Please retry.', closeInSeconds: 10, icon: 'warning'});
                    }
                },
         
                Error: function(up, err) {
                    Metronic.alert({type: 'danger', message: err.message, closeInSeconds: 10, icon: 'warning'});
                },
                
                UploadComplete: function(){
                	var eventUUID = option;
                	//refresh tab_images when uploading done
                	reloadEventMedia(eventUUID);
                	
                }
            }
        });

        
        uploader.setOption('url','/acp/events/fileUploadAndCreateRecord?eventUUID='+option);
        uploader.init();

    }

    var handleReviews = function () {
    	
    	var eventUUID = $("#eventUUID").val();
    	console.log("option eventUUID="+eventUUID);
    	
        var grid = new Datatable();

        grid.init({
            src: $("#datatable_reviews"),
            onSuccess: function (grid) {
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            loadingMessage: 'Loading...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 

                // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
                // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/scripts/datatable.js). 
                // So when dropdowns used the scrollable div should be removed. 
                //"dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r>t<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>",

                "lengthMenu": [
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    //"url": "/acp/events/eventsNewsReviewListData?eventUUID="+eventUUID, // ajax source
                	"url": "/acp/events/eventsNewsReviewListData", // ajax source
                	"data":{
                	    "eventUUID": eventUUID
                	}
                },
                "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                    'orderable': true,
                    'targets': [0]
                }],
                "order": [
                    [0, "asc"]
                ] // set first column as a default sort by asc
            }
        });
    }

    var handleHistory = function () {

        var grid = new Datatable();

        grid.init({
            src: $("#datatable_history"),
            onSuccess: function (grid) {
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            loadingMessage: 'Loading...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
                "lengthMenu": [
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    "url": "demo/ecommerce_product_history.php", // ajax source
                },
                "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                    'orderable': true,
                    'targets': [0]
                }],
                "order": [
                    [0, "asc"]
                ] // set first column as a default sort by asc
            }
        });
    } 
*/
    var initComponents = function () {
        //init datepickers
        $('.date-picker').datepicker({
            rtl: App.isRTL(),		//modified by Athens
            autoclose: true
        });

        //init datetimepickers
        $(".datetime-picker").datetimepicker({
            isRTL: App.isRTL(),		//modified by Athens
            autoclose: true,
            todayBtn: true,
            pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
            minuteStep: 10
        });

        //init maxlength handler
        $('.maxlength-handler').maxlength({
            limitReachedClass: "label label-danger",
            alwaysShow: true,
            threshold: 5
        });
    }

    return {

        //main function to initiate the module
        init: function (option) {
            initComponents();

            //by Athens, no need for member edit
            //handleImages(option);
            //handleReviews();
            //handleHistory();
        }

    };

}();


/*edit member - button:back */
function backToMemberList(){
	location.href = "/acp/member/member_list.html";
}

/*edit member - button:reset */
function resetMember(){
	$("#globalId").val();
	$("#acctName").val();
	$("#name1").val();
	$("#name2").val();
	$("#gender").val();           
	$("#phone1").val();
	$("#phone2").val();
	$("#wechat").val();
	$("#email").val();
	$("#degree").val();
	$("#occupation").val();
	$("#dob").val();
	$("#pobProvince").val();
	$("#pobCity").val();
	$("#homeAddress").val();
	$("#postalcode").val();
	$("#hobbies").val();
	$("#memberActiveDate").val();
	$("#memberLevel").val();
	$("#memberStatus").val();
}

/* member - button:save change,update */
function updateMember() {
    
    var businessObject = getBusinessObject();
    //alert(JSON.stringify(businessObject));
	
    //execute saving
    $.ajax({
        type    :    "post",
        url        : "/acp/member/update?itemJSONString="+JSON.stringify(businessObject),
        dataType:    "html",
        timeout :     30000,
        
        success:function(msg){
//        	alert("INFO: Updated successfully!");
        	alert("提示: 修改成功!");
            location.href="/acp/member/member_list.html";
        },
        error:function(){
//            alert("ERROR: Member updating failed.");     
            alert("错误: 修改失败，请返回到列表页面，重新尝试");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/*edit news - button:save and continue */
function updateMemberAndContinue() {
	//alert("Entered updateNewsAndContinue successfully."); 
    var businessObject = getBusinessObject();	//refer to event-news-edit.js
    //alert(JSON.stringify(businessObject));
	
    //execute saving
    $.ajax({
        type    :    "post",
        url        : "updateMember?itemJSONString="+JSON.stringify(businessObject),
        dataType:    "html",
        timeout :     30000,
        
        success:function(msg){
        	//alert("News updated successfully."); 
        },
        error:function(){
            alert("ERROR: updating failed.");     
        },            
        complete: function(XMLHttpRequest, textStatus){
            //reset to avoid duplication
        }        
    });
}


/*edit news - button:mark deleted */
function markMembersStatusDeleted(acctName) {
	//alert('entered markNewsStatusDeleted');
   
    $.ajax({
        type    :    "post",
        url        : "markMemberStatusDeleted?acctName="+acctName,
        dataType:    "html",
        timeout :     30000,
        
        success:function(msg){
            //update deleted status
        	//alert('update deleted status successfully.');
        },
        error:function(xhr, status, error){
        	//alert("ERROR: Sort Number updating failed."); 
        	//alert(xhr.responseText);
        },            
        complete: function(XMLHttpRequest, textStatus){
        	//alert('update deleted status completed.');
        	//$("#eventStatus").val(3);
        }        
    });
}

function getBusinessObject(){
	var p2 = $("#acctName").val();
    var p3 = $("#name1").val();
    var p4 = $("#name2").val();
    var p5 = $("#gender").val();
    var p6 = $("#nationality").val();
    var p7 = $("#phone1").val();
    var p8 = $("#phone2").val();
    var p9 = $("#wechat").val();
    var p10 = $("#email").val();
    var p11 = $("#degree").val();
    var p12 = $("#occupation").val();
    var p13 = $("#dob").val();
    var p14 = $("#pobProvince").val();
    var p15 = $("#pobCity").val();
    var p16 = $("#homeAddress").val();
    var p17 = $("#postalcode").val();
    var p18 = $("#hobbies").val();
    var p19 = $("#memberActiveDate").val();
    var p20 = $("#memberLevel").val();
    var p21 = $("#memberStatus").val();
    
   

//	validate
    var businessObject =
    {
    //		globalId    :	p1,
    		acctName   	:	p2,
    		name1    	:	p3,
    		name2    	:	p4,
    		gender		:	p5,            
    		nationality	: 	p6,            
    		phone1		:	p7,
    		phone2		:	p8,
    		wechat		:	p9,
    		email		:	p10,
    		degree		:	p11,
    		occupation	:	p12,
    		dob			:	p13,
    		pobProvince	:	p14,
    		pobCity		:	p15,
    		homeAddress	:	p16,
    		postalcode	:	p17,
    		hobbies		:	p18,
    		memberActiveDate	:	p19,
    		memberLevel	:	p20,
    		memberStatus:	p21
    };
    
    return businessObject;
}


