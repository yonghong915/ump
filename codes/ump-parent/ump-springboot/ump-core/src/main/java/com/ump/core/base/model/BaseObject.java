package com.ump.core.base.model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
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
	private String creator;

	@Getter
	@Setter
	private Timestamp createTime;

	@Getter
	@Setter
	private String updator;

	@Getter
	@Setter
	private Timestamp updateTime;

	@Getter
	@Setter
	private String status;

	/**
	 * 1deleted,0 no delete
	 */
	@Getter
	@Setter
	@NonNull
	private String delFlag = "n";

}
