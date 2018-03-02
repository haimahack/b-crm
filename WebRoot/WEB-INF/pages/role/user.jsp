<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>role-user - h-crm</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="../../pages/common/taglib.jsp" %>
	<script type="text/javascript" charset="utf-8" src="${basePath}/resources/sg/tz_page.js"></script>
	
	<style type="text/css">
		ul li{list-style:none;}
		.ubox{height:360px;overflow: auto;margin-left:16px;}
		#userbox li{height:24px;border-bottom: 1px solid #999;}
		#userbox li .num{display:inline-block;max-width: 60px;height: 24px;text-align:right;padding:0 10px;}
		#userbox li .chk{position: relative;top:2px;}
		#userbox li:hover{background:#398337;color:#fff;cursor:pointer;}
		#userbox li.on{background:#333;color:#fff;}
	</style>
</head>
<body>
	<div class="ubox">
		<a href="">全选</a>
		<a href="">反选</a>
		<ul id="userbox">
			<c:forEach items="${datas }" var="user" varStatus="uindex">
				<li>
					<span class="num chk"><input type="checkbox" class="check" value="${user.id}"></span>
					<span class="num">${uindex.count }:</span>
					${user.uname }【${user.email }】
				</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		var tzUserRole={
			init:function(){
				$("#userbox").find("li").on("click",function(){
					$(this).toggleClass("on").find(".check").filter(function(){
						/*选中的同时添加样式*/
						//$(this).prop("checked")?$(this).prop("checked",false):$(this).prop("checked",true);
						//或者
						$(this).prop("checked",!$(this).prop("checked"));
					});
				});
			},
			save:function(roleId){
				//alert("我被父窗口调用了哦...");
				var arr=[];
				var $checked = $("#userbox").find(".check:checked");
				//alert($checked.length);
				
				if($checked.length==0){
					loading("请选择一个用户进行操作...",4);
					return;
				}
				//var arr=$checked.get();
				//alert(arr);
				
				$checked.each(function(){
					//alert($(this).val());
					arr.push($(this).val());
				});
				
				tzAjax.request({
					path:basePath,
					model:"role",
					method:"saveUserRole",
					success:function(data){
						if("success"==data){
							loading("成功分配角色",5);
							//将分配号的用户从列表中删掉
							$checked.parents("li").fadeOut("slow",function(){
								$(this).remove();
							});
						}
					}
				},{users:arr.toString(),rid:roleId});
			}
		};
		
		tzUserRole.init();
	</script>
</body>
</html>