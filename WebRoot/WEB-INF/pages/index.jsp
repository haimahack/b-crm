<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/public.jsp" %>

<!DOCTYPE html>
<html lang="zh">
<head>
	<title>index - h-crm</title>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@ include file="../pages/common/taglib.jsp" %>
	
	<style rel="stylesheet" type="text/css">
		.page-tools {
			font-family:inherit;
			font-weight:normal;
			padding-left: 20px;
			position: relative
		}
		.sitetip {
			font-size: 14px;
			color: #0ff;
			margin-bottom: 12px;
		}
		.tarea {
			width: 99%;
			border: #e6e6e6 1px solid;
			border-color: #ddd #e6e6e6 #e6e6e6 #ddd;
			margin-bottom: 12px;
			box-shadow: inset 1px 1px 1px #eee;
			line-height: 18px;
			border-radius: 5px;
		}
		.tarea:focus {
			border-color: #ccc #ddd #ddd #ccc;
		}
		.subbtn .btn{border:none;background:#FF6100;color:#fff;cursor:pointer}
		.subbtn .btn:hover{background:#FF863E}
		.subbtn {
			text-align: center;
		}
	</style>
</head>
<body>
<div id="wrapper">
	<div class="overlay"></div>
	<!-- left页面 -->
    <%@include file="../pages/common/left.jsp" %>
    
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
				        <div class="page-tools">

						    <h2 class="sitetip">贴入要转换的HTML代码：</h2>
						    <textarea onmouseover="this.focus();this.select();" class="tarea form-control" id="code-content" style="float:none" rows="10">
1.请在输入框内贴入你需要转换的HTML代码
2.HTML转换工具，可以将HTML代码转换为JavaScript字符串
3.直接将你所要用程序输出的大串HTML代码贴到输入框中，即可一键生成
</textarea>
						    <div class="subbtn">
						        <input type="button" onclick="hMark.single(this)" value="转为单引号" class="btn">
						        <input type="button" onclick="hMark.double(this)" value="转为双引号" class="btn">
						        <label for="arrays-sel"><input type="checkbox" id="arrays-sel"> 数组拼接</label>
						    </div>
						    <h2 class="sitetip">转换结果：</h2>
						    <textarea onmouseover="this.focus();this.select();" class="tarea form-control" id="result" rows="10"></textarea>
						</div>
						
						<script type="text/javascript">
						
						var hMark= {
							single:function(){
								var isArraySel = $("#arrays-sel").prop("checked");
						        var htmlArr = $("#code-content").val().replace(/\\/g, "\\\\").replace(/\\/g, "\\/").replace(/\'/g, "\\\'").split('\n');
						        var len = htmlArr.length;
						        var outArr = [];
						        if (isArraySel) {
						            outArr.push("[");
						            jQuery.each(htmlArr, function (index, value) {
						                if (value !== "") {
						                    if (index === len - 1) {
						                        outArr.push("\'" + value + "\'");
						                    } else {
						                        outArr.push("\'" + value + "\',\n");
						                    }
						                }
						
						            });
						            outArr.push("].join(\"\");");
						        } else {
						            jQuery.each(htmlArr, function (index, value) {
						                if (value !== "") {
						                    if (index === len - 1) {
						                        outArr.push("\'" + value + "\';");
						                    } else {
						                        outArr.push("\'" + value + "\'+\n");
						                    }
						                }
						            });
						        }
						
						        $("#result").val(outArr.join(""));
							},
							double:function(){
								var isArraySel = $("#arrays-sel").prop("checked");
						        var htmlArr = $("#code-content").val().replace(/\\/g, "\\\\").replace(/\\/g, "\\/").replace(/\'/g, "\\\'").replace(/\"/g, "\\\"").split('\n');
						        var len = htmlArr.length;
						        var outArr = [];
						        if (isArraySel) {
						            outArr.push("[");
						            jQuery.each(htmlArr, function (index, value) {
						                if (value !== "") {
						                    if (index === len - 1) {
						                        outArr.push("\"" + value + "\"");
						                    } else {
						                        outArr.push("\"" + value + "\",\n");
						                    }
						                }
						
						            });
						            outArr.push("].join(\"\");");
						        } else {
						            jQuery.each(htmlArr, function (index, value) {
						                if (value !== "") {
						                    if (index === len - 1) {
						                        outArr.push("\"" + value + "\";");
						                    } else {
						                        outArr.push("\"" + value + "\"+\n");
						                    }
						                }
						            });
						        }
						        $("#result").val(outArr.join(""));
							}
						};
						</script>
						
				    </div>
				</div>
			</div><!--row end  -->
		</div>
	</div>
	<!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->

</body>
</html>