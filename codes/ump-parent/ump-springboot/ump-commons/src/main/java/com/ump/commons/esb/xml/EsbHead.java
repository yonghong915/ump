package com.ump.commons.esb.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
public class EsbHead {
	@Getter
	@Setter
	@XmlElement(name = "Channel", required = true, defaultValue = "01")
	private String channel;

	@Getter
	@Setter
	@XmlElement(name = "ProviderSystem", required = true, defaultValue = "01")
	private String providerSystem;
}
