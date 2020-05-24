/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-09-02 21:15:56
 *
 */
public class XmlUtils {
	static Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

	public static List<Element> childElementList(Element element, String property) {
		List<Element> childEles = element.elements();
		return childEles;
	}

	public static Document readXmlDocument(InputStream is, boolean validate, String docDsc) {
		if (null == is) {
			return null;
		}
		long startTime = System.currentTimeMillis();
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		double totalDuration = (endTime - startTime) / 1000.0;
		LOGGER.info("XML read {} s {}", totalDuration, docDsc);
		return document;
	}
}
