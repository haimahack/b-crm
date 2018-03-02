<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>stat-contentlist - h-crm</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="../../pages/common/taglib.jsp" %>
	<script type="text/javascript" charset="utf-8" src="${basePath}/resources/js/echarts2/echarts.js"></script>
	<script type="text/javascript" charset="utf-8" src="${basePath}/resources/js/date/WdatePicker.js"></script>
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
			            
						<div class="channel"> 主页  》  统计报表 》  内容统计</div>
						<div class="cnt">
							<div class="date-picker">
								<input type="text"  id="date" onfocus="WdatePicker({onpicking:changeDate,dateFmt:'yyyy',maxDate:'%y', readOnly:true})" class="Wdate tmui-tips" tip="点击选择年份"/>
								<i class="fa fa-fw fa-hand-o-left"></i>	
							</div>
							<%-- <select onchange="loadData(this.value)" style="color:#00f;font-weight:bold;">
								<option >请选择年份</option>
								<c:forEach begin="2015" end="2018" var="year">
									<option value="${year}">${year}</option>	
								</c:forEach>
							</select> --%>
							
							<div class="tabwrap" id="t-main" style="width:auto;height:450px;"></div>
						</div>
						<div id="xxx-content">
						<!--<select id="ssort">
								<option value="asc">升序</option>
								<option value="desc" selected="selected">降序</option>
							</select>
							<table class="tztab" id="tztab">
								<tbody id="tbody">
									<tr>
										<td id="loading" colspan="30"></td>
									</tr>
								</tbody>
							</table>
							<div class="cpage"></div>
						-->
						</div><!-- tabwrap end -->
					</div><!--content end  -->
				</div>
			</div><!--row end  -->
		</div>
	</div><!-- /#page-content-wrapper -->
  	
</div><!-- /#wrapper -->

