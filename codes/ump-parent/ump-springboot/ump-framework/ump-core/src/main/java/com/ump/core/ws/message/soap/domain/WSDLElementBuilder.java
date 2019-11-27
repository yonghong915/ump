/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.ws.message.soap.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.wsdl.Binding;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPHeader;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.exolab.castor.xml.Namespaces;
import org.exolab.castor.xml.schema.Annotation;
import org.exolab.castor.xml.schema.ComplexType;
import org.exolab.castor.xml.schema.Documentation;
import org.exolab.castor.xml.schema.ElementDecl;
import org.exolab.castor.xml.schema.Facet;
import org.exolab.castor.xml.schema.Form;
import org.exolab.castor.xml.schema.Group;
import org.exolab.castor.xml.schema.Order;
import org.exolab.castor.xml.schema.Particle;
import org.exolab.castor.xml.schema.Schema;
import org.exolab.castor.xml.schema.SimpleType;
import org.exolab.castor.xml.schema.Structure;
import org.exolab.castor.xml.schema.XMLType;
import org.exolab.castor.xml.schema.reader.SchemaReader;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.ibm.wsdl.ImportImpl;
import com.ump.core.util.ValidateUtils;
import com.ump.core.util.constants.WSConstants;
import com.ump.core.ws.entity.OperationInfo;
import com.ump.core.ws.entity.ServiceInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-07 22:50:49
 *
 */
public class WSDLElementBuilder {
	private WSDLFactory wsdlFactory = null;
	private List<Schema> wsdlTypes = new ArrayList<Schema>();
	private Map<?, ?> messages;

	@Getter
	@Setter
	private String documentBase;

	@Getter
	@Setter
	private boolean onlyGenerateSelfNestedTypes = false;
	private Map<?, ?> namespaces;
	private Map<String, String> extendedTypeNamespaces = null;

	public WSDLElementBuilder() throws WSDLException {
		wsdlFactory = WSDLFactory.newInstance();
	}

	public ServiceInfo buildServiceInfo(InputStream wsdlIs, String contextUrl) throws WSDLException {
		Definition def = parseWSDL(wsdlIs, contextUrl);
		return buildService(def);
	}

	private ServiceInfo buildService(Definition def) {
		wsdlTypes = createSchemaFromTypes(def);
		namespaces = def.getNamespaces();
		extendedTypeNamespaces = new LinkedHashMap<String, String>();
		messages = def.getMessages();
		ServiceInfo serviceInfo = null;
		Map services = def.getServices();
		if (ValidateUtils.isEmpty(services)) {
			return null;
		}
		Iterator svcIter = services.values().iterator();
		serviceInfo = populateServices((Service) svcIter.next());
		if (ValidateUtils.isEmpty(serviceInfo)) {
			return null;
		}
		serviceInfo.setTargetnamespace(def.getTargetNamespace());
		serviceInfo.setUsedExtendedTypeNamespaces(extendedTypeNamespaces);
		if (ValidateUtils.isNotEmpty(extendedTypeNamespaces)) {
			StringBuffer buf = new StringBuffer();
			for (Map.Entry<String, String> en : extendedTypeNamespaces.entrySet()) {
				buf.append(" xmlns:");
				buf.append(en.getValue());
				buf.append("=\"");
				buf.append(en.getKey());
				buf.append("\"");
			}
			serviceInfo.setExtendedTypeNamespacesString(buf.toString());

			for (OperationInfo op : serviceInfo.getOperations()) {
				String targetNamespace = op.getNamespaceURI();
				if (ValidateUtils.isNotEmpty(targetNamespace)) {
					String namespacePrefix = extendedTypeNamespaces.get(targetNamespace);
					if (ValidateUtils.isEmpty(namespacePrefix)) {
						if (ValidateUtils.isEmpty(extendedTypeNamespaces)) {
							namespacePrefix = "m";
						} else {
							namespacePrefix = "m" + (extendedTypeNamespaces.size() - 1);
						}
						extendedTypeNamespaces.put(targetNamespace, namespacePrefix);
					}
					op.setNamespacePrefix(namespacePrefix);
				}
			}
		}
		return serviceInfo;
	}

