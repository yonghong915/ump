package com.ump.common.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.ump.commons.esb.xml.entity.EsbBody;
import com.ump.commons.esb.xml.entity.EsbReq;
import com.ump.commons.esb.xml.entity.EsbReqHead;
import com.ump.commons.esb.xml.entity.Field;
import com.ump.commons.esb.xml.entity.Row;
import com.ump.commons.esb.xml.entity.Rows;
import com.ump.commons.xml.JaxbUtil;

public class EsbTest {
	@Test
	public void testEsb() {
		EsbReq req = new EsbReq();
		EsbReqHead head = new EsbReqHead();
		head.setChannel("01");
		head.setServiceCode("aopg01");
		head.setServiceOperation("query");
		head.setProviderSystem("ump");
		head.setConsumerSystem("ammp");
		head.setCreateTime(new Date());
		head.setOperator("fangyh");
		head.setSerialNo("20180940500000");
		req.setHead(head);

		EsbBody body = new EsbBody();
		List<Field> fldLst = Lists.newArrayList();
		Field f1 = new Field();
		f1.setName("loginName");
		f1.setValue("amefyg");
		fldLst.add(f1);

		Field f2 = new Field();
		f2.setName("userage");
		f2.setValue("100");
		fldLst.add(f2);

		body.setFieldList(fldLst);

		Rows rows = new Rows();

		List<Row> rowList = Lists.newArrayList();
		Row row = new Row();
		Field row1 = new Field();
		row1.setName("userage");
		row1.setValue("100");

		Field row2 = new Field();
		row2.setName("userage1");
		row2.setValue("1001");

		List<Field> rowsList = Lists.newArrayList();
		rowsList.add(row1);
		rowsList.add(row2);
		row.setRowList(rowsList);

		Row row21 = new Row();
		Field row11 = new Field();
		row11.setName("userage");
		row11.setValue("100");

		rowList.add(row);
		rowList.add(row21);
		rows.setRowList(rowList);

		body.setRows(rows);
		req.setBody(body);
		String xml = JaxbUtil.packXML(req);
		System.out.println("xml=" + xml);

		EsbReq que = JaxbUtil.unpackXML(EsbReq.class, xml);
		System.out.println(que.getHead().getChannel());
	}

}
