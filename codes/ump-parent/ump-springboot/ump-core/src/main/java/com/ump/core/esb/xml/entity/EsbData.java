package com.ump.core.esb.xml.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = XmlAccessType.NONE)
//@XmlSeeAlso({ Student.class })
public class EsbData {
	@Getter
	@Setter
	@XmlJavaTypeAdapter(value = DateAdapter.class)
	private Date createDate;
	
	
}
