package com.commons.test.xstream;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 日期转换器
 * 
 * @author fangyh
 * @Date 2020-04-18 10:59:45
 * @version 1.0
 */
public class SingleValueCalendarConverter implements Converter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Calendar calendar = (Calendar) source;
		writer.setValue(String.valueOf(calendar.getTime().getTime()));
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(Long.parseLong(reader.getValue())));
		return calendar;
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(GregorianCalendar.class);
	}
}
