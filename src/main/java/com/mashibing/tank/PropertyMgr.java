package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    static Properties properties = new Properties();

    private PropertyMgr() {
    }

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
