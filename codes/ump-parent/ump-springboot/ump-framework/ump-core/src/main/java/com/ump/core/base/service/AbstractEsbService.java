package com.ump.core.base.service;

import java.util.HashMap;
import java.util.Map;

import com.ump.commons.exception.BusinessException;

import lombok.Getter;

public abstract class AbstractEsbService implements IEsbService {

	@Getter
	protected String wsdlFileName;

	@Override
	public Map<String, Object> execute(Map<String, Object> context) {
		if (!checkParams(context)) {
			throw new BusinessException("E00001", "接口参数验证失败");
		}
		return invoke(context);
	}

	public void setReqBody(Map<String, Object> reqBody) {
	}

	public Map<String, Object> setRtnBody(Map<String, Object> result) {
		Map<String, Object> svcBody = new HashMap<>();
		if (null == result) {
			return svcBody;
		}
		svcBody = (Map<String, Object>) result.get("returnParam");
		return svcBody;
	}

	protected abstract Map<String, Object> invoke(Map<String, Object> context);

	protected abstract boolean checkParams(Map<String, Object> paramMap);
}
