package com.ump.core.base.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ump.commons.web.ResultRsp;

/**
 * 
 * @author fangyh
 *
 */
@Controller
@RequestMapping("/esbsrv")
public class EsbCtrler extends BaseCtrler {
	private String CHARSET = "UTF-8";

	@RequestMapping("/execute")
	@ResponseBody
	public ResultRsp<?> execute(HttpServletRequest request, HttpServletResponse response) {
		ResultRsp<?> rsp = new ResultRsp<>();

		try {
			request.setCharacterEncoding(CHARSET);
			BufferedReader reader = request.getReader();
			String line = "";
			StringBuilder soapMessage = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				soapMessage.append(line);
			}
			//String serviceName = "";
			//IEsbService esbService = null;
//			RestStatus sm = ServiceMappingEnum.valueOfCode(serviceName);
//			String className = sm.message();
		} catch (IOException e) {
		}
		return rsp;
	}

}
