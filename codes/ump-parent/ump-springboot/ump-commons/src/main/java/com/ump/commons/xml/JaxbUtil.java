package com.ump.commons.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

/**
 * 
 * @author fangyh
 * @date 2018-08-17 21:46:17
 * @version 1.0.0
 */
public final class JaxbUtil {
	private static Logger logger = LoggerFactory.getLogger(JaxbUtil.class);
	private static Map<List<Class<?>>, JAXBContext> jaxbContextMap = new ConcurrentHashMap<>();

	private JaxbUtil() {
	}

	private static JAXBContext getJAXBContext(Class<?>... classes) throws JAXBException {
		List<Class<?>> classList = new ArrayList<>(Arrays.asList(classes));
		if (!jaxbContextMap.containsKey(classList)) {
			JAXBContext jaxbContext = JAXBContext.newInstance(classes);
			jaxbContextMap.put(classList, jaxbContext);
			return jaxbContext;
		}
		return jaxbContextMap.get(classList);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String packXML(Object obj) {
		try {
			JAXBContext jaxbContext = getJAXBContext(obj.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			return writer.toString();
		} catch (JAXBException e) {
			logger.error(StatusCode.PACK_XML_EXCEPTION.message(), e);
			throw new CommonException(StatusCode.PACK_XML_EXCEPTION, e);
		}
	}

	/**
	 * 
	 * @param obj
	 * @param packedXml
	 * @return
	 */
	public static Object unpackXML(Object obj, String packedXml) {
		try {
			JAXBContext jaxbContext = getJAXBContext(obj.getClass());
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return unmarshaller.unmarshal(new StringReader(packedXml));
		} catch (JAXBException e) {
			logger.error(StatusCode.UNPACK_XML_EXCEPTION.message(), e);
			throw new CommonException(StatusCode.UNPACK_XML_EXCEPTION, e);
		}
	}
}
