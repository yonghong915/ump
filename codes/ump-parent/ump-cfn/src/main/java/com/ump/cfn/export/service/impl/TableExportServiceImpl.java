package com.ump.cfn.export.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ump.cfn.export.service.AbstractBaseExportService;

/**
 * 
 * @author fangyh
 *
 */
@Service("tableExportService")
public class TableExportServiceImpl extends AbstractBaseExportService {

	@Override
	public boolean doExport(Map<String, Object> paramMap) {
		System.out.println("ExportTableServiceImpl====doExport");
		return true;
	}

}
