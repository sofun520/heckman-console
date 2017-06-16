package cn.heckman.module.pojo;

import java.util.Date;

public class TUserRole {
	/**
     * 
     */
	private Integer id;

	/**
     * 
     */
	private Integer userId;

	/**
     * 
     */
	private Integer roleId;

	/**
     * 
     */
	private Date addTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}