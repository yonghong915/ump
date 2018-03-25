package com.ump.cust.model;

import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class CustContract extends BaseObject {
	/**
	* 
	*/
	private static final long serialVersionUID = 1130949863828109143L;

	/**
	 * 
	 */
	@Getter
	@Setter
	private Cust cust;
}
