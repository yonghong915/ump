package com.ump.core.util.serialize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateJsonDeserializer extends JsonDeserializer<Date> {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Date deserialize(com.fasterxml.jackson.core.JsonParser jsonParser,
			DeserializationContext deserializationContext)
			throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
		try {
			if (jsonParser != null && StringUtils.isNotEmpty(jsonParser.getText())) {
				return format.parse(jsonParser.getText());
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
