package com.ump.cust.model;

import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Cust extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Getter
	@Setter
	private String custCode;

	/**
	 * 
	 */
	@Getter
	@Setter
	private String custName;

	/**
	 * 
	 */
	@Getter
	@Setter
	private String custDesc;

	/**
	 * 
	 */
	@Getter
	@Setter
	private String custType;

}
