package com.ump.core.batch.imp;

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

	public BatchExecutor(String className, Map<String, Object> paramMap) {
		if (CommUtils.isEmpty(className)) {
			return;
		}
		Object obj = ReflectUtil.getObjectInstance(className);
		if (null != obj) {
			if (obj instanceof IHandler) {
				impl = (IHandler) obj;
			}
		}
	}

	@Override
	public void run() {
		impl.execute();
	}
}