	private ServiceInfo populateServices(Service service) {
		QName qName = service.getQName();
		String name = qName.getLocalPart();
		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setName(name);
		Map<?, ?> ports = service.getPorts();
		Iterator<?> portIter = ports.values().iterator();
		while (portIter.hasNext()) {
			Port port = (Port) portIter.next();
			List<ExtensibilityElement> addElems = findExtensibilityElement(port.getExtensibilityElements(),
					WSConstants.ADDRESS_LOCAL_NAME);
			if (ValidateUtils.isNotEmpty(addElems)) {
				ExtensibilityElement element = addElems.get(0);
				if (ValidateUtils.isNotEmpty(element) && element instanceof SOAPAddress) {
					SOAPAddress soapAddr = (SOAPAddress) element;
					serviceInfo.setEndpoint(soapAddr.getLocationURI());

					Binding binding = port.getBinding();
					List<OperationInfo> operations = populateOperations(binding);
					serviceInfo.setOperations(operations);
				}
			}
		}
		return serviceInfo;
	}

	private List<OperationInfo> populateOperations(Binding binding) {
		List<OperationInfo> operationInfos = new ArrayList<OperationInfo>();
		List operations = binding.getBindingOperations();
		if (!ValidateUtils.isEmpty(operations)) {
			List soapBindingElems = findExtensibilityElement(binding.getExtensibilityElements(),
					WSConstants.BINDING_LOCAL_NAME);
			String style = WSConstants.WSDL_STYLE_DOCUMENT;
			if (!ValidateUtils.isEmpty(soapBindingElems)) {
				ExtensibilityElement soapBindingElem = (ExtensibilityElement) soapBindingElems.get(0);
				if (!ValidateUtils.isEmpty(soapBindingElem) && soapBindingElem instanceof SOAPBinding) {
					SOAPBinding soapBinding = (SOAPBinding) soapBindingElem;
					style = soapBinding.getStyle();
				}
			}

			for (int i = 0; i < operations.size(); i++) {
				BindingOperation oper = (BindingOperation) operations.get(i);
				OperationInfo operationInfo = new OperationInfo(style);
				populateOperations(operationInfo, oper);
				operationInfos.add(operationInfo);
			}
		}
		return operationInfos;
	}

