package com.ump.commons.esb.xml.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Row {
	@XmlElement(name = "field")
	@Getter
	@Setter
	protected List<Field> rowList;
}
