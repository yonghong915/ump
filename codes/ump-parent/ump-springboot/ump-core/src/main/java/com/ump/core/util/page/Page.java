package com.ump.core.util.page;

import java.util.Collections;
import java.util.List;

/**
 * 对分页的基本数据进行封装
 */
/**
 * 
 * @author fangyh
 * @date 2017-04-23 20:23:29
 * @version 1.0
 * @param <T>
 */
public class Page<T> {
	private int pageNum = 1;// 页码，默认是第一页
	private int pageSize = 5;// 每页显示的记录数，默认是5
	private int totalRecord;// 总记录数
	private int totalPage;// 总页数
	private List<T> results;// 对应的当前页记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int thisTotalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
		this.setTotalPage(thisTotalPage);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResults() {
		if (null != results && results.isEmpty()) {
			return Collections.emptyList();
		}
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNum=").append(pageNum).append(", pageSize=").append(pageSize).append(", results=")
				.append(results).append(", totalPage=").append(totalPage).append(", totalRecord=").append(totalRecord)
				.append("]");
		return builder.toString();
	}
}
