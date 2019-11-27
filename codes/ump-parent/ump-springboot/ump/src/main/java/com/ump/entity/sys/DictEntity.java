/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ump.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典实体
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 09:12:18
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "S_DICT")
public class DictEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5428301491215485949L;
	/** 字典项ID */
	@TableId
	private String dictId;
	/** 父项ID */
	private String parentId = "999999";
	/** 字典项编码 */
	private String dictCode;
	/** 字典项值 */
	private String dictValue;
	/** 字典项说明 */
	private String dictDsc;
	/** 节点层级 */
	private Integer dictLevel = 1;
	/** 节点序号 */
	private Integer dictIndex = 1;
}
