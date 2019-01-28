package com.playbuzz.automation.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties properties;
    private static final String configFile = "config.properties";

    protected Config() {}

    public static Config create() {
        return new Config();
    }

    private void loadProperties()  {
        Properties prop = new Properties();

        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(configFile)) {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Fail to load properties file", e);
        }
        properties = prop;
    }

    /**
     * General method for getting property from config by name.
     *
     * @param propertyName name of property
     * @return property value from config file
     */

    public String get(String propertyName) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(propertyName);
    }

    public int getElementPresentTimeout() {
        return Integer.parseInt(get("element.present.timeout"));
    }

    public String getTestDataPath() {
        return get("test.data.path");
    }

    public String getWebdriversPath() {
        return get("webdrivers.path");
    }

    public String getBrowser() {
        return get("browser");
    }

    public String getChromeDriverVersion() {
        return get("chrome.driver.ver");
    }

    public String getGeckoDriverVersion() {
        return get("gecko.driver.ver");
    }

    public String getBaseUrl() {
        return get("base.url");
    }

    public String getApiUrl() {
        return get("api.url");
    }

}
