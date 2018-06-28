package com.ump.cfn.sysmgr.resource.model;

import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.Setter;

public class Resource extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String resourceId;

	@Getter
	@Setter
	private String resourceName;

	@Getter
	@Setter
	private String resourceDesc;

	@Getter
	@Setter
	private String url;
}
