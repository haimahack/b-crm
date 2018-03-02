package com.haima.crm.dao.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haima.crm.entity.Permission;
import com.haima.crm.entity.TzParams;

/**
 * 权限管理
 *@author: haima
 *@fileName: Permission.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月22日  20:21:15
 */
public interface IPermissionMapper {
	
	/**
	 * 查找所有根权限
	 * @param params
	 * @return
	 */
	public List<Permission> findRoot(TzParams params);
	
	/**
	 * 查找所有子权限
	 * @param parentId
	 * @return
	 */
	public List<Permission> findChildren(@Param("pid")Long parentId);
	
	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	List<Permission> find(TzParams params);
	
	/**
	 * 根据主键获取
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
	 * 保存
	 * @param permission
	 * @return
	 */
	int save(Permission permission);
	
	/**
	 * 更新
	 * @param permission
	 * @return
	 */
	int update(Permission permission);
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	int delete(TzParams params);
}
