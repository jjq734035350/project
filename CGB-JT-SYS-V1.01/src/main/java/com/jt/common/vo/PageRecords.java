package com.jt.common.vo;

import java.util.List;
public class PageRecords<T> {
	// 每页显示的记录
	private List<T> records;
	// 总页数
	private int pageCount;
	// 总记录数 
	private int rowCount;
	// 当前页码大小
	private int pageSize;
	// 当前页数
	private int pageCurrent = 1;
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public int getPageCount() {
		pageCount = rowCount/pageSize;
		if(rowCount%pageSize!=0)pageCount++;
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	
}
