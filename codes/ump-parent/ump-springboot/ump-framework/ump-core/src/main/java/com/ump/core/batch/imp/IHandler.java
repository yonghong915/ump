package com.ump.core.batch.imp;

import java.util.Map;

public interface IHandler {
	public abstract void execute(Map<String, Object> paramMap);
}
