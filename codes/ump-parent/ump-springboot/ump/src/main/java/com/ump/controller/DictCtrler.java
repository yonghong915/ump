/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ump.aspect.annotation.AutoLog;
import com.ump.aspect.annotation.LogCodeEnum;
import com.ump.commons.web.ResultRsp;
import com.ump.entity.sys.DictEntity;
import com.ump.service.IDictService;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-25 21:05:36
 *
 */
@RestController
@RequestMapping("/dict")
public class DictCtrler {

	@Autowired
	private IDictService dictService;

	@RequestMapping("/save")
	@AutoLog(logCode = LogCodeEnum.LOGIN)
	public ResultRsp save() {
		DictEntity entity = new DictEntity();
		entity.setDictId(UUID.randomUUID().toString().replace("-", ""));
		entity.setParentId("999999");
		entity.setDictCode("status");
		entity.setDictValue("01");
		entity.setDictDsc("dsc");
		entity.setDictLevel(1);
		entity.setDictIndex(1);
         int a = 4/0;
		dictService.saveDict(entity);
		return new ResultRsp<>();
	}
}
