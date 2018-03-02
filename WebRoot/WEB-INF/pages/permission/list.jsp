<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>permission-list - h-crm</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="../../pages/common/taglib.jsp" %>
	<!-- 引入树形菜单 -->
	<link rel="stylesheet" href="${basePath}/resources/sg/tree/tm_tree.css">
	<script type="text/javascript" src="${basePath}/resources/sg/tree/tm_tree.js"></script>
</head>
  
<body>
  <div id="wrapper">
	
	<!-- left页面 -->
    <%@include file="../../pages/common/left.jsp" %>
    
  	<!-- Page Content -->
	<div id="page-content-wrapper">
		<button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
		<span class="hamb-top"></span>
		<span class="hamb-middle"></span>
		<span class="hamb-bottom"></span>
		</button>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
			        <div class="content">
			            
						<div class="h-channel"> 主页 》 权限管理 </div>
						<div class="cnt">
							<div class="tabwrap">
								<div id="permissiontree"></div>
							</div>
						</div>
					</div><!--content end  -->
				</div>
			</div><!--row end  -->
		</div>
	</div><!-- /#page-content-wrapper -->
  	
</div><!-- /#wrapper -->

<script type="text/javascript">
	$(function(){
		function initPermissionTree(){
			tzAjax.request({
				path:basePath,
				model:"permission",
				method:"root",
				success:function(data){
					var root  = data.root;
					var children =data.children;
					$("#permissiontree").tmTree({outhtml:"<a href='javascript:void(0);' class='remove'><i class='fa fa-fw fa-trash-o'></i></a>",root:root,children:children,type:"checkbox",onclick:function($obj,data){
						//alert(JSON.stringify(data));
					},callback:function(opid){
						//alert("您要删除的opid的是:"+opid);
						var $this = $(this);
						$.tzConfirm({callback:function(ok){
							if(ok){
								$this.closest("li").remove();
							}
						}});
					}});
				}
			});
		};
		
		initPermissionTree();
	});
</script>
</body>
</html>