<script type="text/javascript">
		$(function() {
			$(".tmui-tips").tmTip();
			var y=new Date().getFullYear();
			$("#date").val(y);
			loadData(y);
		});
		

		// 点击日期的回调函数
		function changeDate(dp){
			var date = dp.cal.getNewDateStr();
			loadData(date);
		}
		function loadData(year) {
			/* if(year=='请选择年份'||year==''||year==null||year=='undefined')
				year=new Date().getFullYear(); */
			$.ajax({
				type : "post",
				url : adminPath+"/stat/lists",
				data : {year : year},
				success : function(data) {
					var json = {
						title : year + "年网站内容访问详情",
						datas : parseData(data)
					};
					initChart("t-main", json);
				}
			});
		};

		//解析json数据
		function parseData(jsonArr) {
			var jsonObj = {};
			for ( var key in jsonArr) {
				var num = jsonArr[key].num;
				var m = jsonArr[key].m;
				jsonObj["s" + parseInt(m)] = num;
			}
			;
			var arr = [];
			for (var i = 1; i <= 12; i++) {
				arr.push(jsonObj["s" + i] || 0);
			}
			return arr;
		}

		function initChart(targetId, json) {
			require.config({
				paths : {
					echarts : basePath + '/resources/js/echarts2'
				}
			});
			require(
	            [
	                'echarts',
	                'echarts/chart/bar',
					'echarts/chart/pie',
					'echarts/chart/line'
	            ],
	           function (ec) {
	                var myChart = ec.init(document.getElementById(targetId));
	                var option  = {
					    title : {
					    	x: "center",
					        text: json.title,
					        subtext: "月份/总数"
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					   
					    toolbox : {
							show : true,
							feature : {
								mark : {
									show : true
								},
								//dataView : {
									//	show : false,
									//	readOnly : false
									//},
								magicType : {
									show : true,
									type : [ 'bar','line' ]
									//type : [ 'bar']
								},
								restore : {
									show : true
								},
								saveAsImage : {
									show : true
								}
							}
						},
					    
					    calculable : false,//是否拖拽
					    xAxis : [
					        {
					            type : 'category',
					            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'访问人数',
					            type:'bar',
					            data:json.datas,
					            markPoint : {
					                data : [
					                    {type : 'max', name: '最大值'},
					                    {type : 'min', name: '最小值'}
					                ]
					            },
					            markLine : {
					                data : [
					                    {type : 'average', name: '平均值'}
					                ]
					            }
					        }
					    ]
					};
	                //添加报表数据给echart
					myChart.setOption(option);
	                //初始化echart的config对象
					var ecConfig = require('echarts/config');
	                //执行config的回调函数
					myChart.on(ecConfig.EVENT.CLICK, eConsole);
	            }
		    );
		};
				
			//点击柱形图回调事件
//	 		var timer = null;
//	 		function eConsole(d) {
//	 			//获取用户点击的数据
//	 			var month = parseInt(d.name);
//	 			//参数
//	 	 		var params ={
//	  				month:month,
//	  				order:"create_time desc",
//	  				pageNo:0,
//	  				pageSize:30
//	 	 		};
//	 			loading2($("#tbody"),4);
//	 			//执行业务
//	 			clearTimeout(timer);
//	 	 		timer = setTimeout(function(){
//	 	 			$.ajax({
//	 		 			type:"post",
//	 		 			url:adminPath+"/adminstat/groupcontent",
//	 		 			data:params,
//	 		 			success:function(data){
//	 		 				var len = data.length;
//	 		 				var html = "";
//	 		 				for(var i=0;i<len;i++){
//	 		 					html+="<tr>"+
//	 		 					"	<td>"+data[i].id+"</td>"+
//	 		 					"	<td>"+data[i].title+"</td>"+
//	 		 					"	<td>"+data[i].time+"</td>"+
//	 		 					"	<td>操作</td>"+
//	 		 					"</tr>";
//	 		 				}
//	 		 				$("#tbody").html(html);
//	 		 			}
//	 		 		});
//	 	 		},200);
//	 		};

		var bonAdmin={
			timer:null,
			remove:function(obj){
				//确认删除提示
				$.tzConfirm({title:"删除提示",content:"你 确 定 删 除 吗?",callback:function(ok){
					if(ok){
						var $this = $(obj);
						var opid = $this.data("opid");
						var model = $("#tbody").data("model");
						var params={};
						params["id"] = opid;
						
						clearTimeout(this.timer);
						this.timer = setTimeout(function(){
							loading("执行删除中,请稍后...",3);
							$.ajax({
								type:"post",
								url:adminPath+"/"+model+"/delete",
								data:params,
								success:function(data){
									if("success"==data){
										loading("删除成功...",3);
										$this.parents("tr").remove();
										var year = $("#date").val();
										//alert(year);
										loadData(year);
									}
								}
							});
						},200);
						
					}
				}});
			}
		};
	
		var mark = true;
		var numss=0;
		function eConsole(d) {
			//alert(numss);
			//alert(11);
			var year=$("#date").val();
			if(year==null||year==""||year=="undefined")
				year=new Date().getFullYear();
			//获取用户点击的数据
			var month = parseInt(d.name);
			var ssort = $("#ssort").val()||"desc";
			
			//alert($("#ssort").val());
			//alert(d.data+"=="+d.name);
			var datas = d.data;
			//alert(datas);//typeof obj
			if(datas instanceof Object) datas = 0;
			if(datas<=0){
				loading("没有数据...",5);
				return;
			}
			//参数
	 		var params ={
				year:year,
				month:month,
 				order:"create_time "+ssort,
 				pageNo:0,
 				pageSize:datas
	 		};
			loading2($("#tbody"),4);
			if(mark){
				mark = !mark;
				//执行业务
	 			$.ajax({
		 			type:"post",
		 			url:adminPath+"/stat/groupcontents",
		 			data:params,
		 			error:function(){mark=true;},
		 			success:function(data){
		 				//alert(JSON.stringify(data));
		 				var len = data.length;
		 				var html = "";
		 				//alert(data[0].id);
		 				for(var i=0;i<len;i++){
		 					html+="<tr>"+
		 					"	<td>"+data[i].id+"</td>"+
		 					"	<td>"+data[i].title+"</td>"+
		 					"	<td>"+data[i].time+"</td>"+
		 					"	<td>"+(data[i].isDelete==1?'是':'否')+"</td>"+
		 					"	<td>"+(data[i].isTop==1?'是':'否')+"</td>"+
		 					"	<td>"+(data[i].isPush==1?'是':'否')+"</td>"+
		 					"	<td>"+(data[i].isComment==1?'是':'否')+"</td>"+
		 					"	<td>"+(data[i].issuance==1?'是':'否')+"</td>"+
		 					"	<td class='tmui-tips' tip='删除'><a href='javascript:void(0);' data-opid='"+data[i].id+"' onclick='bonAdmin.remove(this)'><i class='fa fa-fw  fa-trash'></i></a></td>"+
		 					"</tr>";
		 				}
		 				
		 				if(numss==0){
		 					var xxx="<select id=\"ssort\">"+
			 				"	<option value=\"asc\">升序</option>"+
			 				"	<option value=\"desc\" selected=\"selected\">降序</option>"+
			 				"</select>"+
			 				"<table class=\"tztab\" id=\"tztab\">"+
			 				"<thead>"+
			 				"   <tr>"+
			 				"	<th style=\"width: 100px;\">主键</th>"+
			 				"	<th>标题</th>"+
			 				"	<th style=\"width: 160px;\">创建时间</th>"+
			 				"	<th style=\"width: 40px;\">删除</th>"+
			 				"	<th style=\"width: 40px;\">置顶</th>"+
			 				"	<th style=\"width: 40px;\">精华</th>"+
			 				"	<th style=\"width: 40px;\">评论</th>"+
			 				"	<th style=\"width: 40px;\">发布</th>"+
			 				"	<th style=\"width: 80px;\">操作</th>"+
			 				"    </tr>"+
			 				"</thead>"+
			 				"	<tbody id='tbody' data-model='content'>"+
			 				"		<tr>"+
			 				"			<td id=\"loading\" colspan=\"30\"></td>"+
			 				"		</tr>"+
			 				"	</tbody>"+
			 				"</table>"+
			 				"<div class=\"cpage\"></div>";
			 				
			 				$("#xxx-content").append(xxx);
		 				}
		 				$("#tbody").html(html);
		 				
		 				++numss;
		 				mark = true;
		 			}
		 		});
			}
		};
	</script>
</body>
</html>
