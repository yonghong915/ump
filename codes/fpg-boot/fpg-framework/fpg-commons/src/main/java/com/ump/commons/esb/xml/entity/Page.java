package com.ump.commons.esb.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Page {
	@XmlAttribute
	@Getter
	@Setter
	private int totalRecord;

	@XmlAttribute
	@Getter
	@Setter
	private int pageSize;

	@XmlAttribute
	@Getter
	@Setter
	private int pageNum;
}
