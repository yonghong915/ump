package com.ump.commons.esb.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@XmlType(propOrder = { "head", "body" })
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "response")
public class EsbResp {
	@Getter
	@Setter
	private EsbHead head;

	@Getter
	@Setter
	private EsbBody body;
}
