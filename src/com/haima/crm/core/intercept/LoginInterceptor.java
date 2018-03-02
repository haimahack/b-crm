package com.haima.crm.core.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.haima.crm.core.constant.TzConstant;
import com.haima.crm.entity.User;
import com.haima.crm.util.TmStringUtils;


/**
 * 登录拦截
 *@author: haima
 *@fileName: LoginInterceptor.java
 *@packageName: com.haima.crm.core.intercept
 *@date: 2018年2月5日 下午7:40:58
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("登录拦截进来了..........");
		User user =(User)request.getSession().getAttribute(TzConstant.SESSION_USER);
		if(user!=null){
			return true;
		}else{
			//执行是一个ajax请求还是一个普通请求
			String requestType = request.getHeader("X-Requested-With");
			//如果是ajax请求
			if(TmStringUtils.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")){
				response.getWriter().print("logout");
			}else{
				response.sendRedirect(request.getContextPath()+"/login");
			}
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post请求结束执行的方法..........");
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("响应已经被渲染成功后执行的方法..........");
		
	}

}
