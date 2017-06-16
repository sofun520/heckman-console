package cn.heckman.framework.common;

public class Page {

	/* 页码 */
	private int pageNo;
	/* 每页几条 */
	private int pageSize;
	/* 排序字段 */
	private String orderBy;
	/* 排序规则 esc desc */
	private String order;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
