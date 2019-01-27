package com.ump.core.base.start;

import java.util.HashMap;
import java.util.Map;

public class StartupCommand {
	private String name;
	private Map<String, String> properties;

	public String getName() {
		return name;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	private StartupCommand(Builder builder) {
		this.name = builder.name;
		this.properties = builder.properties;
	}

	public static class Builder {
		// required parameters
		private final String name;

		// optional parameters
		private Map<String, String> properties;

		public Builder(String name) {
			this.name = name;
		}

		public Builder properties(Map<String, String> properties) {
			this.properties = properties;
			return this;
		}

		public Builder addProperty(String key, String value) {
			if (properties == null) {
				properties = new HashMap<String, String>();
			}
			properties.put(key, value);
			return this;
		}

		public StartupCommand build() {
			return new StartupCommand(this);
		}
	}
}
