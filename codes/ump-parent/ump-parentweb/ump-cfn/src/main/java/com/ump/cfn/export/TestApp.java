package com.ump.cfn.export;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ump.cfn.export.model.TaskDetail;

public class TestApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
		ExportPublisher demoEventPublisher = context.getBean(ExportPublisher.class);
		TaskDetail task = new TaskDetail();
		ExportEvent event = new ExportEvent(task);
		demoEventPublisher.publishEvent(event);
		context.close();
	}

}
