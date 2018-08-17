package com.ump.common.test;

import org.junit.Assert;
import org.junit.Test;

import com.ump.commons.esb.xml.EsbBody;
import com.ump.commons.esb.xml.EsbHead;
import com.ump.commons.esb.xml.EsbReq;
import com.ump.commons.xml.JaxbUtil;

public class XmlTest {

	@Test
	public void testPackXml() {
		long start = System.currentTimeMillis();
		String xml = "";
		for (int i = 0; i < 5; i++) {
			EsbReq req = new EsbReq();
			EsbHead head = new EsbHead();
			head.setChannel("01");
			head.setProviderSystem("ump");
			req.setHead(head);

			EsbBody body = new EsbBody();
			req.setBody(body);
			xml = JaxbUtil.packXML(req);
		}
		long end = System.currentTimeMillis();
		Assert.assertNotEquals("", xml);
		System.out.println((end - start) + " ms");
	}
}
