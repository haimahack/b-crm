<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- Sidebar background: blueviolet;border-radius: 100%;-->
	<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<li class="sidebar-brand">
				<a href="javascript:;" style="height:65px;">
				   <img width="48px" height="45px" style="background: blueviolet;border-radius: 50%;" alt="" title="haima" src="${basePath }/resources/images/haima.png">H-CRM
				</a>
			</li>
			<li>
				<a href="${adminPath}/index"><i class="fa fa-fw fa-home"></i> 主页</a>
			</li>
			<li><a href="${adminPath}/content/list"><i class="fa fa-fw fa-coffee"></i> 内容管理</a></li>

			<li><a href="${adminPath}/user/list"><i class="fa fa-fw fa-user"></i> 用户管理</a></li>
			<li>
				<a href="${adminPath}/role/list"><i class="fa fa-fw fa-user-secret"></i> 角色管理</a>			</li>
			<li>
				<a href="${adminPath}/permission/list"><i class="fa fa-fw fa-key"></i> 权限管理</a>
			</li>
			<li><a href="${adminPath}/stat/list"><i class="fa fa-fw fa-wrench"></i> 日志管理</a></li>
			<li cla	ss="dropdown">
				<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-bar-chart"></i> 统计报表 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header"> 用户/内容/日志 统计</li>
					<li><a href="${adminPath}/stat/contentlist"><i class="fa fa-fw fa-leaf"></i> 内容统计</a></li>
					<li><a href="${adminPath}/stat/userlist"><i class="fa fa-fw fa-group"></i> 用户统计</a></li>
					<li><a href="${adminPath}/stat/statlist"><i class="fa fa-fw fa-tree"></i> 日志统计</a></li>
			    </ul>
			</li>
			
			<li cla	ss="dropdown">
				<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-magnet"></i> 采集管理 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header"> 站点采集/站点管理</li>
					<li><a href	="${adminPath}/gather/to"><i class="fa fa-fw fa-magic"></i> 站点采集</a></li>
					<li><a href	="${adminPath}/gather/list"><i class="fa fa-fw fa-gears"></i> 站点管理</a></li>
			    </ul>
			</li>
			<li>
				<a href="${basePath }/logout"><i class="fa fa-fw fa-sign-out"></i> 退出</a>	
			</li>
		</ul>
	</nav>
	<!-- /#sidebar-wrapper -->

	<script type="text/javascript">
		$(document).ready(function() {
			var trigger = $('.hamburger'),
		  	overlay = $('.overlay'),
		 	isClosed = false;

			trigger.click(function () {
		  		hamburger_cross();      
			});
	
			function hamburger_cross() {
				if (isClosed == true) {          
					overlay.hide();
					trigger.removeClass('is-open');
					trigger.addClass('is-closed');
					isClosed = false;
					 }
				else {   
					overlay.show();
					trigger.removeClass('is-closed');
					trigger.addClass('is-open');
					isClosed = true;
				}
		  	}
		  
		    $('[data-toggle="offcanvas"]').click(function () {
				$('#wrapper').toggleClass('toggled');
		    });  
		});
	</script>