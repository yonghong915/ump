package com.ump.core.base.batch.config;

import org.springframework.batch.item.ItemProcessor;

public class MyItemProcessor implements ItemProcessor<Person, Person> {
	@Override
	public Person process(Person person) {
		String name = person.getName().toUpperCase();
		person.setName(name);
		return person;
	}

}
