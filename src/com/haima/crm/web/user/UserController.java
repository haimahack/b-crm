package com.haima.crm.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.haima.crm.entity.TzParams;
import com.haima.crm.entity.User;
import com.haima.crm.service.user.IUserService;

/**
 * 用户管理
 *@author: haima
 *@fileName: UserController.java
 *@packageName: com.haima.crm.web.user
 *@date: 2018年2月19日 上午11:43:10
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("list")
	public String list(){
		return "user/list";
	}
	
	@RequestMapping("template")
	public ModelAndView template(TzParams params){
		ModelAndView modelAndView = new ModelAndView();
		List<User> users = userService.find(params) ;
		Long count = userService.count(params);
		modelAndView.setViewName("user/template");
		modelAndView.addObject("datas",users);
		modelAndView.addObject("itemCount",count);
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(User user){
		int code = userService.update(user);
		if(code == 1)
			return "success";
		else
			return "fail";
		
	}
	
	@ResponseBody
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(TzParams params){
		int code = userService.delete(params);
		if(code == 1)
			return "success";
		else
			return "fail";
		
	}
}
