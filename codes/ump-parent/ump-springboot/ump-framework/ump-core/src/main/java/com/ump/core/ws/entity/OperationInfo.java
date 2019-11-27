package com.ump.core.ws.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.wsdl.Message;
import javax.xml.namespace.QName;

import org.exolab.castor.xml.schema.Schema;

import com.ump.core.ws.message.soap.domain.ParameterInfo;

import lombok.Data;

@Data
public class OperationInfo {
	/** SOAP operation type */
	private String operationType = "";
	/** The SOAP encoding style to use */
	private String encodingStyle = "";
	/** The URL where the target object in located */
	private String targetURL = "";
	/** The namespace URI used to this SOAP operation */
	private String namespaceURI = "";

	private String targetObjectURI = "";

	private String targetMethodName = "";

	private String inputMessageText = "";

	private String outputMessageText = "";

	private String inputMessageName = "";

	private String outputMessageName = "";

	private String soapActionURI = "";

	private String style = "document";

	private List<ParameterInfo> inputparamters = new ArrayList<>();

	private List<ParameterInfo> outputparamters = new ArrayList<>();

	private Message inmessage;

	private Message outmessage;

	private String inputUse;

	private String outputUse;

	private String namespacePrefix;

	private String inputMessagePartElementName;

	private String outputMessagePartElementName;

	public Object getInputHeaderMessage;

	private List inputBodyParts = new ArrayList<>();

	private List outputBodyParts = new ArrayList<>();

	private String inputHeaderPart;

	private String outputHeaderPart;

	private QName inputHeaderMessage;

	private QName outputHeaderMessage;

	private List<ParameterInfo> inputSoapHeaderParams = new ArrayList<>();

	private List<ParameterInfo> outputSoapHeaderParams = new ArrayList<>();

	private String inputHeaderUse;

	private String outputHeaderUse;

	private String serviceId;

	private Map<String, ParameterInfo> types = new HashMap<>();
	private List<Schema> wsdltypes = new ArrayList<>();

	public OperationInfo() {
		super();
	}

	public OperationInfo(String style) {
		super();
		setStyle(style);
	}

	public void addInputSoapHeaderParam(ParameterInfo parameter) {
		this.inputSoapHeaderParams.add(parameter);
	}

	public void addOutputSoapHeaderParam(ParameterInfo parameter) {
		this.outputSoapHeaderParams.add(parameter);
	}

	public void addInparameter(ParameterInfo parameter) {
		this.inputparamters.add(parameter);
	}

	public void addOutparameter(ParameterInfo parameter) {
		this.outputparamters.add(parameter);
	}

}
