package com.ump.core.advice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ump.commons.exception.BusinessException;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;
import com.ump.commons.web.StatusCode;

/**
 * exception handler
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler
	public ResultRsp<?> handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
		logger.info("Restful Http请求发生异常...");
		if (e instanceof NullPointerException) {
			logger.error("代码00：" + e.getMessage(), e);
			return ResultUtil.message(StatusCode.NULLPOINTER);
		} else if (e instanceof IllegalArgumentException) {
			logger.error("代码01：" + e.getMessage(), e);
			return ResultUtil.message(StatusCode.ILLEGAL_ARGUMENT);
		} else if (e instanceof SQLException) {
			logger.error("代码02：" + e.getMessage(), e);
			return ResultUtil.message(StatusCode.DB_EXCEPTION);
		} else if (e instanceof CommonException) {
			CommonException ex = (CommonException) e;
			logger.error("【系统异常】{}", e);
			return ResultUtil.error(ex.getCode(), ex.getMessage());
		} else if (e instanceof BusinessException) {
			BusinessException ex = (BusinessException) e;
			logger.error("【系统异常】{}", e);
			return ResultUtil.error(ex.getCode(), ex.getMessage());
		} else {
			logger.error("代码99：" + e.getMessage(), e);
			return ResultUtil.message(StatusCode.OTHER_EXCEPTION);
		}
	}
}
