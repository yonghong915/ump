package com.ump.core.esb.xml;

import org.springframework.beans.factory.annotation.Value;

public class Esb {
	@Value("${value:defaultValue}")
	private String ss;
}
