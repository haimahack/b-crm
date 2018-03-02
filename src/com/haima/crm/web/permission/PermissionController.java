package com.haima.crm.web.permission;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haima.crm.entity.TzParams;
import com.haima.crm.service.permission.IPermissionService;

/**
 * 权限管理
 *@author: haima
 *@fileName: Permission.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月22日  20:21:15
 */

@Controller
@RequestMapping("/sys/permission")
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("list")
	public String list(){
		return "permission/list";
	}
	
	@ResponseBody
	@RequestMapping("root")
	public HashMap<String, Object> root(TzParams params){
		return permissionService.find(params);
	}
	
}
