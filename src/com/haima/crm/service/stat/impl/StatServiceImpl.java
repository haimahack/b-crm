package com.haima.crm.service.stat.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haima.crm.dao.stat.IStatMapper;
import com.haima.crm.entity.Stat;
import com.haima.crm.entity.TzParams;
import com.haima.crm.service.stat.IStatService;
import com.haima.crm.util.TmStringUtils;

/**
 * 统计报表
 *@author: haima
 *@fileName: Stat.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月20日  21:31:54
 */
@Service
public class StatServiceImpl implements IStatService {

	@Autowired
	private IStatMapper statMapper;
	
	
	@Override
	public List<Stat> find(TzParams params) {
		if(TmStringUtils.isEmpty(params.getOrder()))
			params.setOrder("create_time DESC");
		return statMapper.find(params);
	}
	
	@Override
	public Stat get(Long id) {
		return statMapper.get(id);
	}
	
	@Override
	public Long count(TzParams params) {
		return statMapper.count(params);
	}
	
	@Override
	public int save(Stat stat) {
		if(stat!=null){
			statMapper.save(stat);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int update(Stat stat) {
		if(stat!=null){
			statMapper.update(stat);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int delete(TzParams params) {
		if(params!=null){
			statMapper.delete(params);
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public List<HashMap<String, Object>> group(TzParams params) {
		return statMapper.group(params);
	}

	@Override
	public List<HashMap<String, Object>> groupContents(TzParams params) {
		return statMapper.groupContents(params);
	}

	@Override
	public List<HashMap<String, Object>> groupUsers(TzParams params) {
		return statMapper.groupUsers(params);
	}

	@Override
	public List<HashMap<String, Object>> groupStats(TzParams params) {
		return statMapper.groupStats(params);
	}
}
