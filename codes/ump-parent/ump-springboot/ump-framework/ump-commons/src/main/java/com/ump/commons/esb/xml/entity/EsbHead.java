package com.ump.commons.esb.xml.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
public class EsbHead {
	@Getter
	@Setter
	@XmlElement(name = "channel", required = true, defaultValue = "01")
	private String channel;

	@Getter
	@Setter
	@XmlElement(name = "serviceCode", required = true, defaultValue = "01")
	private String serviceCode;

	@Getter
	@Setter
	@XmlElement(name = "serviceOperation", required = true, defaultValue = "01")
	private String serviceOperation;

	@Getter
	@Setter
	@XmlElement(name = "providerSystem", required = true, defaultValue = "01")
	private String providerSystem;

	@Getter
	@Setter
	@XmlElement(name = "consumerSystem", required = true, defaultValue = "01")
	private String consumerSystem;

	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement(name = "createTime", required = true)
	@Getter
	@Setter
	private Date createTime;

	@Getter
	@Setter
	@XmlElement(name = "operator", required = true, defaultValue = "01")
	private String operator;

	@Getter
	@Setter
	@XmlElement(name = "serialNo", required = true, defaultValue = "01")
	private String serialNo;

}
