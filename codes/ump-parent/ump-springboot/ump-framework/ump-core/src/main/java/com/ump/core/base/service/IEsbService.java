package com.ump.core.base.service;

import java.util.Map;

public interface IEsbService {
	public Map<String, Object> execute(Map<String, Object> paramMap);

	public String getWsdlFileName();

	public void setReqBody(Map<String, Object> reqBody);

	public Map<String, Object> setRtnBody(Map<String, Object> result);

}
