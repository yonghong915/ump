package com.ump.cfn.export.service;

import java.util.Map;

public abstract class AbstractBaseExportService implements IExportService {
	public boolean beforeExport(Map<String, Object> paramMap) {
		System.out.println("AbstractBaseExportService===beforeExport");
		return true;
	}

	public boolean afterExport(Map<String, Object> paramMap) {
		System.out.println("AbstractBaseExportService===afterExport");
		return true;
	};
}
