package com.haima.crm.web.stat;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.haima.crm.entity.Stat;
import com.haima.crm.entity.TzParams;
import com.haima.crm.service.stat.IStatService;

/**
 * 统计报表
 *@author: haima
 *@fileName: Stat.java
 *@email: haimaclan@gmail.com
 *@date: 2018年02月20日  21:31:54
 */

@Controller
@RequestMapping("/sys/stat")
public class StatController {

	@Autowired
	private IStatService statService;
	
	@RequestMapping("list")
	public String list(){
		return "stat/list";
	}
	
	/**
	 * 内容统计
	 * @return
	 */
	@RequestMapping("contentlist")
	public String contentlist(){
		return "stat/contentlist";
	}
	
	/**
	 * 用户统计
	 * @return
	 */
	@RequestMapping("userlist")
	public String userlist(){
		return "stat/userlist";
	}
	
	/**
	 * 日志统计
	 * @return
	 */
	@RequestMapping("statlist")
	public String statlist(){
		return "stat/statlist";
	}
	
	@RequestMapping("template")
	public ModelAndView template(TzParams params){
		ModelAndView modelAndView = new ModelAndView();
		List<Stat> stats = statService.find(params);
		Long count = statService.count(params);
		
		modelAndView.addObject("datas",stats);
		modelAndView.addObject("itemCount",count);
		modelAndView.setViewName("stat/template");
		
		return modelAndView;
	}
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(TzParams params){
		int code = statService.delete(params);
		if(code == 1)
			return "success";
		else
			return "fail";
		
	}
	
	/**
	 * 内容统计
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="groupcontents",method=RequestMethod.POST)
	public List<HashMap<String, Object>> groupContents(TzParams params){
		List<HashMap<String, Object>> datas = statService.groupContents(params);
		return datas;
	}
	
	/**
	 * 用户统计
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="groupusers",method=RequestMethod.POST)
	public List<HashMap<String, Object>> groupUsers(TzParams params){
		List<HashMap<String, Object>> datas = statService.groupUsers(params);
		return datas;
	}
	
	/**
	 * 日志统计
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="groupstats",method=RequestMethod.POST)
	public List<HashMap<String, Object>> groupStats(TzParams params){
		List<HashMap<String, Object>> datas = statService.groupStats(params);
		return datas;
	}
	
	@ResponseBody
	@RequestMapping(value="lists",method=RequestMethod.POST)
	public List<HashMap<String, Object>> lists(TzParams params){
		List<HashMap<String, Object>> datas = statService.group(params);
		return datas;
	}

}
