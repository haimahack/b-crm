package com.haima.crm.dao.content;

import java.util.List;

import com.haima.crm.entity.Content;
import com.haima.crm.entity.TzParams;

/**
 *
 *@author: haima
 *@email: haimaclan@gmail.com
 *@fileName: Content.java
 *@date: 2018年02月14日  18:20:51
 */
public interface IContentMapper {
	
	/**
	 * 查询所有
	 * @param params
	 * @return
	 */
	List<Content> find(TzParams params);
	
	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	Content get(Long id);
	
	/**
	 * 获取总数
	 * @param params
	 * @return
	 */
	Long count(TzParams params);
	
	/**
	 * 保存
	 * @param content
	 * @return
	 */
	int save(Content content);
	
	/**
	 * 更新
	 * @param content
	 * @return
	 */
	int update(Content content);
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	int delete(TzParams params);
}
