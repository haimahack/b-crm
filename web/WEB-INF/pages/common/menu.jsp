<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div id="menu">
	  <div id="maskT" class="mask">
	<div class="cat">
</div>
  </div>
	  <div id="maskB" class="mask">
	<div class="cat"></div>
  </div>
	  <div id="maskL" class="mask">
	<div class="cat"></div>
  </div>
	  <div id="maskR" class="mask">
	<div class="cat"></div>
  </div>

  <ul class="m-up">
		<li>
			<a href="javascript:;" id="m-left-go">
				<div class="icon"></div>
				<p>往后瞅瞅</p>
			</a>
		</li>
		<li>
			<a href="javascript:;" id="m-right-go">
				<div class="icon"></div>
				<p>往前看看</p>
			 </a>
		</li>
  </ul>

  <div class="line"></div>

  <ul class="m-down">
		<li>
			<a href="${adminPath}/content/export" id="m-export-go">
				<div class="icon"></div>
				<p>导出瞧瞧</p>
			</a>
		</li>
		<li>
			<a href="javascript:;" id="m-del-go">
				<div class="icon"></div>
				<p>删除选中</p>
			</a>
		</li>
  </ul>
</div>

<script type="text/javascript">
	$("#m-cnt").on("contextmenu",function(ev){
		ev=ev||event;
		//显示鼠标指针的坐标		
		var x = ev.clientX;
		var y = ev.clientY;
		
		//alert(x+"=="+y);
		var menu = document.querySelector("#menu");
		menu.style.display="block";
		menu.style.left = (x-30)+"px";
		menu.style.top = (y-30)+"px";
	
		ev.preventDefault();
	});
	
	$("#m-cnt").on("click",function(){
		var menu = document.querySelector("#menu");
		menu.style.display="none";
	});
	
	/*后退*/
	$("#m-left-go").on("click",function(){
		//总数
		var totalcur = $(".tmui_page_itemcount").text();
		//每页显示数
		var selcur = $(".tm_psize_go").val();
		//页数
		var page = Math.ceil(totalcur/selcur);
		//当前页
		var cur = $("#tm_pagego").val();
		var num = parseInt(cur);
		if((num-1)<=0){
			loading("已经退无可退了...",5);
			return;
		} else{
			$("#tm_pagego").val(num-1);
			$(".tm_go").trigger("click");
		};
		
		//alert(cur+"==="+selcur+"==="+totalcur+"==="+page);
		//tzAdmin.loadData(cur,selcur);
	});
	
	/*前进*/
	$("#m-right-go").on("click",function(){
		//总数
		var totalcur = $(".tmui_page_itemcount").text();
		//每页显示数
		var selcur = $(".tm_psize_go").val();
		//页数
		var page = Math.ceil(totalcur/selcur);
		//当前页
		var cur = $("#tm_pagego").val();
		var num = parseInt(cur);
		if((num+1)>page){
			loading("已经是最后一页了...",5);
			return;
		} else{
			$("#tm_pagego").val(num+1);
			$(".tm_go").trigger("click");
		};
		
		//alert(cur+"==="+selcur+"==="+totalcur+"==="+page);
		//tzAdmin.loadData(cur,selcur);
		//$("#m-right-go").attr("href","javascript:history.forward();");
		//alert($("#m-left-go").attr("href"));
	});

	/*导出*/
    $("#m-export-go").on("click",function(){
    	loading("正在导出Excel,请稍候...",6);
		//var model = $("#tbody").data("model");
		//alert(model);
		// return;
		//tzAjax.request({
		//    path:basePath,
		//    model:"content",
		//    method:"create",
		//    success:function(data){
		//
		//    }
		//});
    });


	//获取已选的复选框的值
    var checkedArray = new Array();//放已经选择的checkbox的value
    var count;//已经选择的个数

	/*删除选中*/
	$("#m-del-go").on("click",function(){
		checkedArray.length=0;
        count=0;
        $('[name="h-item"]:checkbox:checked').each(function() {
            checkedArray.push($(this).val());
            count++;
            
        });
        if (checkedArray.length==0) {
        	loading("没有选中的项...",5);
            return;
        }else{
        	$.tzConfirm({title:"删除提示",content:"你确定删除所选吗?",callback:function(ok){
        		if(ok){
		        	//var $this2 = $(this);
		            //alert($this2);
		        	//alert(checkedArray);
		        	//var arrStr = checkedArray.toString();
		        	//alert(arrStr);
		        	var model = $("#tbody").data("model");
		        	var dlen=checkedArray.length;

		        	$(checkedArray).each(function(i,val){
		        		val = parseInt(val);
		        		var params={};
						params["id"] = val;

						clearTimeout(this.timers2);
		    			timers2 = setTimeout(function(){
		    				loading("执行删除中,请稍后...",3);
		    				$.ajax({
		    					type:"post",
		    					url:adminPath+"/"+model+"/delete",
		    					data:params,
		    					success:function(data){

		    						if("success"==data){
		    							loading("共 "+dlen+" 条数据删除成功...");

		    							$('[name="h-item"]:checkbox:checked').each(function() {
			    							var $this = $(this);
			    				            //alert($this);
		    								var n = $this.parents("tr").index();  // 获取checkbox所在行的顺序
		    								//alert(n);
		    								var num=parseInt(n);
		    						        $("table#tztab").find("tr:eq("+(num+1)+")").remove();


		    							 });
		    						}
		    					}
		    				});
		    			},200);
		        	});
        		}
        	}});
        }//end else

        $('#checkedAll').prop('checked',false);
        /*
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
						}
					}
				});
			},200);
        */
		
	});
    
</script>
