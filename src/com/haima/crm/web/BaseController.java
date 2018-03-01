package com.haima.crm.web;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.haima.crm.util.DateFormateEditor;


@Controller
@SessionAttributes("user")
public class BaseController {
	
	//第一种：获取通用的请求对对象的方式
//	@Autowired
//	protected HttpServletResponse response;
//	@Autowired
//	protected HttpServletRequest request;
	
	//第二种
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;
	
	@ModelAttribute  
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response,HttpSession session){  
		this.request = request;
	    this.response= response;  
	    this.session = session;
	}
	
	
	/*统一处理日期格式化的问题*/
	@InitBinder
	public void initDataBinder(WebDataBinder dataBinder){
		dataBinder.registerCustomEditor(Date.class, new DateFormateEditor());
	}
	
	/*统一异常处理*/
//	@ExceptionHandler(RuntimeException.class)
//	public String handlerException(RuntimeException runtimeException){
//		return "redirect:/error.jsp";
//	}
	
//	/*统一异常处理*/
//	@ExceptionHandler(NullPointerException.class)
//	public String hand(RuntimeException runtimeException){
//		return "redirect:/nulll.jsp";
//	}
	
		
	public void jsonToString(Object obj){
		try {
			PrintWriter writer = response.getWriter();
//			PrintWriter writer = response.getWriter();
			writer.print(JSONUtil.serialize(obj));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

