package com.ump.cfn.file;

import java.util.HashMap;
import java.util.Map;

import com.ump.commons.ReflectUtil;
import com.ump.core.batch.imp.IUpload;

public class FileAction {
	public void upload() {
		// 处理上传操作
		String procName = "com.ump.cfn.batch.FileTrace";
		process(procName);
	}

	private void process(String procName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object obj = ReflectUtil.getObjectInstance(procName, paramMap);
		if (null != obj) {
			if (obj instanceof IUpload) {
				IUpload instance = (IUpload) obj;
				instance.afterUpload();
			}
		}

	}

	public void download() {
	}
}
