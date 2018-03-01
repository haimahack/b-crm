package com.haima.crm.dao.stat;

import java.util.HashMap;
import java.util.List;

import com.haima.crm.entity.Stat;
import com.haima.crm.entity.TzParams;

/**
 * 统计报表
 *@author: haima
 *@fileName: Stat.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月20日  21:31:54
 */
public interface IStatMapper {
	
	/**
	 * 统计
	 * @param params
	 * @return
	 */
	List<HashMap<String, Object>> group(TzParams params);
	
	/**
	 * 内容统计
	 * @param params
	 * @return
	 */
	List<HashMap<String, Object>> groupContents(TzParams params);
	
	/**
	 * 用户统计
	 * @param params
	 * @return
	 */
	List<HashMap<String, Object>> groupUsers(TzParams params);
	
	/**
	 * 日志统计
	 * @param params
	 * @return
	 */
	List<HashMap<String,Object>> groupStats(TzParams params);
	
	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	List<Stat> find(TzParams params);
	
	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	Stat get(Long id);
	
	/**
	 * 获取总数
	 * @param params
	 * @return
	 */
	Long count(TzParams params);
	
	/**
	 * 保存
	 * @param stat
	 * @return
	 */
	int save(Stat stat);
	
	/**
	 * 更新
	 * @param stat
	 * @return
	 */
	int update(Stat stat);
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	int delete(TzParams params);
}
