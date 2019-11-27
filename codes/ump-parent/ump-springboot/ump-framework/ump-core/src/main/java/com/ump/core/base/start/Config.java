/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.base.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-09-02 20:26:06
 *
 */
public class Config {
	public String containerConfig;
	public List<String> loaders;

	private Properties getPropertiesFile(String config) {
		InputStream is = null;
		Properties props = new Properties();
		try {
			is = this.getClass().getClassLoader().getResourceAsStream(config);
			if (null != is) {
				props.load(is);
			}else {
				throw new IOException();
			}
		} catch (IOException e) {
			try (FileInputStream fis = new FileInputStream(new File(config))) {
				if (null != fis) {
					props.load(fis);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
		}
		return props;
	}

	public void readConfig(String cfgFile) {
		Properties props = this.getPropertiesFile(cfgFile);
		
		this.containerConfig = "config/start/ump-containers.xml";
		loaders = new ArrayList<>();
		int currentPosition = 1;
		while (true) {
			String loaderClass = props.getProperty("ump.start.loader" + currentPosition);
			if (null == loaderClass || loaderClass.trim().length() == 0) {
				break;
			} else {
				loaders.add(loaderClass);
				currentPosition++;
			}
		}
	}

}
