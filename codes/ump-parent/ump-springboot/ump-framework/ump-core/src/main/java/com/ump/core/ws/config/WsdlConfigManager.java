package com.ump.core.ws.config;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.io.FileUtils;
import com.ump.commons.util.ClassUtils;

public class WsdlConfigManager {
	private static Logger logger = LoggerFactory.getLogger(WsdlConfigManager.class);
	private static final String DEFAULT_CONFIG_NAME = "wsdl_config";
	private static final String DEFAULT_CONFIG_SUFFIX = ".wsdl";
	private static Map<String, String> wsdlFileCache = new HashMap<>();

	public static void init() {
		ClassLoader classLoader = ClassUtils.getClassLoader();
		URL url = classLoader.getResource(DEFAULT_CONFIG_NAME);
		if (null == url) {
			logger.error("WsdlConfigManager load {} is null!", DEFAULT_CONFIG_NAME);
			return;
		}
		loadWsdl(url);
		Collection<String> filePaths = wsdlFileCache.values();
		if (null == filePaths) {
			return;
		}
		filePaths.forEach(filePath -> {
			WebServiceInterfaceManager.loadWsdlByFullPath(filePath);
		});
	}

	private static void loadWsdl(URL url) {
		if (null == url) {
			return;
		}
		
		logger.info("urlPath={}",url.getPath());
		
		if (!url.getPath().contains("!")) {
			File dir = new File(url.getPath());
			if (!dir.exists() || dir.isFile()) {

			}
			File[] wsdlDirs = dir.listFiles();
			if (null != wsdlDirs && wsdlDirs.length > 0) {
				Arrays.asList(wsdlDirs).stream().forEach(wsdlDir -> {
					if (wsdlDir.isDirectory()) {
						File[] wsdlFiles = wsdlDir.listFiles(new FileUtils.SuffixFilter(DEFAULT_CONFIG_SUFFIX));
						if (null != wsdlFiles && wsdlFiles.length > 0) {
							Arrays.asList(wsdlFiles).stream().forEach(wsdlFile -> {
								String wsdlFilePath = wsdlFile.getAbsolutePath();
								String wsdlFileName = wsdlFile.getName();
								if (!wsdlFileCache.containsKey(wsdlFileName)) {
									wsdlFileCache.put(wsdlFileName, wsdlFilePath);
								}
							});
						}
					}
				});
			}
		}
	}

	public static String getWsdlPath(String wsdlFileName) {
		if (wsdlFileCache.isEmpty()) {
			init();
		}
		return wsdlFileCache.get(wsdlFileName);
	}
}
