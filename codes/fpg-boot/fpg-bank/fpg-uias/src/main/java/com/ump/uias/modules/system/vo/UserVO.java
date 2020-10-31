package com.ump.uias.modules.system.vo;


import com.ump.core.common.aspect.annotation.Check;

import lombok.Data;

@Data
public class UserVO {
	@Check(notNull = true)
	private Integer id; // 问题ID
	@Check(notNull = true, maxLen = 30)
	private String problemName;
}
