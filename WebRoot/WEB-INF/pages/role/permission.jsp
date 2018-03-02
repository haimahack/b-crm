<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>role-permission - h-crm</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="../../pages/common/taglib.jsp" %>
	<!-- 引入树的js和css -->
	<link rel="stylesheet" href="${basePath}/resources/sg/tree/tm_tree.css"/>
	<script type="text/javascript" src="${basePath}/resources/sg/tree/tm_tree.js"></script>
	
	<style type="text/css">
		#permissiontree{height:420px;overflow-y: auto;}
	</style>
	
</head>
<body>
 	<div id="permissiontree"></div>
 	<script type="text/javascript">
	function initPermissionTree(callback){
		tzAjax.request({
			path:basePath,
			model:"permission",
			method:"root",
			success:function(data){
				var root  = data.root;
				var children =data.children;
				$("#permissiontree").tmTree({root:root,children:children,type:"checkbox",onclick:function($obj,data){
					permissions = data.checkArr.opid;
				},callback:function(opid){
					var $this = $(this);
					$.tzConfirm({callback:function(ok){
						if(ok){
							$this.closest("li").remove();
						}
					}});
				}});
				if(callback)callback();
			}
		});
	};
	
	var permissions = "";
	function saveRolePermission(roleId,$dialog,opts){
		if(isEmpty(permissions)){
			loading("请选择一个权限进行操作..",4);
			return;
		}
		tzAjax.request({
			path:basePath,
			model:"role",
			method:"saveRolePermission",
			success:function(data){
				if(data=="success"){
					parent.loading("权限分配成功...",4);
					$dialog.next().remove();//阴影层
					$dialog.remove();//弹窗
				}
			}
		},{rid:roleId,permissions:permissions});
	}
	</script>	
 </body>
</html>