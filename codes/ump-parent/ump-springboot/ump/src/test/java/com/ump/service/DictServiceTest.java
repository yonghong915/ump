/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ump.UmpApplication;
import com.ump.entity.sys.DictEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 10:09:30
 *
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = { UmpApplication.class })
public class DictServiceTest {
	@Autowired
	private IDictService dictService;

	@Test
	public void testSaveDict() {
		DictEntity entity = new DictEntity();
		String dictId = UUID.randomUUID().toString();
		entity.setDictId(dictId);
		dictService.saveDict(entity);
	}

	@Test
	public void testQueryDictList() {
		DictEntity entity = new DictEntity();
		IPage<DictEntity> dictList = dictService.queryDictList(entity, 1, 10);
		System.out.println("=dictList===" + dictList.getRecords().size());
		for (DictEntity en : dictList.getRecords()) {
			System.out.println("dict=" + en.getIsDel() + "  " + en.getDictId() + " " + en.getModTime());
		}

		assertNotNull(dictList);
	}

}
