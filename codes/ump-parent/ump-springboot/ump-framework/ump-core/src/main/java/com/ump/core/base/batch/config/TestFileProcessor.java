package com.ump.core.base.batch.config;

import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TestFileProcessor implements ItemProcessor<TestReport, TestReport> {
	private static final Logger log = LoggerFactory.getLogger(TestFileProcessor.class);

	@Override
	public TestReport process(final TestReport testReport) throws Exception {
		//testReport.setTimeSection(CommonUtils.getTimeSection(0, 0, 0));
		log.info("StatisticResult 【" + testReport + "】");
		return testReport;
	}

}
