package com.ump.exception;

public interface ExceptionCode {
	// 错误码编号
	String getErrorCode();

	// 错误码描述
	String getMessage();

	// 打印重写
	String toString();
}
