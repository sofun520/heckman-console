package cn.heckman.module.pojo;

import java.util.Date;

public class TOperateLog {
	/**
     * 
     */
	private Integer oId;

	/**
     * 
     */
	private String oBusCode;

	/**
     * 
     */
	private String oBusDescription;

	/**
     * 
     */
	private Integer oUserId;

	/**
     * 
     */
	private String oUserName;

	/**
     * 
     */
	private Date oAddTime;

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public String getoBusCode() {
		return oBusCode;
	}

	public void setoBusCode(String oBusCode) {
		this.oBusCode = oBusCode == null ? null : oBusCode.trim();
	}

	public String getoBusDescription() {
		return oBusDescription;
	}

	public void setoBusDescription(String oBusDescription) {
		this.oBusDescription = oBusDescription == null ? null : oBusDescription
				.trim();
	}

	public Integer getoUserId() {
		return oUserId;
	}

	public void setoUserId(Integer oUserId) {
		this.oUserId = oUserId;
	}

	public String getoUserName() {
		return oUserName;
	}

	public void setoUserName(String oUserName) {
		this.oUserName = oUserName == null ? null : oUserName.trim();
	}

	public Date getoAddTime() {
		return oAddTime;
	}

	public void setoAddTime(Date oAddTime) {
		this.oAddTime = oAddTime;
	}

	public TOperateLog() {
	}

	public TOperateLog(String oBusCode, String oBusDescription,
			Integer oUserId, String oUserName) {
		this.oBusCode = oBusCode;
		this.oBusDescription = oBusDescription;
		this.oUserId = oUserId;
		this.oUserName = oUserName;
		this.oAddTime = new Date();
	}

}