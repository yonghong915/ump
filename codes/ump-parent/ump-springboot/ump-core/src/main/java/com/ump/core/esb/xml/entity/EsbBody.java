package com.ump.core.esb.xml.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@XmlType(namespace = "body")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class EsbBody {
	@XmlElementWrapper(name = "data")
	// @XmlElement(name = "row")
	@XmlElements({ @XmlElement(name = "dept", type = Student.class) })
	@Getter
	@Setter
	private List<EsbData> data;
}
