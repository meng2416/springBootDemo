package cn.leap.demo.common.utils;

import java.util.List;

public class EasyPage<T> {
	private long total;
	private List<T> rows;

	public EasyPage(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "EasyPage [total=" + total + ", rows=" + rows + "]";
	}
}
