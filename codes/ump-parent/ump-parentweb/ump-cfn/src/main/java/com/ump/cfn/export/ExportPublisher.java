package com.ump.cfn.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.ump.cfn.export.model.TaskDetail;

/**
 * 事件发布类
 * 
 * @author fangyh
 *
 */
@Component
public class ExportPublisher implements ApplicationEventPublisher {
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void publishEvent(Object event) {
		processExportEvent(event);
	}

	private void processExportEvent(Object event) {
		TaskDetail taskDetail = new TaskDetail();
		applicationContext.publishEvent(new ExportEvent(taskDetail));
	}

}
