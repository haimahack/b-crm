<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
   <title>stat-statlist - h-crm</title>
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
			            
						<div class="channel"> 主页  》  统计报表 》  日志统计</div>
						<div class="cnt">
							<div class="date-picker">
								<input type="text"  id="date" onfocus="WdatePicker({onpicking:changeDate,dateFmt: 'yyyy-MM-dd', maxDate:'%y-%M-%d', readOnly:true})" class="Wdate tmui-tips" tip="点击选择日期"/>
								<i class="fa fa-fw fa-hand-o-left"></i>	
							</div>
							<div class="tabwrap" id="main" style="width:auto;height:450px;"></div>
						</div>
					</div><!--content end  -->
				</div>
			</div><!--row end  -->
		</div>
	</div><!-- /#page-content-wrapper -->
  	
</div><!-- /#wrapper -->

<script type="text/javascript">
	$(function(){
		$(".tmui-tips").tmTip();
		var d = new Date().format('yyyy-MM-dd');
		$("#date").val(d);
		loadData(d);
	});
	
	
	function loadData(date){
		tzAjax.request({
			path:basePath,
			model:"stat",
			method:"groupstats",
	//			before:function(){
	//				loading("请稍后，数据加载中...");
	//			},
	//			error:function(){
	//				loading("请求出错...",4);
	//			},
	//			logout:function(){
	//				//记录用户退出的时间和记录，写入到数据库中....
	//			},
			success:function(data){
				var json = {
					title:date+"年网站操作日志详情",	
					datas:parseData(data),
					timeLines:getTimeLine()
				};
				initChart("main",json);
			}
		},{"date":date});
	}
	
	// 点击日期的回调函数
	function changeDate(dp){
		var date = dp.cal.getNewDateStr();
		loadData(date);
	}
	
	//解析json数据
	function parseData(jsonArr){
		var jsonObj = {};
		for(var key in jsonArr){
			var num = jsonArr[key].num;
			var m = jsonArr[key].h;
			jsonObj["s"+parseInt(m)]= num;
		};
		var arr = [];
		for(var i=0;i<=23;i++){
			arr.push(jsonObj["s"+i]||0);
		}
		return arr;
	}
	
	//时间轴准备
	function getTimeLine(){
		var arr = [];
		for(var i=0;i<=23;i++){
			arr.push((i<10?"0"+i:i)+":00");
		}
		return arr;
	}
	
	function initChart(targetId,json){
		require.config({
	        paths: {
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
	            var option = {
	       	    title : {
	       	    	x:"center",
	       	        text: json.title,
	       	        subtext: '时间/操作数'
	       	    },
	       	    tooltip : {
	       	        trigger: 'axis'
	       	    },
	        	    //legend: {
	       	        //data:['意向','预购','成交']
	        	   // },
	       	    toolbox: {
	       	        show : true,
	       	        feature : {
	       	            mark : {show: true},
	       	            //dataView : {show: true, readOnly: false},
	       	            magicType : {show: true, type: ['line', 'bar']},
	       	            restore : {show: true},
	       	            saveAsImage : {show: true}
	       	        }
	       	    },
	       	    calculable : false,
	       	    xAxis : [
	       	        {	
	       	        	name:"时间段",
	       	            type : 'category',
	       	            data :json.timeLines
	       	        }
	       	    ],
	       	    yAxis : [
	       	        {
	       	        	name:"操作数",	
	       	            type : 'value'
	       	        }
	       	    ],
	       	    series : [
	       	        {
	       	            name:'操作日志',
	       	            type:'line',
	       	            smooth:true,
	       	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
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
	            	                    
				myChart.setOption(option);
				var ecConfig = require('echarts/config');
				myChart.on(ecConfig.EVENT.CLICK, eConsole);
	        }
	    );
	}

	function eConsole(param) {
	};
</script>
</body>
</html>
