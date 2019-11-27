package com.ump.core.ws.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import lombok.Data;

@Data
public class WebServiceInterfaceInfo extends InterfaceInfo implements Cloneable {

	private String wsdlUrl;

	private String namespaceURI;

	private String typeNamespaceString;

	private String namespacePrefix;

	private String soapAction;

	private String inputMessagePartElementName;

	private String outputMessagePartElementName;

	private String style;

	private String inputUse;

	private String outputUse;

	private String inputHeaderPart;

	private String outputHeaderPart;

	private QName inputHeaderMessage;

	private QName outputHeaderMessage;

	private List<Parameter> inputSoapHeaderParams = new ArrayList<>();

	private List<Parameter> outputSoapHeaderParams = new ArrayList<>();

	private String inputHeaderUse;

	private String outputHeaderUse;

	private Map<String, Parameter> types = new HashMap<>();

	public WebServiceInterfaceInfo clone() throws CloneNotSupportedException {
		return (WebServiceInterfaceInfo) super.clone();
	}

}
