package com.haima.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 进入主页
 */
@Controller
@RequestMapping("/sys")
public class IndexController {

	@RequestMapping("/index")
	public String index(){
		return "index";
	}
}
