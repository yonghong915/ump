package com.ump.cfn.sysmgr.code.model;

import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.Setter;

public class CodeInfo extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private String codeInfoCode;

	@Getter
	@Setter
	private String codeInfoValue;

	@Getter
	@Setter
	private String codeInfoDesc;

	@Getter
	@Setter
	private String codeTypeCode;

	@Getter
	@Setter
	private String parentCodeId;

	@Getter
	@Setter
	private String orderNo;

}
