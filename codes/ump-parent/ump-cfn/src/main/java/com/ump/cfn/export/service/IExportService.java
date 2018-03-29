package com.ump.cfn.export.service;

import java.util.Map;

public interface IExportService {
	public boolean beforeExport(Map<String, Object> paramMap);

	public boolean afterExport(Map<String, Object> paramMap);

	public boolean doExport(Map<String, Object> paramMap);
}
