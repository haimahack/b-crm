package com.haima.crm.core.aop;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.haima.crm.dao.stat.IStatMapper;
import com.haima.crm.entity.Stat;
import com.haima.crm.entity.User;
import com.haima.crm.util.TmStringUtils;
import com.haima.crm.util.ip.TmIpUtil;

/**
 * aop切面
 *@author: haima
 *@fileName: LogAware.java
 *@packageName: com.haima.crm.core.aop
 *@date: 2018年2月21日 下午4:15:46
 */
@Component
public class LogAware implements ServletContextAware{

	/**
	 注解说明
		1 @Aspect
		作用是把当前类标识为一个切面供容器读取
		
		2 @Before
		标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
		
		3 @AfterReturning
		后置增强，相当于AfterReturningAdvice，方法正常退出时执行
		
		4 @AfterThrowing
		异常抛出增强，相当于ThrowsAdvice
		
		5 @After
		final增强，不管是抛出异常或者正常退出都会执行
		
		6 @Around
		环绕增强，相当于MethodInterceptor
		
		7 @DeclareParents
		引介增强，相当于IntroductionInterceptor
		
		
	execution切点函数
		execution函数用于匹配方法执行的连接点，语法为：
		execution(方法修饰符(可选)  返回类型  方法名  参数  异常模式(可选)) 
		
		参数部分允许使用通配符：
		
		*  匹配任意字符，但只能匹配一个元素
		.. 匹配任意字符，可以匹配任意多个元素，表示类时，必须和*联合使用
		+  必须跟在类名后面，如Horseman+，表示类本身和继承或扩展指定类的所有类
		
	示例中的* chop(..)解读为：
		方法修饰符  无
		返回类型      *匹配任意数量字符，表示返回类型不限
		方法名          chop表示匹配名称为chop的方法
		参数               (..)表示匹配任意数量和类型的输入参数
	 */
	
	@Autowired
	private IStatMapper statMapper;
	
	private ServletContext application;

	
	/** 
	 * 环绕通知
	 * @Around("execution(* com.haima.crm.service.*.*.*(..))")
	 */	
	public Object doBasicProfiling(ProceedingJoinPoint point) throws Throwable{
		System.err.println("》》》》》 我是环绕通知，我来了...");
		
		//排除 getLogin方法
		String methodName = point.getSignature().getName();
		if(TmStringUtils.isNotEmpty(methodName) && methodName.equals("getLogin")){
			return  point.proceed();
		}
		
		// 执行该方法
		Object classObj = point.getTarget();//拦截的类名
		long start = System.currentTimeMillis();
		Object object = point.proceed();
		long end = System.currentTimeMillis();
		long time = (end - start);
		Object[] params = point.getArgs();
		StringBuilder builder = new StringBuilder("");
		if(params.length>0){
			for (int i = 0; i < params.length; i++) {
				builder.append(String.valueOf(params[i]));
				if(i<params.length-1){
					builder.append(",");
				}
			}
		}
		
		//执行的类
		String className = classObj.getClass().getName();
		//返回类型
		String returnType = null;
		if(object!=null){
			returnType = object.getClass().getName();
		}
		
		//保存aop拦截日记到数据库
		try{
			saveLog(className,methodName,time);
		}catch(Exception e){
			
		}
		System.out.println("》》》》》【AOP拦截】【Class："+className+"】【Method："+methodName+"】【ReturnType："+returnType+"】【Time："+time+"ms】《《《《《");
		
		return object;
	}
	
	//保存aop拦截日记到数据库
	private void saveLog(String className,String methodName,long time){
		Stat stat = new Stat();
		
		User user = (User) application.getAttribute("user_log");
		HttpServletRequest request = (HttpServletRequest) application.getAttribute("request_log");
		
		if(user!=null){
			stat.setClassname(className); //类名称
			stat.setMethod(methodName);//方法
			stat.setTime(time);//耗时（单位毫秒）
			stat.setIp(TmIpUtil.getIpAddress(request));
			stat.setHostAddress(TmIpUtil.ipLocation(request));//ip坐标地址
			stat.setUserId(user.getId());// 用户ID
			stat.setUsername(user.getUname());//登录用户名
			
			String module = getModule(request);
			stat.setModule(module);//模块
			
			if(module.contains("content/"))
				stat.setDescription("》》 内容 测试");//详情
			else if(module.contains("role/"))
				stat.setDescription("》》 角色 测试");
			else if(module.contains("user/"))
				stat.setDescription("》》 用户 测试");
			else if(module.contains("stat/"))
				stat.setDescription("》》 统计 测试");
			else if(module.contains("permission/"))
				stat.setDescription("》》 统计 测试");
			else if(module.contains("gather/"))
				stat.setDescription("》》 采集 测试");
			else if(module.contains("sys/index"))
				stat.setDescription("》》 主页 测试");
			else if(module.contains("login"))
				stat.setDescription("》》 登录 测试");
			else if(module.contains("logout"))
				stat.setDescription("》》 注销 测试");
			else
				stat.setDescription("》》 其他 测试");
			
			statMapper.save(stat);
		}
	}
	
	/**
	 * 根据访问地址获取模块名
	 * @return
	 */
	private String getModule(HttpServletRequest request){
		
		//http://localhost:8080/h-crm/sys/role/template
		
		/** 当前链接使用的协议  http|https|ftp...*/
		//String scheme = request.getScheme();
		/** 服务器地址 localhost|127.0.0.1 ...*/
		String serverName = request.getServerName();
		/** 端口号 8080|80|9090 ...*/
		int port = request.getServerPort();
		/** 应用名称 h-crm*/
		//String contextPath =request.getContextPath();
		/** 请求的相对url sys/role/template*/
		String servletPath = request.getServletPath();
		
		String module = null;
		
		if(serverName.equalsIgnoreCase("localhost")||serverName.equalsIgnoreCase("127.0.0.1")||serverName.startsWith("192.168.11")){
			if(port==8080){
				module = servletPath;
			}
		}
		//System.out.println("》》》》"+module);
		return module;
	}
	
	@Override
	public void setServletContext(ServletContext application) {
		// TODO Auto-generated method stub
		this.application=application;
	}
}
