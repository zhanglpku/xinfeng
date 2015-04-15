package com.pku.xinfeng.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
public class ResourceProperties {

	public static String getPropertyValue(String baseName, String propertyName,
			Object[] msgParams, Locale local) {
		Locale currentLocale = local;
		if (currentLocale == null) {
			currentLocale = Locale.getDefault();

		}
		ResourceBundle bundle = ResourceBundle.getBundle(baseName,
				currentLocale);

		String msg = (String) bundle.getObject(propertyName);
		if (msgParams == null)
			return msg;
		MessageFormat mf = new MessageFormat("");
		mf.setLocale(currentLocale);
		mf.applyPattern(msg);
		return mf.format(msgParams);
	}

	public static String getPropertyValue(String baseName, String propertyName) {
		return getPropertyValue(baseName, propertyName, null, null);
	}
	public static String getPropertyValue(String baseName, String propertyName,Locale local) {
		return getPropertyValue(baseName, propertyName, null, local);
	}
	public static String getPropertyValue(String baseName, String propertyName,Object[] msgParams) {
		return getPropertyValue(baseName, propertyName, msgParams, null);
	}
}
