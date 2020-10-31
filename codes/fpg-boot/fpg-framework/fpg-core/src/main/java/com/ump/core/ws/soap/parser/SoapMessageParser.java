package com.ump.core.ws.soap.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.ump.core.ws.entity.Parameter;

public class SoapMessageParser {

	public static Map<String, Object> soapMessage2Map(String soapMsg, List<Parameter> soapHeaderParamList,
			List<Parameter> soapBodyParamList) {
		Map<String, Object> output = soapMessage2Map(xmlString2Document(soapMsg), soapHeaderParamList,
				soapBodyParamList);
		return output;
	}

	private static Map<String, Object> soapMessage2Map(Document doc, List<Parameter> soapHeaderParamList,
			List<Parameter> soapBodyParamList) {
		Map<String, Object> parseResult = new HashMap<>();
		if (null == doc) {
			throw new IllegalArgumentException("get document from soap message is null for soap message parse");
		}
		Element root = doc.getRootElement();
		if (null == root) {
			throw new IllegalArgumentException("the document from soap message has no element for soap message parse");
		}
		if (null != soapHeaderParamList && !soapHeaderParamList.isEmpty()) {
			// SoapHeader
			//Element headerElement = root.element(WSContants.SOAP_HEADER);
		}
		return parseResult;
	}

	private static Document xmlString2Document(String soapMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Map<String, Object> soapMessage2Map(String string, Object inputSoapHeaderParams,
			Object inputParameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
