package com.ump.core.base.model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String pid;

	/**
	 * 1deleted,0 no delete
	 */
	@Getter
	@Setter
	private String deleteFlag = "0";

	@Getter
	@Setter
	private String creator;

	@Getter
	@Setter
	private Timestamp createDate;

	@Getter
	@Setter
	private String updator;

	@Getter
	@Setter
	private Timestamp updateDate;
}