	private OperationInfo populateOperations(OperationInfo operationInfo, BindingOperation bindingOper) {
		Operation oper = bindingOper.getOperation();
		operationInfo.setTargetMethodName(oper.getName());

		List operElems = findExtensibilityElement(bindingOper.getExtensibilityElements(),
				WSConstants.OPERATION_LOCAL_NAME);
		if (!ValidateUtils.isEmpty(operElems)) {
			ExtensibilityElement operElem = (ExtensibilityElement) operElems.get(0);
			if (!ValidateUtils.isEmpty(operElem) && operElem instanceof SOAPBinding) {
				SOAPOperation soapOperation = (SOAPOperation) operElem;
				operationInfo.setSoapActionURI(soapOperation.getSoapActionURI());
			}
		}

		BindingInput bindingInput = bindingOper.getBindingInput();
		List inputHeadElems = findExtensibilityElement(bindingInput.getExtensibilityElements(),
				WSConstants.HEAD_LOCAL_NAME);
		if (!ValidateUtils.isEmpty(inputHeadElems)) {
			ExtensibilityElement headerElem = (ExtensibilityElement) inputHeadElems.get(0);
			if (!ValidateUtils.isEmpty(headerElem) && headerElem instanceof SOAPHeader) {
				SOAPHeader soapHeader = (SOAPHeader) headerElem;
				operationInfo.setInputHeaderPart(soapHeader.getPart());
				operationInfo.setInputHeaderMessage(soapHeader.getMessage());
				operationInfo.setInputHeaderUse(soapHeader.getUse());
			}
		}

		List bodyElems = findExtensibilityElement(bindingInput.getExtensibilityElements(), WSConstants.BODY_LOCAL_NAME);
		if (!ValidateUtils.isEmpty(bodyElems)) {
			ExtensibilityElement bodyElem = (ExtensibilityElement) bodyElems.get(0);
			if (!ValidateUtils.isEmpty(bodyElem) && bodyElem instanceof SOAPBody) {
				SOAPBody soapBody = (SOAPBody) bodyElem;
				List styles = soapBody.getEncodingStyles();
				String encodingStyle = null;
				if (!ValidateUtils.isEmpty(styles)) {
					encodingStyle = styles.get(0).toString();
				}
				encodingStyle = ValidateUtils.isEmpty(encodingStyle) ? WSConstants.DEFAULT_SOAP_ENCODING_STYLE
						: encodingStyle;

				operationInfo.setEncodingStyle(encodingStyle);
				operationInfo.setTargetObjectURI(soapBody.getNamespaceURI());
				operationInfo.setInputBodyParts(soapBody.getParts());
				operationInfo.setInputUse(soapBody.getUse());
			}
		}

		BindingOutput bindingOutput = bindingOper.getBindingOutput();
		List outputHeadElems = findExtensibilityElement(bindingOutput.getExtensibilityElements(),
				WSConstants.HEAD_LOCAL_NAME);
		if (!ValidateUtils.isEmpty(outputHeadElems)) {
			ExtensibilityElement headerElem = (ExtensibilityElement) outputHeadElems.get(0);
			if (!ValidateUtils.isEmpty(headerElem) && headerElem instanceof SOAPHeader) {
				SOAPHeader soapHeader = (SOAPHeader) headerElem;
				operationInfo.setOutputHeaderPart(soapHeader.getPart());
				operationInfo.setOutputHeaderMessage(soapHeader.getMessage());
				operationInfo.setOutputHeaderUse(soapHeader.getUse());
			}
		}

		List outBodyElems = findExtensibilityElement(bindingOutput.getExtensibilityElements(),
				WSConstants.BODY_LOCAL_NAME);
		if (!ValidateUtils.isEmpty(outBodyElems)) {
			ExtensibilityElement bodyElem = (ExtensibilityElement) outBodyElems.get(0);
			if (!ValidateUtils.isEmpty(bodyElem) && bodyElem instanceof SOAPBody) {
				SOAPBody soapBody = (SOAPBody) bodyElem;
				operationInfo.setOutputBodyParts(soapBody.getParts());
				operationInfo.setOutputUse(soapBody.getUse());
			}
		}

		if (!ValidateUtils.isEmpty(operationInfo.getInputHeaderMessage())) {
			Message inputHeaderMessage = (Message) this.messages.get(operationInfo.getInputHeaderMessage());
			getParameterFromMessage(operationInfo, inputHeaderMessage, 1, Boolean.TRUE);
		}

		if (!ValidateUtils.isEmpty(operationInfo.getOutputHeaderMessage())) {
			Message inputHeaderMessage = (Message) this.messages.get(operationInfo.getOutputHeaderMessage());
			getParameterFromMessage(operationInfo, inputHeaderMessage, 2, Boolean.TRUE);
		}

		Input inDef = oper.getInput();
		if (!ValidateUtils.isEmpty(inDef)) {
			Message inMsg = inDef.getMessage();
			if (!ValidateUtils.isEmpty(inMsg)) {
				operationInfo.setInputMessageName(inMsg.getQName().getLocalPart());
				operationInfo.setInputMessagePartElementName(getMessagePartElementName(inMsg));
				getParameterFromMessage(operationInfo, inMsg, 1, Boolean.FALSE);
				operationInfo.setInmessage(inMsg);
			}
		}

		Output outDef = oper.getOutput();
		if (!ValidateUtils.isEmpty(outDef)) {
			Message outMsg = outDef.getMessage();
			if (!ValidateUtils.isEmpty(outMsg)) {
				operationInfo.setOutputMessageName(outMsg.getQName().getLocalPart());
				operationInfo.setOutputMessagePartElementName(getMessagePartElementName(outMsg));
				getParameterFromMessage(operationInfo, outMsg, 2, Boolean.FALSE);
				operationInfo.setOutmessage(outMsg);
			}
		}

		return operationInfo;
	}

	private String getMessagePartElementName(Message message) {
		String ret = null;
		Map<?, ?> parts = message.getParts();
		Iterator<?> partIter = parts.values().iterator();
		Part part = (Part) partIter.next();
		QName elementName = part.getElementName();
		if (ValidateUtils.isNotEmpty(elementName)) {
			ret = elementName.getLocalPart();
		}
		if (ValidateUtils.isEmpty(ret)) {
			QName typeName = part.getTypeName();
			if (ValidateUtils.isNotEmpty(typeName)) {
				ret = typeName.getLocalPart();
			}
		}
		return ret;
	}

