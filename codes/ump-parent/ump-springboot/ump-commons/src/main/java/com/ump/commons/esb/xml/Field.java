package com.ump.commons.esb.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @date 2018-08-13 22:40:13
 * @version 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Field {

	@XmlAttribute(name = "name")
	@Getter
	@Setter
	private String name = "";

	@XmlValue
	@Getter
	@Setter
	private String value = "";
}
