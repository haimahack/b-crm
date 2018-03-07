package com.haima.crm.service.content;

import com.haima.crm.entity.Content;
import com.haima.crm.entity.TzParams;

import java.util.List;


/**
 * 内容管理
 *@author: haima
 *@fileName: Content.java
 *@date: 2018年02月14日  18:20:51
 */
public interface IContentService {

	/**
	 * 查询所有内容信息
	 * @param params
	 * @return
	 */
	List<Content> find(TzParams params);

	/**
	 * 查询所有
	 * @return
	 */
	List<Content> findAll();
	
	/**
	 * 根据主键获取内容
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
	 * 保存内容
	 * @param content
	 * @return
	 */
	int save(Content content);
	
	/**
	 * 更新内容
	 * @param content
	 * @return
	 */
	int update(Content content);
	
	/**
	 * 删除内容
	 * @param params
	 * @return
	 */
	int delete(TzParams params);
}
