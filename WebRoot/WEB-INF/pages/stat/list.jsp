<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>stat-list - h-crm</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="../../pages/common/taglib.jsp" %>
	<!-- 右键菜单样式 -->
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/menu.css">
		<!-- 分页组件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath }/resources/sg/tz_page.js"></script>
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
			            
						<div class="h-channel"> 主页 》 日志管理 </div>
						<div class="cnt" id="m-cnt">
							<!-- menu页面 -->
							<%@include file="../../pages/common/menu.jsp" %>
							
							<div class="tabwrap">
								<!--表格-->
								<table class="tztab" id="tztab">
									<caption>
										日志管理
										<div class="fr sbtn">
											<input type="text" id="keywords" class="fl" onchange="tzAdmin.change(this)" placeholder="输入搜索关键词..."/>
											<a href="javascript:void(0);" onclick="tzAdmin.search(this)"><i class="fa fa-fw fa-search-plus"></i></a>
										</div>
									</caption>
									<thead>
										<tr>
											<th style="width: 54px;"><input type="checkbox" id="checkedAll" />全选</th>
											<th style="width: 100px;">主键</th>
											<th style="width: 80px;">模块</th>
											<th style="width: 100px;">方法</th>
											<th>类名</th>
											<th style="width: 100px;">创建时间</th>
											<th style="width: 80px;">创建人</th>
											<th style="width: 100px;">IP</th>
											<th style="width: 54px;">操作</th>
										</tr>
									</thead>
									<!--身体部-->
									<tbody id="tbody" data-model="stat">
										<tr>
											<td id="loading" colspan="50"></td>
										</tr>
									</tbody>
								</table>
								<div class="cpage"></div>
							</div><!-- tabwrap end -->
						</div>
					</div><!--content end  -->
				</div>
			</div><!--row end  -->
		</div>
	</div><!-- /#page-content-wrapper -->
  	
</div><!-- /#wrapper -->

<script type="text/javascript">
	$(function(){
		//在 tz_admin.js调用
		//加载数据
		tzAdmin.loadData(0,20,function(itemCount){
			tzAdmin.initPage(itemCount);//分页加载一次
		});
		
	});
	
	/*全选
    全选cheched和下方的checkbox按钮的checked是一致的，
    故可用this.checked。
    注意：$(this).checked是错的
    */
    $('#checkedAll').prop('checked',false);
    $('#checkedAll').on("click",function() {
        $('[name="h-item"]:checkbox').prop('checked', this.checked);
    });

    /*判断复选框的总数，是否和选中的复选框的数量相等
    相等：全选了
    不相等：没有全选
    */
    $('[name="h-item"]:checkbox').on("click",function() {
    	alert(11);
        /*得到的是ul下 name=item 的复选框数组*/
        var $checkedArray = $('[name="h-item"]:checkbox');
        /*$checkedArray.filter(':checked') -----> 已经选择的复选框 */
        var flag = $checkedArray.length==$checkedArray.filter(':checked').length;
        alert(flag);
        $('#checkedAll').prop('checked',flag);
　　　　
    });
</script>
</body>
</html>
