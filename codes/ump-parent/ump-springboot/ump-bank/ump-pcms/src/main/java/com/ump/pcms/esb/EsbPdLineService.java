package com.ump.pcms.esb;

import java.util.Map;

import com.ump.core.base.service.AbstractEsbService;

public class EsbPdLineService extends AbstractEsbService {
	public EsbPdLineService() {
		super.wsdlFileName = "S000001.wsdl";
	}

	@Override
	protected Map<String, Object> invoke(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean checkParams(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return false;
	}

}
