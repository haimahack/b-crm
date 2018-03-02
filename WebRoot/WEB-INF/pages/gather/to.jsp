<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <title>gather-to - h-crm</title>
    <meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${basePath}/resources/sg/css/sg.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/style.css">
	<script type="text/javascript" charset="utf-8" src="${basePath }/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${basePath}/resources/sg/sgutil.js"></script>
	<script type="text/javascript" charset="utf-8" src="${basePath}/resources/sg/sg.js"></script>
	
	<!-- to style -->
	<style type="text/css" rel="stylesheet" >
		*{margin: 0;padding: 0;}
		html,body{width:100%;height: 100%;}
		a{text-decoration: none;}
		ul li{list-style: none;}
		.f-index{margin:60px auto;text-align: center;}
		.i-f-a{margin:100px auto;font-size: 24px;font-weight: bold;}
		#f-a{position: relative;color:#07f;text-shadow: 1px 1px 1px #0ff,3px 3px 3px #fff;}
		#f-a::after{content:"";position: absolute;
					left:50%;bottom:-6px;width:0;height:6px;
					background: #f0f;transition:all 0.3s;}
		#f-a:hover::after{width:100%;left: 0;right: 0;}
	</style>
  </head>
  
  <body>
	<div class="f-index">
		<div class="i-f-a">
			<select id="news">
			  <option value="http://news.sina.com.cn/" name="news">新浪新闻</option>
			  <option value="http://news.163.com/" name="news">网易新闻</option>
			  <option value="http://news.qq.com/" name="news">腾讯新闻</option>
			</select>
			<a id="f-a" href="javascript:;"><i class="fa fa-fw fa-hand-o-right"></i>点我采集数据</a>
		</div>
	</div>
	
	<script type="text/javascript">
	
		//var news = $("#news").val();
		$("#f-a").on("click",function(){
			var news = $("#news").val();
			//alert(news);
			loading("正在采集数据，采集数据需要大量时间请耐心等候...");
			$("#f-a").unbind();
			$("#f-a").remove();
			$.post('data',{news:news},function(data){
				if(data){
					$("body").html(data);
				}
			});
		});
	</script>
  </body>
</html>
