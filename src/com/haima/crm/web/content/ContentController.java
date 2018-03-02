package com.haima.crm.web.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.haima.crm.entity.Content;
import com.haima.crm.entity.TzParams;
import com.haima.crm.service.content.IContentService;

/**
 * 内容管理
 *@author: haima
 *@fileName: ContentController.java
 *@date: 2018年2月14日 下午8:31:58
 */

@Controller
@RequestMapping("/sys/content")
public class ContentController {

	@Autowired
	private IContentService contentService;
	
	@RequestMapping("list")
	public String list(){
		return "content/list";
	}
	
	@RequestMapping("template")
	public ModelAndView template(TzParams params){
		ModelAndView modelAndView = new ModelAndView();
		List<Content> contents = contentService.find(params);
		Long count = contentService.count(params);
		
		modelAndView.addObject("datas",contents);
		modelAndView.addObject("itemCount",count);
		modelAndView.setViewName("content/template");
		
		return modelAndView;
	}
	
	
	@ResponseBody
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Content content){
		int code = contentService.update(content);
		if(code == 1)
			return "success";
		else
			return "fail";
		
	}
	
	@ResponseBody
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(TzParams params){
		int code = contentService.delete(params);
		if(code == 1)
			return "success";
		else
			return "fail";
		
	}
	
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("content/add");
		return modelAndView;
	}
}
