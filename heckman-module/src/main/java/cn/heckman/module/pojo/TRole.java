package cn.heckman.module.pojo;

import java.util.Date;
import java.util.List;

public class TRole {
	/**
	 * 主键
	 */
	private Integer rId;

	/**
	 * 角色名称
	 */
	private String rName;

	/**
	 * 描述
	 */
	private String rDescription;

	/**
	 * 添加时间
	 */
	private Date rAddTime;

	/**
	 * 状态（0：正常；1：删除）
	 */
	private String rStatus;

	private List<TPermission> permissions;

	public List<TPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<TPermission> permissions) {
		this.permissions = permissions;
	}

	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName == null ? null : rName.trim();
	}

	public String getrDescription() {
		return rDescription;
	}

	public void setrDescription(String rDescription) {
		this.rDescription = rDescription == null ? null : rDescription.trim();
	}

	public Date getrAddTime() {
		return rAddTime;
	}

	public void setrAddTime(Date rAddTime) {
		this.rAddTime = rAddTime;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus == null ? null : rStatus.trim();
	}
}