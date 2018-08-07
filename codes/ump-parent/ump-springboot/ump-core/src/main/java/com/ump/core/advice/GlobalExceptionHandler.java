package com.ump.core.advice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ump.commons.exception.CommonException;
import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.StatusCode;

@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler
	public ResultRsp<?> handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
		logger.info("Restful Http请求发生异常...");
		if (res.getStatus() == HttpStatus.BAD_REQUEST.value()) {
			logger.info("修改返回状态值为200");
			res.setStatus(HttpStatus.OK.value());
		}

		if (e instanceof NullPointerException) {
			logger.error("代码00：" + e.getMessage(), e);
			return new ResultRsp<>(StatusCode.NULLPOINTER);
		} else if (e instanceof IllegalArgumentException) {
			logger.error("代码01：" + e.getMessage(), e);
			return new ResultRsp<>(StatusCode.ILLEGAL_ARGUMENT);
		} else if (e instanceof SQLException) {
			logger.error("代码02：" + e.getMessage(), e);
			return new ResultRsp<>(StatusCode.DB_EXCEPTION);
		} else if (e instanceof CommonException) {
			CommonException ex = (CommonException) e;
			logger.error("【系统异常】{}", e);
			return new ResultRsp<>(ex.getCode(), ex.getMessage());
		} else {
			logger.error("代码99：" + e.getMessage(), e);
			return new ResultRsp<>(StatusCode.OTHER_EXCEPTION);
		}
	}
}
