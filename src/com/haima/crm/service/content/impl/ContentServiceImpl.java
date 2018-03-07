package com.haima.crm.service.content.impl;

import com.haima.crm.dao.content.IContentMapper;
import com.haima.crm.entity.Content;
import com.haima.crm.entity.TzParams;
import com.haima.crm.service.content.IContentService;
import com.haima.crm.util.TmStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容管理
 *@author: haima
 *@fileName: Content.java
 *@date: 2018年02月14日  18:20:51
 */
@Service
public class ContentServiceImpl implements IContentService {

	@Autowired
	private IContentMapper contentMapper;
	
	
	@Override
	public List<Content> find(TzParams params) {
		if(TmStringUtils.isEmpty(params.getOrder()))
			params.setOrder("create_time DESC");
		return contentMapper.find(params);
	}

	@Override
	public List<Content> findAll() {
		return contentMapper.findAll();
	}

	@Override
	public Content get(Long id) {
		return contentMapper.get(id);
	}
	
	@Override
	public Long count(TzParams params) {
		return contentMapper.count(params);
	}
	
	@Override
	public int save(Content content) {
		if(content!=null){
			contentMapper.save(content);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int update(Content content) {
		if(content!=null){
			contentMapper.update(content);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int delete(TzParams params) {
		if(params!=null){
			contentMapper.delete(params);
			return 1;
		}else{
			return 0;
		}
	}

}