	private void getParameterFromMessage(OperationInfo operationInfo, Message msg, int manner,
			boolean isSoapHeaderMsg) {
		List msgParts = msg.getOrderedParts(null);
		String messageName = msg.getQName().getLocalPart();
		for (int i = 0; i < msgParts.size(); i++) {
			Part part = (Part) msgParts.get(i);
			String partName = part.getName();
			ElementDecl elemDecl = getElementDecl(part, operationInfo);
			XMLType xmlType = null;
			if (ValidateUtils.isNotEmpty(elemDecl)) {
				xmlType = elemDecl.getType();
			}
			if (ValidateUtils.isEmpty(xmlType)) {
				xmlType = getComplexType(part, operationInfo);
			}
			if (ValidateUtils.isEmpty(xmlType) && ValidateUtils.isNotEmpty(elemDecl)) {
//				XMLType localXMLType = elemDecl.getXMLType();
//				if (!ValidateUtils.isEmpty(localXMLType)) {
//					xmlType = getTypeReference(localXMLType);
//				}

			}

			if (isSoapHeaderMsg) {
				if (partName.equals(operationInfo.getInputHeaderPart())
						|| partName.equals(operationInfo.getOutputHeaderPart())) {
					if (xmlType instanceof ComplexType) {
						ParameterInfo parameter = new ParameterInfo();
						parameter.setName(partName);
						if (manner == 1) {
							operationInfo.addInputSoapHeaderParam(parameter);
						} else {
							operationInfo.addOutputSoapHeaderParam(parameter);
						}

						if (ValidateUtils.isEmpty(xmlType)) {
							buildParameterType(operationInfo, manner, parameter, elemDecl, xmlType, null);
						} else {
							QName typeName = part.getTypeName();
							if (ValidateUtils.isNotEmpty(typeName)) {
								parameter.setKind(typeName.getLocalPart());
							}
						}
						continue;
					}
				}
			}

			List bodyParts = null;
			if (manner == 1) {
				bodyParts = operationInfo.getInputBodyParts();
			} else {
				bodyParts = operationInfo.getOutputBodyParts();
			}

			if (ValidateUtils.isNotEmpty(bodyParts) && bodyParts.contains(partName)) {
				if (xmlType instanceof ComplexType) {
					buildComplexParameter((ComplexType) xmlType, null, operationInfo, manner, null, false);
					continue;
				}
			}

			if (ValidateUtils.isNotEmpty(partName)
					&& ("parameters".equals(partName)
							|| partName.equals(operationInfo.getTargetMethodName() + WSConstants.SOAP_REQUEST_SUFFIX)
							|| partName.equals(operationInfo.getTargetMethodName() + WSConstants.SOAP_RESPONSE_SUFFIX))
					|| (partName != null && partName.equals(messageName))) {
				if (xmlType instanceof ComplexType) {
					buildComplexParameter((ComplexType) xmlType, null, operationInfo, manner, null, Boolean.FALSE);
					continue;
				}
			}

			ParameterInfo parameter = new ParameterInfo();
			parameter.setName(partName);
			if (manner == 1) {
				if (isSoapHeaderMsg) {
					operationInfo.addInputSoapHeaderParam(parameter);
				} else {
					operationInfo.addInparameter(parameter);
				}
			} else {
				if (isSoapHeaderMsg) {
					operationInfo.addOutputSoapHeaderParam(parameter);
				} else {
					operationInfo.addOutparameter(parameter);
				}
			}

			if (xmlType != null) {
				buildParameterType(operationInfo, manner, parameter, elemDecl, xmlType, null);
			} else {
				QName typeName = part.getTypeName();
				if (typeName != null) {
					parameter.setKind(typeName.getLocalPart());
				}

			}
		}
		operationInfo.setWsdltypes(wsdlTypes);
	}

