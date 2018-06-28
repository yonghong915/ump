package com.ump.cfn.export;

import org.springframework.context.ApplicationEvent;

import com.ump.cfn.export.model.TaskDetail;

public class ExportEvent extends ApplicationEvent {
	/**
	 * 事件
	 */
	private static final long serialVersionUID = 1L;
	public ExportEvent(TaskDetail taskDetail) {
		 super(taskDetail);
	}



}
