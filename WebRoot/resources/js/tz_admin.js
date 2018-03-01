$(function(){
	$(".tmui-tips").tmTip();
});

function loading2(target,mark){
	$.loading({target:$(target),mark:1});
};

var tzAdmin={
	//pageNo,pageSize全部使用模板
	timer:null,
	ltimer:null,
	initPage:function(itemCount){
		//alert(itemCount);
		//var itemcount = $("#tbody").data("itemcount");//部分使用模板
		$(".cpage").tzPage(itemCount,{
			num_edge_entries:5,//边缘页数
			num_display_entries:6,//主体页数
			num_edge_entries:5,
			current_page:0,
			showGo:true,
			showSelect:true,
			items_per_page:20,//每页显示多少条
			prev_text:"前一页",
			next_text:"后一页",
			ellipse_text:"...",
			callback:function(pageNo,psize){
				
				tzAdmin.loadData(pageNo,psize);
				
				/*//部分使用模板
				$.ajax({
					type:"post",
					url:adminPath+"/content/template",
					success:function(data){
						//将模板中的数据放到tbody中，避免拼接HTML
						$("#tbody").html(data);
					}
				});
				*/
			}
		});
	},
	//全部使用模板
	loadData:function(pageNo,pageSize,callback){
		var keyword=$("#keywords").val();
		var model = $("#tbody").data("model");
		var $this = this;
		
		clearTimeout(this.ltimer);
		this.ltimer=setTimeout(function(){
			$.ajax({
				type:"post",
				beforeSend:function(){loading2($("#loading"),4);},
				url:adminPath+"/"+model+"/template",
				data:{pageNo:pageNo*pageSize,pageSize:pageSize,keyword:keyword},
				success:function(data){
					var $data = $(data);
					//将模板中的数据放到tbody中，避免拼接HTML(复杂页面拼接耗时耗力，不利于维护,简单的可以使用拼接)
					$("#tbody").html(data);
					//加载时间插件
					$(".tmui-tips").tmTip();
					var itemCount = $data.find("#itemCount").val();
					//搜索关键词高亮
					if(isNotEmpty(keyword))
						$this.highligter(keyword);
					
					if(callback)
						callback(itemCount);
				}
			});
		},500);
		
	},
	highligter:function(key){
		$("#tbody").find(".tmui-keys").each(function(){
			var text = $(this).text();
			//alert(text);
			//替换一个
			//var nt =text.replace(key,"<span class='red'>"+key+"</span>")
			//全局替换("ig"表示忽略大小写全局替换)
			var regex = new RegExp(key,"ig");
			var nt =text.replace(regex,"<span class='red'>"+key+"</span>")
			$(this).html(nt);
		});
	},
	change:function(){
		$("#keywords").on("change",function(){
			tzAdmin.loadData(0,8,function(itemCount){
				tzAdmin.initPage(itemCount);
			});
		});
	},
	search:function(){
		var keyword=$("#keywords").val();
		
		if(isEmpty(keyword)){
			loading("请输入关键词...",4);
			$("#keywords").focus();
			return;
		}
		tzAdmin.loadData(0,8,function(itemCount){
			tzAdmin.initPage(itemCount);
		});
	},
	op:function(obj){
		//alert(11);
		var $this = $(obj);
		var opid = $this.attr("opid");
		var mark = $this.attr("mark");
		var val = $this.attr("val");
		var model = $("#tbody").data("model");
		//alert(opid);
		//alert(model);
		
		var params={};
		params[mark] = val;
		params["id"] = opid;
		
		$this.removeData();
		//alert(JSON.stringify(params));
		clearTimeout(this.timer);
		this.timer = setTimeout(function(){
			loading("执行更新中,请稍后...",3);
			$.ajax({
				type:"post",
				url:adminPath+"/"+model+"/update",
				data:params,
				success:function(data){
					if("success"==data){
						loading("更新成功...",3);
						$this.attr({
							opid:opid,
							val:val==0?1:0,
						}).removeClass().addClass(val==0?"red":"green").text(val==0?"否":"是");
					}
				}
			});
		},200);
	},
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
							}
						}
					});
				},1000);
				
			}
		}});
		
	},
	sleep:function(n){//n表示的毫秒数
		var start = new Date().getTime();
        while (true){
        	var end = new Date().getTime();
        	if ( (end - start) > n)
        		break;
        }
	}
	
};




//自己封装 的ajax
var tzAjax = {
	request: function(options,params){
		//继承参数
		var opts = $.extend({},{
			path:"",
			type:"post",
			model:"",
			method:"",
			params:"",
			before:function(){loading("请稍后,数据执行中...",4);},
			success:function(){},
			error:function(){loading("哎呀,出错了...",6);},
			logout:function(){}
		},options);
		//url拼接
		if(!opts.url){
			opts.url = opts.path+"/sys/"+opts.model+"/"+opts.method+(opts.params?"?"+opts.params:"");
			//opts.url = opts.path+"/"+opts.model+"/"+opts.method;
		}
		//alert(opts.url);
		//alert(opts.path);
		$.ajax({
			type:opts.type,
			url:opts.url,
			beforeSend:opts.before,
			error:opts.error,
			data:params,
			success:function(data){
				//alert(data);
				//loading("remove");
				if(data=="logout"){
					if(opts.logout)
						opts.logout();
					//第一种方案：登陆弹出框
					//alert("登陆弹出框在此弹出");
					//第二种方案：直接跳转
					window.location.href = opts.path+"/login";
				}else{
					if(opts.success)
						opts.success(data);
				}
			}
		});
	}	
};

