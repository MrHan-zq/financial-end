package com.qst.financial.config;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyUtils {
	public static final String DEFAULT_PROPERTIES = "dataSourceConfig.properties";

    private PropertyUtils() {

    }

    public static String get(Object key, String file) {
        Properties props = new Properties();
        PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(file);
        InputStream is;
        try {
            is = resource.getInputStream();
            propertiesPersister.load(props, new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {

        }
        Object result = props.get(key);
        return result == null ? "" : result.toString();
    }

    public static String get(Object key) {
        return get(key, DEFAULT_PROPERTIES);
    }
   /* public static void main(String[] args) {
    	get("user");
	}*/
}
