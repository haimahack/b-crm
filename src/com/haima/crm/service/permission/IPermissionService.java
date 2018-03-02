package com.haima.crm.service.permission;

import java.util.HashMap;

import com.haima.crm.entity.Permission;
import com.haima.crm.entity.TzParams;


/**
 * 权限管理
 *@author: haima
 *@fileName: Permission.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月22日  20:21:15
 */
public interface IPermissionService {

	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	HashMap<String, Object> find(TzParams params);
	
	/**
	 * 根据主键获取内容
	 * @param id
	 * @return
	 */
	Permission get(Long id);
	
	/**
	 * 获取总数
	 * @param params
	 * @return
	 */
	Long count(TzParams params);
	
	/**
	 * 保存内容
	 * @param permission
	 * @return
	 */
	int save(Permission permission);
	
	/**
	 * 更新内容
	 * @param permission
	 * @return
	 */
	int update(Permission permission);
	
	/**
	 * 删除内容
	 * @param params
	 * @return
	 */
	int delete(TzParams params);
}