	private void buildComplexParameter(ComplexType type, ParameterInfo parentParam, OperationInfo operationInfo,
			int manner, Order order, boolean isSoapHeaderMsg) {
		if (ValidateUtils.isNotEmpty(parentParam)) {
			if (onlyGenerateSelfNestedTypes) {
				ParameterInfo ancestorParameterInfo = parentParam
						.getAncestorsParameterInfoByRefTypeName(parentParam.getRefType());
				if (ValidateUtils.isNotEmpty(ancestorParameterInfo)) {
					ParameterInfo superAncestorParam = ancestorParameterInfo.getParentParameter();
					superAncestorParam.getChildParameters().remove(ancestorParameterInfo);

					ParameterInfo typeParam = ancestorParameterInfo;
					ancestorParameterInfo = ancestorParameterInfo.cloneSimple();
					superAncestorParam.getChildParameters().add(ancestorParameterInfo);
					typeParam.setName(ancestorParameterInfo.getRefType());
					typeParam.setParentParameter(null);
					typeParam.setRefType(null);
					typeParam.setRefTypeParameterInfo(null);
					operationInfo.getTypes().put(typeParam.getName(), typeParam);
					ancestorParameterInfo.setRefTypeParameterInfo(typeParam);
					return;
				}

			} else {

				String complexTypeName = type.getName();
				if (ValidateUtils.isNotEmpty(complexTypeName)) {
					ParameterInfo existTypeParam = operationInfo.getTypes().get(complexTypeName);
					if (ValidateUtils.isNotEmpty(existTypeParam)) {
						parentParam.setRefTypeParameterInfo(existTypeParam);
						return;
					}

					ParameterInfo typeParam = parentParam.clone();
					typeParam.setName(complexTypeName);
					typeParam.setParentParameter(null);
					typeParam.setRefType(null);
					typeParam.setRefTypeParameterInfo(null);
					operationInfo.getTypes().put(complexTypeName, typeParam);
					parentParam.setRefTypeParameterInfo(typeParam);
					parentParam = typeParam;
				}
			}
		}
		XMLType baseType = type.getBaseType();
		if (ValidateUtils.isEmpty(baseType)) {
		}

		if (ValidateUtils.isNotEmpty(baseType)) {
			buildComplexParameter((ComplexType) baseType, parentParam, operationInfo, manner, order, isSoapHeaderMsg);
		}

		Enumeration<Particle> particleEnum = type.enumerate();
		Group group = null;
		while (particleEnum.hasMoreElements()) {
			Particle particle = particleEnum.nextElement();
			if (particle instanceof Group) {
				group = (Group) particle;
				break;
			}
		}

		if (ValidateUtils.isNotEmpty(group)) {
			Order childOrder = group.getOrder();
			Enumeration<Particle> groupEnum = group.enumerate();
			while (groupEnum.hasMoreElements()) {
				Structure item = groupEnum.nextElement();
				if (item.getStructureType() == Structure.ELEMENT) {
					ElementDecl elementDecl = (ElementDecl) item;
					ParameterInfo parameter = new ParameterInfo();
					parameter.setName(elementDecl.getName());

					if (ValidateUtils.isNotEmpty(parentParam)) {
						parentParam.setOrderType(childOrder.name());
					}
					if (ValidateUtils.isEmpty(parentParam)) {
						if (manner == 1) {
							if (isSoapHeaderMsg) {
								operationInfo.addInputSoapHeaderParam(parameter);
							} else {
								if (childOrder.equals(Order.choice)) {
									ParameterInfo choiceParam = new ParameterInfo();
									choiceParam.addChoiceParam(elementDecl.getName(), choiceParam);
								} else {
									operationInfo.addInparameter(parameter);
								}

							}
						} else {
							if (isSoapHeaderMsg) {
								operationInfo.addOutputSoapHeaderParam(parameter);
							} else {
								if (childOrder.equals(Order.choice)) {
									ParameterInfo choiceParam = new ParameterInfo();
									choiceParam.addChoiceParam(elementDecl.getName(), choiceParam);
								} else {
									operationInfo.addOutparameter(parameter);
								}

							}
						}
					} else {
						if (childOrder.equals(Order.choice)) {
							parentParam.addChoiceParam(elementDecl.getName(), parameter);
						} else {
							parentParam.addChildParameter(parameter);
							parameter.setParentParameter(parentParam);
						}
					}

					XMLType xmlType = elementDecl.getType();
					if (ValidateUtils.isEmpty(xmlType)) {
						// XMLType localXMLType = elementDecl.getXMLType();
					}
					if (ValidateUtils.isNotEmpty(xmlType)) {
						buildParameterType(operationInfo, manner, parameter, elementDecl, xmlType, childOrder);
					}
				}
			}
		}
	}

