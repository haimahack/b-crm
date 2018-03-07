package com.haima.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc: 角色
 * @Author: haima
 * @FileName: Role.java
 * @PackageName: com.haima.crm.entity
 * @Date: 2018-03-02 16:00
 * @E-mail: haimaclan@gmail.com
 */
public class Role implements Serializable {
	
	private Long id;
	private String rname; //角色名
	private Date createTime;
	private Date updateTime;
	private String description;
	private Long userId;
	private Integer isDelete;

	
	public Role() {
		super();
	}

	public Role(Long id,String rname,Date createTime,Date updateTime,String description,Long userId,Integer isDelete) {
		super();
		this.id = id;
		this.rname = rname;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.description = description;
		this.userId = userId;
		this.isDelete = isDelete;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
