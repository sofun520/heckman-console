package cn.heckman.module.pojo;

import java.util.Date;
import java.util.List;

public class TPermission {
	/**
	 * 主键id
	 */
	private Integer pId;

	/**
	 * token值
	 */
	private String pToken;

	/**
	 * 链接url
	 */
	private String pUrl;

	/**
	 * 描述
	 */
	private String pDescription;

	/**
	 * 类型，1：菜单；2：操作
	 */
	private String pType;

	/**
     * 
     */
	private Integer pParent;

	/**
	 * 添加时间
	 */
	private Date pAddTime;

	/**
	 * 状态（0：正常；1：删除）
	 */
	private String pStatus;

	private List<TPermission> cList;

	public List<TPermission> getcList() {
		return cList;
	}

	public void setcList(List<TPermission> cList) {
		this.cList = cList;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpToken() {
		return pToken;
	}

	public void setpToken(String pToken) {
		this.pToken = pToken == null ? null : pToken.trim();
	}

	public String getpUrl() {
		return pUrl;
	}

	public void setpUrl(String pUrl) {
		this.pUrl = pUrl == null ? null : pUrl.trim();
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription == null ? null : pDescription.trim();
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType == null ? null : pType.trim();
	}

	public Integer getpParent() {
		return pParent;
	}

	public void setpParent(Integer pParent) {
		this.pParent = pParent;
	}

	public Date getpAddTime() {
		return pAddTime;
	}

	public void setpAddTime(Date pAddTime) {
		this.pAddTime = pAddTime;
	}

	public String getpStatus() {
		return pStatus;
	}

	public void setpStatus(String pStatus) {
		this.pStatus = pStatus == null ? null : pStatus.trim();
	}

	@Override
	public String toString() {
		return "TPermission [pId=" + pId + ", pToken=" + pToken + ", pUrl="
				+ pUrl + ", pDescription=" + pDescription + ", pType=" + pType
				+ ", pParent=" + pParent + ", pAddTime=" + pAddTime
				+ ", pStatus=" + pStatus + "]";
	}

}