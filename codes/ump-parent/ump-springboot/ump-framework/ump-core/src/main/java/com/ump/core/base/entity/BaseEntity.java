package com.ump.core.base.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ump.core.util.serialize.DateJsonDeserializer;
import com.ump.core.util.serialize.DateJsonSerializer;

import lombok.Data;

/**
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
@Data
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/***/
	@TableId
	private Long pkId;

	/***/
	private String createBy;

	/***/
	@JsonDeserialize(using = DateJsonDeserializer.class)
	@JsonSerialize(using = DateJsonSerializer.class)
	private Timestamp createDt;

	/***/
	private String updateBy;

	/***/
	@JsonDeserialize(using = DateJsonDeserializer.class)
	@JsonSerialize(using = DateJsonSerializer.class)
	private Timestamp updateDt;

	/**
	 * 1deleted,0 no delete
	 */
	private Integer delFlag = 0;

}
