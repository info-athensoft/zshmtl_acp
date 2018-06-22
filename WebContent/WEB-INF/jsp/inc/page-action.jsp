<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="webapp_name" value="/acp"/>

<div class="page-actions">
    <div class="btn-group">
        <button type="button" class="btn btn-circle btn-outline red dropdown-toggle" data-toggle="dropdown">
            <i class="fa fa-plus"></i>&nbsp;
            <span class="hidden-sm hidden-xs">常用功能&nbsp;</span>&nbsp;
            <i class="fa fa-angle-down"></i>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="${webapp_name}/events/eventsNewsCreate">
                    <i class="icon-docs"></i> 新建新闻 </a>
            </li>
            <li>
                <a href="${webapp_name}/ad/adpost/create.html">
                    <i class="icon-tag"></i> 新建广告 </a>
            </li>
            <li>
                <a href="${webapp_name}/member/member_list.html">
                    <i class="icon-share"></i> 审核会员 </a>
            </li>
            <!-- 
            <li class="divider"> </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-flag"></i> Comments
                    <span class="badge badge-success">4</span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-users"></i> Feedbacks
                    <span class="badge badge-danger">2</span>
                </a>
            </li>
             -->
        </ul>
    </div>
</div>