<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>role-list - h-crm</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="../../pages/common/taglib.jsp" %>
	<script type="text/javascript" charset="utf-8" src="${basePath}/resources/sg/tz_page.js"></script>
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
						<div class="h-channel"> 主页 》 角色管理 </div>
						<div class="cnt">
							<div class="tabwrap">
					
								<!--表格-->
								<table class="tztab" id="tztab">
									<caption>
										角色分配与授权
										<div class="fr sbtn">
											<input type="text" id="keywords" class="fl" placeholder="搜索的关键字..."/>
											<a href="javascript:void(0);" onclick="tzAdmin.search(this)" ><i class="fa fa-fw fa-search-plus"></i></a>
										</div>
									</caption>
									<thead>
										<tr>
											<th style="width:100px;">主键</th>
											<th>角色名称</th>	
											<th style="width:90px;">用户id</th>	
											<th style="width:100px;">创建时间</th>
											<th>角色详情</th>
											<th style="width:80px;">删除状态</th>	
											<th style="width:80px;">操作</th>
										</tr>
									</thead>
									<!--身体部-->
									<tbody id="tbody" data-model="role">
										<tr>
											<td id="loading" colspan="30"></td>
										</tr>
									</tbody>
								</table>
								<div class="cpage"></div>
							</div>
						</div>
					</div><!--content end  -->
				</div>
			</div><!--row end  -->
		</div>
	</div><!-- /#page-content-wrapper -->
  	
</div><!-- /#wrapper -->

<script type="text/javascript">

	//在 tz_admin.js调用
	//加载数据
	tzAdmin.loadData(0,20,function(itemCount){
		tzAdmin.initPage(itemCount);//分页加载一次
	});

	var tzRole ={
		user:function(obj){
			var $obj = $(obj);
			var roleId = $obj.data("opid");
			//alert(roleId);
			$.tzIframe({width:400,height:400,title:"分配角色",content:adminPath+"/role/user/"+roleId,callback:function(iframe,$dialog,opts){
				if(iframe){
					//alert(1);
					iframe.tzUserRole.save(roleId);
				}
			}});
		},
		permission:function(obj){
			var $obj = $(obj);
			var roleId = $obj.data("opid");
			//alert(roleId);
			$.tzIframe({width:460,height:400,title:"分配权限",content:adminPath+"/role/permission/"+roleId,callback:function(iframe,$dialog,opts){
				if(iframe){
					//alert(1);
					iframe.saveRolePermission(roleId,$dialog,opts);
				}
			},loadSuccess:function(iframe){
				//alert("iframe加载完成");
				iframe.initPermissionTree(function(){
					//选中已授权的权限,需要查询出
					//var arr = [2,3,1];//测试数据
					//for(var i=0;i<arr.length;i++){
					//	$(iframe.document).find("body").find(".tm-tree-checkbox[opid="+arr[i]+"]").addClass("tm-tree-checkbox-checked");
					//}
					//$(iframe.document).find("body").find(".tm-tree-checkbox").addClass("tm-tree-checkbox-checked");
				});
			}});
		},
		del:function(){
			
		}
	};
</script>
</body>
</html>
