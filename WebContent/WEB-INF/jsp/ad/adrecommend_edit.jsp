<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- global variables settings -->
<c:set var="webapp_name" value="/acp"/>

<!-- page variables  -->
<c:set var="inc_dir" value="../inc"/>
<!-- ENDS page variables -->

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>Athensoft | Ad - AdPost Edit</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="Preview page of Metronic Admin Theme #2 for edit product" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${webapp_name}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${webapp_name}/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${webapp_name}/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="${webapp_name}/assets/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${webapp_name}/assets/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid">
        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <jsp:include page="${inc_dir}/page-logo.jsp"></jsp:include>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN PAGE ACTIONS -->
                <!-- DOC: Remove "hide" class to enable the page header actions -->
                <jsp:include page="${inc_dir}/page-action.jsp"></jsp:include>
                <!-- END PAGE ACTIONS -->
                <!-- BEGIN PAGE TOP -->
                <jsp:include page="${inc_dir}/page-top.jsp"></jsp:include>
                <!-- END PAGE TOP -->
            </div>
            <!-- END HEADER INNER -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            
                <jsp:include page="${inc_dir}/page-sidebar.jsp"></jsp:include>
            	<!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    <!-- BEGIN THEME PANEL -->
                    <!-- END THEME PANEL -->
                    <h1 class="page-title">广告系统 <small> 推荐广告</small></h1>
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
							<li>
								<i class="fa fa-home"></i>
								<a href="#">首页</a>
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<a href="${webapp_name}/ad/ad_list.html">广告</a>
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<a href="#">推荐广告</a>
							</li>
						</ul>
                        <div class="page-toolbar">
                            <div class="btn-group pull-right">
                                <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true"> Actions
                                    <i class="fa fa-angle-down"></i>
                                </button>
                                <ul class="dropdown-menu pull-right" role="menu">
                                    <li>
                                        <a href="#">
                                            <i class="icon-bell"></i> Action</a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="icon-shield"></i> Another action</a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="icon-user"></i> Something else here</a>
                                    </li>
                                    <li class="divider"> </li>
                                    <li>
                                        <a href="#">
                                            <i class="icon-bag"></i> Separated link</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                            <form class="form-horizontal form-row-seperated" action="#">
                                <div class="portlet">
                                    <div class="portlet-title">
                                        <div class="caption">
                                           	 修改广告<span class="caption-helper"> </span> </div>
                                        <div class="actions btn-set">
                                            <button type="button" name="back" class="btn btn-secondary-outline" onclick="backToAdPostList();">
                                                <i class="fa fa-angle-left"></i> 返回</button>
                                            <button class="btn btn-secondary-outline" onclick="resetUpdateAdPost();">
                                                <i class="fa fa-reply"></i> 清空</button>
                                            <button class="btn btn-success" onclick="updateAdPost();">
                                                <i class="fa fa-check"></i> 保存</button>
                                                
                                            <!--    
                                            <button class="btn btn-success" onclick="return false;">
                                                <i class="fa fa-check-circle"></i> Save &amp; Continue Edit</button>
                                                 
                                            <div class="btn-group">
                                                <a class="btn btn-success dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                                    <i class="fa fa-share"></i> More
                                                    <i class="fa fa-angle-down"></i>
                                                </a>
                                                <div class="dropdown-menu pull-right">
                                                	<ul>
	                                                    <li><a href="javascript:;"> Duplicate </a></li>
	                                                    <li><a href="javascript:;"> Delete </a></li>
	                                                    <li class="dropdown-divider"> </li>
	                                                    <li><a href="javascript:;"> Print </a></li>
                                                   	</ul>
                                                </div>
                                            </div> -->
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="tabbable-bordered">
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#tab_general" data-toggle="tab"> 广告信息 </a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab_general">
												<div class="form-body">
													<!-- 
													<div class="form-group">
														<label class="col-md-2 control-label">Global ID: <span class="required">* </span>
														</label>
														<div class="col-md-10">
															<input type="text" class="form-control" id="globalId" name="globalId" placeholder="" disabled="disabled">
														</div>
													</div> -->
													
													<div class="form-group">
														<label class="col-md-2 control-label">广告类型: <span class="required">* </span></label>
														<div class="col-md-10">
															<select class="table-group-action-input form-control input-medium" id="adType" name="adType">
																<option value="0" ${adPostObject.adType == '0' ? 'selected' : ''}>请选择...</option>
																<option value="1" ${adPostObject.adType == '1' ? 'selected' : ''}>图片广告</option>
																<option value="2" ${adPostObject.adType == '2' ? 'selected' : ''}>文字广告</option>
																<option value="3" ${adPostObject.adType == '3' ? 'selected' : ''}>链接广告</option>
															</select>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-2 control-label">广告状态: <span class="required">* </span></label>
														<div class="col-md-10">
															<select class="table-group-action-input form-control input-medium" id="adpostStatus" name="adpostStatus">
																<option value="0" ${adPostObject.adType == '0' ? 'selected' : ''}>请选择...</option>
																<option value="1" ${adPostObject.adType == '1' ? 'selected' : ''}>已发布</option>
																<option value="2" ${adPostObject.adType == '2' ? 'selected' : ''}>待发布</option>
																<option value="3" ${adPostObject.adType == '3' ? 'selected' : ''}>已删除</option>
																<option value="4" ${adPostObject.adType == '4' ? 'selected' : ''}>已过期</option>
																<option value="5" ${adPostObject.adType == '5' ? 'selected' : ''}>审查中</option>
															</select>
														</div>
													</div>
													
													<!-- 
													<div class="form-group">
														<label class="col-md-2 control-label">会员编号: <span class="required">* </span></label>
														<div class="col-md-4">
															<input type="text" class="form-control" id="adOwnerId" name="adOwnerId" placeholder="" disabled="disabled">
														</div>
													</div> -->
													
													<div class="form-group">
														<label class="col-md-2 control-label">会员账号: <span class="required">* </span></label>
														<div class="col-md-4">
															<input type="text" class="form-control" id="acctName" name="acctName" placeholder="登录邮箱" value="${adPostObject.acctName}">
														</div>
													</div>
													
													<div class="row" id="div_user_ad">
					                                	<label class="col-md-2 control-label">广告图片: <span class="required">* </span></label>
							                                
				                       					<div class="col-md-10">
				                       						<div class="fileinput fileinput-new" data-provides="fileinput">
							                                    <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
							                                       	<!--  <img id="fileinput-new-img" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>-->
							                                       	
							                                       		<c:if test="${not empty adPostObject.adImage}">
							                                       			<a href="${adPostObject.adImage}" class="fancybox-button" data-rel="fancybox-button">
							                                       			<img id="fileinput-new-img" src="${adPostObject.adImage}" alt=""/>
							                                       			</a>
							                                       		</c:if>
							                                       		<c:if test="${empty adPostObject.adImage}">
							                                       			<img id="fileinput-new-img" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>
							                                       		</c:if>
							                                       	
																</div>
							                                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
																</div>
							                                    <div>
							                                        <span class="btn btn-sm default btn-file">
							                                        	<span class="fileinput-new btn-sm"> 选择图片 </span>
							                                        	<span class="fileinput-exists btn-sm"> 更改 </span>
							                                        	<input type="file" id="ufile" name="...">
																	</span>
							                                        <a href="javascript:;" class="btn btn-sm red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
							                                        <a href="javascript:;" class="btn btn-sm green fileinput-exists" data-dismiss="fileinput" onclick=" clickUpload('${adPostObject.acctName}','${adPostObject.adUUID}')"> 上传 </a>
							                                    </div>
							                                </div>
				                       						
				                       						<!-- 
							                    		  	<div class="form-group">
							                              		<div class="col-md-12">
							                              			<br/>
							                                  		<input type="text" id="adPost-cover-img-title" class="form-control" placeholder="Image Title">
							                              		</div>
							                              	</div> -->
							                            
							                             	<div class="form-group">
							                                	<div class="col-md-12">
							                                		<br/>
							                                    	<!-- <input type="text" id="booth-cover-img-url" class="form-control" placeholder="Image URL" disabled="disabled"> -->
							                                    	<input type="text" id="user-ad-img-url" class="form-control" placeholder="Image URL" disabled="disabled" value="${adPostObject.adImage}">
							                                	</div>
							                            	</div>
							                            	<div class="form-group">
							                                	<div class="col-md-12">
							                                    	<span class="label label-danger">NOTE!</span> Image dimension must be in ratio of 4:3 at least by 400 x 300
							                                	</div>
							                            	</div> 
							                      		</div>
				                       				</div>
													
													<div class="form-group">
														<label class="col-md-2 control-label">广告标题: <span class="required">* </span></label>
														<div class="col-md-10">
															<input type="text" class="form-control" id="adTitle" name="adTitle" value="${adPostObject.adTitle}">
															<span class="help-block">限20字 </span>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-2 control-label">广告文字: <span class="required">* </span></label>
														<div class="col-md-10">
															<textarea class="form-control" id="adText" name="adText">${adPostObject.adTitle}</textarea>
															<span class="help-block">限60字 </span>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-2 control-label">广告链接: <span class="required">* </span></label>
														<div class="col-md-10">
															<input type="text" class="form-control" id="adLink" name="adLink" value="${adPostObject.adLink}">
															<span class="help-block">其它视频或链接广告 </span>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-2 control-label">到期日: <span class="required">* </span></label>
														<div class="col-md-4">
															<input type="text" class="form-control" id="expireDate" name="expireDate" value="${adPostObject.expireDate}">
															<span class="help-block"></span>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-2 control-label">操作员: <span class="required">* </span></label>
														<div class="col-md-4">
															<input type="text" class="form-control" id="author" name="author">
														</div>
													</div>
													
													
													
													<input type="hidden" class="form-control" id="adUUID" name="adUUID" value="${adPostObject.adUUID}">
												</div>
												</div>
                                            </div>
                                        </div>
                                    </div>
                                
                                
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            <!-- Removed to quick_sidebar.html -->
            <!-- END QUICK SIDEBAR -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <jsp:include page="${inc_dir}/page-footer.jsp"></jsp:include>
        <!-- END FOOTER -->
        <!-- BEGIN QUICK NAV -->
        
        <!-- END QUICK NAV -->
<!--[if lt IE 9]>
<script src="${webapp_name}/assets/global/plugins/respond.min.js"></script>
<script src="${webapp_name}/assets/global/plugins/excanvas.min.js"></script> 
<script src="${webapp_name}/assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="${webapp_name}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${webapp_name}/assets/global/scripts/datatable.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/global/plugins/plupload/js/plupload.full.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${webapp_name}/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${webapp_name}/assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="${webapp_name}/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
<!-- 
<script src="${webapp_name}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
<script src="${webapp_name}/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
 -->
<!-- END THEME LAYOUT SCRIPTS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${webapp_name}/assets/pages/scripts-local/global-validate.js"></script>
<script type="text/javascript" src="${webapp_name}/assets/pages/scripts-local/ad/adpost-edit.js"></script>
<script type="text/javascript" src="${webapp_name}/assets/pages/scripts-local/ad/adpost-upload.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {    
	
	
});    
</script>
</body>

</html>