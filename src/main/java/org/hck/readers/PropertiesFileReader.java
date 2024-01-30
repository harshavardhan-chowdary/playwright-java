package org.hck.readers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
    private final Properties properties;
    private String defaultPropertiesFile;

    public PropertiesFileReader() {
        this("config.properties");
    }

    public PropertiesFileReader(String defaultPropertiesFile) {
        this.defaultPropertiesFile = defaultPropertiesFile;
        properties = new Properties();
        loadProperties(defaultPropertiesFile);
    }

    public void setDefaultPropertiesFile(String defaultPropertiesFile) {
        this.defaultPropertiesFile = defaultPropertiesFile;
        loadProperties(defaultPropertiesFile);
    }

    private void loadProperties(String propertiesFile) {
        try (InputStream input = new FileInputStream(propertiesFile)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements
        }
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
