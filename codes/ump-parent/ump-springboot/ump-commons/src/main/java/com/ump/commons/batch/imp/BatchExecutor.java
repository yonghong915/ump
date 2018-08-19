package com.ump.commons.batch.imp;

import java.util.Map;

import com.ump.commons.CommUtils;
import com.ump.commons.ReflectUtil;

/**
 * 
 * @author fangyh
 *
 */
public class BatchExecutor implements Runnable {
	private IHandler impl;
	private Map<String, Object> paramMap;

	public BatchExecutor(String className, Map<String, Object> paramMap) {
		if (CommUtils.isEmpty(className)) {
			return;
		}
		Object obj = ReflectUtil.getObjectInstance(className);
		if (null != obj && (obj instanceof IHandler)) {
			this.impl = (IHandler) obj;
			this.paramMap = paramMap;
		}
	}

	@Override
	public void run() {
		impl.execute(paramMap);
	}
}
