<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>gather-list - h-crm</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="../../pages/common/taglib.jsp" %>
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
			            
						<div class="h-channel"> 主页 》 站点采集 </div>
						<div class="cnt">
							<h1>开发中...</h1>
						</div>
					</div><!--content end  -->
				</div>
			</div><!--row end  -->
		</div>
	</div><!-- /#page-content-wrapper -->
  	
</div><!-- /#wrapper -->

<script type="text/javascript">
	$(function(){
	});
</script>
</body>
</html>
