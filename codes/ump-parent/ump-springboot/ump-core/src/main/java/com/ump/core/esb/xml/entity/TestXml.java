package com.ump.core.esb.xml.entity;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class TestXml {
	public static void main(String[] args) {
		try {
			JAXBContext jc = JAXBContext.newInstance(EsbRequest.class);
			Marshaller marshaller = jc.createMarshaller();
			EsbRequest req = new EsbRequest();
			EsbHead head = new EsbHead();
			//head.setChanId("02");
			head.setCreateDate(new Date());
			req.setHead(head);

			EsbBody body = new EsbBody();

			List<EsbData> datas = new ArrayList<>();
			Student data = new Student();
			data.setName("aaaaa");
			data.setCreateDate(new Date());
			datas.add(data);

			Student data1 = new Student();
			data1.setName("bbbbb");
			data1.setCreateDate(new Date());
			datas.add(data1);

			//body.setData(data1);
			body.setData(datas);
			req.setBody(body);
			// 格式化输出，设置换行和缩进，不设置则会显示在一行
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// marshaller.marshal(obj, path);
			StringWriter sw = new StringWriter();
			//marshaller.marshal(req, System.out);
			marshaller.marshal(req, sw);
			System.out.println(sw.toString());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeXML(Object obj, File path, Class<?>... clazz) throws JAXBException {
		JAXBContext jctx = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jctx.createMarshaller();
		// 格式化输出，设置换行和缩进，不设置则会显示在一行
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(obj, path);
		// 设置控制台输出
		marshaller.marshal(obj, System.out);
	}

	public static Object readXML(File path, Class<?>... clazz) throws JAXBException {
		JAXBContext jctx = JAXBContext.newInstance(clazz);
		Unmarshaller unMarshaller = jctx.createUnmarshaller();
		Object obj = unMarshaller.unmarshal(path);
		return obj;
	}
}
