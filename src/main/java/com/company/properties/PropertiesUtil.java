package com.company.properties;

import com.company.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by anjalhussan on 10/22/16.
 */
public class PropertiesUtil {
    private String dataLocation;
    private Properties properties;

    private PropertiesUtil(String dataLocation) {
        this.dataLocation = dataLocation;
        loadProperties();
    }

    public static PropertiesUtil create(String dataLocation) {
        return new PropertiesUtil(dataLocation);
    }

    static <T> T valueOf(Class<T> clazz, String arg) {
        Exception cause = null;
        T ret = null;
        try {
            ret = clazz.cast(clazz.getDeclaredMethod("valueOf", String.class).invoke(null, arg)
            );
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            cause = e;
        }
        if (cause == null) {
            return ret;
        } else {
            throw new IllegalArgumentException(cause);
        }
    }

    private void loadProperties() {
        FileInputStream fileInputStream = null; //"src/test/resources/xxx.properties"
        try {
            fileInputStream = new FileInputStream(dataLocation);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public String getProperties(String name) {
        String prop = null;
        try {
            prop = properties.getProperty(name);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public <T> T getProperties(String name, Class<T> type) {
        T prop = null;
        try {

            String temp = properties.getProperty(name);
            prop = valueOf(type, temp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String data(Repository value) {
        return getProperties(value.getValue());
    }

}
