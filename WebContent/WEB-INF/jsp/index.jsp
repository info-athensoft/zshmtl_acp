<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- global variables settings -->
<c:set var="webapp_name" value="/acp"/>


<!-- page variables  -->
<c:set var="inc_dir" value="inc"/>
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
        <title>INF. Athensoft | ACP</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="Preview page of Metronic Admin Theme #2 for statistics, charts, recent events and reports" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${webapp_name}/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
        <link href="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css" />
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
            <div class="page-sidebar-wrapper">
                <!-- END SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <jsp:include page="${inc_dir}/page-sidebar.jsp"></jsp:include>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    <!-- BEGIN THEME PANEL -->
                    <!-- END THEME PANEL -->
                    
                    <h1 class="page-title"> 蒙特利尔职商会管理后台
                        <small>版本号 1.0 发布日期 2018-06</small>
                    </h1>
                    <!-- 
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <i class="icon-home"></i>
                                <a href="#">Home</a>
                                <i class="fa fa-angle-right"></i>
                            </li>
                            <li>
                                <span>Dashboard</span>
                            </li>
                        </ul>
                        
                    </div> -->
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-green-sharp">
                                            <span data-counter="counterup" data-value="7800">0</span>
                                        </h3>
                                        <small>总新闻数</small>
                                    </div>
                                    <div class="icon">
                                        <i class="icon-pie-chart"></i>
                                    </div>
                                </div>
                                <div class="progress-info">
                                    <div class="progress">
                                        <span style="width: 76%;" class="progress-bar progress-bar-success green-sharp">
                                            <span class="sr-only">76% progress</span>
                                        </span>
                                    </div>
                                    <div class="status">
                                        <div class="status-title"> 增长率 </div>
                                        <div class="status-number"> 76% </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-red-haze">
                                            <span data-counter="counterup" data-value="1349">0</span>
                                        </h3>
                                        <small>总评论数</small>
                                    </div>
                                    <div class="icon">
                                        <i class="icon-like"></i>
                                    </div>
                                </div>
                                <div class="progress-info">
                                    <div class="progress">
                                        <span style="width: 85%;" class="progress-bar progress-bar-success red-haze">
                                            <span class="sr-only">85% change</span>
                                        </span>
                                    </div>
                                    <div class="status">
                                        <div class="status-title"> 增长率 </div>
                                        <div class="status-number"> 85% </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-blue-sharp">
                                            <span data-counter="counterup" data-value="567"></span>
                                        </h3>
                                        <small>总广告数</small>
                                    </div>
                                    <div class="icon">
                                        <i class="icon-basket"></i>
                                    </div>
                                </div>
                                <div class="progress-info">
                                    <div class="progress">
                                        <span style="width: 45%;" class="progress-bar progress-bar-success blue-sharp">
                                            <span class="sr-only">45% grow</span>
                                        </span>
                                    </div>
                                    <div class="status">
                                        <div class="status-title"> 增长率 </div>
                                        <div class="status-number"> 45% </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-purple-soft">
                                            <span data-counter="counterup" data-value="276"></span>
                                        </h3>
                                        <small>总会员数</small>
                                    </div>
                                    <div class="icon">
                                        <i class="icon-user"></i>
                                    </div>
                                </div>
                                <div class="progress-info">
                                    <div class="progress">
                                        <span style="width: 57%;" class="progress-bar progress-bar-success purple-soft">
                                            <span class="sr-only">56% change</span>
                                        </span>
                                    </div>
                                    <div class="status">
                                        <div class="status-title"> 增长率 </div>
                                        <div class="status-number"> 57% </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-lg-6 col-xs-12 col-sm-12">
                            <div class="portlet light ">
                                <div class="portlet-title tabbable-line">
                                    <div class="caption">
                                        <i class="icon-bubbles font-dark hide"></i>
                                        <span class="caption-subject font-dark bold uppercase">会员管理请求</span>
                                    </div>
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#portlet_member_1" data-toggle="tab"> 新申请 </a>
                                        </li>
                                        <li>
                                            <a href="#portlet_member_2" data-toggle="tab"> 其它处理 </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="portlet-body">
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="portlet_member_1">
                                            <!-- BEGIN: Comments -->
                                            <div class="mt-comments">
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar1.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Michael Baker</span>
                                                            <span class="mt-comment-date">26 Feb, 10:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> Lorem Ipsum is simply dummy text of the printing and typesetting industry. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-pending">Pending</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar6.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Larisa Maskalyova</span>
                                                            <span class="mt-comment-date">12 Feb, 08:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> It is a long established fact that a reader will be distracted. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-rejected">Rejected</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar8.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Natasha Kim</span>
                                                            <span class="mt-comment-date">19 Dec,09:50 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The generated Lorem or non-characteristic Ipsum is therefore or non-characteristic. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-pending">Pending</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar4.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Sebastian Davidson</span>
                                                            <span class="mt-comment-date">10 Dec, 09:20 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The standard chunk of Lorem or non-characteristic Ipsum used since the 1500s or non-characteristic. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-rejected">Rejected</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END: Comments -->
                                        </div>
                                        <div class="tab-pane" id="portlet_member_2">
                                            <!-- BEGIN: Comments -->
                                            <div class="mt-comments">
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar4.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Michael Baker</span>
                                                            <span class="mt-comment-date">26 Feb, 10:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar8.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Larisa Maskalyova</span>
                                                            <span class="mt-comment-date">12 Feb, 08:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> It is a long established fact that a reader will be distracted by. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar6.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Natasha Kim</span>
                                                            <span class="mt-comment-date">19 Dec,09:50 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar1.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Sebastian Davidson</span>
                                                            <span class="mt-comment-date">10 Dec, 09:20 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The standard chunk of Lorem Ipsum used since the 1500s </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END: Comments -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-xs-12 col-sm-12">
                            <div class="portlet light ">
                                <div class="portlet-title tabbable-line">
                                    <div class="caption">
                                        <i class="icon-bubbles font-dark hide"></i>
                                        <span class="caption-subject font-dark bold uppercase">广告管理请求</span>
                                    </div>
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#portlet_ad_1" data-toggle="tab"> 新增申请 </a>
                                        </li>
                                        <li>
                                            <a href="#portlet_ad_2" data-toggle="tab"> 变更申请 </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="portlet-body">
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="portlet_ad_1">
                                            <!-- BEGIN: Comments -->
                                            <div class="mt-comments">
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar1.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Michael Baker</span>
                                                            <span class="mt-comment-date">26 Feb, 10:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> Lorem Ipsum is simply dummy text of the printing and typesetting industry. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-pending">Pending</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar6.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Larisa Maskalyova</span>
                                                            <span class="mt-comment-date">12 Feb, 08:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> It is a long established fact that a reader will be distracted. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-rejected">Rejected</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar8.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Natasha Kim</span>
                                                            <span class="mt-comment-date">19 Dec,09:50 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The generated Lorem or non-characteristic Ipsum is therefore or non-characteristic. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-pending">Pending</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar4.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Sebastian Davidson</span>
                                                            <span class="mt-comment-date">10 Dec, 09:20 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The standard chunk of Lorem or non-characteristic Ipsum used since the 1500s or non-characteristic. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-rejected">Rejected</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END: Comments -->
                                        </div>
                                        <div class="tab-pane" id="portlet_ad_2">
                                            <!-- BEGIN: Comments -->
                                            <div class="mt-comments">
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar4.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Michael Baker</span>
                                                            <span class="mt-comment-date">26 Feb, 10:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar8.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Larisa Maskalyova</span>
                                                            <span class="mt-comment-date">12 Feb, 08:30AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> It is a long established fact that a reader will be distracted by. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar6.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Natasha Kim</span>
                                                            <span class="mt-comment-date">19 Dec,09:50 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc. </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-comment">
                                                    <div class="mt-comment-img">
                                                        <img src="${webapp_name}/assets/pages/media/users/avatar1.jpg" /> </div>
                                                    <div class="mt-comment-body">
                                                        <div class="mt-comment-info">
                                                            <span class="mt-comment-author">Sebastian Davidson</span>
                                                            <span class="mt-comment-date">10 Dec, 09:20 AM</span>
                                                        </div>
                                                        <div class="mt-comment-text"> The standard chunk of Lorem Ipsum used since the 1500s </div>
                                                        <div class="mt-comment-details">
                                                            <span class="mt-comment-status mt-comment-status-approved">Approved</span>
                                                            <ul class="mt-comment-actions">
                                                                <li>
                                                                    <a href="#">Quick Edit</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">View</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Delete</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END: Comments -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="row">
                        
                        <div class="col-lg-6 col-xs-12 col-sm-12">
                            <div class="portlet light ">
                                <div class="portlet-title">
                                    <div class="caption caption-md">
                                        <i class="icon-bar-chart font-dark hide"></i>
                                        <span class="caption-subject font-dark bold uppercase">本日最新评论</span>
                                        <span class="caption-helper"></span>
                                    </div>
                                    <div class="inputs">
                                        <div class="portlet-input input-inline input-small ">
                                            <div class="input-icon right">
                                                <i class="icon-magnifier"></i>
                                                <input type="text" class="form-control form-control-solid input-circle" placeholder="search..."> </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="scroller" style="height: 338px;" data-always-visible="1" data-rail-visible1="0" data-handle-color="#D7DCE2">
                                        <div class="general-item-list">
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar4.jpg">
                                                        <a href="" class="item-name primary-link">Nick Larson</a>
                                                        <span class="item-label">3 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-success"></span> Open</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar3.jpg">
                                                        <a href="" class="item-name primary-link">Mark</a>
                                                        <span class="item-label">5 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-warning"></span> Pending</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat tincidunt ut laoreet. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar6.jpg">
                                                        <a href="" class="item-name primary-link">Nick Larson</a>
                                                        <span class="item-label">8 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-primary"></span> Closed</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar7.jpg">
                                                        <a href="" class="item-name primary-link">Nick Larson</a>
                                                        <span class="item-label">12 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-danger"></span> Pending</span>
                                                </div>
                                                <div class="item-body"> Consectetuer adipiscing elit Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar9.jpg">
                                                        <a href="" class="item-name primary-link">Richard Stone</a>
                                                        <span class="item-label">2 days ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-danger"></span> Open</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar8.jpg">
                                                        <a href="" class="item-name primary-link">Dan</a>
                                                        <span class="item-label">3 days ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-warning"></span> Pending</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar2.jpg">
                                                        <a href="" class="item-name primary-link">Larry</a>
                                                        <span class="item-label">4 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-success"></span> Open</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    	
                        <div class="col-lg-6 col-xs-12 col-sm-12">
                            <div class="portlet light ">
                                <div class="portlet-title">
                                    <div class="caption caption-md">
                                        <i class="icon-bar-chart font-dark hide"></i>
                                        <span class="caption-subject font-dark bold uppercase">新闻与活动</span>
                                        <span class="caption-helper"></span>
                                    </div>
                                    <div class="inputs">
                                        <div class="portlet-input input-inline input-small ">
                                            <div class="input-icon right">
                                                <i class="icon-magnifier"></i>
                                                <input type="text" class="form-control form-control-solid input-circle" placeholder="search..."> </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="scroller" style="height: 338px;" data-always-visible="1" data-rail-visible1="0" data-handle-color="#D7DCE2">
                                        <div class="general-item-list">
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar4.jpg">
                                                        <a href="" class="item-name primary-link">Nick Larson</a>
                                                        <span class="item-label">3 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-success"></span> Open</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar3.jpg">
                                                        <a href="" class="item-name primary-link">Mark</a>
                                                        <span class="item-label">5 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-warning"></span> Pending</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat tincidunt ut laoreet. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar6.jpg">
                                                        <a href="" class="item-name primary-link">Nick Larson</a>
                                                        <span class="item-label">8 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-primary"></span> Closed</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar7.jpg">
                                                        <a href="" class="item-name primary-link">Nick Larson</a>
                                                        <span class="item-label">12 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-danger"></span> Pending</span>
                                                </div>
                                                <div class="item-body"> Consectetuer adipiscing elit Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar9.jpg">
                                                        <a href="" class="item-name primary-link">Richard Stone</a>
                                                        <span class="item-label">2 days ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-danger"></span> Open</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar8.jpg">
                                                        <a href="" class="item-name primary-link">Dan</a>
                                                        <span class="item-label">3 days ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-warning"></span> Pending</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                            <div class="item">
                                                <div class="item-head">
                                                    <div class="item-details">
                                                        <img class="item-pic rounded" src="${webapp_name}/assets/pages/media/users/avatar2.jpg">
                                                        <a href="" class="item-name primary-link">Larry</a>
                                                        <span class="item-label">4 hrs ago</span>
                                                    </div>
                                                    <span class="item-status">
                                                        <span class="badge badge-empty badge-success"></span> Open</span>
                                                </div>
                                                <div class="item-body"> Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    	
                    
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            
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
   <script src="${webapp_name}/assets/global/plugins/moment.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/morris/morris.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/morris/raphael-min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amcharts/themes/patterns.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/horizontal-timeline/horizontal-timeline.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
   <!-- END PAGE LEVEL PLUGINS -->
   <!-- BEGIN THEME GLOBAL SCRIPTS -->
   <script src="${webapp_name}/assets/global/scripts/app.min.js" type="text/javascript"></script>
   <!-- END THEME GLOBAL SCRIPTS -->
   <!-- BEGIN PAGE LEVEL SCRIPTS -->
   <script src="${webapp_name}/assets/pages/scripts/dashboard.min.js" type="text/javascript"></script>
   <!-- END PAGE LEVEL SCRIPTS -->
   <!-- BEGIN THEME LAYOUT SCRIPTS -->
   <script src="${webapp_name}/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
   <script src="${webapp_name}/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
   <!-- END THEME LAYOUT SCRIPTS -->
   <script>
       
   </script>
</body>

</html>