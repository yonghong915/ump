package com.ump.commons.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

/**
 * file property tools
 * 
 * @author fangyh
 * @date 2018-08-08 21:39:08
 * @version 1.0.0
 */
public final class PropertyUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

	private PropertyUtil() {
	}

	/**
	 * Load file property
	 * 
	 * @param filePath
	 * @return
	 */
	public static Properties getProperties(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			logger.error("file {} can not be exists.", filePath);
		}
		Properties props = new Properties();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			props.load(br);
		} catch (IOException e) {
			logger.error("load property file error:", e);
			throw new CommonException(StatusCode.LOAD_FILE_EXCEPTION);
		}
		return props;
	}

	/**
	 * Obtain File Property attribute value
	 * 
	 * @param filePath
	 * @param attrName
	 * @return
	 */
	public static String getPropertyValue(String filePath, String attrName) {
		Properties props = getProperties(filePath);
		return props.getProperty(attrName, "");
	}
}
