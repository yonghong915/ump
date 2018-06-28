package com.ump.cfn.export;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.ump.exception.BusinessException;

import com.ump.cfn.export.model.TaskDetail;
import com.ump.cfn.export.service.IExportService;

/**
 * 事件监听器
 * 
 * @author fangyh
 *
 */
@Component
public class ExportListener implements ApplicationListener<ExportEvent> {

	@Autowired
	private ApplicationContext applicationContext;

	@Async
	@Override
	public void onApplicationEvent(ExportEvent event) {
		TaskDetail taskDetail = (TaskDetail) event.getSource();

		IExportService baseService = this.getBaseService();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("taskDetail", taskDetail);
		try {
			if (baseService.beforeExport(paramMap) && baseService.doExport(paramMap)
					&& baseService.afterExport(paramMap)) {
				System.out.println("onApplicationEvent.....");
			}
		} catch (BusinessException e) {
		}
		System.out.println("");
	}

	private IExportService getBaseService() {
		String beanId = StringUtils
				.uncapitalize(StringUtils.replaceOnce("AbstractBaseExportService", "AbstractBase", "Table"));
		IExportService baseService = (IExportService) applicationContext.getBean(beanId);
		return baseService;
	}

}
