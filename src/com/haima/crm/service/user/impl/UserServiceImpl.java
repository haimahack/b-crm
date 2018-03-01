package com.haima.crm.service.user.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haima.crm.dao.user.IUserMapper;
import com.haima.crm.entity.TzParams;
import com.haima.crm.entity.User;
import com.haima.crm.service.user.IUserService;
import com.haima.crm.util.TmStringUtils;

/**
 * 用户管理
 *@author: haima
 *@fileName: User.java
 *@date: 2018年02月08日  00:31:56
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserMapper userMapper;
	
	@Override
	public User getLogin(TzParams params) {
		return userMapper.getLogin(params);	
	}
	
	@Override
	public List<User> find(TzParams params) {
		if(TmStringUtils.isEmpty(params.getOrder()))
			params.setOrder("create_time DESC");
		return userMapper.find(params);
	}
	
	@Override
	public User get(Long id) {
		return userMapper.get(id);
	}
	
	@Override
	public Long count(TzParams params) {
		return userMapper.count(params);
	}
	
	@Override
	public int save(User user) {
		if(user!=null){
			userMapper.save(user);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int update(User user) {
		if(user!=null){
			userMapper.update(user);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int delete(TzParams params) {
		if(params!=null){
			userMapper.delete(params);
			return 1;
		}else{
			return 0;
		}
	}
}
