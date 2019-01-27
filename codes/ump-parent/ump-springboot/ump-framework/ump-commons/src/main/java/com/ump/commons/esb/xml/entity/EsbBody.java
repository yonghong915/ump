package com.ump.commons.esb.xml.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
public class EsbBody {

	@XmlElement(name = "field")
	@Getter
	@Setter
	protected List<Field> fieldList;

	@XmlElement(name = "rows")
	@Getter
	@Setter
	protected Rows rows;
}
