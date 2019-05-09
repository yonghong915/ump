package com.ump.core.base.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ump.commons.constant.ConstantUtil;
import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;
import com.ump.core.common.aspect.annotation.AutoLog;

/**
 * 
 * @author fangyh
 *
 */
@Controller
@RequestMapping("/esbsrv")
public class EsbCtrler extends BaseCtrler {
	private String CHARSET = "UTF-8";

	@RequestMapping("/aaa")
	@ResponseBody
	@AutoLog(logType = ConstantUtil.LogType.OPERATE_TYPE, value = "aaaa")
	public ResultRsp<?> execute(String param) {
		return ResultUtil.success();
	}

	@RequestMapping("/execute")
	@ResponseBody
	public ResultRsp<?> execute(HttpServletRequest request, HttpServletResponse response) {
		ResultRsp<?> rsp = ResultUtil.success();

		try {
			request.setCharacterEncoding(CHARSET);
			BufferedReader reader = request.getReader();
			String line = "";
			StringBuilder soapMessage = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				soapMessage.append(line);
			}
			// String serviceName = "";
			// IEsbService esbService = null;
//			RestStatus sm = ServiceMappingEnum.valueOfCode(serviceName);
//			String className = sm.message();
		} catch (IOException e) {
		}
		return rsp;
	}

}
