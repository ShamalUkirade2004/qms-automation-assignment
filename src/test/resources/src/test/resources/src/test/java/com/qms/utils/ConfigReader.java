package com.qms.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Reads configuration from config.properties file.
 * Centralizes all test configuration in one place.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url", "http://216.48.184.249:5289");
    }

    public static String getLoginUrl() {
        return properties.getProperty("login.url", "http://216.48.184.249:5289/login");
    }

    public static String getDeviationPageUrl() {
        return properties.getProperty("deviation.page.url",
                "http://216.48.184.249:5289/quality/records/new?template_id=ef20c1ca-208e-4162-b6bf-f9aa7cfb7464");
    }

    public static String getUsername() {
        return properties.getProperty("username", "testing@aivoa.net");
    }

    public static String getPassword() {
        return properties.getProperty("password", "password123");
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait", "20"));
    }
}
