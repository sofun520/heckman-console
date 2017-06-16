package cn.heckman.module.vo;

import cn.heckman.module.pojo.TUser;

public class PermissionUser extends TUser {

	private int pParent;

	private int pType;

	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getpType() {
		return pType;
	}

	public void setpType(int pType) {
		this.pType = pType;
	}

	public int getpParent() {
		return pParent;
	}

	public void setpParent(int pParent) {
		this.pParent = pParent;
	}

}
