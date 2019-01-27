package com.ump.commons.esb.json;

import java.util.Date;

import javax.annotation.Nonnull;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

public class SysHead {

	@Nonnull
	@Setter
	@Getter
	@JSONField(name = "TRANS_CODE")
	private String transCode;

	@Nonnull
	@Setter
	@Getter
	@JSONField(name = "CHAN_ID")
	private String chanId = "01";

	@Nonnull
	@Setter
	@Getter
	@JSONField(name = "TRANS_TIME", format = "yyyy-MM-dd HH:mm:ss,SSS")
	private Date transTime = new Date();

	@Nonnull
	@Setter
	@Getter
	@JSONField(name = "RSP_CODE")
	private String rspCode = "000000";

	@Nonnull
	@Setter
	@Getter
	@JSONField(name = "RSP_MSG")
	private String rspMsg = "success";
}
