package org.ecomileage.web.common.utils;

import java.util.Properties;

public class ConstantUtils {
	private static final String NOTFOUND = "not found";
	private static Properties propsConfig;

	public static String getConfig(String key) {
		try {
			if (propsConfig == null) {
				propsConfig = new Properties();
				propsConfig.load(ConstantUtils.class.getClassLoader()
						.getResourceAsStream("META-INF/config/database.properties"));
			}
			return propsConfig.getProperty(key, NOTFOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return NOTFOUND;
		}
	}
}
