package com.haima.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *@author: haima
 *@fileName: User.java
 *@packageName: com.haima.crm.entity
 *@date: 2018年2月22日 下午8:19:26
 */
public class User implements Serializable {
	
	private Long id;            //主键
	private String uname;       //账号
	private String nickname;    //昵称
	private String pwd;         //密码
	private Integer sex;        //0 女 1 男 -1保密
	private Integer age;        //年龄
	private Date birthday;      //生日
	private String userPic;     //头像图片
	private String email;       //邮箱
	private String mobile;      //手机
	private String weixin;      //微信
	private Date createTime;    //创建时间
	private Date updateTime;    //更新时间
	private Date loginTime;     //最后登录时间
	private String loginIp;     //最后登录ip
	private Integer isDelete;   //是否删除 0 未删除 1删除
	private Integer isEmail;    //是否绑定邮箱 0 未绑定 1 绑定
	private Integer isPhone;    //是否绑定手机 0 未绑定 1 绑定
	private Integer isWeixin;   //是否绑定微信 0 未绑定 1绑定
	private Integer isDisable;  //是否禁用 0 未禁用 1禁用
	private String address;     //地址

	public User() {
		super();
	}

	public User(Long id,String uname,String nickname,String pwd,Integer sex,Integer age,Date birthday,String userPic,String email,String mobile,String weixin,Date createTime,Date updateTime,Date loginTime,String loginIp,Integer isDelete,Integer isEmail,Integer isPhone,Integer isWeixin,Integer isDisable,String address) {
		super();
		this.id = id;
		this.uname = uname;
		this.nickname = nickname;
		this.pwd = pwd;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
		this.userPic = userPic;
		this.email = email;
		this.mobile = mobile;
		this.weixin = weixin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.isDelete = isDelete;
		this.isEmail = isEmail;
		this.isPhone = isPhone;
		this.isWeixin = isWeixin;
		this.isDisable = isDisable;
		this.address = address;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
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

	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsEmail() {
		return isEmail;
	}
	public void setIsEmail(Integer isEmail) {
		this.isEmail = isEmail;
	}

	public Integer getIsPhone() {
		return isPhone;
	}
	public void setIsPhone(Integer isPhone) {
		this.isPhone = isPhone;
	}

	public Integer getIsWeixin() {
		return isWeixin;
	}
	public void setIsWeixin(Integer isWeixin) {
		this.isWeixin = isWeixin;
	}

	public Integer getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
