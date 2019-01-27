package com.ump.core.base.batch.config;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public class Person {
	@Size(max = 4, min = 2) // 1此处使用JSR-303注解来校验数据。
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private int age;
	@Getter
	@Setter
	private String nation;
	@Getter
	@Setter
	private String address;
}
