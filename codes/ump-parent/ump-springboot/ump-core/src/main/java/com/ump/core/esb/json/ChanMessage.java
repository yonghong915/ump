package com.ump.core.esb.json;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

public class ChanMessage<SH, AH, BD> {

	@Getter
	@Setter
	@JSONField(name = "SYS_HEAD")
	private SH sysHead;

	@Getter
	@Setter
	@JSONField(name = "APP_HEAD")
	private AH appHead;

	@Getter
	@Setter
	@JSONField(name = "BODY")
	private BD body;

	public ChanMessage() {
	}

	public ChanMessage(SH sysHead, AH appHead, BD body) {
		super();
		this.sysHead = sysHead;
		this.appHead = appHead;
		this.body = body;
	}
}
