package com.ump.commons.esb.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@XmlType(propOrder = { "head", "body" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Esb")
public class EsbReq {

	@Getter
	@Setter
	@XmlAttribute(name = "direction", required = true)
	private String direction = "request";

	@Getter
	@Setter
	@XmlElement(name = "Head", required = true)
	private EsbHead head;

	@Getter
	@Setter
	@XmlElement(name = "Body", required = true)
	private EsbBody body;
}
