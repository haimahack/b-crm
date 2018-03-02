package com.haima.crm.web.role;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.haima.crm.entity.Role;
import com.haima.crm.entity.TzParams;
import com.haima.crm.service.role.IRoleService;
import com.haima.crm.util.TmStringUtils;

/**
 * 角色管理
 *@author: haima
 *@fileName: Role.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月22日  22:13:19
 */

@Controller
@RequestMapping("/sys/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("list")
	public String list(){
		return "role/list";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="saveUserRole")
	public String saveUserRole(HttpServletRequest request){
		
		String users = request.getParameter("users");
		String rid = request.getParameter("rid");
		
		if(TmStringUtils.isNotEmpty(users) && TmStringUtils.isNotEmpty(rid)){
			String[] us = users.split(",");
			
			for (String s : us) {
				roleService.saveRoleUser(Long.parseLong(s), Long.parseLong(rid));
			}
			return "success";
		}else{
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="saveRolePermission")
	public String saveRolePermission(HttpServletRequest request){
		
		String permissions = request.getParameter("permissions");
		String roleId = request.getParameter("rid");
		
		if(TmStringUtils.isNotEmpty(permissions) && TmStringUtils.isNotEmpty(roleId)){
			String[] ps = permissions.split(",");
			Long id = Long.parseLong(roleId);
			/** 先删除角色对应的所有权限，然后再重新分配新的权限*/
			roleService.deleteRolePermission(id);
			for (String s : ps) {
				roleService.saveRolePermission(id,Long.parseLong(s));
			}
			return "success";
		}else{
			return "fail";
		}
	}
	
	@RequestMapping("user/{rid}")
	public ModelAndView user(@PathVariable("rid")Long roleId,TzParams params){
		ModelAndView modelAndView = new ModelAndView();
		params.setIsDelete(0);//所有未删除的
		params.setRoleId(roleId);
		params.setPageSize(30);
		List<HashMap<String, Object>> users = roleService.findFilterUsers(params);
		Long totalcount = roleService.countFilterUsers(params);
		
		modelAndView.addObject("datas",users);
		modelAndView.addObject("count",totalcount);
		modelAndView.setViewName("role/user");
		return modelAndView;
	}
	
	@RequestMapping("permission/{rid}")
	public ModelAndView permission(@PathVariable("rid")Integer roleId,TzParams params){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role/permission");
		return modelAndView;
	}
	
	@RequestMapping("template")
	public ModelAndView template(TzParams params){
		ModelAndView modelAndView = new ModelAndView();
		List<Role> roles = roleService.find(params) ;
		Long count = roleService.count(params);
		
		modelAndView.addObject("datas",roles);
		modelAndView.addObject("itemCount",count);
		modelAndView.setViewName("role/template");
		
		return modelAndView;
	}
}
