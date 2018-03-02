<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title> add-content -- h-crm </title>
  <meta charset="utf-8">

  <style type="text/css">
	*{margin:0;padding:0;}

	h2{font-weight:bold;}
	.con-plus{font-size:16px;height:400px;
		margin:10px auto;text-align:center;overflow:auto;
	}
	#con-box{text-align:center;}
	#con-box input:not(.btnR){font-size:16px;width:260px;height:26px;}
	#con-box textarea{width:320px;height:150px;}
	p{margin:10px;}
	.btnR{width:200px;height:45px;text-align:center;font-size:20px;}
  </style>
 </head>

 <body>
	<div class="con-plus">
		<h2 id="con-error"></h2>
		<div id="con-box">
			<p>
				标题:<input type="text" onkeyup maxlength="255" name="title" id="title" onblur="this.value=this.value.trim()" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').trim())" autofocus="autofocus" >
			</p>
			<p>
				子标题:<input type="text" maxlength="255" name="subTitle" id="subTitle" onblur="this.value=this.value.trim()" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').trim())"></input>
			</p>
			<p>
				内容:
				<textarea name="content" id="content" onblur="this.value=this.value.trim()" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').trim())"></textarea>
			</p>
			<p>
				静态链接:
				<input type="text" maxlength="1200" name="staticLink" id="staticLink" onblur="this.value=this.value.trim()" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').trim())">
			</p>
			<p>
				标签:
				<input type="text" maxlength="255" name="tag" id="tag" onblur="this.value=this.value.trim()" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').trim())">
			</p>
			<p>
				封面图片:
				<input type="text" maxlength="1200" name="coverImg" id="coverImg" onblur="this.value=this.value.trim()" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').trim())">
			</p>
			<p>
				小图片:
				<input type="text" maxlength="1200" name="smallImg" id="smallImg" onblur="this.value=this.value.trim()" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').trim())">
			</p>
			<p>
				封面图片宽度:
				<input type="text"  name="coverWidth" id="coverWidth">
			</p>
			<p>
				封面图片高度:
				<input type="text"  name="coverHeight" id="coverHeight">
			</p>
			<p>
				内容类型:
				<select id="type" name="type">
					<option value="">-- 选择内容类型 --</option>
					<option value="t1">文章</option>
					<option value="t2">音乐</option>
					<option value="t3">视频</option>
					<option value="t4">图片</option>
					<option value="t5">其他</option>
				</select>
			</p>
			<p>
				<input class="btnR" type="button" onclick="saveContent()" value="保存内容">
			</p>
		</div>
	</div>

	<script type="text/javascript">
		function $(id){
			return document.getElementById(id);
		}


		if (!String.prototype.trim){

			/*---------------------------------------
			* 清除字符串两端空格，包含换行符、制表符
			*---------------------------------------*/
			String.prototype.trim = function () { 
				return this.replace(/(^[\s\t\n]*)|([\s\t\n]*$)/g,"");
			}
			 
		}

		//匹配url
		function isUrl(s){
			var reg=/(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g; 
			return reg.test(s);
		}
		//匹配正整数
		function isInteger(s){
			var reg=/^[0-9]*[1-9][0-9]*$/;
			return reg.test(s);
		}
		//匹配正浮点数
		function isFloat(s){
			var reg=/^[0-9]+(?:.[0-9]{0,2})?$/;
		}


		//保存内容
		function saveContent(){
			var conDom = $("con-box");
			//alert(conDom);

			//id获取元素值
			var title = $("title").value;
			var subTitle = $("subTitle").value;
			var content = $("content").value;
			var staticLink=$("staticLink").value;
			var tag=$("tag").value;
			var coverImg=$("coverImg").value;
			var smallImg=$("smallImg").value;
			var coverWidth=$("coverWidth").value;
			var coverHeight=$("coverHeight").value;

			//alert(title);
			/*title start*/
			if(title==""){
				$("con-error").innerHTML="请输入标题...";
				$("title").value="";
				$("title").focus();
				return false;
			}
			if(title!="" && title.length>255){
				$("con-error").innerHTML="标题太长...";
				$("title").select();
				return false;
			}
			/*title end*/

			/*subTitle start*/
			if(subTitle==""){
				$("con-error").innerHTML="请输入子标题...";
				$("subTitle").value="";
				$("subTitle").focus();
				return false;
			}
			//alert(subTitle.length);
			if(subTitle!="" && subTitle.length>255){
				$("con-error").innerHTML="子标题太长...";
				$("subTitle").select();
				return false;
			}
			/*subTitle end*/
			
			if(content==""){
				$("con-error").innerHTML="请输入内容...";
				$("content").value="";
				$("content").focus();
				return false;
			}

			if(staticLink==""){
				$("con-error").innerHTML="请输入静态链接...";
				$("staticLink").value="";
				$("staticLink").focus();
				return false;
			}
			if(staticLink!="" && isUrl(staticLink)==false){
				$("con-error").innerHTML="静态链接地址不符要求...";
				$("staticLink").style.border="#f00 1px solid";

				return false;
			}

			if(tag==""){
				$("con-error").innerHTML="请输入标签...";
				$("tag").value="";
				$("tag").focus();
				return false;
			}
			if(tag!="" && tag.length>255){
				$("con-error").innerHTML="标签太长...";
				$("tag").select();
				return false;
			}


			//选择框 select 的值
			var svalue=$("type").value;
			//获取当前选中的索引
			var selectIndex = $("type").selectedIndex;
			//获取索引对应的option
			var optionDom = $("type").options[selectIndex];
			//获取value和文本
			var v = optionDom.value;
			var t = optionDom.text;//innerText -- 火狐不支持
			//alert(v+"=="+t);


			if(svalue==""){
				$("con-error").innerHTML = "请选择内容类型...";
				return false;
			}

			
			
			//单选按钮
			/*
			var radioDoms = formDom.sex;
			var rlen = false;
			var rvalue = "";
			for(var i=0;i<radioDoms.length;i++){
				if(radioDoms[i].checked){
					rvalue=radioDoms[i].value;
					rlen=true;
					break;
				}
			}
			*/

			//复选按钮 carr.toString();
			/*
			var checkDoms = formDom.hobbys;
			var clen=false;
			var carr=[];
			for(var i=0;i<checkDoms.length;i++){
				if(checkDoms[i].checked){
					carr.push(checkDoms[i].value);
					clen=true;
				}
			}
			*/
		}
	</script>
 </body>
</html>