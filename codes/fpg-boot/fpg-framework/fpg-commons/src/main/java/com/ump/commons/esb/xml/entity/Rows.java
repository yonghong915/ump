package com.ump.commons.esb.xml.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Rows {
	@XmlAttribute(name = "name")
	@Getter
	@Setter
	private String name = "rows";

	@XmlElement(name = "row")
	@Getter
	@Setter
	private List<Row> rowList;
}
