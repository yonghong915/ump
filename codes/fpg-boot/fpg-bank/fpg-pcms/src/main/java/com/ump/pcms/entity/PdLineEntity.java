package com.ump.pcms.entity;

import com.ump.core.base.entity.BaseEntity;

import lombok.Data;

@Data
public class PdLineEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1228762503609839038L;
	private String lineId;

	private String lineCode;

	private String lineName;

	private String lineDsc;
}
