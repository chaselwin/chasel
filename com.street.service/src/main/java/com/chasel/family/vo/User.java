package com.chasel.family.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	@NotNull(message="账号不能为空")
	private String account;
	@NotNull(message="密码不能为空")
	private String password;
	@NotNull(message="名字不能为空")
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date birthday;
	private String mobile;
	private String qq;
	private String email;
	private String wechat;
	private String photo;
	private String synopsis;
	private int permissions;

	public int getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public String getQq() {
		return qq;
	}

	public String getEmail() {
		return email;
	}

	public String getWechat() {
		return wechat;
	}

	public String getPhoto() {
		return photo;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public int getPermissions() {
		return permissions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", name=" + name + ", birthday="
				+ birthday + ", mobile=" + mobile + ", qq=" + qq + ", email=" + email + ", wechat=" + wechat
				+ ", photo=" + photo + ", synopsis=" + synopsis + ", permissions=" + permissions + "]";
	}
	
}
