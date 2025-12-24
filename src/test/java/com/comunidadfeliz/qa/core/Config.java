package com.comunidadfeliz.qa.core;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("qa.properties")) {
            if (is != null) props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("No pude cargar qa.properties", e);
        }
    }

    public static String get(String key, String def) {
        // Primero intenta ENV, si no existe usa properties
        String env = System.getenv(key);
        if (env != null && !env.isBlank()) return env;

        String p = props.getProperty(key);
        return (p == null || p.isBlank()) ? def : p;
    }
}
