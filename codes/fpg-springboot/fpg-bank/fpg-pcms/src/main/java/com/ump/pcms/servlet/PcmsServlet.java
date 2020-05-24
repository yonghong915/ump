package com.ump.pcms.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.core.base.service.IEsbService;
import com.ump.core.ws.config.WebServiceInterfaceManager;
import com.ump.core.ws.config.WsdlConfigManager;
import com.ump.core.ws.entity.OperationInfo;
import com.ump.core.ws.entity.ServiceInfo;
import com.ump.core.ws.entity.WebServiceInterfaceInfo;
import com.ump.core.ws.soap.parser.SoapMessageParser;
import com.ump.pcms.esb.EsbFactory;

@WebServlet(urlPatterns = "/pcms/serv/*")
public class PcmsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("doPost>>>>>>");
		String rsp = "";
		List<String> reqMsg = IOUtils.readLines(request.getReader());
		String soapMessage = StringUtils.join(reqMsg.toArray(), "");
		String serviceName = getServiceName(soapMessage);
		IEsbService esbService = EsbFactory.getNewEsbService(serviceName);
		WebServiceInterfaceInfo webInfo = null;
		OperationInfo operation = null;
		String fullPath = WsdlConfigManager.getWsdlPath(esbService.getWsdlFileName());
		ServiceInfo serviceInfo = WebServiceInterfaceManager.getServiceInfoByWsdl(fullPath);
		Map<String, Object> refValue = new HashMap<>();
		List<OperationInfo> ops = serviceInfo.getOperations();
		if (null != ops) {
			for (OperationInfo option : ops) {
				webInfo = WebServiceInterfaceManager.getInterfaceInfoByFullPath(fullPath, option);
				if (null != webInfo && option.toString().equals(serviceName)) {
					operation = option;
					break;
				}
			}
		}

		if (StringUtils.isEmpty(serviceName) && null != esbService) {
			if (null != webInfo && null != operation) {
				Map<String, Object> soapContent = SoapMessageParser.soapMessage2Map(soapMessage.toString(),
						webInfo.getInputSoapHeaderParams(), webInfo.getInputParameters());
				rsp = runService(fullPath, operation, soapContent, esbService, refValue);
			}
		}

	}

	private String runService(String fullPath, OperationInfo operation, Map<String, Object> soapContent,
			IEsbService esbService, Map<String, Object> refValue) {
		String res = "";
		String errorCode = "", errorMsg = "";
		Map<String, Object> rspBody = new HashMap<>();
		Map<String, Object> context = new HashMap<>();
		Map<String, Object> reqBody = (Map<String, Object>) soapContent.get("Body");
		esbService.setReqBody(reqBody);
		context = (Map<String, Object>) reqBody.get("SvcBody");

		Map<String, Object> result = esbService.execute(context);
		rspBody = esbService.setRtnBody(result);
		Map<String, Object> header = new HashMap<>();
		//res = ih.pack(fullPath, operation.toString(), header, rspBody, false);
		return null;
	}

	private String getServiceName(String soapMessage) {
		soapMessage = soapMessage.substring(soapMessage.indexOf("urn:/") + 5);
		soapMessage = soapMessage.substring(0, soapMessage.indexOf("</"));
		return soapMessage;
	}

}
