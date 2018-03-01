package com.haima.crm.service.permission.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haima.crm.dao.permission.IPermissionMapper;
import com.haima.crm.entity.Permission;
import com.haima.crm.entity.TzParams;
import com.haima.crm.service.permission.IPermissionService;

/**
 * 权限管理
 *@author: haima
 *@fileName: Permission.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月22日  20:21:15
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private IPermissionMapper permissionMapper;
	
	/**
	 * var root=[
					{name : "内容管理",url : "",opid : 1,pid : "root1"}, 
					{name : "用户管理",url : "",opid : 2,pid : "root2"}, 
					{name : "日志管理",url : "",opid : 3,pid : "root3"}, 
					{name : "主题框架4",url : "",opid : 4,pid : "root4"}, 
					{name : "主题框架5",url : "",opid : 5,pid : "root5"}, 
					{name : "主题框架6",url : "",opid : 6,pid : "root6"} 
				];
		children : {
			root1 : [ {
				name : "框架11",
				url : "javascript:void(0);",
				opid : 11,
				pid : "c11"
			},
			...
	 */
	private TreeMap<String,List<HashMap<String,Object>>> childrenMap = null;
	
	@Override
	public HashMap<String, Object> find(TzParams params) {
		//数据
		HashMap<String, Object> datas = new HashMap<>();
		//所有根权限
		List<Permission> permissions = permissionMapper.findRoot(params);
		//子权限
		childrenMap = new TreeMap<>();
		
		if(permissions!=null && permissions.size()>0){
			// new ArrayList<>() jdk1.7以上 泛型的菱形写法
			List<HashMap<String, Object>> maps=new ArrayList<>();
			
			for(Permission permission:permissions){
				HashMap<String, Object> map=new HashMap<>();
				map.put("name", permission.getPname());
				map.put("url", "javascrip:void(0);");
				map.put("opid", permission.getId());
				map.put("pid", permission.getId());
				
				maps.add(map);
				//递归
				getChildrenData(permission.getId());
			}
			datas.put("root", maps);
			datas.put("children", childrenMap);
			return datas;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 子权限
	 * @param pid
	 */
	private void getChildrenData(Long pid){
		//子权限
		List<Permission> childrenPermissions = permissionMapper.findChildren(pid);
		
		if(childrenPermissions!=null && childrenPermissions.size()>0){
			// new ArrayList<>() jdk1.7以上 泛型的菱形写法
			List<HashMap<String, Object>> maps=new ArrayList<>();
			
			for(Permission permission:childrenPermissions){
				HashMap<String, Object> map=new HashMap<>();
				map.put("name", permission.getPname());
				map.put("url", "javascrip:void(0);");
				map.put("opid", permission.getId());
				map.put("pid", permission.getId());
				
				maps.add(map);
				getChildrenData(permission.getId());
			}
			childrenMap.put(pid.toString(), maps);
		}
	}
	
	@Override
	public Permission get(Long id) {
		return permissionMapper.get(id);
	}
	
	@Override
	public Long count(TzParams params) {
		return permissionMapper.count(params);
	}
	
	@Override
	public int save(Permission permission) {
		if(permission!=null){
			permissionMapper.save(permission);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int update(Permission permission) {
		if(permission!=null){
			permissionMapper.update(permission);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int delete(TzParams params) {
		if(params!=null){
			permissionMapper.delete(params);
			return 1;
		}else{
			return 0;
		}
	}

}
