package cn.heckman.module.common;

public class BasePage {

	public Integer page;

	public Integer pageCount;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page == null ? 1 : page;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
