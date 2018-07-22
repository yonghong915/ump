package com.ump.core.esb.xml.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class EsbHead implements Serializable {
	public EsbHead() {
	}

	@Getter
	@Setter
	@XmlElement(name = "chanId", required = true, defaultValue = "01")
	private String chanId = "01";

	@Getter
	@Setter
	@XmlElement(name = "tradeId", required = true)
	private String tradeId = "00000000";

	@XmlJavaTypeAdapter(value = DateAdapter.class)
	@Getter
	@Setter
	private Date createDate;

	@Getter
	@Setter
	@XmlElement(name = "rspCode", required = true)
	private String rspCode = "000000";

	@Getter
	@Setter
	@XmlElement(name = "rspMsg", required = true)
	private String rspMsg = "success";
}
