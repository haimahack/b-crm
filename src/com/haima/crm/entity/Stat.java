package com.haima.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc: 日志
 * @Author: haima
 * @FileName: Stat.java
 * @PackageName: com.haima.crm.entity
 * @Date: 2018-03-02 16:00
 * @Email: haimaclan@163.com
 */
public class Stat implements Serializable {
	
	private Long id;             //主键
	private String classname;    //类型
	private String method;       //方法名
	private Long time;           //耗时(单位毫秒)
	private Date createTime;     //创建时间
	private String ip;           //ip
	private String hostAddress;  //主机地址
	private Long userId;         //用户id
	private String username;     //登录用户名
	private String module;       //模块
	private String description;  //详情

	
	public Stat() {
		super();
	}

	public Stat(Long id,String classname,String method,Long time,Date createTime,String ip,String hostAddress,Long userId,String username,String module,String description) {
		super();
		this.id = id;
		this.classname = classname;
		this.method = method;
		this.time = time;
		this.createTime = createTime;
		this.ip = ip;
		this.hostAddress = hostAddress;
		this.userId = userId;
		this.username = username;
		this.module = module;
		this.description = description;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostAddress() {
		return hostAddress;
	}
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
