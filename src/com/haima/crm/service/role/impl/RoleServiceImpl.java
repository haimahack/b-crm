package com.haima.crm.service.role.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haima.crm.dao.role.IRoleMapper;
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
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleMapper roleMapper;
	
	
	@Override
	public List<Role> find(TzParams params) {
		if(TmStringUtils.isEmpty(params.getOrder()))
			params.setOrder("create_time DESC");
		return roleMapper.find(params);
	}
	
	@Override
	public Role get(Long id) {
		return roleMapper.get(id);
	}
	
	@Override
	public Long count(TzParams params) {
		return roleMapper.count(params);
	}
	
	@Override
	public int save(Role role) {
		if(role!=null){
			roleMapper.save(role);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int update(Role role) {
		if(role!=null){
			roleMapper.update(role);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int delete(TzParams params) {
		if(params!=null){
			roleMapper.delete(params);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int saveRoleUser(Long userId, Long roleId) {
		if(userId!=null&&roleId!=null){
			roleMapper.saveRoleUser(userId, roleId);
			return 1;
		}else{
			return 0;
		}
	}
	
	@Override
	public int saveRolePermission(Long roleId, Long permissionId) {
		if(roleId!=null&&permissionId!=null){
			roleMapper.saveRolePermission(roleId, permissionId);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int deleteRolePermission(Long roleId) {
		if(roleId!=null){
			roleMapper.deleteRolePermission(roleId);
			return 1;
		}else{
			return 0;
		}
	}
	
	@Override
	public List<HashMap<String, Object>> findFilterUsers(TzParams params) {
		return roleMapper.findFilterUsers(params);
	}

	@Override
	public Long countFilterUsers(TzParams params) {
		if(params!=null){
			return roleMapper.countFilterUsers(params);
		}else{
			return -1L;
		}
	}

	@Override
	public HashMap<String, Object> findRolesByUserId(Long userId) {
		return roleMapper.findRolesByUserId(userId);
	}

}
