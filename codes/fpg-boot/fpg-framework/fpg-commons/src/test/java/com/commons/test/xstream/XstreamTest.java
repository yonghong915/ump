package com.commons.test.xstream;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XstreamTest {
	private static final XStream xstream = new XStream(new DomDriver());

	public static void main(String[] args) {
		xstream.processAnnotations(Ccms_312_001_01.class);
		
		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(new Class[]{Ccms_312_001_01.class,Ccms_312_001_01.Address.class});
		
		Ccms_312_001_01.GroupHeader groupHeader = new Ccms_312_001_01.GroupHeader();
		groupHeader.setMessageIdentification("ccc");
		groupHeader.setSystemCode("syso12");

		List<Ccms_312_001_01.Address> addressList = new ArrayList<>();
		addressList.add(new Ccms_312_001_01.Address("江苏省南京市玄武大道1000号", "201001", "1801989098"));
		addressList.add(new Ccms_312_001_01.Address("江苏省南京市玄武大道1001号", "201001", "1811989098"));
		Ccms_312_001_01 msg = new Ccms_312_001_01("aaa", "bbb", groupHeader, addressList,new GregorianCalendar());
		//注册转换器
		xstream.registerConverter(new SingleValueCalendarConverter()); 
		String xml = xstream.toXML(msg);
		System.out.println(xml);
		
		Ccms_312_001_01 a = (Ccms_312_001_01) xstream.fromXML(xml);//xml 转 对象
		System.out.println(a);
	}

}
