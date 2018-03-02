package com.haima.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限实体
 *@author: haima
 *@fileName: Permission.java
 *@packageName: com.haima.crm.entity
 *@date: 2018年2月22日 下午8:18:56
 */
public class Permission implements Serializable {
	
	private Long id;            //主键
	private String pname;       //权限名
	private String description; //权限详情
	private Date createTime;    //创建时间
	private Date updateTime;    //更新时间
	private String purl;        //权限地址
	private String module;      //权限模块
	private String method;      //权限方法
	private Long parentId;      //父权限id
	private Long userId;        //用户id
	private Integer isDelete;   //是否删除 0 -未删除 1-删除

	
	public Permission() {
		super();
	}

	public Permission(Long id,String pname,String description,Date createTime,Date updateTime,String purl,String module,String method,Long parentId,Long userId,Integer isDelete) {
		super();
		this.id = id;
		this.pname = pname;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.purl = purl;
		this.module = module;
		this.method = method;
		this.parentId = parentId;
		this.userId = userId;
		this.isDelete = isDelete;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


}
