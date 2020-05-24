package com.ump.commons.esb.json;


import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

public class AppHead {
	/** 总条数 */
	@Setter
	@Getter
	@JSONField(name = "TOTAL_NUM")
	private Integer totalNum;

	/** 当前页 */
	@Setter
	@Getter
	@JSONField(name = "PAGE_INDEX")
	private Integer pageIndex;

	/** 当前记录数 */
	@Setter
	@Getter
	@JSONField(name = "CURRENT_NUM")
	private Integer currentNum;

	public AppHead() {
		super();
	}

	public AppHead(Integer totalNum, Integer pageIndex, Integer currentNum) {
		super();
		this.totalNum = totalNum;
		this.pageIndex = pageIndex;
		this.currentNum = currentNum;
	}

}
