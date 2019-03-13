	
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