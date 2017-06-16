package cn.heckman.module.pojo;

import java.util.Date;
import java.util.List;

public class TUser {
	/**
     * 
     */
	private Integer uId;

	/**
     * 
     */
	private String uUsername;

	/**
     * 
     */
	private String uPassword;

	/**
     * 
     */
	private String uPhone;

	/**
     * 
     */
	private Date uAddTime;

	/**
     * 
     */
	private String uEmail;

	/**
	 * 0:正常；1删除；2锁定
	 */
	private String uStatus;

	private List<TRole> roles;

	private String uPic;

	public TUser() {
		super();
	}

	public TUser(String uUsername, String uPassword) {
		super();
		this.uUsername = uUsername;
		this.uPassword = uPassword;
	}

	public String getuPic() {
		return uPic;
	}

	public void setuPic(String uPic) {
		this.uPic = uPic;
	}

	public List<TRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TRole> roles) {
		this.roles = roles;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuUsername() {
		return uUsername;
	}

	public void setuUsername(String uUsername) {
		this.uUsername = uUsername == null ? null : uUsername.trim();
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword == null ? null : uPassword.trim();
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone == null ? null : uPhone.trim();
	}

	public Date getuAddTime() {
		return uAddTime;
	}

	public void setuAddTime(Date uAddTime) {
		this.uAddTime = uAddTime;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail == null ? null : uEmail.trim();
	}

	public String getuStatus() {
		return uStatus;
	}

	public void setuStatus(String uStatus) {
		this.uStatus = uStatus == null ? null : uStatus.trim();
	}
}