package com.sapient.statestreetscreeningapplication.utils;

import java.io.IOException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyUtils.
 */
public class PropertyUtils {

	/**
	 * Read property.
	 *
	 * @param propertyName the property name
	 * @return the string
	 */
	public static String readProperty(String propertyName) {
		Properties prop = new Properties();
		String value = null;
		try {
			// load a properties file
			prop.load(PropertyUtils.class.getClassLoader().getResourceAsStream(
					"application.properties"));
			// read the property value. will return null if not found
			value = prop.getProperty(propertyName);
		} catch (IOException e) {
			CustomLoggerUtils.INSTANCE.log
					.error("Error in reading 'application.properties' file. Please ensure that the file exists in the application classpath.");
		}
		if (value == null) {
			CustomLoggerUtils.INSTANCE.log.error("Property: " + propertyName
					+ " not found in application.properties file.");
		}
		return value;
	}
}
