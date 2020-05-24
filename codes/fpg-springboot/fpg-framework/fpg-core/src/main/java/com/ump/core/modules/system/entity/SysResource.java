package com.ump.core.modules.system.entity;

import com.ump.core.base.entity.BaseEntity;

import lombok.Data;

@Data
public class SysResource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/***/
	private Long parentId;

	/***/
	private String resourceCode;

	/***/
	private String resourceName;

	/***/
	private String resourceDesc;

	/***/
	private String resourceType;

	/***/
	private String resourceUrl;

	/***/
	private Integer orderNum;

	/***/
	private String resourceIcon;

	/***/
	private String isLeaf;
}
