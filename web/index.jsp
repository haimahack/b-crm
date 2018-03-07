<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <title>index - h-crm</title>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" href="${basePath}/resources/sg/css/sg.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/style.css">
	<!-- index style -->
	<style type="text/css" rel="stylesheet" >
		*{margin: 0;padding: 0;}
		html,body{width:100%;height: 100%;}
		a{text-decoration: none;}
		ul li{list-style: none;}
		.f-index{margin:60px auto;text-align: center;}
		.f-index h1{text-shadow: 2px 2px 2px #ff0,3px 3px 3px #07f;}
		.i-f-a{margin:100px auto;font-size: 24px;font-weight: bold;}
		.f-a{position: relative;color:#07f;text-shadow: 1px 1px 1px #0ff,3px 3px 3px #fff;}
		.f-a::after{content:"";position: absolute;
					left:50%;bottom:-6px;width:0;height:6px;
					background: #f0f;transition:all 0.3s;}
		.f-a:hover::after{width:100%;left: 0;right: 0;}
	</style>
  </head>
  
  <body>
	<div class="f-index">
		<div class="i-f-h">
			<h1>You can see this text indicating that you have been successful.</h1>
		</div>
		<div class="i-f-a">
			<a class="f-a" href="sys/index"><i class="fa fa-fw fa-hand-o-right"></i>前去首页</a>
		</div>
	</div>
  </body>
</html>
