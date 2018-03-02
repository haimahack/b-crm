package com.haima.crm.service.role;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haima.crm.entity.Role;
import com.haima.crm.entity.TzParams;


/**
 * 角色管理
 *@author: haima
 *@fileName: Role.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月22日  22:13:19
 */
public interface IRoleService {

	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	List<Role> find(TzParams params);
	
	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	Role get(Long id);
	
	/**
	 * 获取总数
	 * @param params
	 * @return
	 */
	Long count(TzParams params);
	
	/**
	 * 保存
	 * @param role
	 * @return
	 */
	int save(Role role);
	
	/**
	 * 更新
	 * @param role
	 * @return
	 */
	int update(Role role);
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	int delete(TzParams params);
	
	/**
	 * 给用户分配角色
	 * 保存用户所对应的角色
	 * @param userId
	 * @param roleId
	 * @return
	 */
	int saveRoleUser(@Param("userId")Long userId,@Param("roleId")Long roleId);
	
	/**
	 * 给用户分配权限
	 * @param roleId
	 * @param permissionId
	 * @return
	 */
	int saveRolePermission(@Param("roleId")Long roleId,@Param("permissionId")Long permissionId);
	
	/**
	 * 删除一个角色下的所有权限
	 * @param roleId
	 * @return
	 */
	int deleteRolePermission(@Param("roleId")Long roleId);
	
	/**
	 * 查询没有分配角色的用户
	 * @param params
	 * @return
	 */
	List<HashMap<String, Object>> findFilterUsers(TzParams params);
	
	/**
	 * 查询没有分配角色用户的总数
	 * @param params
	 * @return
	 */
	Long countFilterUsers(TzParams params);
	
	/**
	 * 根据用户主键查询出对应的角色
	 * @param userId
	 * @return
	 */
	HashMap<String, Object> findRolesByUserId(Long userId);
}
