<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- global variables settings -->
<c:set var="webapp_name" value="/acp"/>

<div class="page-sidebar-wrapper">
	<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
	<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
	<div class="page-sidebar navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->
		<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
		<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
		<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
		<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<ul class="page-sidebar-menu page-sidebar-menu-hover-submenu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
			<li class="start active ">
				<a href="/acp/index">
				<i class="icon-home"></i>
				<span class="title">首页</span>
				<span id="menu-dashborad" class="selected"></span>
				</a>
			</li>
			
			
			<li>
				<a href="javascript:;">
				<i class="icon-badge"></i>
				<span class="title">会员管理</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 会员列表</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 会员信息</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 会员申请</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 会员续约</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 状态审核</a>
					</li>
				</ul>
			</li>
			
			<li>
				<a href="javascript:;">
				<i class="icon-badge"></i>
				<span class="title">广告管理</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						请求受理</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						广告列表</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_create.html">
						新增广告</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_create.html">
						修改广告</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_create.html">
						删除广告</a>
					</li>
				</ul>
			</li>
			
			<li>
				<a href="javascript:;">
				<i class="icon-badge"></i>
				<span class="title">新闻管理</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="${webapp_name}/events/eventsNewsList">
						 新闻列表</a>
					</li>
					<li>
						<a href="${webapp_name}/events/eventsNewsCreate">
						新增新闻</a>
					</li>
					<li>
						<a href="${webapp_name}/events/eventsNewsList">
						 修改新闻</a>
					</li>
					<li>
						<a href="${webapp_name}/events/eventsNewsDelete">
						 删除新闻</a>
					</li>
				</ul>
			</li>
			
			<li>
				<a href="javascript:;">
				<i class="icon-badge"></i>
				<span class="title">帮助中心</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 关于</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 联系我们</a>
					</li>
					<li>
						<a href="${webapp_name}/ad/adpost_list.html">
						 用户协议与条款</a>
					</li>
				</ul>
			</li>
		
			
			<!-- event system -->
			<li class="start">
				<a href="javascript:;">
				<i class="icon-user"></i>
				<span class="title">测试模块</span>
				<span class="arrow "></span>
				<span id="menu-myevents"></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="/acp/events/eventsDashboard">
						<i class="icon-home"></i>
						Dashboard</a>
						
					</li>
					<li>
						<a href="#">
						<i class="icon-home"></i>
						My News<span class="arrow"></a>
						<ul class="sub-menu">
						<li>
							<a href="/acp/events/eventsNewsList">
							<i class="icon-pencil"></i>
							News List</a>
						</li>
						<li>
							<a href="/acp/events/eventsNewsList">
							<i class="icon-paper-plane"></i>
							News Edit</a>
						</li>
						<li>
							<a href="/acp/events/eventsNewsCreate">
							<i class="icon-paper-plane"></i>
							News Create</a>
						</li>
						<li>
							<a href="/acp/events/eventsNewsDelete">
							<i class="icon-paper-plane"></i>
							News Remove - Admin</a>
						</li>
						</ul>
					</li>
				</ul>
			</li>
			<!-- end event system -->
			
			<!-- resource system -->
			<!-- 
			<li class="start active ">
				<a href="javascript:;">
				<i class="icon-user"></i>
				<span class="title">My Resources</span>
				<span class="arrow "></span>
				<span id="menu-myevents"></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="/acp/site/eventsDashboard">
						<i class="icon-home"></i>
						Dashboard</a>
						
					</li>
					<li>
						<a href="#">
						<i class="icon-home"></i>
						Website<span class="arrow"></span></a>
						<ul class="sub-menu">
							<li>
								<a href="/acp/site/list">
								<i class="icon-pencil"></i>
								Website List</a>
							</li>
							<li>
								<a href="/acp/site/list">
								<i class="icon-paper-plane"></i>
								Website Edit</a>
							</li>
							<li>
								<a href="/acp/site/create">
								<i class="icon-paper-plane"></i>
								Website Register</a>
							</li>
							<li>
								<a href="/acp/site/delete">
								<i class="icon-paper-plane"></i>
								Website Remove</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			 -->
			<!-- end resource system -->
			
			<!-- 
			<li>
				<a href="javascript:;">
				<i class="icon-user"></i>
				<span class="title">Advertiser</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="ecommerce_index.html">
						<i class="icon-home"></i>
						Dashboard</a>
					</li>
					<li>
						<a href="page_portfolio.html">
						<i class="icon-pencil"></i>
						Edit Business Profile</a>
					</li>
					<li>
						<a href="/admin/bizprofile">
						<i class="icon-paper-plane"></i>
						Post Ad. Page</a>
					</li>
					<li>
						<a href="ecommerce_orders.html">
						<i class="icon-basket"></i>
						Order a service</a>
					</li>
				</ul>
			</li>
			 -->
			
			<!-- 
			<li>
				<a href="javascript:;">
				<i class="icon-star"></i>
				<span class="title">My Credits</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="ecommerce_index.html">
						<i class="icon-home"></i>
						Dashboard</a>
					</li>
					<li>
						<a href="ecommerce_orders.html">
						<i class="icon-basket"></i>
						Orders</a>
					</li>
					<li>
						<a href="ecommerce_orders_view.html">
						<i class="icon-tag"></i>
						Order View</a>
					</li>
					<li>
						<a href="ecommerce_products.html">
						<i class="icon-handbag"></i>
						Products</a>
					</li>
					<li>
						<a href="ecommerce_products_edit.html">
						<i class="icon-pencil"></i>
						Product Edit</a>
					</li>
				</ul>
			</li>
			 -->
			
			<!-- 
			<li>
				<a href="javascript:;">
				<i class="icon-basket"></i>
				<span class="title">My Purchase</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="ecommerce_index.html">
						<i class="icon-home"></i>
						Dashboard</a>
					</li>
					<li>
						<a href="ecommerce_orders.html">
						<i class="icon-basket"></i>
						Order a service</a>
					</li>
					<li>
						<a href="ecommerce_orders_view.html">
						<i class="icon-docs"></i>
						History Orders</a>
					</li>
					<li>
						<a href="ecommerce_products.html">
						<i class="icon-handbag"></i>
						View Adogo Services</a>
					</li>
					
				</ul>
			</li>
			 -->
			
			<!-- 
			<li>
				<a href="javascript:;">
				<i class="icon-badge"></i>
				<span class="title">My Certificates</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="/admin/bizprofile">
						<i class="icon-info"></i>
						Post Business Profile</a>
					</li>
					<li>
						<a href="page_portfolio.html">
						<i class="icon-feed"></i>
						Edit Ad. Page</a>
					</li>
				</ul>
			</li>
			-->
			
			<!-- 
			<li>
				<a href="javascript:;">
				<i class="icon-folder"></i>
				<span class="title">Multi Level Menu</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="javascript:;">
						<i class="icon-settings"></i> Item 1 <span class="arrow"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="javascript:;">
								<i class="icon-user"></i>
								Sample Link 1 <span class="arrow"></span>
								</a>
								<ul class="sub-menu">
									<li>
										<a href="#"><i class="icon-power"></i> Sample Link 1</a>
									</li>
									<li>
										<a href="#"><i class="icon-paper-plane"></i> Sample Link 1</a>
									</li>
									<li>
										<a href="#"><i class="icon-star"></i> Sample Link 1</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#"><i class="icon-camera"></i> Sample Link 1</a>
							</li>
							<li>
								<a href="#"><i class="icon-link"></i> Sample Link 2</a>
							</li>
							<li>
								<a href="#"><i class="icon-pointer"></i> Sample Link 3</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-globe"></i> Item 2 <span class="arrow"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="#"><i class="icon-tag"></i> Sample Link 1</a>
							</li>
							<li>
								<a href="#"><i class="icon-pencil"></i> Sample Link 1</a>
							</li>
							<li>
								<a href="#"><i class="icon-graph"></i> Sample Link 1</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">
						<i class="icon-bar-chart"></i>
						Item 3 </a>
					</li>
				</ul>
			</li>
			 -->
							
		</ul>
		<!-- END SIDEBAR MENU -->
	</div>
</div>