	private void buildParameterType(OperationInfo operationInfo, int manner, ParameterInfo parameter,
			ElementDecl elemDecl, XMLType xmlType, Order order) {
		if (ValidateUtils.isNotEmpty(elemDecl)) {
			Enumeration<Annotation> annotations = elemDecl.getAnnotations();
			if (ValidateUtils.isNotEmpty(annotations)) {
				while (annotations.hasMoreElements()) {
					Annotation annotation = annotations.nextElement();
					Enumeration<Documentation> docs = annotation.getDocumentation();
					if (ValidateUtils.isNotEmpty(docs)) {
						while (docs.hasMoreElements()) {
							Documentation doc = docs.nextElement();
							String cotent = doc.getContent();
							if (ValidateUtils.isNotEmpty(cotent)) {
								parameter.getDocumenactionlist().add(cotent);
							}
						}
					}

				}
			}

			String typeNamespace = null;
			Schema schema = null;
			if (ValidateUtils.isNotEmpty(elemDecl.getReference())) {
				schema = elemDecl.getReference().getSchema();
			} else {
				schema = elemDecl.getSchema();
			}
			if (ValidateUtils.isNotEmpty(schema)) {
				typeNamespace = schema.getTargetNamespace();
			}
			parameter.setTypeNamespace(typeNamespace);

			if (ValidateUtils.isNotEmpty(typeNamespace)) {
				String typeNamespacePrefix = extendedTypeNamespaces.get(typeNamespace);
				if (ValidateUtils.isEmpty(typeNamespacePrefix)) {
					Namespaces namespaces = schema.getNamespaces();
					typeNamespacePrefix = namespaces.getNamespacePrefix(typeNamespace);
					if (ValidateUtils.isEmpty(typeNamespacePrefix)) {
						if (extendedTypeNamespaces.size() == 0) {
							typeNamespacePrefix = "m";
						} else {
							typeNamespacePrefix = "m" + (extendedTypeNamespaces.size() - 1);
						}
					}
					extendedTypeNamespaces.put(typeNamespace, typeNamespacePrefix);
				}
				parameter.setTypeNamespacePrefix(typeNamespacePrefix + ":");
			}

			if (ValidateUtils.isNotEmpty(schema)) {
				Form form = schema.getElementFormDefault();
				if (ValidateUtils.isNotEmpty(form)) {
					parameter.setElementFormDefault(form.getValue());
				}

			}

			parameter.setNillable(elemDecl.isNillable());
			parameter.setEmptiable(elemDecl.isEmptiable());
			parameter.setDefaultValue(elemDecl.getDefaultValue());
			parameter.setValid(elemDecl.isValid());
		}

		if (xmlType.isComplexType()) {
			ComplexType complexType = (ComplexType) xmlType;
			if (ValidateUtils.isNotEmpty(elemDecl) && elemDecl.getMaxOccurs() == -1) {
				parameter.setKind(WSConstants.PARAM_KIND_ARRAY);
			} else {
				parameter.setKind(WSConstants.PARAM_KIND_STRUC);
			}
			parameter.setRefType(complexType.getName());
			buildComplexParameter(complexType, parameter, operationInfo, manner, order, Boolean.FALSE);
		} else {
			String typeName = xmlType.getName();
			if (ValidateUtils.isEmpty(typeName)) {
				typeName = xmlType.getBaseTypeName();
			}
			if (xmlType.isSimpleType()) {
				SimpleType simpleType = (SimpleType) xmlType;
				if (simpleType.isNumericType()) {
					if (ValidateUtils.isEmpty(typeName)) {
						typeName = WSConstants.PARAM_KIND_NUMBERIC;
					}
					Facet totalDigitsFacet = simpleType.getFacet("totalDigits");
					if (ValidateUtils.isNotEmpty(totalDigitsFacet)
							&& ValidateUtils.isNotEmpty(totalDigitsFacet.getValue())) {
						parameter.setTotalDigits(totalDigitsFacet.getValue());
					}

					Facet fractionDigits = simpleType.getFacet("fractionDigits");
					if (ValidateUtils.isNotEmpty(fractionDigits)
							&& ValidateUtils.isNotEmpty(fractionDigits.getValue())) {
						parameter.setFractionDigits(fractionDigits.getValue());
					}
				} else if (simpleType.isDateTimeType()) {
					if (ValidateUtils.isEmpty(typeName)) {
						typeName = WSConstants.PARAM_KIND_DATETIME;
					}
				}

				if (simpleType.getLength() != null) {

					parameter.setLength(simpleType.getLength());
				}

				if (simpleType.getMaxLength() != null) {
					parameter.setMaxLength(simpleType.getMaxLength());
				}
				if (simpleType.getMinLength() != null) {
					parameter.setMinLength(simpleType.getMinLength());
				}

			} else if (ValidateUtils.isEmpty(typeName) && xmlType.isAnyType()) {
				typeName = WSConstants.PARAM_KIND_ANYTYPE;
			}

			if (ValidateUtils.isNotEmpty(elemDecl) && elemDecl.getMaxOccurs() == ElementDecl.UNBOUNDED) {
				parameter.setKind(WSConstants.PARAM_KIND_ARRAY);
			} else {
				parameter.setKind(typeName);
			}
		}
	}

