package com.codefans.test.pagination;

public class PageBean {
	private int pageIndex;// 页码

	private int begin;// 开始行

	private int end;// 结束行

	// 开始行和结束行组成闭区间

	private int rowPerPage;// 每页多少行

	private int rowCount;// total 总的记录数

	private int pageCount;// max page 最大页码
	private boolean base0;// 是否以零为基准

	public boolean isBase0() {
		return base0;
	}

	public void setBase0(boolean base0) {
		this.base0 = base0;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	/**
	 * 产生分页的对象
	 *
	 * @param pageIndex
	 *            第几页
	 * @param rowPerPage
	 *            每页多少
	 * @param rowCount
	 *            一共多少
	 * @param base0
	 *            开始行和结束行是否以0作为基数
	 * @return 分页实体bean
	 * @throws Exception
	 */
	public PageBean(int pageIndex, int rowPerPage, int rowCount, boolean base0) throws Exception {
		begin = 0;
		end = 0;
		if (rowPerPage <= 0)
			rowPerPage = 10;
		if (rowCount < 0)
			throw new Exception("参数不符合规则！rowCount>=0");
		pageCount = 0;
		if (rowCount % rowPerPage != 0)
			pageCount = (int) Math.round(rowCount / rowPerPage + 0.5);// 总页码
		else
			pageCount = rowCount / rowPerPage;
		if (rowCount > 0) {
			if (pageIndex <= 0)
				pageIndex = 1;
			if (pageIndex > pageCount)
				pageIndex = pageCount;
			begin = (pageIndex - 1) * rowPerPage + 1;
			end = begin + rowPerPage - 1;
			if (end > rowCount)
				end = rowCount;
		}
		if (base0) {
			begin = begin - 1;
			end = end - 1;
		}
		setPageIndex(pageIndex);
		setRowCount(rowCount);
		setRowPerPage(rowPerPage);
		setBase0(base0);
	}
}
