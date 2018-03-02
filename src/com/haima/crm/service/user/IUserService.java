package com.haima.crm.service.user;

import java.util.List;

import com.haima.crm.entity.TzParams;
import com.haima.crm.entity.User;


/**
 * 用户管理
 *@author: haima
 *@fileName: User.java
 *@date: 2018年02月08日  00:28:15
 */
public interface IUserService {

	/**
	 *  根据账号和密码实现登陆
	 * @param params
	 * @return
	 */
	User getLogin(TzParams params);
	
	/**
	 * 查询所有内容信息
	 * @param params
	 * @return
	 */
	List<User> find(TzParams params);
	
	/**
	 * 根据主键获取内容
	 * @param id
	 * @return
	 */
	User get(Long id);
	
	/**
	 * 获取总数
	 * @param params
	 * @return
	 */
	Long count(TzParams params);
	
	/**
	 * 保存内容
	 * @param user
	 * @return
	 */
	int save(User user);
	
	/**
	 * 更新内容
	 * @param user
	 * @return
	 */
	int update(User user);
	
	/**
	 * 删除内容
	 * @param params
	 * @return
	 */
	int delete(TzParams params);

}
