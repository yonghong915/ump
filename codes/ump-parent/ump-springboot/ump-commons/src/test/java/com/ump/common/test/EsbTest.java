package com.ump.common.test;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.ump.commons.esb.xml.EsbBody;
import com.ump.commons.esb.xml.EsbHead;
import com.ump.commons.esb.xml.EsbReq;

public class EsbTest {
	@Test
	public void testEsb() {
		EsbReq req = new EsbReq();
		EsbHead head = new EsbHead();
		head.setChannel("01");
		head.setProviderSystem("ump");
		req.setHead(head);

		EsbBody body = new EsbBody();
		req.setBody(body);
		try {
			String xml = packgeXML(req, EsbReq.class);
			System.out.println("xml=" + xml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static String packgeXML(Object obj, Class<?> load) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(load);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
		StringWriter writer = new StringWriter();
		marshaller.marshal(obj, writer);
		return writer.toString();
	}

	public static String unpackageXML() {
		return "";
	}

	public static <T> T parseXML(Class<T> clazz, String context) {

		T result = null;
		Locale.setDefault(Locale.ENGLISH);
		try {
			StringReader reader = new StringReader(context);
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			result = (T) u.unmarshal(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String convertObjectToXml(String xmlEncoding, Object obj, Class<?> clazz) {
		JAXBContext jaxbContext = null;
		StringWriter outPutSW = new StringWriter();
		try {
			jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			if (!"UTF-8".equals(xmlEncoding))
				jaxbMarshaller.setProperty("jaxb.encoding", xmlEncoding);

			jaxbMarshaller.marshal(obj, outPutSW);
			return outPutSW.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}
}