	private XMLType getTypeReference(XMLType localXMLType) {
		XMLType referredType = null;
		if (ValidateUtils.isEmpty(wsdlTypes)) {
			return referredType;
		}
		String name = localXMLType.getName();
		if (ValidateUtils.isEmpty(name)) {
			return referredType;
		}
		String canonicalName = name;
		String nsPrefix = "";
		int colon = name.indexOf(58);
		if (colon >= 0) {
			canonicalName = name.substring(colon + 1);
			nsPrefix = name.substring(0, colon);
		}
		return null;
	}

	private XMLType getComplexType(Part part, OperationInfo operationInfo) {
		ComplexType complexType = null;
		QName typeQName = part.getTypeName();
		if (ValidateUtils.isEmpty(typeQName)) {
			typeQName = part.getElementName();
		}
		if (ValidateUtils.isEmpty(typeQName)) {
			return complexType;
		}

		String typeName = typeQName.getLocalPart();
		Stack<Schema> wsdlTypeStack = new Stack<>();
		wsdlTypeStack.addAll(wsdlTypes);
		Schema schema = null;
		while (wsdlTypeStack.size() > 0) {
			schema = wsdlTypeStack.pop();
			if (ValidateUtils.isNotEmpty(schema)) {
				String targetNamespace = schema.getTargetNamespace();
				operationInfo.setNamespaceURI(targetNamespace);

				complexType = schema.getComplexType(typeName);
				if (ValidateUtils.isNotEmpty(complexType)) {
					break;
				}

				addImportSchema2Stack(schema, wsdlTypeStack);
			}

		}
		return complexType;
	}

	private void addImportSchema2Stack(Schema schema, Stack<Schema> wsdlTypeStack) {
		Collection<Schema> importSchemas = schema.getImportedSchema();
		if (ValidateUtils.isEmpty(importSchemas)) {
			return;
		}
		importSchemas.forEach(importedSchema -> {
			wsdlTypeStack.add(importedSchema);
		});
	}

	private ElementDecl getElementDecl(Part part, OperationInfo operationInfo) {
		if (ValidateUtils.isEmpty(wsdlTypes)) {
			return null;
		}
		ElementDecl elemDecl = null;
		QName name = part.getElementName();
		if (ValidateUtils.isEmpty(name)) {
			name = part.getTypeName();
		}
		if (ValidateUtils.isEmpty(name)) {
			return null;
		}

		String elemName = name.getLocalPart();
		if (ValidateUtils.isEmpty(elemName)) {
			return null;
		}

		Stack<Schema> wsdlTypeStack = new Stack<>();
		wsdlTypeStack.addAll(wsdlTypes);
		Schema schema = null;
		while (wsdlTypeStack.size() > 0) {
			schema = wsdlTypeStack.pop();
			if (ValidateUtils.isNotEmpty(schema)) {
				String targetNamespace = schema.getTargetNamespace();
				operationInfo.setNamespaceURI(targetNamespace);

				elemDecl = schema.getElementDecl(elemName);
				if (ValidateUtils.isNotEmpty(elemDecl)) {
					break;
				}
				addImportSchema2Stack(schema, wsdlTypeStack);
			}
		}

		return elemDecl;
	}

	private List<ExtensibilityElement> findExtensibilityElement(List<ExtensibilityElement> extensibilityElements,
			String elementType) {
		List<ExtensibilityElement> elements = new ArrayList<>();
		if (ValidateUtils.isEmpty(extensibilityElements)) {
			return elements;
		}
		for (int i = 0, len = extensibilityElements.size(); i < len; i++) {
			ExtensibilityElement element = extensibilityElements.get(i);
			if (elementType.equals(element.getElementType().getLocalPart())) {
				elements.add(element);
			}
		}
		return elements;
	}

