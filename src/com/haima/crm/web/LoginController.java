package com.haima.crm.web;

import java.util.HashMap;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haima.crm.core.constant.TzConstant;
import com.haima.crm.entity.TzParams;
import com.haima.crm.entity.User;
import com.haima.crm.service.role.IRoleService;
import com.haima.crm.service.user.IUserService;
import com.haima.crm.util.TmStringUtils;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	
	@ResponseBody
	@RequestMapping(value="logined",method=RequestMethod.POST)
	public String logined(TzParams params){
		if(params!=null){
			if(TmStringUtils.isNotEmpty(params.getUname()) && TmStringUtils.isNotEmpty(params.getUname())){
				Long millis = RandomUtils.nextLong(2550, 3050);
				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String pass = params.getPwd();
				String md5Base64 = TmStringUtils.md5Base64(pass);
				String sha512Base64 = TmStringUtils.sha512Base64(pass);
				sha512Base64 = sha512Base64.substring(3,86);
				String key="!"+sha512Base64+md5Base64;
				
				params.setPwd(key);
				System.out.println("》》》 "+key+" 《《《");
				
				//System.out.println(params.getUname()+"==="+params.getPwd());
				
				User user = userService.getLogin(params);
				if(user!=null){
					/** 打印账号与删除状态*/
					//System.out.println(user.getUname()+"《《《"+user.getIsDelete()+"《《《《");
					
					if(user.getIsDisable()!=0){
						return "forbidden";
					}else{
						/** 查询用户对应的角色 */
						HashMap<String, Object> roles = roleService.findRolesByUserId(user.getId());
						session.setAttribute(TzConstant.SESSION_USER_ROLE, roles);
						
						session.setAttribute(TzConstant.SESSION_USER, user);
						session.setAttribute(TzConstant.SESSION_USER_USERNAME, user.getUname());
						
						//方便aop监控用户行为和请求参数
						request.getServletContext().setAttribute("user_log", user);
						//为了获取ip地址
						request.getServletContext().setAttribute("request_log", request);
						
						try {
							Thread.sleep(millis);
							System.out.println("》》》》》 暂停"+millis+"s !《《《《《");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return "success";
					}
				}else{
					return "fail";
				}
			}else{
				return "null";//请输入账号和密码
			}
		}else{
			return "error";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(){
		/**
		 * session.removeAttribute()
		 * 移除session中的某项属性。
		 */
		session.removeAttribute("user_log");
		session.removeAttribute("request_log");
		session.removeAttribute("session_user");
		session.removeAttribute("session_user_username");
		/**
		 * session.invalidate()
		 * 是销毁跟用户关联session,例如有的用户强制关闭浏览器,而跟踪用户的信息的session还存在,可是用户已经离开了。
			虽然session 生命周期浏览默认时间30分,但是在30分钟内别的用户还可以访问到前一个用户的页面,需销毁用户的session。
		 */
		session.invalidate();
		return "redirect:login";
	}
}
