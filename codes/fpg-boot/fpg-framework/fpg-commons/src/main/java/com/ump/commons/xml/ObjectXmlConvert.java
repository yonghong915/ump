package com.ump.commons.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ObjectXmlConvert {
	private static final XStream xStream = new XStream(new DomDriver());

	/**
	 * object对象转换为xml数据
	 * 
	 * @param obj
	 * @return
	 */
	public static String ObjToXml(Object obj) {
		return xStream.toXML(obj);
	}

	/**
	 * xml数据转换为object对象
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Object xmlToObj(String xml) throws Exception {
		return (Object) xStream.fromXML(xml);
	}

}
