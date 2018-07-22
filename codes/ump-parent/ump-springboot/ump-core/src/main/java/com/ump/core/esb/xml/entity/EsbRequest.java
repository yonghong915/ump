package com.ump.core.esb.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@XmlType(propOrder = { "head", "body" })
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "request")
public class EsbRequest {

	@Getter
	@Setter
	private EsbHead head;

	@Getter
	@Setter
	private EsbBody body;

}
