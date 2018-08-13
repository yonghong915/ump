package com.ump.commons.esb.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
public class EsbBody {

//	@XmlAttribute
//	@Getter
//	@Setter
	protected List<Field> list;
}