	private List<Schema> createSchemaFromTypes(Definition def) {
		List<Schema> schemas = new ArrayList<>();
		Types types = def.getTypes();
		if (ValidateUtils.isEmpty(types)) {
			Map importsSchema = def.getImports();
			Iterator importIter = importsSchema.keySet().iterator();
			while (importIter.hasNext()) {
				String importKey = (String) importIter.next();
				Vector vector = (Vector) importsSchema.get(importKey);
				Iterator importImplIter = vector.iterator();
				while (importImplIter.hasNext()) {
					ImportImpl impl = (ImportImpl) importImplIter.next();
					def = impl.getDefinition();
					types = def.getTypes();
					if (ValidateUtils.isNotEmpty(types)) {
						break;
					}
				}
			}
		}

		if (ValidateUtils.isNotEmpty(types)) {
			List schemaExElem = findExtensibilityElement(types.getExtensibilityElements(),
					WSConstants.SCHEMA_LOCAL_NAME);
			for (int i = 0; i < schemaExElem.size(); i++) {
				ExtensibilityElement schemaEle = (ExtensibilityElement) schemaExElem.get(i);
				if (!ValidateUtils.isEmpty(schemaEle) && schemaEle instanceof javax.wsdl.extensions.schema.Schema) {
					org.w3c.dom.Element domSchemaEle = ((javax.wsdl.extensions.schema.Schema) schemaEle).getElement();
					Schema schema = createSchemaFromType(domSchemaEle, def);
					schemas.add(schema);
				}
			}
		}
		return schemas;
	}

	private Schema createSchemaFromType(Element domSchemaEle, Definition def) {

		DOMBuilder domBuilder = new DOMBuilder();
		org.jdom2.Element jdomSchemaElement = domBuilder.build(domSchemaEle);
		if (ValidateUtils.isEmpty(jdomSchemaElement)) {
			throw new IllegalArgumentException("Unable to read");
		}

		Map namespaces = def.getNamespaces();
		if (ValidateUtils.isEmpty(namespaces)) {
			Iterator nsIter = namespaces.values().iterator();
			while (nsIter.hasNext()) {
				String nsPrefix = (String) nsIter.next();
				String nsURI = (String) namespaces.get(nsPrefix);
				if (ValidateUtils.isNotEmpty(nsPrefix)) {
					Namespace nsDecl = Namespace.getNamespace(nsPrefix, nsURI);
					jdomSchemaElement.addNamespaceDeclaration(nsDecl);
				}
			}
		}

		jdomSchemaElement.detach();
		Schema schema = null;
		try {
			schema = convertElement2Schema(jdomSchemaElement, documentBase);
			if (ValidateUtils.isNotEmpty(schema) && ValidateUtils.isNotEmpty(namespaces)) {
				Iterator nsIter = namespaces.keySet().iterator();
				while (nsIter.hasNext()) {
					String nsPrefix = (String) nsIter.next();
					String nsURI = (String) namespaces.get(nsPrefix);
					if (ValidateUtils.isNotEmpty(nsPrefix)) {
						String exNsURI = schema.getNamespace(nsPrefix);
						if (ValidateUtils.isEmpty(exNsURI)) {
							schema.addNamespace(nsPrefix, nsURI);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schema;
	}

	public Definition parseWSDL(String wsdlLoaction) throws WSDLException {
		WSDLReader reader = wsdlFactory.newWSDLReader();
		Definition def = reader.readWSDL(null, wsdlLoaction);
		return def;
	}

	private Definition parseWSDL(InputStream wsdlIs, String contextUrl) throws WSDLException {
		WSDLReader reader = wsdlFactory.newWSDLReader();
		InputSource localInputSource = new InputSource(wsdlIs);
		Definition def = reader.readWSDL(contextUrl, localInputSource);
		return def;
	}

	public static XMLType getBaseTypeFromSchemas(String baseTypeName, List<Schema> schemaList) {
		XMLType type = null;
		for (Schema schema : schemaList) {
			type = schema.getType(baseTypeName);
			if (ValidateUtils.isNotEmpty(type)) {
				break;
			}
		}
		return type;
	}

	public static Schema convertElement2Schema(org.jdom2.Element element, String documentBase) throws IOException {
		Schema schema = null;
		String content = outputString(element);
		if (ValidateUtils.isNotEmpty(content)) {
			schema = readSchema(new StringReader(content), documentBase);
		}
		return schema;
	}

	private static Schema readSchema(StringReader reader, String documentBase) throws IOException {
		InputSource inputSource = new InputSource(reader);
		SchemaReader schemaReader = new SchemaReader(inputSource);
		if (ValidateUtils.isNotEmpty(documentBase)) {
			// schemaReader.set
		}
		schemaReader.setValidation(false);
		Schema schema = schemaReader.read();
		return schema;
	}

	private static String outputString(org.jdom2.Element element) {
		XMLOutputter xmlWriter = new XMLOutputter(Format.getRawFormat());
		return xmlWriter.outputString(element);
	}
}
