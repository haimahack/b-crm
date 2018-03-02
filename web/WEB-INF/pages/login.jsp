<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../pages/common/public.jsp" %>   

<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>login - h-crm</title>
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/login-default.css">
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/login-styles.css">
	<%@ include file="../pages/common/taglib.jsp" %>
	
	<style type="text/css">
	
		/*底部 start*/
		#mid-box{margin-top:30px;height: 5px;background: #ac3;width:100%;position: relative;}
		#mid-box span{background: #0ff;height: 5px;line-height:5px;position: absolute;top: 0;left: 50%;}
		
		#mid-box span:nth-child(1){background: #111;}
		#mid-box span:nth-child(2){background: #ff0;}
		#mid-box span:nth-child(3){background: #f0f;}
		
		/*底部动画开始*/
		@keyframes move2{
			0%{width:0%}
			100%{margin-left: -50%;width: 100%;}
		}
		
		#mid-box .move2{animation: move2 1s ease infinite both alternate;}
		#mid-box .d1{animation-delay: 0.2s;}
		#mid-box .d2{animation-delay: 0.6s;}
		#mid-box .d3{animation-delay: 0.4s;}
		/*底部动画结束 >>> 底部 start*/
	</style>
</head>

<body>
	<div class="htmleaf-container">
	
		<div class="login-wrap">
			<div class="login-html">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">注册</label>
				<div class="login-form">
					<div class="sign-in-htm">
						<div class="group">
							<label for="user" class="label">账 号 / 邮 箱</label>
							<input id="uname" type="text" class="input" maxlength="40" autofocus="autofocus">
						</div>
						<div class="group">
							<label for="pass" class="label">密 码</label>
							<input id="pwd" type="password" class="input" maxlength="40">
						</div>
						<div class="group">
							<input id="check" type="checkbox" class="check">
							<label for="check"><span class="icon"></span> 保持登录状态</label>
						</div>
						<div class="group">
							<input type="button" onclick="tz_login(this)" class="button" id="h-entry" value="登 录">
						</div>
						<div class="foot-lnk">
							<a href="#forgot">忘记密码?</a>
						</div>
						<div id="mid-box">
							<span class="move2"></span>
							<span class="move2 d1"></span>
							<span class="move2 d2"></span>
							<span class="move2 d3"></span>
						</div>
					</div>
					<div class="sign-up-htm">
						<div class="group">
							<label for="user" class="label">账 号</label>
							<input id="u-uname" type="text" class="input" maxlength="40">
						</div>
						<div class="group">
							<label for="pass" class="label">密 码</label>
							<input id="u-pwd" type="password" class="input" maxlength="40">
						</div>
						<div class="group">
							<label for="pass" class="label">确 认 密 码</label>
							<input id="r-pwd" type="password" class="input" maxlength="40">
						</div>
						<div class="group">
							<label for="email" class="label">邮 箱 地 址</label>
							<input id="email" type="text" class="input" maxlength="50">
						</div>
						<div class="group">
							<input type="button" class="button" id="h-enroll" value="注 册">
						</div>
						<div id="mid-box">
							<span class="move2"></span>
							<span class="move2 d1"></span>
							<span class="move2 d2"></span>
							<span class="move2 d3"></span>
						</div>
						<div class="foot-lnk">
							<label for="tab-1">Already Member?</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<script type="text/javascript">
		
		$(function(){
	 		$(document).keydown(function(e){
	 			if(e.keyCode==13){
	  				$("#h-entry").trigger("click");
	  			}
	 		});//end keydown
	 		
	 		//alert("URL的来源---"+document.referrer);
	 		//alert(tzMap.get("xxx_account_xxx",1));
	 		$("#uname").keyup(function(){
	 			tzMap.put("x_user_name_x",$(this).val(),1);
	 		});//end keyup
	 		
	 		var user_name = tzMap.get("x_user_name_x",1);
	 		if(user_name)
	 			$("#uname").val(user_name);
		});
		
	 	//登录
		function tz_login(obj){
			var uname = $("#uname").val();
	 		var pwd = $("#pwd").val();
	 		if(isEmpty(uname)){
	 			loading("请输入账号",4);
	 			$("#uname").focus();
	 			return;
	 		}
	 		if(isEmpty(pwd)){
	 			loading("请输入密码",4);
	 			$("#pwd").focus();
	 			return;
	 		}
	 		//alert(uname+"=="+pwd);
	 		/*
	 		email:^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$
	 		*/
	 		var params={};
	 		var reg = /^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$/;
	 		if(reg.test(uname))
	 			params ={email:uname,pwd:pwd};
	 		else
	 			params = {uname:uname,pwd:pwd};
	 		//去除事件
	 		$(obj).removeAttr("onclick").val("登 录 中 ...").css({"background":"#555","color":"#ff0","font-weight":"bold"});
	 		
	 		tzAjax.request({
	 			url:basePath+"/logined",
	 			error:function(){
	 				//将去除的事件重新绑定
	 				$(obj).attr("onclick","tz_login(this)").val("登 录").css({"background":"#1161ee","color":"#fff"});
	 			},
	 			success:function(data){
	 				//alert(data);
	 				//将去除的事件重新绑定 //{"background":"#1161ee","color":"#fff"}
	 				$(obj).attr("onclick","tz_login(this)").val("登 录").css({"background":"#1161ee","color":"#fff"});
	 				if(data=="error" || data=="null"){
	 					$("#pwd").val("");
	 					$("#uname").select().focus();
	 					loading("请填写账号或者密码...",4);
	 					return;
	 				}else if(data=="fail"){
	 					//账号或者密码输入错误
	 					$("#pwd").val("").focus();
	 					loading("请输入正确的账号和密码!!!",4);
	 					return;
	 				}else if(data=="forbidden"){
	 					$("#pwd").val("");
	 					$("#uname").select().focus();
	 					loading("您的账号已被禁用...",4);
	 					return;
	 				}else{
	 					$(obj).val("登 录 成 功 !").css({"background":"#555","color":"#f0f","font-weight":"bold"});
	 					//获取请求地址
	 					var refer=document.referrer;
	 					//alert(refer);
	 					//alert(refer+"=="+(refer===login));
	 					var logins = basePath+'/login';
	 					//登陆成功后跳到之前退出前的访问页面
	 					var go =(refer===logins||refer=='')?basePath+'/sys/index':refer;
	 					//alert(go);
	 					window.location.href =go;
	 				}
	 			}
	 		},params);
	 	}//end tz_login
	 	
	 	/*
		//登录
		function tz_login(obj){
	  		var uname = $("#uname").val();
	  		var pwd = $("#pwd").val();
	  		var params = {uname:uname,pwd:pwd};
	  		$.ajax({
	  			type:"post",
	  			url:basePath+"/logined",
	  			data:params,
	  			success:function(data){
	  				alert(data);
	  				if(data=="error" || data=="null"){
	  					loading("请填写账号或者密码...",4);
	  					$("#pwd").val("");
	  					$("#uname").select();
	  				}else if(data=="fail"){
	  					//账号或者密码输入错误
	  					$("#pwd").val("");
	  					$("#uname").select();
	  					loading("请输入正确的账号和密码!!!",4);
	  				}else if(data=="forbidden"){
	  					$("#pwd").val("");
	  					$("#uname").select();
	  					loading("您的账号已被禁止...",4);
	  				}else{
	  					//登陆成功
	  					window.location.href = adminPath+"/index";
	  				}
	  			}
	  		});
	  	};//end tz_login
		*/
	</script>
</body>
</html>