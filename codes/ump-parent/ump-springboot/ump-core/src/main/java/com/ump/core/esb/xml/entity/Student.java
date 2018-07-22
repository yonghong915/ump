package com.ump.core.esb.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name="student")
public class Student extends EsbData {
	//@XmlElement
	@Setter
	@Getter
	private String name;
}
