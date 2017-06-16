package cn.heckman.module.pojo;

import java.util.Date;

public class TSmsTemp {
	/**
	 * 主键
	 */
	private Integer tId;

	/**
	 * 模板名称
	 */
	private String tName;

	/**
	 * 模板id,对应云片网模板id
	 */
	private String tTempId;

	/**
	 * 模板描述
	 */
	private String tDescription;

	/**
	 * 模板内容
	 */
	private String tContent;

	/**
	 * 添加时间
	 */
	private Date tAddTime;

	/**
	 * 添加人
	 */
	private Integer tAddUser;

	/**
	 * 修改时间
	 */
	private Date tUpdateTime;

	/**
	 * 修改人
	 */
	private Integer tUpdateUser;

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName == null ? null : tName.trim();
	}

	public String gettTempId() {
		return tTempId;
	}

	public void settTempId(String tTempId) {
		this.tTempId = tTempId == null ? null : tTempId.trim();
	}

	public String gettDescription() {
		return tDescription;
	}

	public void settDescription(String tDescription) {
		this.tDescription = tDescription == null ? null : tDescription.trim();
	}

	public String gettContent() {
		return tContent;
	}

	public void settContent(String tContent) {
		this.tContent = tContent == null ? null : tContent.trim();
	}

	public Date gettAddTime() {
		return tAddTime;
	}

	public void settAddTime(Date tAddTime) {
		this.tAddTime = tAddTime;
	}

	public Integer gettAddUser() {
		return tAddUser;
	}

	public void settAddUser(Integer tAddUser) {
		this.tAddUser = tAddUser;
	}

	public Date gettUpdateTime() {
		return tUpdateTime;
	}

	public void settUpdateTime(Date tUpdateTime) {
		this.tUpdateTime = tUpdateTime;
	}

	public Integer gettUpdateUser() {
		return tUpdateUser;
	}

	public void settUpdateUser(Integer tUpdateUser) {
		this.tUpdateUser = tUpdateUser;
	}
}