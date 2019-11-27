/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.base.start;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.ump.commons.exception.ContainerException;
import com.ump.core.util.ValidateUtils;
import com.ump.core.util.XmlUtils;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-09-02 21:05:02
 *
 */
public class ContainerConfig {
	protected static Map<String, Container> containers = new LinkedHashMap<>();

	protected ContainerConfig() {
	}

	protected ContainerConfig(String configFileLoacation) {
		Document containerDocument = null;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFileLoacation);
		containerDocument = XmlUtils.readXmlDocument(is, true, "");
		Element root = containerDocument.getRootElement();
		for (Element curElement : XmlUtils.childElementList(root, "container")) {
			Container container = new Container(curElement);
			containers.put(container.name, container);
		}

	}

	public static Container getContainer(String containerName, String configFile) throws ContainerException {
		Container container = containers.get(containerName);
		if (null == containers) {
			synchronized (ContainerConfig.class) {
				container = containers.get(containerName);
				if (null == containers) {
					if (null == configFile) {
						throw new ContainerException("Container config file cannot be null.");
					}
					new ContainerConfig(containerName);
					container = containers.get(containerName);
				}
			}
			if (null == container) {
				throw new ContainerException("no container found with the name:" + containerName);
			}
		}
		return container;
	}

	public static class Container {
		public String name;
		public String className;
		public Map<String, Property> properties;

		public Container(Element element) {
			this.name = element.attributeValue("name");
			this.className = element.attributeValue("class");
			properties = new LinkedHashMap<>();
			for (Element ourElement : XmlUtils.childElementList(element, "property")) {
				Property property = new Property(ourElement);
				properties.put(property.name, property);
			}
		}

		public Property getProperty(String name) {
			return properties.get(name);
		}

		public static class Property {
			public String name;
			public String value;
			public Map<String, Property> properties;

			public Property(Element element) {
				this.name = element.attributeValue("name");
				this.value = element.attributeValue("value");
				properties = new LinkedHashMap<>();
			}
		}
	}

	public static Collection<Container> getContainer(String configFile) throws ContainerException {
		if (containers.isEmpty()) {
			synchronized (ContainerConfig.class) {
				if (containers.isEmpty()) {
					if (null == configFile) {
						throw new ContainerException("Container config file cannot be null.");
					}
					new ContainerConfig(configFile);
				}
			}
			if (containers.isEmpty()) {
				throw new ContainerException("No container loaded.");
			}
		}
		return containers.values();
	}

	public static String getPropertyValue(ContainerConfig.Container parentProp, String name, String defaultValue) {
		ContainerConfig.Container.Property prop = parentProp.getProperty(name);
		if (null == prop || ValidateUtils.isEmpty(prop.value)) {
			return defaultValue;
		} else {
			return prop.value;
		}
	}
}
