package com.ump.commons.esb.xml.entity;

import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

public class EsbRespHead extends EsbHead {
	@Getter
	@Setter
	@XmlElement(name = "rspCode", required = true)
	private String rspCode = "000000";

	@Getter
	@Setter
	@XmlElement(name = "rspMsg", required = true)
	private String rspMsg = "success";
}
